package connection;

import java.util.ArrayList;
import java.util.List;

public class TweetProcessor {
	
	private HashtagMap hashtagMap;
	
	public TweetProcessor() {
		this.hashtagMap = new HashtagMap();
	}
	
	public void getRelatedHashtags(String tweetMessage, String hashtagToExpand) {
		List<String> relatedHashtag = processTweet(tweetMessage, hashtagToExpand);
		for (String hashtag : relatedHashtag) {
			this.hashtagMap.insertHastag(hashtag);
		}
		
	}

	private List<String> processTweet(String tweetMessage, String hashtagToExpand) {
		// TODO cercare tutti gli hashtag nel messaggio da aggiungere alla mappa degli hashtag
		
		List<String> relatedHashtag = new ArrayList<String> ();
		
		/*Scorro tutta la stringa alla ricerca degli hashtags*/
		for (int i = 0; i < tweetMessage.length(); i++){
			
			/*Quando incontro un hashtag*/
			if (tweetMessage.charAt(i) == '#'){
				
				int j = 0;
				String currentHashtag = "";
				boolean hashtagFound = false;
				for (j = i+1; j < tweetMessage.length() && !hashtagFound; j++){
					/*Se matcha non fare niente altrimenti probabilmente l'hashtag è finito*/
					if ((tweetMessage.charAt(j)>= 'a' && tweetMessage.charAt(j)<= 'z') ||
							(tweetMessage.charAt(j)>= 'A' && tweetMessage.charAt(j)<= 'Z') ||
							(tweetMessage.charAt(j)>= '0' && tweetMessage.charAt(j)<= '9') ||
							(tweetMessage.charAt(j)>= 128 && tweetMessage.charAt(j)<= 237)){
					}
					else {
						currentHashtag = tweetMessage.substring(i, j);
						hashtagFound = true;
						
						/*Se ci sono due hashtag senza spazio in mezzo*/
						if (tweetMessage.charAt(j) == 35){
							i = j-1;
						}
						
						if (!currentHashtag.equalsIgnoreCase(hashtagToExpand)){
							relatedHashtag.add(currentHashtag);
						}
					}
						
				}
				
			}
			
		}
		
		for (String s : relatedHashtag){
			System.out.println(s);
		}
		
		
		return relatedHashtag;
	}

	public HashtagMap getHashtagMap() {
		return hashtagMap;
	}

	public void setHashtagMap(HashtagMap hashtagMap) {
		this.hashtagMap = hashtagMap;
	}
	
}
