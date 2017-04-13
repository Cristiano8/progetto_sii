package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import connection.TweetTokenizer;

public class TweetTokenizerTest {
	
	private TweetTokenizer tweetTokenizer;
	
	@Before
	public void setUp(){
		tweetTokenizer = new TweetTokenizer();
	}

	@Test
	public void cleanTweetLowerCase(){
		String s = "Tweet Per TestAre il METODO";
		String sL = "tweet per testare il metodo";
		s = tweetTokenizer.cleanTweet(s);
		assertTrue(s.equals(sL));
	}
	
	@Test
	public void cleanTweetUserName(){
		
		String s = "Tweet Per TestAre il METODO @user";
		String sC = "tweet per testare il metodo";
		s = tweetTokenizer.cleanTweet(s);
		assertTrue(s.equals(sC));
		
		String s1 = "Tweet Per TestAre il METODO@user";
		String s1C = "tweet per testare il metodo";
		s1 = tweetTokenizer.cleanTweet(s1);
		assertTrue(s1.equals(s1C));
		
		String s2 = "@userTweet Per TestAre il METODO";
		String s2C = "per testare il metodo";
		s2 = tweetTokenizer.cleanTweet(s2);
		System.out.println("s2: " + s2);
		System.out.println("s2C: " + s2C);
		assertTrue(s2.equals(s2C));
		
		String s3 = "Tweet @user Per TestAre il METODO";
		String s3C = "tweet  per testare il metodo";
		s3 = tweetTokenizer.cleanTweet(s3);
		System.out.println("s3: " + s3);
		System.out.println("s3C: " + s3C);
		assertTrue(s3.equals(s3C));
	}
	
	@Test
	public void cleanTweetHashtag(){

		String s = "Tweet Per TestAre il METODO #test";
		String sC = "tweet per testare il metodo test";
		s = tweetTokenizer.cleanTweet(s);
		assertTrue(s.equals(sC));

		String s1 = "Tweet Per TestAre il METODO#user";
		String s1C = "tweet per testare il metodouser"; 
		s1 = tweetTokenizer.cleanTweet(s1);
		assertTrue(s1.equals(s1C));
				
		String s2 = "#userTweet Per TestAre il METODO";
		String s2C = "usertweet per testare il metodo";
		s2 = tweetTokenizer.cleanTweet(s2);
		assertTrue(s2.equals(s2C));
		
		String s3 = "Tweet #user Per TestAre il METODO";
		String s3C = "tweet user per testare il metodo";
		s3 = tweetTokenizer.cleanTweet(s3);
		assertTrue(s3.equals(s3C));
	}
	
	@Test
	public void cleanTweetPunctuation(){
		
		String s = "Tweet Per TestAre, il METODO .";
		String sC = "tweet per testare il metodo";
		s = tweetTokenizer.cleanTweet(s);
		System.out.println("s: " + s + " sC: " + sC);
		assertTrue(s.equals(sC));
		
		String s1 = "Tweet Per: TestAre il METODO.";
		String s1C = "tweet per testare il metodo";
		s1 = tweetTokenizer.cleanTweet(s1C);
		System.out.println("s1: " + s1 + " s1C: " + s1C);
		assertTrue(s1.equals(s1C));
		
		String s2 = ".Tweet Per ;TestAre il METODO!";
		String s2C = "tweet per testare il metodo";
		s2 = tweetTokenizer.cleanTweet(s2);
		System.out.println("s2: " + s2 + " s2C: " + s2C);
		assertTrue(s2.equals(s2C));
		
		String s3 = "Tweet.... Per TestAre ,,il METODO??";
		String s3C = "tweet per testare il metodo";
		s3 = tweetTokenizer.cleanTweet(s3);
		System.out.println("s3: " + s3 + " s3C: " + s3C);
		assertTrue(s3.equals(s3C));
		
//		String s4 = "http://prova.com";
//		String s4C = "httpprovacom";
//		s4 = tweetTokenizer.cleanTweet(s4);
//		System.out.println("s4: " + s4 + " s4C: " + s4C);
//		assertTrue(s4.equals(s4C));
	}
	
	
	@Test
	public void cleanTweetRemoveUrl(){
		String s = "Tweet. http://prova.com";
		String sC = "tweet";
		s = tweetTokenizer.cleanTweet(sC);
		System.out.println("s: " + s + " sC: " + sC);
		assertTrue(s.equals(sC));
		
		String s1 = "Tweet per testarehttp://prova.com il metodo";
		String s1C = "tweet per testare il metodo";
		s1 = tweetTokenizer.cleanTweet(s1);
		System.out.println("s1: " + s1 + " s1C: " + s1C);
		assertTrue(s1.equals(s1C));
		
		String s2 = "Tweet per testare il metodo http://prova.com";
		String s2C = "tweet per testare il metodo";
		s2 = tweetTokenizer.cleanTweet(s2C);
		System.out.println("s2: " + s2 + " s2C: " + s2C);
		assertTrue(s2.equals(s2C));

		
		String s3 = "http://prova.comTweet per testare il metodo";
		String s3C = "per testare il metodo";
		s3 = tweetTokenizer.cleanTweet(s3C);
		System.out.println("s3: " + s3 + " s3C: " + s3C);
		assertTrue(s3.equals(s3C));
	}
	
}
