package tweetprocessing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/* Classe per la mappa da utilizzare per ampliare la ricerca dei tweet partendo da un hashtag iniziale,
 * gli hashtag presenti nei tweets ritirati inizialmente vengono salvati nella mappa con il relativo
 * numero di occorrenze*/

public class HashtagMap {

	private HashMap<String, Integer> hashtagMap;

	public HashtagMap() {
		this.hashtagMap = new HashMap<String, Integer>();
	}

	public HashMap<String, Integer> getHashtagMap() {
		return hashtagMap;
	}

	public void setHashtagMap(HashMap<String, Integer> hashtagMap) {
		this.hashtagMap = hashtagMap;
	}
	
	public Integer get(String hashtag) {
		return this.hashtagMap.get(hashtag);
	}
	
	public int size() {
		return this.hashtagMap.size();
	}
	
	/* ritorna una lista contenente tutti gli hashtag presenti nella mappa */
	public List<String> getAllHashtags() {
		List<String> listHashtags = new ArrayList<>();
		listHashtags.addAll(this.hashtagMap.keySet());
		return listHashtags;
	}
	

	/* Inserimento di un nuovo hashtag o aggiornamento delle occorrenze dell'hashtag fra i tweets ritrovati */
	public void insertHastag(String hashtag) {
		if(this.hashtagMap.containsKey(hashtag)) {
			int value = this.hashtagMap.get(hashtag);
			value++;
			this.hashtagMap.replace(hashtag, value);
		}
		else {
			this.hashtagMap.put(hashtag, 1);
		}
	}

	/* Prendo tutti gli hashtag che hanno un numero di occorrenze 
	 * nei tweets maggiore o uguale al valore specificato */
	public List<String> getTopHashtagsByValue(int minValue) {
		List<String> listHashtags = new ArrayList<String>();
		Iterator<Map.Entry<String, Integer>> entries = this.hashtagMap.entrySet().iterator();

		while (entries.hasNext()) {

			Entry<String, Integer> thisEntry = (Entry<String, Integer>) entries.next();
			String key = thisEntry.getKey();
			int value = thisEntry.getValue();

			if (value >= minValue) {
				listHashtags.add(key);
			}

		}

		return listHashtags;
	}

	
	/* ritorna la lista degli hashtag che hanno più occorrenze rispetto alla media (calcolata levando
	 * i valori che hanno un'unica occorrenza, che fanno abbassare di molto la media) */
	public List<String> getHashtagOverMean() {
		List<String> hashtagOverMean = new ArrayList<>();
		
		List<Integer> values = new ArrayList<>(this.hashtagMap.values());
		int mean = this.computeMeanWithoutSingleValues(values);
		System.out.println(mean);
		Iterator<Map.Entry<String, Integer>> entries = this.hashtagMap.entrySet().iterator();
		
		while (entries.hasNext()) {
			Entry<String, Integer> thisEntry = (Entry<String, Integer>) entries.next();
			if (thisEntry.getValue() >= mean) {
				hashtagOverMean.add(thisEntry.getKey());
			}
		}
		
		return hashtagOverMean;
	}

	/* calcola la media ignorando i valori che hanno un'unica occorrenza */
	private int computeMeanWithoutSingleValues(List<Integer> values) {
		int total = 0;
		int cont = 0;
		for (Integer i : values) {
			if (i.intValue() != 1) {
				total += i.intValue();
				cont++;
			}
			
		}
		return total/cont;
	}

	/* Prende i top threshold hashtag che hanno il maggior numero di occorrenze*/
	// complessità O(N^2) si può fare anche O(N)?
	public List<String> getTopHashtagsByThreshold(int threshold) {
		
		if (threshold <= 0) {
			return null; //dovremmo fare un eccezione se ci andrà
		}
		
		List<String> listHashtags = new ArrayList<String>();
		
		// se la soglia è più grande del numero di elementi della mappa ritornali tutti
		if (this.hashtagMap.size() <= threshold) {
			
			listHashtags.addAll(this.hashtagMap.keySet());
		
		} else {

			Iterator<Map.Entry<String, Integer>> entries = this.hashtagMap.entrySet().iterator();

			while (entries.hasNext()) {

				Entry<String, Integer> thisEntry = (Entry<String, Integer>) entries.next();
				String key = thisEntry.getKey();
				int value = thisEntry.getValue();

				Iterator<Map.Entry<String, Integer>> entries2 = this.hashtagMap.entrySet().iterator();
				int count = 0;
				while (entries2.hasNext() && count < threshold) {

					Entry<String, Integer> thisEntry2 = (Entry<String, Integer>) entries2.next();
					int value2 = thisEntry2.getValue();
					if (value2 > value)
						count++;

				}

				if (count < threshold) {  //Se l'iteratore arriva alla fine vuol dire che non ha superato il count<threshold
					listHashtags.add(key);
				}

			}
		}
		return listHashtags;

	}
	
	
	
	/*Scorro la mappa e aggiorno un'altra mappa che ha i tre massimi temporanei, alla fine avrà i top hashtag*/ 
	public List<String> getTopHashtagsByThresholdUsingASupportMap(int threshold) {
		
		if (threshold <= 0) {
			return null; //dovremmo fare un eccezione se ci andrà
		}
		
		
		// mappa che mantiene il topHashtag temporanei
		Map<String, Integer> supportMap = new HashMap<>(threshold);
		
		Iterator<Map.Entry<String, Integer>> entries = this.hashtagMap.entrySet().iterator();

		while (entries.hasNext()) {

			Entry<String, Integer> currentEntry = (Entry<String, Integer>) entries.next();
			
			String hashtag = currentEntry.getKey();
			int value = currentEntry.getValue();
			
			// prendo l'hashtag che ha il value più basso nella supportMap
			Entry<String, Integer> hashtagWithMinValue = this.getHashtagWithMinValue(supportMap, threshold);
			
			// se la supportMap non è completa lo aggiungo a prescindere
			if (hashtagWithMinValue == null) {
				supportMap.put(hashtag, value);
			}
			
			//altrimenti controllo se il valore più piccolo presente nella mappa di supporto è più piccolo del valore corrente
			else{
				
				String minHashtag = hashtagWithMinValue.getKey();
				int minValue = hashtagWithMinValue.getValue();
				
				// se l'hashtag corrente ha un valore maggiore del minimo della supportMap lo sostituisco
				if(minValue < value) {
					supportMap.remove(minHashtag);
					supportMap.put(hashtag, value);
				}
				
			}
			
		
		}
		
		List<String> topHashtags = new ArrayList<>();
		topHashtags.addAll(supportMap.keySet());
		return topHashtags;
	}

	
	
	private Entry<String, Integer> getHashtagWithMinValue(Map<String, Integer> topHashtags, int threshold) {
			
		if (topHashtags.size() < threshold) 
			return null;
		
		else {
			Iterator<Map.Entry<String, Integer>> entries = topHashtags.entrySet().iterator();	
			Entry<String, Integer> minEntry = (Entry<String, Integer>) entries.next();
			
			while (entries.hasNext()) {
				Entry<String, Integer> currentEntry = (Entry<String, Integer>) entries.next();
				
				if (currentEntry.getValue() < minEntry.getValue())
					minEntry = currentEntry;
			}
			
			return minEntry;
		}
		
	} 
	
	

	@Override
	public String toString() {
		return this.hashtagMap.toString();
	}
}
