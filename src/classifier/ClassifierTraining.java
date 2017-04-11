package classifier;

import java.util.Arrays;
import java.util.List;

import connection.TweetRetriever;
import connection.TweetTokenizer;

import twitter4j.Status;
import twitter4j.TwitterException;

import de.daslaboratorium.machinelearning.classifier.Classifier;
import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier;

public class ClassifierTraining {
	
	private TweetRetriever tr;
	private TweetTokenizer tt;
	
	private final static String POSITIVE_QUERY = ":) OR #iloveit";
	private final static String NEGATIVE_QUERY = ":( OR #ihateit";
	
	private final static String POSITIVE_CATEGORY = "positive";
	private final static String NEGATIVE_CATEGORY = "negative";

	public ClassifierTraining(TweetRetriever tr) {
		this.tr = tr;
		this.tt = new TweetTokenizer();
	}
	

	/* Scarica da twitter dei tweet positivi e negativi e li salva sul db. 
	 * Chiamato solo nella prima esecuzione */
	public void savePosAndNegTweets() throws TwitterException {
		
		final Classifier<String, String> bayes = new BayesClassifier<>();
		
		List<Status> positiveTweets = this.tr.getTweetsForTraining(POSITIVE_QUERY);
		List<Status> negativeTweets = this.tr.getTweetsForTraining(NEGATIVE_QUERY);
		
		List<String> posTokenizedTweets = this.tt.tokenize(positiveTweets);
		List<String> negTokenizedTweets = this.tt.tokenize(negativeTweets);
		
		this.saveOnDB(posTokenizedTweets, POSITIVE_CATEGORY);
		this.saveOnDB(negTokenizedTweets, NEGATIVE_CATEGORY);
		
		this.learn(bayes, posTokenizedTweets, POSITIVE_CATEGORY);
		this.learn(bayes, negTokenizedTweets, NEGATIVE_CATEGORY);

	}

	// Salva i tweet tokenizzati sul db con la relativa categoria
	private void saveOnDB(List<String> tokenizedTweets, String category) {
		// TODO Auto-generated method stub
		
	}


	private void learn(Classifier<String, String> bayes, List<String> tweets, String category) {
		for (String tweet : tweets) {
			bayes.learn(category, Arrays.asList(tweet.split("\\s")));
		}
	}
	
	/* Prende la lista dei tweet positivi e negativi salvati sul db 
	   e addestra il classificatore */
	public void trainFromDB() {
		
	}

}
