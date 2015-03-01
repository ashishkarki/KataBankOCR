package mainPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ashish karki
 * @Description Read from input file and create actual numerical values list
 * 
 */
public class FileParser {
	/**
	 * Each account number has nine digits
	 */
	private final int NUM_OF_DIGITS = 9;
	/**
	 * Each digit in OCR form takes up-to three characters length
	 */
	private final int DIGIT_LENGTH = 3;

	/**
	 * Stores actual account numbers from OCR encoded file (as a list of
	 * Strings)
	 */
	private final List<String> actualAccountNumbers = new ArrayList<>();

	/**
	 * The file to be parsed.
	 */
	private final String fileName;

	/**
	 * @param fileName
	 *            : name of the file with OCR inputs
	 */
	public FileParser(final String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return A list containing containing corresponding actual digits (all in
	 *         string format)
	 */
	public List<String> performFileParsing() {
		try {
			File inputFile = new File(this.fileName);
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String line;

			/**
			 * For each entry, this is a collection of NINE strings representing
			 * each digit within an account number.
			 */
			StringBuilder digitsInActualFormSB = new StringBuilder();

			/**
			 * Used to determine if we started a new entry. Each entry is 3
			 * lines plus an empty line.
			 */
			int lineCounter = 0;

			/**
			 * Map OCR digit to Actual digit
			 */
			OCRtoDigitsMapper ocrToDigitsMapper = new OCRtoDigitsMapper();

			while ((line = br.readLine()) != null) {
				if (lineCounter == 4) {
					// Skip the fourth line of each entry
					lineCounter = 0;

					actualAccountNumbers.add(digitsInActualFormSB.toString());
					digitsInActualFormSB = new StringBuilder();

					continue;
				} else {
					// read each line of an entry
					lineCounter++;
					String line1 = line;
					lineCounter++;
					String line2 = br.readLine();
					lineCounter++;
					String line3 = br.readLine();
					lineCounter++;

					for (int i = 0; i < NUM_OF_DIGITS; i++) {
						String ocrDigit = buildOCRDigitRepresentation(i, line1,
								line2, line3);
						String actualDigit = ocrToDigitsMapper
								.getActualDigit(ocrDigit);
						// Replace invalid digit with "?" symbol
						actualDigit = (null == actualDigit) ? "?" : actualDigit;
						digitsInActualFormSB.append(actualDigit);
					}

				}

				// This is just to view the output from main()
				System.out.println(digitsInActualFormSB.toString());
				System.out.println("\n***************************");
			}

			br.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("File not found. Re-check file name.Details:\n");
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			System.out.println("Error while reading file.Details:\n");
			ioe.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error. Details:\n");
			e.printStackTrace();
		}

		return actualAccountNumbers;
	}

	/**
	 * @param index
	 *            : the digit which is being parsed currently
	 * @param line1
	 * @param line2
	 * @param line3
	 * @return String representation of current digit (of the current entry)
	 */
	private String buildOCRDigitRepresentation(int index, String line1,
			String line2, String line3) {
		String curDigitLine1 = line1.substring(index * DIGIT_LENGTH, index
				* DIGIT_LENGTH + DIGIT_LENGTH);
		String curDigitLine2 = line2.substring(index * DIGIT_LENGTH, index
				* DIGIT_LENGTH + DIGIT_LENGTH);
		String curDigitLine3 = line3.substring(index * DIGIT_LENGTH, index
				* DIGIT_LENGTH + DIGIT_LENGTH);

		return curDigitLine1 + curDigitLine2 + curDigitLine3;
	}

	/**
	 * @param args
	 *            TODO ONLY TO VIEW OUTPUT FOR THIS CLASS. SHOULD BE REPLACED BY
	 *            A TEST.
	 */
	public static void main(String[] args) {
		FileParser fp = new FileParser("ActualData.txt");
		fp.performFileParsing();
	}

}
