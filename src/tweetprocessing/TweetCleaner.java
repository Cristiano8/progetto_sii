package tweetprocessing;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;

public class TweetCleaner {


	Stemmer stemmer = new Stemmer();

	/*The input is the list of the tweets retrieved, i need to tokenize them using the method cleanTweet.
	 * Quindi ogni tweet pulito viene inserito nella lista cleaned per continuare la procedura*/

	public List<String> clean(List<Status> tweets) {

		//Take only the text of each tweet
		List<String> textTweets = new ArrayList<>(); // list of tweet text
		for(Status tweet : tweets) {
			textTweets.add(tweet.getText());
		}

		//Clean the whole list of tweet
		List<String> cleaned = new ArrayList<>();	
		for(String tweet : textTweets) {
			String cleanedTweet = this.cleanTweet(tweet);
//			String stemmedTweet = this.stem(cleanedTweet);
			cleaned.add(cleanedTweet);
		}

		return cleaned;
	}


	/*Clean the string, lowering the case, removing the hashtags, usernames, punctuation and URL. Return the string cleaned*/
	public String cleanTweet(String tweet) {

		//remove RT
		tweet = tweet.replace("RT", "");

		//Convert tweet to lower case
		tweet = tweet.toLowerCase();

		//remove hashtag
		tweet = tweet.replace("#", "");

		//remove usernames
		tweet = tweet.replaceAll("@[^\\s]+", "");

		//remove URL, they are now without punctuation
		tweet = tweet.replaceAll("http[^\\s]+", "");
		
		//remove punctuation with a white space
		tweet = tweet.replaceAll("[^a-zA-Z0-9\\s]+", " ");

		//remove numbers
		tweet = tweet.replaceAll("[0-9]+", "");
		
		tweet = tweet.replaceAll("'", " ");

		//remove the white space at the start and at the end of the string
		if(tweet.startsWith(" ")) {
			tweet = tweet.substring(1);
		}
		if(tweet.endsWith(" ")) {
			tweet = tweet.substring(0, tweet.length() - 1);
		}
		
		//remove \n
		tweet = tweet.replaceAll("\\n", "");
		
		tweet = tweet.replaceAll("[\\s\\s]+", " ");

		return tweet;
	}

//	public String stem(String tweet) {
//		// stemming
//		String tweetToBeStemmed = tweet;
//		tweet = "";
//		for(String s: tweetToBeStemmed.split(" ")){
//			s = stemmer.stem(s);
//			if (s != null){
//				tweet = tweet + " " + s ;
//			}
//		}
//		
//		if (tweet.startsWith(" "))
//			return tweet.substring(1);
//		else
//			return tweet;
//	}




}
