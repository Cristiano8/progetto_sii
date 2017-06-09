package trainer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import connection.TweetRetriever;
import db.DBManager;
import db.PosNegWordReader;
import twitter4j.TwitterException;
import util.Constants;

public class TweetTrainer {

//	private TweetManager tm;
	private DBManager dbManager;
	private PosNegWordReader posNegWordReader;
	private CreateTrainingSet trainingSetCreator;
	private TweetRetriever tweetRetriever;



	
	public TweetTrainer() {
		this.dbManager = new DBManager();
		this.posNegWordReader = new PosNegWordReader();
		this.trainingSetCreator = new CreateTrainingSet();
		this.tweetRetriever = TweetRetriever.getInstance();
		
		
	}

	
//	public void setTweetManager(TweetManager tm) {
//		this.tm = tm;
//	}

	
	public void trainFromLIWC() throws IOException {
		// prende le feature dal file LIWC
		List<String> positiveFeatures = this.posNegWordReader.getFeaturesByCategory(Constants.POSITIVE_CATEGORY);
		List<String> negativeFeatures = this.posNegWordReader.getFeaturesByCategory(Constants.NEGATIVE_CATEGORY);
		
		// salva nel db le parole prese dal file LIWC
		this.dbManager.addTweetsForTraining(positiveFeatures, Constants.POSITIVE_CATEGORY);
		this.dbManager.addTweetsForTraining(negativeFeatures, Constants.NEGATIVE_CATEGORY);
		
	}

	/* Scarica da twitter dei tweet positivi e negativi e li salva sul db */
	public void trainFromTwitter() throws TwitterException, IOException {
		// prende le feature da Twitter
		List<String> positiveTweets = this.tweetRetriever.getTweetsForTraining(Constants.POSITIVE_QUERY);
		System.out.println("positive taken " + positiveTweets.size());
		List<String> negativeTweets = this.tweetRetriever.getTweetsForTraining(Constants.NEGATIVE_QUERY);
		System.out.println("negative taken " + negativeTweets.size());
		

		// Salva i tweet tokenizzati sul db con la relativa categoria
		this.dbManager.addTweetsForTraining(positiveTweets, Constants.POSITIVE_CATEGORY);
		this.dbManager.addTweetsForTraining(negativeTweets, Constants.NEGATIVE_CATEGORY);
		
		this.trainingSetCreator.createWekaFileForTrain(positiveTweets, negativeTweets);
		
		System.out.println("Trained from Twitter");

	}


	/* Prende la lista dei tweet positivi e negativi salvati sul db 
	   e addestra il classificatore */
	public void trainFromDB() throws IOException {
		List<Document> tweetsFromDB = this.dbManager.getTweetsForTraining();
		List<String> posTweets = new ArrayList<>();
		List<String> negTweets = new ArrayList<>();

		if (!tweetsFromDB.isEmpty()) {

			for (Document d : tweetsFromDB) {
				String category = d.getString(Constants.CATEGORY_FIELD);
				if (category.equals(Constants.POSITIVE_CATEGORY))
					posTweets.add(d.getString(Constants.TWEET_FIELD));
				else 
					negTweets.add(d.getString(Constants.TWEET_FIELD));

			}
			
		this.trainingSetCreator.createWekaFileForTrain(posTweets, negTweets);
		
		} else {
			System.out.println("No documents retrieved from db");
		}
	}
	
	

	public void flushDB() {
		this.dbManager.removeAllDocuments();
	}

	/* ritorna true se il db è vuoto */
	public boolean dbIsEmpty() {
		return this.dbManager.isEmpty();
	}
	
	
	
	
}
