package tweetprocessing;

import java.util.List;

import classifier.TweetClassifier;
import de.daslaboratorium.machinelearning.classifier.Classification;
import twitter4j.Status;
import twitter4j.TwitterException;

public class TweetManager {
	
	private TweetRetriever tr;
	private TweetTokenizer tt;
	private TweetClassifier tc;
	
	public TweetManager() {
		this.tr = new TweetRetriever();
		this.tt = new TweetTokenizer();
	}
	
	public void evaluateEventSentiment(String query) throws TwitterException {
		// Prende tutti i tweet relativi alla query in input
		List<Status> tweets = this.tr.getTweetsForHashtag(query);
		// Tokenizza i tweet 
		List<String> tokenizedTweets = this.tt.tokenize(tweets);
		// Classifica i tweet tokenizzati
		List<Classification<String, String>> c = this.tc.classifyTweets(tokenizedTweets);
		
		int i = 0;
		
		// stampa la categoria per ogni tweet
		while (i < tweets.size() && i < c.size()) {
			System.out.println(tweets.get(i) + " : " + c.get(i).getCategory());
			i++;
		}
	}
	
	public List<String> getTweetsForTraining(String query) throws TwitterException {
		List<Status> tweets = this.tr.getTweetsForTraining(query);
		return this.tt.tokenize(tweets);
	}

}
