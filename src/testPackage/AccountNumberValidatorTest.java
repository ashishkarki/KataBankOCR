/**
 * 
 */
package testPackage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import mainPackage.AccountNumberValidator;

import org.junit.Before;
import org.junit.Test;

/**
 * @author ashish karki
 */
public class AccountNumberValidatorTest {
	private AccountNumberValidator numberValidator;

	@Before
	public void setUp() {
		numberValidator = new AccountNumberValidator();
	}

	/**
	 * Test method for
	 * {@link mainPackage.AccountNumberValidator#isValidAccountNumber(java.lang.String)}
	 * .
	 */
	@Test
	public void testValidateAccountNumber() {
		// test a valid account (from example)
		assertTrue("A valid account number",
				numberValidator.isValidAccountNumber("457508000"));

		// test an invalid account (from example)
		assertFalse("An invalid account number",
				numberValidator.isValidAccountNumber("664371495"));
	}

}
