package db;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DBManager {
	
	private static final String CATEGORY_FIELD = "category";

	private static final String TWEET_FIELD = "tweet";

	private static final String COLLECTION_NAME = "tweets";

	private static final String DB_NAME = "progetto_sii";
	
	private MongoClient mc;
	private MongoDatabase mdb;
	
	public DBManager() {
		this.mc = new MongoClient("localhost");
		this.mdb = this.mc.getDatabase(DB_NAME);
	}
	
	public List<Document> getTweetsForTraining() {
		List<Document> foundDoc = new ArrayList<>();
		try {
			
			MongoCollection<Document> tweets = mdb.getCollection(COLLECTION_NAME);
			foundDoc = tweets.find().into(new ArrayList<>());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return foundDoc;
		
	}
	
	public void addTweetsForTraining(List<String> tweets, String category) {
		try {
			MongoCollection<Document> tweetsInDB = mdb.getCollection(COLLECTION_NAME);
			
			for (String tweet : tweets) {
				Document d = new Document(TWEET_FIELD, tweet).append(CATEGORY_FIELD, category);
				tweetsInDB.insertOne(d);
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeAllDocuments() {
		try {
			MongoCollection<Document> tweetsInDB = mdb.getCollection(COLLECTION_NAME);
			
			tweetsInDB.deleteMany(new Document());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
