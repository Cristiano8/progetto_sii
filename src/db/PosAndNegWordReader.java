package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PosAndNegWordReader {

	private static final String PATH_TO_FILE = "/home/federico/Documenti/ProgettoSII/pos-and-neg-words";

	public List<String> read() throws IOException {

		List<String> lines = new ArrayList<>();

		BufferedReader br = new BufferedReader(new FileReader(PATH_TO_FILE));

		try {
			String line = br.readLine();

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
