package connection;

import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterException;

public class Main {
	
	public static void main(String args[]) throws TwitterException {


		TwitterConnection tc = new TwitterConnection();

//		long start = System.nanoTime();

		List<Status> d = tc.getTwitsForHashtag("#tordivalle OR #famostostadio");
//		System.out.println("#asroma: " + d.size());
		
//		List<Status> s = tc.getTwitsForHashtag("#asroma OR asroma OR asr");
//		System.out.println("#asroma + asroma + asr: " + s.size());
//		long end = System.nanoTime();
//		System.out.println((end - start) / 1000);



		//System.out.println(s.size());
		//		for (Status t : ret) {
		//			System.out.println(t.getUser() + "\n");
		//			System.out.println(t.getText() + "\n\n");
		//		}
		

	}

}
