package main;

import classifier.TweetClassifier;
import tweetprocessing.TweetManager;
import twitter4j.TwitterException;

public class EventSentimentAnalysis {
	
	private TweetManager tm;
	private TweetClassifier tc;

	
	public EventSentimentAnalysis(String query) throws TwitterException {
		
		this.tm = new TweetManager();
		this.tc = new TweetClassifier(tm);
		// faccio il training del classificatore
		/* la prima chiamata */
		this.tc.trainFromPosAndNegTweets(); 
		this.tc.printClassifier();
		
		// dopo che ho già scaricato dei tweet positivi e negativi e messi nel db
		//this.tc.trainFromDB();
		
		/* per svuotare il db */
//		this.tc.flushDB(); 
		
		//this.tm.evaluateEventSentiment(query);
	}

}
