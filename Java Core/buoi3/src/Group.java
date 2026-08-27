import java.time.LocalDate;
import java.util.Arrays;

public class Group {
    int id;
    String name;
    Account creator;
    LocalDate createDate;

    Account[] accounts = new Account[]{};//  ds casc account trong group nay
}