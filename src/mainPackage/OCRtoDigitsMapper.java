package mainPackage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ashish karki
 * @Description Maps a digit's OCR representation to its actual form
 */
public class OCRtoDigitsMapper {
	private final Map<String, String> ocrToDigitsMap = new HashMap<>();

	/**
	 * Constructor
	 */
	public OCRtoDigitsMapper() {
		ocrToDigitsMap.put(" _ | ||_|", "0");
		ocrToDigitsMap.put("     |  |", "1");
		ocrToDigitsMap.put(" _  _||_ ", "2");
		ocrToDigitsMap.put(" _  _| _|", "3");
		ocrToDigitsMap.put("   |_|  |", "4");
		ocrToDigitsMap.put(" _ |_  _|", "5");
		ocrToDigitsMap.put(" _ |_ |_|", "6");
		ocrToDigitsMap.put(" _   |  |", "7");
		ocrToDigitsMap.put(" _ |_||_|", "8");
		ocrToDigitsMap.put(" _ |_| _|", "9");
	}

	/**
	 * @param ocrDigit
	 * @return Actual representation of this OCR digit
	 */
	public String getActualDigit(String ocrDigit) {
		return ocrToDigitsMap.get(ocrDigit);
	}
}
