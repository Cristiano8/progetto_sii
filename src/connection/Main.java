package connection;

import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterException;

public class Main {
	
	public static void main(String args[]) throws TwitterException {


		TweetAnalyzer ta = new TweetAnalyzer();

//		long start = System.nanoTime();

		List<Status> d = ta.getTwitsForHashtag("#tordivalle");
		
		
		
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
