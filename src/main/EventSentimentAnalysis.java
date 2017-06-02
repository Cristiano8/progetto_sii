package main;

import java.io.File;
import java.util.List;

import classifier.PredictionResult;
import classifier.TweetClassifier;
import connection.TweetRetriever;
import output.Output;
import trainer.TweetTrainer;

public class EventSentimentAnalysis {
	
	private static final String PATHNAME = "train/tweet-train.arff";
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
		if(NEW){
			
			this.tweetTrainer.flushDB();
		
		}
		
		
		/*Se il file ARFF è gia presente non prendere i tweet per fare il training*/
		File arff = new File(PATHNAME);
		if(!arff.exists()){
			
			/* training da twitter se il db è vuoto e se il file ARFF non c'è */
			this.tweetTrainer.trainFromTwitter(); 
			System.out.println("Trained from Twitter");
			
		}
			
		
		/* training dal file LIWC (prima chiamata) */
//		this.tweetClassifier.trainFromLIWC();
//		System.out.println("Trained from LIWC");
//		this.tweetClassifier.printClassifier();
		
		/* training dal db se il file ARFF non c'è */
//		this.tweetTrainer.trainFromDB();
//		System.out.println("Trained from db");
		
		/* per svuotare il db */
//		this.tweetClassifier.flushDB(); 
		
		/* Prende i tweet da classificare */
		List<String> tweetRetrieved = this.tweetRetriever.getTweetsForHashtag(query);
		
		/* valuta il sentiment realativo alla query */
		List<PredictionResult> classification = this.tweetClassifier.classifyTweets(tweetRetrieved);
		
		this.output.analize(classification);
	}

}
