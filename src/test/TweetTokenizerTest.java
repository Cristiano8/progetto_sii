package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tweetprocessing.TweetCleaner;
import twitter4j.ExtendedMediaEntity;
import twitter4j.GeoLocation;
import twitter4j.HashtagEntity;
import twitter4j.MediaEntity;
import twitter4j.Place;
import twitter4j.RateLimitStatus;
import twitter4j.Scopes;
import twitter4j.Status;
import twitter4j.SymbolEntity;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserMentionEntity;

public class TweetTokenizerTest {
	
	private TweetCleaner tweetTokenizer;
	
	@Before
	public void setUp(){
		tweetTokenizer = new TweetCleaner();
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
		String s3C = "tweet per testare il metodo";
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
		
		String s = "Tweet Per TestAre, il METODO.";
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
		
		String s1 = "Tweet per testare http://prova.com il metodo";
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
	
	
	@Test
	public void tokenizeNumber() {
		String s = "I like 69 and4hf";
		
		String cleaned = "i like andhf";
		
		assertEquals(tweetTokenizer.cleanTweet(s), cleaned);
		
	}
	
	@Test
	public void stemmerTest() {
		List<Status> s = new ArrayList<>();
		s.add(new Status() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public UserMentionEntity[] getUserMentionEntities() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public URLEntity[] getURLEntities() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public SymbolEntity[] getSymbolEntities() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public MediaEntity[] getMediaEntities() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public HashtagEntity[] getHashtagEntities() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public ExtendedMediaEntity[] getExtendedMediaEntities() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public RateLimitStatus getRateLimitStatus() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getAccessLevel() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int compareTo(Status arg0) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public boolean isTruncated() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isRetweetedByMe() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isRetweeted() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isRetweet() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isPossiblySensitive() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean isFavorited() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public String[] getWithheldInCountries() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public User getUser() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getText() {
				// TODO Auto-generated method stub
				return "Voglio tokenizzare questa stringa";
			}
			
			@Override
			public String getSource() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Scopes getScopes() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Status getRetweetedStatus() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getRetweetCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public long getQuotedStatusId() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Status getQuotedStatus() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Place getPlace() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getLang() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public long getInReplyToUserId() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public long getInReplyToStatusId() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getInReplyToScreenName() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public long getId() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public GeoLocation getGeoLocation() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getFavoriteCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public long getCurrentUserRetweetId() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Date getCreatedAt() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public long[] getContributors() {
				// TODO Auto-generated method stub
				return null;
			}
		});
//		System.out.println(tweetTokenizer.tokenize(s).toString());
		assertEquals("[vogl tokenizz string]", tweetTokenizer.clean(s).toString());
	}
	
}
