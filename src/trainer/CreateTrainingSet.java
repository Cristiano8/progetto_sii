package trainer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import util.Constants;

public class CreateTrainingSet {


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
			fileArff.write("\'" + ptweet + "\'" + "," + Constants.POSITIVE_CATEGORY);
			fileArff.write("\n");
		}
		
		for (String ntweet : negTweets) {
			fileArff.write("\'" + ntweet + "\'" + "," + Constants.NEGATIVE_CATEGORY);
			fileArff.write("\n");
		}
		
		fileArff.close();
		
	}

	
}
