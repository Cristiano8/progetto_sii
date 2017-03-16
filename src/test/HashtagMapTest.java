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
		int size = hashtagMap.size();
		this.hashtagMap.insertHastag(hashtag);
		assertEquals(this.hashtagMap.size(), size + 1);
		assertEquals(this.hashtagMap.get(hashtag).intValue(), 1);
	}
	
	@Test
	public void testInsertHastag_OldElement() {
		this.hashtagMap.insertHastag("a");
		assertEquals(this.hashtagMap.get("a").intValue(), 6);
	}
	
	//Test Get Hashtag by MinValue
	@Test
	public void testGetTopHashtagsByMinValue() {
		List<String> topHashtag = new ArrayList<>();
		topHashtag = this.hashtagMap.getTopHashtagsByValue(6);
		
		assertTrue(topHashtag.contains("d"));
		assertTrue(topHashtag.contains("e"));
		
		assertFalse(topHashtag.contains("a"));
		assertFalse(topHashtag.contains("b"));
		assertFalse(topHashtag.contains("c"));
	}
	
	@Test
	public void testGetTopHashtagsByMinValue_Empty() {
		List<String> topHashtag = new ArrayList<>();
		topHashtag = this.hashtagMap.getTopHashtagsByValue(10);
		assertTrue(topHashtag.isEmpty());
	}
	
	//Test Get Hashtag by Threshold
	@Test
	public void testGetTop3Hashtags() {
		List<String> top3Hashtag = this.hashtagMap.getTopHashtagsByThresholdUsingASupportMap(3);
		
		assertTrue(top3Hashtag.size() == 3);
		
		assertTrue(top3Hashtag.contains("a"));
		assertTrue(top3Hashtag.contains("d"));
		assertTrue(top3Hashtag.contains("e"));
		
		assertFalse(top3Hashtag.contains("b"));
		assertFalse(top3Hashtag.contains("c"));
	}
	
	@Test
	public void testGetTop7Hashtags() {
		List<String> top7Hashtag = this.hashtagMap.getTopHashtagsByThresholdUsingASupportMap(7);
		
		assertTrue(top7Hashtag.size() == this.hashtagMap.size());
		
		assertTrue(top7Hashtag.contains("a"));
		assertTrue(top7Hashtag.contains("b"));
		assertTrue(top7Hashtag.contains("c"));
		assertTrue(top7Hashtag.contains("d"));
		assertTrue(top7Hashtag.contains("e"));
	}
	
	@Test
	public void testGetTop0Hashtags() {
		List<String> top0Hashtag = this.hashtagMap.getTopHashtagsByThresholdUsingASupportMap(0);
		
		assertNull(top0Hashtag);
	}
	
	// Test Get Hashtag Over Mean
	@Test
	public void testGetHashtagOverMean() {
		List<String> topHashtagByMean = this.hashtagMap.getHashtagOverMean();
		
		assertTrue(topHashtagByMean.size() == 3);
		
		assertTrue(topHashtagByMean.contains("a"));
		assertTrue(topHashtagByMean.contains("d"));
		assertTrue(topHashtagByMean.contains("e"));
		
		assertFalse(topHashtagByMean.contains("b"));
		assertFalse(topHashtagByMean.contains("c"));
	}
	
	//Test getTopHashstagByThresholdUsingASupportMap
	@Test
	public void testGetTopHashstagByThresholdUsingASupportMap(){
		List<String> topHashtags = this.hashtagMap.getTopHashtagsByThresholdUsingASupportMap(3);
		
		assertEquals(topHashtags.size(), 3);
		
		assertTrue(topHashtags.contains("a"));
		assertTrue(topHashtags.contains("d"));
		assertTrue(topHashtags.contains("e"));
		
		assertFalse(topHashtags.contains("b"));
		assertFalse(topHashtags.contains("c"));
	}

}
