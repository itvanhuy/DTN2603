package common;

public final class StringCommon {
    public static final String EMAIL_REGEX = "(?i)^[a-z0-9._%+-]+@gmail\\.com$";
    public static final String NUMBER_REGEX = "[0-9]+";
    public static final String YYYY_MM_DD_REGEX = "^\\d{4}-\\d{2}-\\d{2}$";

    private StringCommon() {
    }
}
