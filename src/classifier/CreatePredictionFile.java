package classifier;

import java.io.FileWriter;
import java.util.List;

public class CreatePredictionFile {


	public void createFileARFFToPredict(List<String> tweets) throws Exception {
		
		String prediction = "predict/tweets.arff";
		FileWriter fileArff = new FileWriter(prediction);
		fileArff.write("@relation isTweetTarget");	
		fileArff.write("\n");
		fileArff.write("@attribute tweet string");
		fileArff.write("\n");
		fileArff.write("@attribute class {positive, negative}");
		fileArff.write("\n");
		fileArff.write("@data");
		fileArff.write("\n");
		
		for (String t : tweets) {
			fileArff.write("\'" + t + "\'" + "," + "?");
			fileArff.write("\n");
		}
		
		fileArff.close();
		
//		for (String t : tweets) {
//			createDatasetCopy(t, directory + "/tweet-" + cont);
//			cont++;
//		}
	}

}
