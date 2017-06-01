package db;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DBManager {
	
	private static final String DB_NAME = "progetto_sii";
	
	private static final String COLLECTION_NAME = "tweets";
	
	private static final String CATEGORY_FIELD = "category";
	private static final String TWEET_FIELD = "tweet";
	
	private MongoClient mc;
	private MongoDatabase mdb;
	
	public DBManager() {
		this.mc = new MongoClient("localhost");
		this.mdb = this.mc.getDatabase(DB_NAME);
	}
	
	public DBManager(String dbName) {
		this.mc = new MongoClient("localhost");
		this.mdb = this.mc.getDatabase(dbName);
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
	
	// aggiunge al db una lista di features nella categoria specificata
	public void addTweetsForTraining(List<String> features, String category) {
		try {
			MongoCollection<Document> featuresInDB = mdb.getCollection(COLLECTION_NAME);
			
			for (String feature : features) {
				Document d = new Document(TWEET_FIELD, feature).append(CATEGORY_FIELD, category);
				featuresInDB.insertOne(d);
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

	public MongoDatabase getDb() {
		return this.mdb;
	}
	
//	public boolean isEmpty() {
//		return mdb.getCollection(COLLECTION_NAME).
//	}

}
