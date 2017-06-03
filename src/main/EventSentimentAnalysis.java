package main;

import java.io.File;
import java.util.List;

import classifier.PredictionResult;
import classifier.TweetClassifier;
import connection.TweetRetriever;
import trainer.TweetTrainer;
import util.Constants;
import util.Output;

public class EventSentimentAnalysis {
	

	private static final boolean NEW = false;
	
	private TweetTrainer tweetTrainer;
	private TweetRetriever tweetRetriever;
	private TweetClassifier tweetClassifier;
	private Output output;

	
	public EventSentimentAnalysis(String query) throws Exception {
		
		this.tweetTrainer = new TweetTrainer();
		this.tweetRetriever = TweetRetriever.getInstance();
		this.tweetClassifier = new TweetClassifier();
		this.output = new Output();

		
		/*Se si vuole cominciare un altra ricerca prima si svuota il db, basta cambiare la costante NEW*/
		if (NEW) {
			
			this.tweetTrainer.flushDB();
		
		}
		
		
		/*Se il file ARFF è gia presente non prendere i tweet per fare il training*/
		File arff = new File(Constants.TRAINING_PATH);
		if (!arff.exists()) {
			
			if (this.tweetTrainer.dbIsEmpty()) {
				/* training da twitter se il db è vuoto e se il file ARFF non c'è */
				System.out.println("Training from Twitter");
				this.tweetTrainer.trainFromTwitter(); 
				System.out.println("Trained from Twitter");
			}
			else {
				/* training dal db se non è vuoto e se il file ARFF non c'è */
				System.out.println("Training from db");
				this.tweetTrainer.trainFromDB();
				System.out.println("Trained from db");
			}		
			
		}
			
		
		/* training dal file LIWC (prima chiamata) */
//		this.tweetClassifier.trainFromLIWC();
//		System.out.println("Trained from LIWC");
//		this.tweetClassifier.printClassifier();
	
		
		/* Prende i tweet da classificare */
		List<String> tweetRetrieved = this.tweetRetriever.getTweetsForHashtag(query);
		
		/* valuta il sentiment realativo alla query */
		List<PredictionResult> classification = this.tweetClassifier.classifyTweets(tweetRetrieved);
		
		this.output.analize(classification);
	}

}
