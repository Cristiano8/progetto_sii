package connection;

import java.util.ArrayList;

import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/* Classe che ha la responsabilità di accedere a Twitter e prendere tutti i Tweet realitivi ad un evento */

public class TweetRetriever {

	private Twitter twitter;	
	private TwitterConnection tc;
	private TweetProcessor tp;

	public TweetRetriever() {
		this.tc = new TwitterConnection();
		this.twitter = tc.getTwitter();
		this.tp = new TweetProcessor();
	}

	public void getTwitsForHashtag(String query) throws TwitterException {
		int cont = 0;
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
						cont++;
					}

				}

			} while ((q = qr.nextQuery()) != null);

		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to perform first search: " + te.getMessage());
		}

		//TODO aggiungere tweets al db (contiene i retweet)
		System.out.println(cont);
		System.out.println("First search: " + tweets.size());

		this.extendQuery();


	}

	/* seconda query fatta accoppiando gli hashtag che hanno più occorrenze nei tweet
	 * recuperati dalla prima query */
	private void extendQuery() {
		List<String> hashtagsForSecondQuery = this.tp.getTop3Hashtags();

		String hashtag1 = hashtagsForSecondQuery.get(0);
		String hashtag2 = hashtagsForSecondQuery.get(1);
		String hashtag3 = hashtagsForSecondQuery.get(2);

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
		
//		printTweets(tweetsFromExtendedSearch);
		
		System.out.println("Second search: " + tweetsFromExtendedSearch.size());

		
		// TODO aggiungere tweetsFromExtendedSearch al db (contiene i retweet)

	}

	/*
	private void printTweets(List<Status> tweetsFromExtendedSearch) {
		for (Status s : tweetsFromExtendedSearch) {
			if (!s.isRetweet()) 
				System.out.println(s.getUser().getScreenName() + " - " + s.getText() + "||||");
		}
		
	} */


}
