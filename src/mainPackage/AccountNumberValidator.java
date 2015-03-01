package mainPackage;

/**
 * @author ashish karki
 * @Descrition Validate if the checksum of an account is valid as per the rules
 *             specified.
 * @Expression checksum calculation is (d1+2*d2+3*d3 +..+9*d9) mod 11 = 0 is
 *             valid (where is d1 is the rightmost digit and d9 the leftmost)
 */
public class AccountNumberValidator {

	/**
	 * @param accountNumber
	 *            : actual numeric account number
	 * @return determine if this account number validates to the checksum
	 *         described in above expression.
	 */
	public boolean isValidAccountNumber(String accountNumber) {
		char[] accountDigits = accountNumber.toCharArray();
		int total = 0;
		int n = accountDigits.length;

		for (int digitIndex = 0; digitIndex < n; digitIndex++) {
			total += Character.getNumericValue(accountDigits[digitIndex])
					* (n - digitIndex);
		}

		return (total % 11) == 0;
	}
}
