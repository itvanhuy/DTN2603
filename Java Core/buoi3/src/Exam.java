import java.time.LocalDate;
import java.util.Arrays;

public class Exam {
    int id;
    String code;
    String title;
    CategoryQuestion category;
    int duration;
    Account creator;
    LocalDate createDate;
    Question[] questions;
}