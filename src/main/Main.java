package main;


import tweetprocessing.TweetRetriever;
import twitter4j.TwitterException;

public class Main {
	
	public static void main(String args[]) throws TwitterException {
		
		TweetRetriever tr = new TweetRetriever();

//		long start = System.nanoTime();

		tr.getTweetsForHashtag("#trump");
		
//		tr.getTweetsForTraining(":) OR #iloveit");
		
		
		//System.out.println(d.size());
		
//		List<Status> s = tc.getTwitsForHashtag("#asroma OR asroma OR asr");
//		System.out.println("#asroma + asroma + asr: " + s.size());
//		long end = System.nanoTime();
//		System.out.println((end - start) / 1000);



		//System.out.println(s.size());
		//		for (Status t : ret) {
		//			System.out.println(t.getUser() + "\n");
		//			System.out.println(t.getText() + "\n\n");
		//		}
		
		/*PROVE*/
		//HashtagMap c = new HashtagMap();
		//HashMap<String, Integer> map = c.createHashtagMap();
		//c.getTop3Hastags(map);
		//c.getTopHashtagsByHalfOfTweets(map, 1000);
		//c.getTopHashtagsByMinValue(map, 30);
		
		

	}

}
