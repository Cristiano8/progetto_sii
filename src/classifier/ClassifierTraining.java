package classifier;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;

import connection.TweetRetriever;
import connection.TweetTokenizer;
import db.DBManager;
import twitter4j.Status;
import twitter4j.TwitterException;

import de.daslaboratorium.machinelearning.classifier.Classifier;
import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier;

public class ClassifierTraining {

	private TweetRetriever tr;
	private TweetTokenizer tt;
	private DBManager dbm;

	private final static String POSITIVE_QUERY = ":) OR #iloveit";
	private final static String NEGATIVE_QUERY = ":( OR #ihateit";

	private final static String POSITIVE_CATEGORY = "positive";
	private final static String NEGATIVE_CATEGORY = "negative";

	private static final String CATEGORY_FIELD = "category";
	private static final String TWEET_FIELD = "tweet";

	final Classifier<String, String> bayes = new BayesClassifier<>();

	public ClassifierTraining(TweetRetriever tr) {
		this.tr = tr;
		this.tt = new TweetTokenizer();
		this.dbm = new DBManager();
	}


	/* Scarica da twitter dei tweet positivi e negativi e li salva sul db. 
	 * Chiamato solo nella prima esecuzione */
	public void savePosAndNegTweets() throws TwitterException {

		List<Status> positiveTweets = this.tr.getTweetsForTraining(POSITIVE_QUERY);
		List<Status> negativeTweets = this.tr.getTweetsForTraining(NEGATIVE_QUERY);

		List<String> posTokenizedTweets = this.tt.tokenize(positiveTweets);
		List<String> negTokenizedTweets = this.tt.tokenize(negativeTweets);

		// Salva i tweet tokenizzati sul db con la relativa categoria
		this.dbm.addTweetsForTraining(posTokenizedTweets, POSITIVE_CATEGORY);
		this.dbm.addTweetsForTraining(negTokenizedTweets, NEGATIVE_CATEGORY);

		this.learn(POSITIVE_CATEGORY, posTokenizedTweets);
		this.learn(NEGATIVE_CATEGORY, negTokenizedTweets);

	}


	private void learn(String category, List<String> tweets) {
		for (String tweet : tweets) {
			bayes.learn(category, Arrays.asList(tweet.split("\\s")));
		}
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

}
