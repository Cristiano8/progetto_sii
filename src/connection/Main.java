package connection;


import twitter4j.TwitterException;

public class Main {
	
	public static void main(String args[]) throws TwitterException {

		double startTime = System.nanoTime();
		
		TweetRetriever tr = new TweetRetriever();

//		long start = System.nanoTime();

		tr.getTwitsForHashtag("#trump");
		
		double endTime = System.nanoTime();
		
		System.out.println("It took : " + (endTime - startTime)/1000000000 + " seconds");
		
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
