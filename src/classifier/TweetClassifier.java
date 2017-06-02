package classifier;

import java.util.ArrayList;
import java.util.List;

public class TweetClassifier {
	
	private CreatePredictionFile predictionFileCreator;
	private WekaClassifier wekaClassifier;
	
	public TweetClassifier() throws Exception {
		this.predictionFileCreator = new CreatePredictionFile();
		this.wekaClassifier = new WekaClassifier();
	}

	public List<PredictionResult> classifyTweets(List<String> tweets) throws Exception {

		List<PredictionResult> prList = new ArrayList<>();
		
		predictionFileCreator.createFileARFFToPredict(tweets);

		for (int i=1; i<=tweets.size(); i++) {
			prList.add(wekaClassifier.getBaselineClassification(i));
		}

		return prList;

	}

}
