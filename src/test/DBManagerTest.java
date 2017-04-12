package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import db.DBManager;


public class DBManagerTest {
	
	private DBManager dbm;
	private MongoDatabase database;

	private List<String> posTweets;
	private List<String> negTweets;
	
	@Before
	public void setUp() {
		this.dbm = new DBManager("test_progetto_sii");
		this.database = this.dbm.getDb();
		
		dbm.removeAllDocuments();
		
		try {
			MongoCollection<Document> documents = database.getCollection("tweets");
			documents.insertOne(new Document("tweet", "sono molto felice").append("category", "positive"));
			documents.insertOne(new Document("tweet", "forza roma").append("category", "positive"));
			documents.insertOne(new Document("tweet", "disappunto politica italiana").append("category", "negative"));
			documents.insertOne(new Document("tweet", "odio la lazio").append("category", "negative"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.posTweets = new ArrayList<>();
		posTweets.add("evviva");
		posTweets.add("positività");
		
		this.negTweets = new ArrayList<>();
		negTweets.add("tristezza");

	}

	@Test
	public void testGetTweetsForTraining() {
		List<Document> doc = dbm.getTweetsForTraining();
		
		assertTrue(doc.size() == 4);
		
	}

	@Test
	public void testAddTweetsForTraining() {
		dbm.addTweetsForTraining(posTweets, "positive");
		List<Document> doc = dbm.getTweetsForTraining();
		assertTrue(doc.size() == 6); 
		System.out.println(doc.toString());
	}


}
