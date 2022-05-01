package app.domain.model;

import org.junit.Test;
import pt.isep.lei.esoft.auth.AuthFacade;
import org.junit.Before;

/**
 * Employee's Tests
 * 
 * @author Tomás Russo <1211288@isep.ipp.pt>
 */

public class EmployeeTest {
    AuthFacade authFacade;

    @Before
    public void setUp() {
        this.authFacade = new AuthFacade();
    }

    /**
     * Check that it is not possible to create an instance of the Employee class with null values.
     * 
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
	public void ensureNullIsNotAllowed() {
		Employee instance = new Employee(null, null, null, null, null, null);
	}

    /**
     * Check that it is not possible to create an instance of the Employee class with empty values.
     * 
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureEmptyIsNotAllowed() {
        Employee instance = new Employee("", "", "", "", "", "");
    }

    /**
     * Check that it is not possible to create an instance of the Employee class with an invalid email.
     * 
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
	public void ensureValidEmail() {
		String invalidEmail = "joana";
		
		Employee instance = new Employee("Joana Maria", "Av. da Liberdade", "+351916478865", invalidEmail, "30365258 4 ZZ0", "Nurse");
	}

    /**
     * Check that it is not possible to create an instance of the Employee class with an invalid phone number, which contains an invalid length.
     * 
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
	public void ensureValidPhoneNumberWithValidLength() {
		String invalidPhoneNumber = "+3519164788687897845";
		
		Employee instance = new Employee("Joana Maria", "Av. da Liberdade", invalidPhoneNumber, "joanamaria@gmail.com", "30365258 4 ZZ0", "Nurse");
	}

    /**
     * Check that it is not possible to create an instance of the Employee class with an invalid phone number, which contains letters.
     * 
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
	public void ensureValidPhoneNumberWithoutLetters() {
		String invalidPhoneNumber = "+3519164foe";
		
		Employee instance = new Employee("Joana Maria", "Av. da Liberdade", invalidPhoneNumber, "joanamaria@gmail.com", "30365258 4 ZZ0", "Nurse");
	}

    /**
     * 
     * Check that it is not possible to create an instance of the Employee class with an invalid Citizen Card number.
     * 
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
	public void ensureValidCCNumber() {
		String invalidCCNumber = "30325126512";
		
		Employee instance = new Employee("Joana Maria", "Av. da Liberdade", "+351916478865", "joanamaria@gmail.com", invalidCCNumber, "Nurse");
	}
}