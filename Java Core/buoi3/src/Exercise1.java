import java.util.Scanner;

public class Exercise1 {

    public static void question1() {
        float salary1 = 5240.5f;
        float salary2 = 10970.055f;
        int rounded1 = Math.round(salary1);
        int rounded2 = Math.round(salary2);
        System.out.println("Exercise 1 - Question 1:");
        System.out.println("Lương Account 1 (làm tròn): " + rounded1);
        System.out.println("Lương Account 2 (làm tròn): " + rounded2);
        System.out.println();
    }

    public static void question2() {
        int randomNumber = (int) (Math.random() * 90000) + 10000;
        System.out.println("Exercise 1 - Question 2:");
        System.out.println("Số ngẫu nhiên 5 chữ số: " + randomNumber);
        System.out.println();
    }

    public static void question3() {
        int randomNumber = (int) (Math.random() * 90000) + 10000;
        System.out.println("Exercise 1 - Question 3:");
        String numStr = String.valueOf(randomNumber);
        String lastTwoStr = numStr.substring(numStr.length() - 2);
        System.out.println("2 số cuối (String): " + lastTwoStr);
        int lastTwoInt = randomNumber % 100;
        System.out.println("2 số cuối (int): " + lastTwoInt);
        System.out.println();
    }

    public static void question4(Scanner sc) {
        System.out.println("Exercise 1 - Question 4:");
        System.out.print("Nhập số nguyên a: ");
        int a = sc.nextInt();
        System.out.print("Nhập số nguyên b: ");
        int b = sc.nextInt();
        sc.nextLine(); // xóa bộ đệm
        double result = divide(a, b);
        System.out.println("Thương của " + a + " và " + b + " là: " + result);
        System.out.println();
    }

    private static double divide(int a, int b) {
        if (b == 0) throw new ArithmeticException("Không thể chia cho 0");
        return (double) a / b;
    }
}