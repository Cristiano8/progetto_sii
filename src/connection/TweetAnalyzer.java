package connection;

import java.util.ArrayList;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/* Classe che ha la responsabilità di accedere a Twitter e prendere tutti i Tweet realitivi ad un evento */

public class TweetAnalyzer {

	private Twitter twitter;	
	private TwitterConnection tc;
	private TweetProcessor tp;
	private HashtagMap hm;

	public TweetAnalyzer() {
		this.tc = new TwitterConnection();
		this.twitter = tc.getTwitter();
		this.tp = new TweetProcessor();
		this.hm = tp.getHashtagMap();
	}

	public List<Status> getTwitsForHashtag(String query) throws TwitterException {

		List<Status> tweets = new ArrayList<>();

		try {

			Query q = new Query(query + " lang:it");
			q.setCount(100);
			QueryResult qr;

			/*Prova per tweetProcessor*/
			//			qr = twitter.search(q);
			//			List<Status> tweetsInAPage = qr.getTweets();
			//			tweets.addAll(tweetsInAPage);
			//			tp.getRelatedHashtags(tweets.get(0).getText());
			//			System.out.println("@" + tweets.get(0).getUser().getScreenName() +
			//					" - " + tweets.get(0).getCreatedAt() +
			//					" - " + tweets.get(0).getText());

			do {
				qr = twitter.search(q);
				List<Status> tweetsInAPage = qr.getTweets();
				tweets.addAll(tweetsInAPage);

				for (Status tweet : tweetsInAPage) {
					if (!tweet.isRetweet()) { // aggiungo gli hashtag alla mappa solo se non appartengono a retweet
						tp.addRelatedHashtags(tweet.getText(), query); // aggiungo altri hashtag nel Tweet
					
//										System.out.println("@" + tweet.getUser().getScreenName() +
//												" - " + tweet.getCreatedAt() +
//												" - " + tweet.getText());
					}

				}

			} while ((q = qr.nextQuery()) != null);

		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
		}

		System.out.println(hm.toString());
		System.out.println(hm.getHashtagOverMean().toString());

		return tweets;

	}

}
