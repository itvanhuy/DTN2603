public class Exercise3 {

    public static void question1() {
        Integer salary = 5000;
        float floatSalary = salary.floatValue();
        System.out.printf("Exercise 3 - Question 1: Lương float: %.2f\n", floatSalary);
        System.out.println();
    }

    public static void question2() {
        String str = "1234567";
        int intValue = Integer.parseInt(str);
        System.out.println("Exercise 3 - Question 2: int từ String: " + intValue);
        System.out.println();
    }

    public static void question3() {
        Integer integerObj = Integer.valueOf("1234567");
        int intValue = integerObj; // auto-unboxing
        System.out.println("Exercise 3 - Question 3: int từ Integer: " + intValue);
        System.out.println();
    }
}