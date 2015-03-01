package mainPackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author ashish karki
 * @Description Write the results to an output file with comments (ILL, ERR)
 */
public class OutputFileWriter {

	private final String inputFileName;
	private final String outputFileName;

	/**
	 * @param inputFileName
	 *            : input fileName
	 * 
	 * @param outputFileName
	 *            : output fileName
	 */
	public OutputFileWriter(final String inputFileName,
			final String outputFileName) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
	}

	/**
	 * Write to output file
	 */
	public File writeToFile() {
		FileParser fileParser = new FileParser(this.inputFileName);

		// Get the list of actual account numbers from input file
		List<String> actualAccountNumbers = fileParser.performFileParsing();

		// Checksum validator
		AccountNumberValidator acNumValidator = new AccountNumberValidator();
		File outputFile = null;

		try {
			outputFile = new File(this.outputFileName);
			if (!outputFile.exists()) {
				outputFile.createNewFile();
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

			// validate the results before writing and add comments
			for (String actualAccountNum : actualAccountNumbers) {
				if (actualAccountNum.contains("?")) {
					bw.write(actualAccountNum);
					bw.write(" ");
					bw.write("ILL");
					bw.write("\n");
				} else {
					if (acNumValidator.isValidAccountNumber(actualAccountNum)) {
						bw.write(actualAccountNum);
						bw.write("\n");
					} else {
						bw.write(actualAccountNum);
						bw.write(" ");
						bw.write("ERR");
						bw.write("\n");
					}
				}

			}

			bw.close();
		} catch (IOException ioe) {
			System.out.println("Error while writing file.Details:\n");
			ioe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error. Details:\n");
			e.printStackTrace();
		}

		return outputFile;
	}
}
