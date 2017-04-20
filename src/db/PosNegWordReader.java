package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PosNegWordReader {

	private static final String PATH_TO_FILE = "/home/federico/Documenti/ProgettoSII/pos-and-neg-words";
	
	public List<String> getFeaturesByCategory(String category) throws IOException {
		List<String> features = new ArrayList<>();
		
		List<String> liwcFile = this.read();
		
		for (String line : liwcFile) {
			String splitLine[] = line.split("\\s");
			String word = splitLine[0];
			if (category.equals(splitLine[1]))
				features.add(word);
		}
		
		return features;
	}

	public List<String> read() throws IOException {

		List<String> lines = new ArrayList<>();

		BufferedReader br = new BufferedReader(new FileReader(PATH_TO_FILE));

		String line = "";
		try {
			while (line != null) {
				line = br.readLine();
				if (line != null) {
					lines.add(line);
				}
			}
		} finally {
			br.close();
		}

		return lines;
	}


}
