package main;

import java.util.List;

import classifier.PredictionResult;
import classifier.TweetClassifier;
import connection.TweetRetriever;
import output.Output;
import trainer.TweetTrainer;

public class EventSentimentAnalysis {
	
	private TweetTrainer tt;
	private TweetRetriever tr;
	private TweetClassifier tc;
	private Output output;

	
	public EventSentimentAnalysis(String query) throws Exception {
		
		this.tt = new TweetTrainer();
		this.tr = TweetRetriever.getInstance();
		this.tc = new TweetClassifier();
		this.output = new Output();

		
			
		/* training da twitter se il db è vuoto e se il file ARFF non c'è */
		this.tt.trainFromTwitter(); 
		System.out.println("Trained from Twitter");
		
		/* training dal file LIWC (prima chiamata) */
//		this.tc.trainFromLIWC();
//		System.out.println("Trained from LIWC");
//		this.tc.printClassifier();
		
		/* training dal db se il file ARFF non c'è */
//		this.tt.trainFromDB();
//		System.out.println("Trained from db");
		
		/* per svuotare il db */
//		this.tc.flushDB(); 
		
		/* Prende i tweet da classificare */
		List<String> tweetRetrieved = this.tr.getTweetsForHashtag(query);
		
		/* valuta il sentiment realativo alla query */
		List<PredictionResult> classification = this.tc.classifyTweets(tweetRetrieved);
		
		this.output.analize(classification);
	}

}
