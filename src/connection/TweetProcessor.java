package connection;

import java.util.ArrayList;
import java.util.List;

public class TweetProcessor {
	
	private HashtagMap hashtagMap;
	private String hashtagToExpand;
	
	public TweetProcessor(String s) {
		this.hashtagMap = new HashtagMap();
		this.hashtagToExpand = s;
	}
	
	public void getRelatedHashtags(String tweetMessage) {
		List<String> relatedHashtag = processTweet(tweetMessage);
		
		//for (String hashtag : relatedHashtag) {
		//	this.hashtagMap.insertHastag(hashtag);
		//}
		
	}

	private List<String> processTweet(String tweetMessage) {
		// TODO cercare tutti gli hashtag nel messaggio da aggiungere alla mappa degli hashtag
		
		List<String> relatedHashtag = new ArrayList<String> ();
		//int indiceHashtag = tweetMessage.indexOf("#");
		//int indiceFineHastag = tweetMessage.indexOf(" ", indiceHashtag);
		//String hashtag = tweetMessage.substring(indiceHashtag,indiceFineHastag);
		//System.out.println(hashtag);
		
		int endIndex = 0;
		
		while (tweetMessage.indexOf("#", endIndex) != -1){
		
			int beginIndex = tweetMessage.indexOf("#", endIndex);
			endIndex = tweetMessage.indexOf(" ", beginIndex);
			String currentHashtag = tweetMessage.substring(beginIndex,endIndex-1);
			
			if (!currentHashtag.equals(hashtagToExpand)){
				relatedHashtag.add(currentHashtag);
			}
			
		}
		
		for (String s : relatedHashtag){
			System.out.println(s);
		}
		
		return relatedHashtag;
	}
}
