package main;

import java.io.IOException;
import java.util.List;

import classifier.TweetClassifier;
import de.daslaboratorium.machinelearning.classifier.Classification;
import output.Output;
import tweetprocessing.TweetManager;
import twitter4j.TwitterException;

public class EventSentimentAnalysis {
	
	private TweetManager tm;
	private TweetClassifier tc;
	private Output output;

	
	public EventSentimentAnalysis(String query) throws TwitterException, IOException {
		
		this.tm = new TweetManager();
		this.tc = new TweetClassifier();
		
		tm.setTweetClassifier(tc);
		tc.setTweetManager(tm);
		
		/* training da twitter (prima chiamata) */
//		this.tc.trainFromTwitter(); 
//		System.out.println("Trained from Twitter");
//		this.tc.printClassifier();
		
		/* training dal file LIWC (prima chiamata) */
//		this.tc.trainFromLIWC();
//		System.out.println("Trained from LIWC");
//		this.tc.printClassifier();
		
		/* training dal db */
		this.tc.trainFromDB();
		System.out.println("Trained from db");
		
		/* per svuotare il db */
//		this.tc.flushDB(); 
		
		/* valuta il sentiment realativo alla query */
		List<Classification<String, String>> classification = this.tm.evaluateEventSentiment(query);
		
		this.output = new Output(classification);
	}

}
