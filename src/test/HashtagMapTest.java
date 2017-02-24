package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import connection.HashtagMap;

public class HashtagMapTest {
	
	private HashtagMap hashtagMap;
	
	@Before
	public void setUp() {
		this.hashtagMap = new HashtagMap();
		this.hashtagMap.getHashtagMap().put("a", 5);
		this.hashtagMap.getHashtagMap().put("b", 1);
		this.hashtagMap.getHashtagMap().put("c", 3);
		this.hashtagMap.getHashtagMap().put("d", 6);
		this.hashtagMap.getHashtagMap().put("e", 8);
		
	}

	// Test Insert Hashtag
	
	@Test
	public void testInsertHastag_NewElement() {
		String hashtag = "z";
		int size = hashtagMap.getHashtagMap().size();
		this.hashtagMap.insertHastag(hashtag);
		assertEquals(this.hashtagMap.getHashtagMap().size(), size + 1);
		assertEquals(this.hashtagMap.getHashtagMap().get(hashtag).intValue(), 1);
	}
	
	@Test
	public void testInsertHastag_OldElement() {
		this.hashtagMap.insertHastag("a");
		assertEquals(this.hashtagMap.getHashtagMap().get("a").intValue(), 6);
	}
	
	//Test Get Hashtag by MinValue

	@Test
	public void testGetTopHashtagsByMinValue() {
		List<String> topHashtag = new ArrayList<>();
		topHashtag = this.hashtagMap.getTopHashtagsByMinValue(6);
		
		assertTrue(topHashtag.contains("d"));
		assertTrue(topHashtag.contains("e"));
		
		assertFalse(topHashtag.contains("a"));
		assertFalse(topHashtag.contains("b"));
		assertFalse(topHashtag.contains("c"));
	}
	
	@Test
	public void testGetTopHashtagsByMinValue_Empty() {
		List<String> topHashtag = new ArrayList<>();
		topHashtag = this.hashtagMap.getTopHashtagsByMinValue(10);
		assertTrue(topHashtag.isEmpty());
	}
	
//	// Test Get Hashtag by Half of Tweets
//
//	@Test
//	public void testGetTopHashtagsByHalfOfTweets() {
//		fail("Not yet implemented");
//	}
	
	// Test Get Top Hashtag

	@Test
	public void testGetTop3Hashtags() {
		List<String> top3Hashtag = this.hashtagMap.getTopHashtags(3);
		
		assertTrue(top3Hashtag.size() == 3);
		
		assertTrue(top3Hashtag.contains("a"));
		assertTrue(top3Hashtag.contains("d"));
		assertTrue(top3Hashtag.contains("e"));
		
		assertFalse(top3Hashtag.contains("b"));
		assertFalse(top3Hashtag.contains("c"));
	}
	
	@Test
	public void testGetTop7Hashtags() {
		List<String> top7Hashtag = this.hashtagMap.getTopHashtags(7);
		
		assertTrue(top7Hashtag.size() == this.hashtagMap.getHashtagMap().size());
		
		assertTrue(top7Hashtag.contains("a"));
		assertTrue(top7Hashtag.contains("b"));
		assertTrue(top7Hashtag.contains("c"));
		assertTrue(top7Hashtag.contains("d"));
		assertTrue(top7Hashtag.contains("e"));
	}
	
	@Test
	public void testGetTop0Hashtags() {
		List<String> top0Hashtag = this.hashtagMap.getTopHashtags(0);
		
		assertNull(top0Hashtag);
	}

}
