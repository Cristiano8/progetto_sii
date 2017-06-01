package classifier;

import java.util.ArrayList;
import java.util.List;

public class TweetClassifier {
	
	private CreatePredictionFile cpf;
	private WekaClassifier wc;
	
	public TweetClassifier() throws Exception {
		this.cpf = new CreatePredictionFile();
		this.wc = new WekaClassifier();
	}

	public List<PredictionResult> classifyTweets(List<String> tweets) throws Exception {

		List<PredictionResult> prList = new ArrayList<>();
		
		cpf.createFileARFFToPredict(tweets);

		for (int i=1; i<=tweets.size(); i++) {
			prList.add(wc.getBaselineClassification(i));
		}

		return prList;

	}

}
