package connection;

import java.util.ArrayList;

import java.util.List;

import tweetprocessing.TweetCleaner;
import tweetprocessing.TweetProcessor;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/* Classe che ha la responsabilità di accedere a Twitter e 
 * prendere tutti i Tweet relativi ad un evento */

public class TweetRetriever {

	private static final int QUERY_LIMIT = 10;
	
	private static TweetRetriever instance;
	
	private TwitterConnection tc;
	private Twitter twitter;	
	private TweetCleaner tcl;
	private TweetProcessor tp;

	private TweetRetriever() {
		this.tc = new TwitterConnection();
		this.twitter = tc.getTwitter();
		this.tp = new TweetProcessor();
		this.tcl = new TweetCleaner();
	}
	
	public static TweetRetriever getInstance() {
		if (instance == null) {
			instance = new TweetRetriever();
		}
		return instance;
	}

	/* Prende i tweet in base a query per il training e ritorna la lista di tweet puliti */
	public List<String> getTweetsForTraining(String query) throws TwitterException {
		List<Status> tweets = new ArrayList<>();

		try {

			Query q = new Query(query + " lang:it -filter:retweets");
			q.setCount(100);
			QueryResult qr;
			
			int cont = 0;

			do {
				qr = twitter.search(q);
				List<Status> tweetsInAPage = qr.getTweets();
				tweets.addAll(tweetsInAPage);
				cont++;
			} while (cont < QUERY_LIMIT);

			
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to perform training search: " + te.getMessage());
		}

		return this.tcl.clean(tweets);
	}

	public List<String> getTweetsForHashtag(String query) throws TwitterException {

		List<Status> tweets = new ArrayList<>(); // conterrà tutti i tweet della prima query

		try {

			Query q = new Query(query + " lang:it");
			q.setCount(100);
			QueryResult qr;

			do {
				qr = twitter.search(q);
				List<Status> tweetsInAPage = qr.getTweets();
				tweets.addAll(tweetsInAPage);

				for (Status tweet : tweetsInAPage) {
					if (!tweet.isRetweet()) { // aggiungo gli hashtag alla mappa solo se non appartengono a retweet
						tp.addRelatedHashtags(tweet.getText(), query); // aggiungo altri hashtag nel Tweet
					}

				}

			} while ((q = qr.nextQuery()) != null);

		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to perform first search: " + te.getMessage());
		}

		tweets.addAll(this.extendQuery());

		return this.tcl.clean(tweets);


	}

	/* seconda query fatta accoppiando gli hashtag che hanno più occorrenze nei tweet
	 * recuperati dalla prima query */
	private List<Status> extendQuery() throws TwitterException {

		List<String> hashtagsForSecondQuery = this.tp.getTop3Hashtags();

		String hashtag1 = hashtagsForSecondQuery.get(0);
		String hashtag2 = hashtagsForSecondQuery.get(1);
		String hashtag3 = hashtagsForSecondQuery.get(2);

		System.out.println(hashtag1 + " " + hashtag2 + " " + hashtag3);

		String query1 = "(" + hashtag1 + " AND " + hashtag2 + ")";
		String query2 = "(" + hashtag2 + " AND " + hashtag3 + ")";
		String query3 = "(" + hashtag3 + " AND " + hashtag1 + ")";
		

		List<Status> tweetsFromExtendedSearch = new ArrayList<>();

		try {

			Query q = new Query(query1 + " OR " + query2 + " OR " + query3);
			q.setCount(100);
			QueryResult qr;

			do {
				qr = twitter.search(q);
				List<Status> tweetsInAPage = qr.getTweets();
				tweetsFromExtendedSearch.addAll(tweetsInAPage);


			} while ((q = qr.nextQuery()) != null);

		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to perform second search: " + te.getMessage());
		}

		return tweetsFromExtendedSearch;

	}


}
