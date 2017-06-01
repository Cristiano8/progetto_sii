package trainer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CreateTrainingSet {

	private static final String POSITIVE_CATEGORY = "positive";
	private static final String NEGATIVE_CATEGORY = "negative";

	public void createWekaFileForTrain(List<String> posTweets, List<String> negTweets) throws IOException {
		
		String fileName = "train/tweet-train.arff";
		FileWriter fileArff = new FileWriter(fileName);
		fileArff.write("@relation isTarget");	
		fileArff.write("\n");
		fileArff.write("@attribute article string");
		fileArff.write("\n");
		fileArff.write("@attribute class {positive, negative}");
		fileArff.write("\n");
		fileArff.write("@data");
		fileArff.write("\n");
		
		for (String ptweet : posTweets) {
			fileArff.write("\'" + ptweet + "\'" + "," + POSITIVE_CATEGORY);
			fileArff.write("\n");
		}
		
		for (String ntweet : negTweets) {
			fileArff.write("\'" + ntweet + "\'" + "," + NEGATIVE_CATEGORY);
			fileArff.write("\n");
		}
		
		fileArff.close();
		
	}

	
}
