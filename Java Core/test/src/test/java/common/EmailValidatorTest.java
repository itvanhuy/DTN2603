package common;

import junit.framework.TestCase;

public class EmailValidatorTest extends TestCase {
    public void testAcceptsLettersDigitsAndAllowedDomainPunctuation() {
        assertTrue(EmailValidator.isValid("User123@sub-domain.example9"));
    }

    public void testRejectsPunctuationInLocalPart() {
        assertFalse(EmailValidator.isValid("first.last@example.com"));
        assertFalse(EmailValidator.isValid("first+tag@example.com"));
    }

    public void testRequiresNonEmptyPartsAndSingleSeparator() {
        assertFalse(EmailValidator.isValid("@example.com"));
        assertFalse(EmailValidator.isValid("username@"));
        assertFalse(EmailValidator.isValid("user@@example.com"));
        assertFalse(EmailValidator.isValid(null));
    }
}
