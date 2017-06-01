package trainer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import connection.TweetRetriever;
import db.DBManager;
import db.PosNegWordReader;
import twitter4j.TwitterException;

public class TweetTrainer {

//	private TweetManager tm;
	private DBManager dbm;
	private PosNegWordReader pnwr;
	private CreateTrainingSet ct;
	private TweetRetriever tr;


	private final static String POSITIVE_QUERY = ":)";
	private final static String NEGATIVE_QUERY = ":(";

	private final static String POSITIVE_CATEGORY = "positive";
	private final static String NEGATIVE_CATEGORY = "negative";

	private static final String CATEGORY_FIELD = "category";
	private static final String TWEET_FIELD = "tweet";

	
	public TweetTrainer() {
		this.dbm = new DBManager();
		this.pnwr = new PosNegWordReader();
		this.ct = new CreateTrainingSet();
		this.tr = TweetRetriever.getInstance();
		
	}

	
//	public void setTweetManager(TweetManager tm) {
//		this.tm = tm;
//	}

	
	public void trainFromLIWC() throws IOException {
		// prende le feature dal file LIWC
		List<String> positiveFeatures = this.pnwr.getFeaturesByCategory(POSITIVE_CATEGORY);
		List<String> negativeFeatures = this.pnwr.getFeaturesByCategory(NEGATIVE_CATEGORY);
		
		// salva nel db le parole prese dal file LIWC
		this.dbm.addTweetsForTraining(positiveFeatures, POSITIVE_CATEGORY);
		this.dbm.addTweetsForTraining(negativeFeatures, NEGATIVE_CATEGORY);
		
	}

	/* Scarica da twitter dei tweet positivi e negativi e li salva sul db */
	public void trainFromTwitter() throws TwitterException, IOException {
		// prende le feature da Twitter
		List<String> positiveTweets = this.tr.getTweetsForTraining(POSITIVE_QUERY);
		System.out.println("positive taken " + positiveTweets.size());
		List<String> negativeTweets = this.tr.getTweetsForTraining(NEGATIVE_QUERY);
		System.out.println("negative taken " + negativeTweets.size());
		

		// Salva i tweet tokenizzati sul db con la relativa categoria
		this.dbm.addTweetsForTraining(positiveTweets, POSITIVE_CATEGORY);
		this.dbm.addTweetsForTraining(negativeTweets, NEGATIVE_CATEGORY);
		
		this.ct.createWekaFileForTrain(positiveTweets, negativeTweets);
		
		System.out.println("DONE 2");

	}



	/* Prende la lista dei tweet positivi e negativi salvati sul db 
	   e addestra il classificatore */
	public void trainFromDB() throws IOException {
		List<Document> tweetsFromDB = this.dbm.getTweetsForTraining();
		List<String> posTweets = new ArrayList<>();
		List<String> negTweets = new ArrayList<>();

		if (!tweetsFromDB.isEmpty()) {

			for (Document d : tweetsFromDB) {
				String category = d.getString(CATEGORY_FIELD);
				if (category.equals(POSITIVE_CATEGORY))
					posTweets.add(d.getString(TWEET_FIELD));
				else 
					negTweets.add(d.getString(TWEET_FIELD));

			}
			
		this.ct.createWekaFileForTrain(posTweets, negTweets);
		
		} else {
			System.out.println("No documents retrieved from db");
		}
	}
	
	

	public void flushDB() {
		this.dbm.removeAllDocuments();
	}
	
	
	
	
}
