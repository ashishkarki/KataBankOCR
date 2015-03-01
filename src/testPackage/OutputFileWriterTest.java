package testPackage;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mainPackage.AccountNumberValidator;
import mainPackage.FileParser;
import mainPackage.OutputFileWriter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author ashish karki
 * @Description Tests the output to a text file
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(OutputFileWriter.class)
public class OutputFileWriterTest {
	private FileParser testFileParser;
	private AccountNumberValidator testAcNumValidator;
	private OutputFileWriter testOutputFileWriter;

	// file names to be used in this test
	private final String testInputFileName = "TestData.txt";
	private final String testOutputFileName = "TestOutput.txt";

	@Before
	public void setUp() throws Exception {
		testOutputFileWriter = new OutputFileWriter(testInputFileName,
				testOutputFileName);

		testFileParser = Mockito.mock(FileParser.class);
		testAcNumValidator = Mockito.mock(AccountNumberValidator.class);

		PowerMockito.whenNew(FileParser.class).withArguments(Mockito.any())
				.thenReturn(testFileParser);
		Mockito.when(testFileParser.performFileParsing()).thenReturn(
				getTestNumbers());

		Mockito.when(testAcNumValidator.isValidAccountNumber("457508000"))
				.thenReturn(true);
		Mockito.when(testAcNumValidator.isValidAccountNumber("664371495"))
				.thenReturn(false);

	}

	/**
	 * Test method for {@link mainPackage.OutputFileWriter#writeToFile} .
	 */
	@Test
	public void testWriteToFile() {
		testOutputFileWriter.writeToFile();

		/*
		 * Read from the output file and see if everything is good
		 */
		List<String> accountNumbers = readFileAndCreateList(testOutputFileName);
		assertEquals("457508000", accountNumbers.get(0));
		assertEquals("664371495 ERR", accountNumbers.get(1));
		assertEquals("6110??36 ILL", accountNumbers.get(2));
	}

	/**
	 * @return a sample of accounts (as specified in example) to check the
	 *         output
	 */
	private List<String> getTestNumbers() {
		List<String> accounts = new ArrayList<>();
		accounts.add("457508000");
		accounts.add("664371495");
		accounts.add("6110??36");

		return accounts;
	}

	/**
	 * @param outputFile
	 *            : name of output file
	 * @return list of strings (per line) read from the test-output-file
	 */
	private List<String> readFileAndCreateList(String outputFile) {
		List<String> result = new ArrayList<>();

		try {
			File inputFile = new File(outputFile);
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String line;

			while ((line = br.readLine()) != null) {
				result.add(line);
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

		return result;
	}
}
