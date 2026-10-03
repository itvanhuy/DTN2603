package common;

public final class EmailValidator {
    private EmailValidator() {
    }

    public static boolean isValid(String email) {
        return email != null && email.matches(StringCommon.EMAIL_REGEX);
    }
}
