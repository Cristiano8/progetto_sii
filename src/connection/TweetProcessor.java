package connection;

import java.util.ArrayList;
import java.util.List;

public class TweetProcessor {
	
	private static final int THRESHOLD = 3;
	private HashtagMap hashtagMap;
	
	public TweetProcessor() {
		this.hashtagMap = new HashtagMap();
	}
	
	/* Prende tutti gli hashtag presenti in un tweet che non siano hashtagToExpand
	 * e li aggiunge alla mappa degli hashtag */
	public void addRelatedHashtags(String tweetMessage, String hashtagToExpand) {
		List<String> relatedHashtag = processTweet(tweetMessage, hashtagToExpand);
		for (String hashtag : relatedHashtag) {
			this.hashtagMap.insertHastag(hashtag);
		}
		
	}

	private List<String> processTweet(String tweetMessage, String hashtagToExpand) {
		
		List<String> relatedHashtag = new ArrayList<String> ();
		
		/*Scorro tutta la stringa alla ricerca degli hashtags*/
		for (int i = 0; i < tweetMessage.length(); i++) {
			
			/*Quando incontro un hashtag*/
			if (tweetMessage.charAt(i) == '#') {
				
				int j = 0;
				String currentHashtag = "";
				boolean hashtagFound = false;
				for (j = i+1; j < tweetMessage.length() && !hashtagFound; j++) {
					/*Se matcha non fare niente altrimenti probabilmente l'hashtag è finito*/
					if ((tweetMessage.charAt(j)>= 'a' && tweetMessage.charAt(j)<= 'z') ||
						(tweetMessage.charAt(j)>= 'A' && tweetMessage.charAt(j)<= 'Z') ||
						(tweetMessage.charAt(j)>= '0' && tweetMessage.charAt(j)<= '9') ||
						(tweetMessage.charAt(j)>= 128 && tweetMessage.charAt(j)<= 237)) //caratteri speciali
					{
						continue;
					}
					else {
						currentHashtag = tweetMessage.substring(i, j);
						hashtagFound = true;
						
						/*Se ci sono due hashtag senza spazio in mezzo*/
						if (tweetMessage.charAt(j) == '#') {
							i = j-1;
						}
						
						// l'hashtag trovato non è uguale a quello da espandere aggiungilo alla mappa degli hashtag
						if (!currentHashtag.equalsIgnoreCase(hashtagToExpand)) {
							relatedHashtag.add(currentHashtag.toLowerCase());
						}
					}
						
				}
				
			}
			
		}
				
		return relatedHashtag;
	}

	public HashtagMap getHashtagMap() {
		return hashtagMap;
	}

	public void setHashtagMap(HashtagMap hashtagMap) {
		this.hashtagMap = hashtagMap;
	}
	
	/*HashCode of the tweet to not insert the same tweet multiple times in the db*/
	public int doTheHashCode(String s){
		int hashCode = s.hashCode();
		System.out.println("The hashcode for " + s + " is " + hashCode);
		return hashCode;
	}

	public List<String> getTop3Hashtags() {
		return this.hashtagMap.getTopHashtagsByThresholdUsingASupportMap(THRESHOLD);
	}


	
}
