package tweetprocessing;

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
		
		//remove hashtag
		tweet = tweet.replace("#", "");
		
		//remove usernames
		tweet = tweet.replaceAll("@[^\\s]+", "");
		
		//remove punctuation
		tweet = tweet.replaceAll("[^a-zA-Z0-9\\s]+", "");
		
		//remove URL, they are now without punctuation
		tweet = tweet.replaceAll("(http[a-zA-Z-0-9]+)", "");
		
		if(tweet.startsWith(" ")){
			tweet = tweet.substring(1);
		}
		
		if(tweet.endsWith(" ")){
			tweet = tweet.substring(0, tweet.length()-1);
		}
		
		
		return tweet;
	}

}