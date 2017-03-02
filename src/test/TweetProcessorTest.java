package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import connection.TweetProcessor;

public class TweetProcessorTest {
	
	private TweetProcessor tweetProcessor;
	
	@Before
	public void setUp(){
		tweetProcessor = new TweetProcessor();
	}

	/*Stringa che non contiene hashtag quindi la mappa non dovrà contenere niente*/
	@Test
	public void emptyTweet() {
		String s = "Ciao, questa stringa non contiene hashtag";
		String h = "#hashtagDummy";
		tweetProcessor.getRelatedHashtags(s, h);
		assertEquals(0, tweetProcessor.getHashtagMap().size());
	}
	
	/*Stringa che contiene un hashtag*/
	@Test
	public void oneHashtagTweet(){
		String s = "Ciao, #forzaRoma eddaje";
		String h = "#hashtagDummy";
		tweetProcessor.getRelatedHashtags(s, h);
		assertEquals(1, tweetProcessor.getHashtagMap().size());	
	}
	
	/*Stringa che contiene 3 hashtag, contiene anche hashtag seguiti spazio, virgola,punto*/
	@Test
	public void threeHashtagTweet(){
		String s = "Ciao, #forzaRoma eddaje #DeRossi, #QuantoCazzoèforteFazio.";
		String h = "#hashtagDummy";
		tweetProcessor.getRelatedHashtags(s, h);
		assertEquals(3, tweetProcessor.getHashtagMap().size());	
	}
	
	/*Stringa che contiene 3 hashtag, ma uno non è da mettere*/
	@Test
	public void sameHashtagOfThatOneToExpandTweet(){
		String s = "Ciao, #forzaRoma eddaje #DeRossi, #QuantoCazzoèforteFazio.";
		String h = "#DeRossi";
		tweetProcessor.getRelatedHashtags(s, h);
		assertEquals(2, tweetProcessor.getHashtagMap().size());	
	}
	
	/*Stringa che contiene 3 hashtag, ma uno non è da mettere ma è scritto con diverse minuscole/maiuscole*/
	@Test
	public void sameHashtagOfThatOneToExpandButDifferentCaseTweet(){
		String s = "Ciao, #forzaRoma eddaje #DeRossi, #QuantoCazzoèforteFazio.";
		String h = "#derossi";
		tweetProcessor.getRelatedHashtags(s, h);
		assertEquals(2, tweetProcessor.getHashtagMap().size());	
	}
	
	/*Stringa che contiene 2 hashtag senza spazio in mezzo*/
	@Test
	public void twoHashtagWithoutSpaceBetweenThemTweet(){
		String s = "Ciao, #forzaRoma#DeRossi, #QuantoCazzoèforteFazio.";
		String h = "#hashtagDummy";
		tweetProcessor.getRelatedHashtags(s, h);
		assertEquals(3, tweetProcessor.getHashtagMap().size());	
	}
	
	

}
