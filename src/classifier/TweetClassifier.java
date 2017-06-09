package classifier;


import java.util.List;

public class TweetClassifier {
	
	private CreatePredictionFile predictionFileCreator;
	private WekaClassifier wekaClassifier;
	
	public TweetClassifier() throws Exception {
		this.predictionFileCreator = new CreatePredictionFile();
		this.wekaClassifier = new WekaClassifier();
	}

	public List<PredictionResult> classifyTweets(List<String> tweets) throws Exception {
		
		System.out.println("Classifying tweets retrieved");

		predictionFileCreator.createFileARFFToPredict(tweets);
		this.wekaClassifier.initClassifier();
		
		System.out.println("File predict created");
		
		return this.wekaClassifier.getBaselineClassification();

	}

}
