package connection;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/* Classe che ha la responsabilità di accedere a Twitter e prendere tutti i Tweet realitivi ad un evento */

public class TweetAnalyzer {
	
	private Twitter twitter;	
	private TwitterConnection tc;
	private TweetProcessor tp;
	
	public TweetAnalyzer() {
		this.tc = new TwitterConnection();
		this.twitter = tc.getTwitter();
		// this.tp = new TweetProcessor();
	}

	public List<Status> getTwitsForHashtag(String hashtag) throws TwitterException {
		
		List<Status> tweets = new ArrayList<>();
		this.tp = new TweetProcessor(hashtag);
		
		try {
			
			Query q = new Query(hashtag + " lang:it");
			q.setCount(100);
			QueryResult qr; 
			
			do {
				qr = twitter.search(q);
				List<Status> tweetsInAPage = qr.getTweets();
				tweets.addAll(tweetsInAPage);
				
				for (Status tweet : tweetsInAPage) {
					
					//tp.getRelatedHashtags(tweet.getText()); // cerco altri hashtag nel Tweet
					System.out.println("@" + tweet.getUser().getScreenName() +
							" - " + tweet.getCreatedAt() +
							" - " + tweet.getText());
				}
				
			} while ((q = qr.nextQuery()) != null);
			
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
		}
		
		return tweets;

	}
	
}
