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
		assertEquals(0, s.compareTo(sL));
	}
	
	@Test
	public void cleanTweetRemoveUrl(){
		String s = "Twett per testare http://prova.com il metodo";
		String s1 = "Twett per testarehttp://prova.com il metodo";
		String s2 = "Twett per testare il metodo http://prova.com";
		String s3 = " http://prova.comTwett per testare il metodo";
		String sL = "tweet per testare il metodo";
		s = tweetTokenizer.cleanTweet(s);
		s1 = tweetTokenizer.cleanTweet(s1);
		s2 = tweetTokenizer.cleanTweet(s2);
		s3 = tweetTokenizer.cleanTweet(s3);
		assertEquals(0, s.compareTo(sL));
		assertEquals(0, s1.compareTo(sL));
		assertEquals(0, s2.compareTo(sL));
		assertEquals(0, s3.compareTo(sL));
	}
	
	@Test
	public void cleanTweetUserName(){
		String s = "Tweet Per TestAre il METODO @user";
		String s1 = "Tweet Per TestAre il METODO@user";
		String s2 = " @userTweet Per TestAre il METODO";
		String s3 = "Tweet @user Per TestAre il METODO";
		String sL = "tweet per testare il metodo";
		s = tweetTokenizer.cleanTweet(s);
		s1 = tweetTokenizer.cleanTweet(s1);
		s2 = tweetTokenizer.cleanTweet(s2);
		s3 = tweetTokenizer.cleanTweet(s3);
		assertEquals(0, s.compareTo(sL));
		assertEquals(0, s1.compareTo(sL));
		assertEquals(0, s2.compareTo(sL));
		assertEquals(0, s3.compareTo(sL));
	}
	
	@Test
	public void cleanTweetHashtag(){
		String s = "Tweet Per TestAre il METODO #test";
		String s1 = "Tweet Per TestAre il METODO#user";
		String s2 = "#userTweet Per TestAre il METODO";
		String s3 = "Tweet #user Per TestAre il METODO";
		String sL = "tweet per testare il metodo";
		s = tweetTokenizer.cleanTweet(s);
		s1 = tweetTokenizer.cleanTweet(s1);
		s2 = tweetTokenizer.cleanTweet(s2);
		s3 = tweetTokenizer.cleanTweet(s3);
		assertEquals(0, s.compareTo(sL));
		assertEquals(0, s1.compareTo(sL));
		assertEquals(0, s2.compareTo(sL));
		assertEquals(0, s3.compareTo(sL));
	}
	
	@Test
	public void cleanTweetPunctuation(){
		String s = "Tweet Per TestAre, il METODO .";
		String s1 = "Tweet Per: TestAre il METODO.";
		String s2 = ".Tweet Per ;TestAre il METODO!";
		String s3 = "Tweet.... Per TestAre ,,il METODO??";
		String sL = "tweet per testare il metodo";
		s = tweetTokenizer.cleanTweet(s);
		s1 = tweetTokenizer.cleanTweet(s1);
		s2 = tweetTokenizer.cleanTweet(s2);
		s3 = tweetTokenizer.cleanTweet(s3);
		assertEquals(0, s.compareTo(sL));
		assertEquals(0, s1.compareTo(sL));
		assertEquals(0, s2.compareTo(sL));
		assertEquals(0, s3.compareTo(sL));
	}
	
}
