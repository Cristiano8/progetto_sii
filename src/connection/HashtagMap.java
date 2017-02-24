package connection;

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

	/*Inserimento di un nuovo hashtag o aggiornamento delle occorrenze dell'hashtag fra i tweets ritrovati*/

	public void insertHastag(String hashtag){
		if(this.hashtagMap.containsKey(hashtag)){
			int value = this.hashtagMap.get(hashtag);
			value++;
			this.hashtagMap.replace(hashtag, value);
		}
		else {
			this.hashtagMap.put(hashtag, 1);
		}
	}

	/*Prendo tutti gli hashtag che hanno un numero di occorrenze nei tweets maggiore o uguale al valore specificato*/

	public List<String> getTopHashtagsByMinValue (int n){
		List<String> listHashtags = new ArrayList<String>();
		Iterator<Map.Entry<String, Integer>> entries = this.hashtagMap.entrySet().iterator();

		while (entries.hasNext()){

			Entry<String, Integer> thisEntry = (Entry<String, Integer>) entries.next();
			String key = thisEntry.getKey();
			int value = thisEntry.getValue();

			if (value >= n){
				listHashtags.add(key);
			}

		}

		return listHashtags;
	}

	/*Prendo tutti gli hashtag che hanno un numero di occorrenze nei tweets che è minimo la metà del numero dei tweets 
	 * ritornati alla prima ricerca, n dovrebbe essere il numero di tweets ritornati*/

	public List<String> getTopHashtagsByHalfOfTweets (int n){
		int half = n/2;
		List<String> listHashtags = new ArrayList<String>();
		Iterator<Map.Entry<String, Integer>> entries = this.hashtagMap.entrySet().iterator();

		while (entries.hasNext()){

			Entry<String, Integer> thisEntry = (Entry<String, Integer>) entries.next();
			String key = thisEntry.getKey();
			int value = thisEntry.getValue();

			if (value >= half){
				listHashtags.add(key);
			}

		}

		return listHashtags;
	}

	/*Prende i top threshold hashtag che hanno il maggior numero di occorrenze*/
	// complessità O(N^2) si può fare anche O(N)?
	public List<String> getTopHashtags(int threshold){
		
		if (threshold <= 0) {
			return null; //dovremmo fare un eccezione se ci andrà
		}
		
		List<String> listHashtags = new ArrayList<String>();
		
		// se la soglia è più grande del numero di elementi della mappa ritornali tutti
		if (this.hashtagMap.size() <= threshold) {
			
			listHashtags.addAll(this.hashtagMap.keySet());
		
		} else {

			Iterator<Map.Entry<String, Integer>> entries = this.hashtagMap.entrySet().iterator();

			while (entries.hasNext()){

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

				if (count < threshold) {	//Se l'iteratore arriva alla fine vuol dire che non ha superato il count<3
					//EDIT: se il next è NULL ma count < threshold aggiunge l'elemento ugualmente perciò
					//il controllo va fatto sul count
					listHashtags.add(key);
				}

			}
		}
		return listHashtags;


		//		Map<Integer, String> inverseMap = reverseMap(map);
		//		TreeMap<Integer, String> sortedInverseMap = new TreeMap<>(inverseMap);
		//		
		//		List<String> top3Hashtags = new ArrayList<>();
		//		top3Hashtags.add(0, sortedInverseMap.);


	}
	


	/*
	/* rigira una mappa: es da Map<Key,Value> a Map<Value,Key> /*
	private Map<Integer, String> reverseMap(Map<String, Integer> map) {
		Map<Integer, String> inverseMap = new HashMap<>();
		for(Map.Entry<String, Integer> entry : map.entrySet()){
		    inverseMap.put(entry.getValue(), entry.getKey());
		}
		return inverseMap;
	}
	 */
}