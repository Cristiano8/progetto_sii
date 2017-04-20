package classifier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import db.DBManager;
import db.PosNegWordReader;
import twitter4j.TwitterException;
import de.daslaboratorium.machinelearning.classifier.Classification;
import de.daslaboratorium.machinelearning.classifier.Classifier;
import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier;
import tweetprocessing.TweetManager;

public class TweetClassifier {

	private TweetManager tm;
	private DBManager dbm;
	private PosNegWordReader pnwr;

	private final static String POSITIVE_QUERY = ":)";
	private final static String NEGATIVE_QUERY = ":(";

	private final static String POSITIVE_CATEGORY = "positive";
	private final static String NEGATIVE_CATEGORY = "negative";

	private static final String CATEGORY_FIELD = "category";
	private static final String TWEET_FIELD = "tweet";

	public final Classifier<String, String> bayes = new BayesClassifier<>();
	
	public TweetClassifier() {
		this.dbm = new DBManager();
		this.pnwr = new PosNegWordReader();
		
		bayes.setMemoryCapacity(1000);
	}

	
	public void setTweetManager(TweetManager tm) {
		this.tm = tm;
	}

	
	public void trainFromLIWC() throws IOException {
		// prende le feature dal file LIWC
		List<String> positiveFeatures = this.pnwr.getFeaturesByCategory(POSITIVE_CATEGORY);
		List<String> negativeFeatures = this.pnwr.getFeaturesByCategory(NEGATIVE_CATEGORY);
		
		// salva nel db le parole prese dal file LIWC
		this.dbm.addTweetsForTraining(positiveFeatures, POSITIVE_CATEGORY);
		this.dbm.addTweetsForTraining(negativeFeatures, NEGATIVE_CATEGORY);
		
		// addestra il classificatore con le parole del file LIWC
		this.learn(POSITIVE_CATEGORY, positiveFeatures);
		this.learn(NEGATIVE_CATEGORY, negativeFeatures);
		
	}

	/* Scarica da twitter dei tweet positivi e negativi e li salva sul db. 
	 * Chiamato solo alla prima esecuzione */
	public void trainFromTwitter() throws TwitterException {
		// prende le feature da Twitter
		List<String> positiveTweets = this.tm.getTweetsForTraining(POSITIVE_QUERY);
		List<String> negativeTweets = this.tm.getTweetsForTraining(NEGATIVE_QUERY);

		// Salva i tweet tokenizzati sul db con la relativa categoria
		this.dbm.addTweetsForTraining(positiveTweets, POSITIVE_CATEGORY);
		this.dbm.addTweetsForTraining(negativeTweets, NEGATIVE_CATEGORY);

		// addestra il classificatore con la lista dei tweets
		this.learn(POSITIVE_CATEGORY, positiveTweets);
		this.learn(NEGATIVE_CATEGORY, negativeTweets);

	}

	// fa il learn di una lista di feature per una categoria
	private void learn(String category, List<String> features) {
		for (String feature : features) {
			bayes.learn(category, Arrays.asList(feature.split("\\s")));
		}
	}
	
	public void printClassifier() {
		System.out.println(bayes.getCategories().toString());
		System.out.println(bayes.getFeatures().toString());
		System.out.println(bayes.getFeatures().size());
	}

	/* Prende la lista dei tweet positivi e negativi salvati sul db 
	   e addestra il classificatore */
	public void trainFromDB() {
		List<Document> tweetsFromDB = this.dbm.getTweetsForTraining();

		if (!tweetsFromDB.isEmpty()) {

			for (Document d : tweetsFromDB) {
				String category = d.getString(CATEGORY_FIELD);
				List<String> tweet = Arrays.asList(d.getString(TWEET_FIELD).split("\\s"));

				bayes.learn(category, tweet);
			}

		} else {
			System.out.println("No documents retrieved from db");
		}
	}

	public void flushDB() {
		this.dbm.removeAllDocuments();
	}
	
	public List<Classification<String, String>> classifyTweets(List<String> tweets) {
		List<Classification<String, String>> c = new ArrayList<>();
		for (String tweet : tweets) {
			List<String> tweetToClassify = Arrays.asList(tweet.split("\\s"));
			c.add(bayes.classify(tweetToClassify));
		}
		
		return c;
	}
	
//	private void learnFromFile() throws IOException {
//		List<String> posAndNegWords = this.panr.read();
//		for (String line : posAndNegWords) {
//			String fields[] = line.split("\\t");
//		}
//	}
	
}
