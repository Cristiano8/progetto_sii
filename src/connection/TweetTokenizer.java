package connection;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;

public class TweetTokenizer {
	
	/*The input is the list of the tweets retrieved, i need to tokenize them using the method cleanTweet.
	 * Quindi ogni tweet pulito viene inserito nella lista cleaned per continuare la procedura*/
	
	public List<String> tokenize(List<Status> tweets) {
		
		//Take only the text of each tweet
		List<String> textTweets = new ArrayList<>();
		for(Status tweet : tweets){
			textTweets.add(tweet.getText());
		}
		
			
		//Clean the whole list of tweet
		List<String> cleaned = new ArrayList<>();	
		for(String tweet : textTweets){
			String cleanedTweet;
			cleanedTweet = this.cleanTweet(tweet);
			cleaned.add(cleanedTweet);
		}
		
		List<String> tokenized = new ArrayList<>();
		return tokenized;
	}
	
	public String cleanTweet(String tweet){
		
		//Convert tweet to lower case
		tweet = tweet.toLowerCase();
		
		//remove URLs
		tweet = tweet.replaceAll("(www\\.[^\\s]+)|(https?://[^\\s]+)", "");
		
		//remove usernames
		tweet = tweet.replaceAll("@[^\\s]", "");
		
		//remove hashtag
		tweet = tweet.replaceAll("#", "");
		
		//remove punctuation
		tweet = tweet.replaceAll("\\p.{Punct}+", "");
		
		return tweet;
	}

}
