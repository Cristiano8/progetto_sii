package connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*Classe per la mappa da utilizzare per ampliare la ricerca dei tweet partendo da un hashtag iniziale, gli hashtag presenti
 * nei tweets ritirati inizialmente vengono salvati nella mappa con il relativo numero di occorrenze*/

public class HashtagMap {
	
	private HashMap<String, Integer> hashtagMap;
	
	/*Creazione della mappa*/
	
	public HashMap<String, Integer> createHashtagMap (){
		hashtagMap = new HashMap<String, Integer>();
		return hashtagMap;
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
	
	/*Prendo tutti gli hashtag che hanno un numero di occorrenze nei tweets maggiore al valore specificato*/
	
	public List<String> getTopHashtagsByMinValue (int n){
		List<String> listHashtags = new ArrayList<String>();
		Iterator<Map.Entry<String, Integer>> entries = this.hashtagMap.entrySet().iterator();
		
		while (entries.hasNext()){
			
			Entry thisEntry = (Entry) entries.next();
			String key = (String) thisEntry.getKey();
			int value = (int) thisEntry.getValue();
			
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
			
			Entry thisEntry = (Entry) entries.next();
			String key = (String) thisEntry.getKey();
			int value = (int) thisEntry.getValue();
			
			if (value >= half){
				listHashtags.add(key);
			}
			
		}
		
		return listHashtags;
	}
	
	/*Prende i top 3 hashtag che hanno il maggior numero di occorrenze*/
	
	public List<String> getTop3Hastags(){
		List<String> listHashtags = new ArrayList<String>();
		Iterator<Map.Entry<String, Integer>> entries = this.hashtagMap.entrySet().iterator();
		
		while (entries.hasNext()){
			
			Entry thisEntry = (Entry) entries.next();
			String key = (String) thisEntry.getKey();
			int value = (int) thisEntry.getValue();
			
			Iterator<Map.Entry<String, Integer>> entries2 = this.hashtagMap.entrySet().iterator();
			int count = 0;
			while (entries2.hasNext() && count < 3){
				
				Entry thisEntry2 = (Entry) entries.next();
				int value2 = (int) thisEntry2.getValue();
				if (value2 > value)
					count++;
				
			}
			
			if (!entries2.hasNext()){			//Se l'iteratore arriva alla fine vuol dire che non ha superato il count<3
				listHashtags.add(key);
			}
			
		}
		
		return listHashtags;
	
	}
	
	
	
	
}
