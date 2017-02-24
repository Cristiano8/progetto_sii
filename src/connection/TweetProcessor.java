package connection;

import java.util.List;

public class TweetProcessor {
	
	private HashtagMap hashtagMap;
	
	public TweetProcessor() {
		this.hashtagMap = new HashtagMap();
	}
	
	public void getRelatedHashtags(String tweetMessage) {
		List<String> relatedHashtag = processTweet(tweetMessage);
		
		for (String hashtag : relatedHashtag) {
			this.hashtagMap.insertHastag(hashtag);
		}
		
	}

	private List<String> processTweet(String tweetMessage) {
		// TODO cercare tutti gli hashtag nel messaggio da aggiungere alla mappa degli hashtag
		return null;
	}
}
