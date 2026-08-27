import java.util.Scanner;

public class Exercise4 {

    public static void question1(Scanner sc) {
        System.out.print("Exercise 4 - Q1: Nhập xâu kí tự: ");
        String input = sc.nextLine().trim();
        String[] words = input.split("\\s+");
        System.out.println("Số từ: " + words.length);
        System.out.println();
    }

    public static void question2(Scanner sc) {
        System.out.print("Exercise 4 - Q2: Nhập s1: ");
        String s1 = sc.nextLine();
        System.out.print("Nhập s2: ");
        String s2 = sc.nextLine();
        System.out.println("Kết quả nối: " + s1 + s2);
        System.out.println();
    }

    public static void question3(Scanner sc) {
        System.out.print("Exercise 4 - Q3: Nhập tên: ");
        String name = sc.nextLine();
        if (!name.isEmpty()) {
            name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        }
        System.out.println("Tên sau khi viết hoa chữ cái đầu: " + name);
        System.out.println();
    }

    public static void question4(Scanner sc) {
        System.out.print("Exercise 4 - Q4: Nhập tên: ");
        String name = sc.nextLine();
        for (int i = 0; i < name.length(); i++) {
            System.out.println("Ký tự thứ " + (i + 1) + " là: " + name.charAt(i));
        }
        System.out.println();
    }

    public static void question5(Scanner sc) {
        System.out.print("Exercise 4 - Q5: Nhập họ: ");
        String ho = sc.nextLine();
        System.out.print("Nhập tên: ");
        String ten = sc.nextLine();
        System.out.println("Họ và tên đầy đủ: " + ho + " " + ten);
        System.out.println();
    }

    public static void question6(Scanner sc) {
        System.out.print("Exercise 4 - Q6: Nhập họ và tên đầy đủ: ");
        String full = sc.nextLine().trim();
        String[] parts = full.split("\\s+");
        if (parts.length == 3) {
            System.out.println("Họ là: " + parts[0]);
            System.out.println("Tên đệm là: " + parts[1]);
            System.out.println("Tên là: " + parts[2]);
        } else {
            System.out.println("Vui lòng nhập đúng 3 phần (họ, đệm, tên)");
        }
        System.out.println();
    }

    public static void question7(Scanner sc) {
        System.out.print("Exercise 4 - Q7: Nhập họ và tên (có dấu cách thừa): ");
        String raw = sc.nextLine();
        String normalized = raw.trim().replaceAll("\\s+", " ");
        String[] nameParts = normalized.split(" ");
        StringBuilder capitalized = new StringBuilder();
        for (String part : nameParts) {
            if (!part.isEmpty()) {
                capitalized.append(Character.toUpperCase(part.charAt(0))).append(part.substring(1).toLowerCase()).append(" ");
            }
        }
        System.out.println("Sau chuẩn hóa: " + capitalized.toString().trim());
        System.out.println();
    }

    public static void question8() {
        String[] groups = {"Java Basics", "Python", "Java Advanced", "C++"};
        System.out.print("Exercise 4 - Q8: Các group chứa 'Java': ");
        for (String g : groups) {
            if (g.contains("Java")) System.out.print(g + " ");
        }
        System.out.println("\n");
    }

    public static void question9() {
        String[] groups = {"Java Basics", "Python", "Java Advanced", "C++"};
        System.out.print("Exercise 4 - Q9: Các group chính xác 'Java': ");
        for (String g : groups) {
            if (g.equals("Java")) System.out.print(g + " ");
        }
        System.out.println("\n");
    }

    public static void question10(Scanner sc) {
        System.out.print("Exercise 4 - Q10: Nhập chuỗi 1: ");
        String s1 = sc.nextLine();
        System.out.print("Nhập chuỗi 2: ");
        String s2 = sc.nextLine();
        if (isReverse(s1, s2)) {
            System.out.println("OK");
        } else {
            System.out.println("KO");
        }
        System.out.println();
    }

    private static boolean isReverse(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(s2.length() - 1 - i)) return false;
        }
        return true;
    }

    public static void question11(Scanner sc) {
        System.out.print("Exercise 4 - Q11: Nhập chuỗi: ");
        String str = sc.nextLine();
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == 'a' || c == 'A') count++;
        }
        System.out.println("Số lần xuất hiện 'a': " + count);
        System.out.println();
    }

    public static void question12(Scanner sc) {
        System.out.print("Exercise 4 - Q12: Nhập chuỗi: ");
        String str = sc.nextLine();
        StringBuilder reversed = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed.append(str.charAt(i));
        }
        System.out.println("Chuỗi đảo ngược: " + reversed.toString());
        System.out.println();
    }

    public static void question13(Scanner sc) {
        System.out.print("Exercise 4 - Q13: Nhập chuỗi: ");
        String str = sc.nextLine();
        boolean noDigit = true;
        if (str != null) {
            for (char c : str.toCharArray()) {
                if (Character.isDigit(c)) {
                    noDigit = false;
                    break;
                }
            }
        } else {
            noDigit = false;
        }
        System.out.println(noDigit);
        System.out.println();
    }

    public static void question14(Scanner sc) {
        System.out.print("Exercise 4 - Q14: Nhập chuỗi: ");
        String str = sc.nextLine();
        System.out.print("Nhập ký tự cần thay: ");
        char oldChar = sc.next().charAt(0);
        System.out.print("Nhập ký tự thay thế: ");
        char newChar = sc.next().charAt(0);
        sc.nextLine(); // clear buffer
        System.out.println("Chuỗi sau thay: " + str.replace(oldChar, newChar));
        System.out.println();
    }

    public static void question15(Scanner sc) {
        System.out.print("Exercise 4 - Q15: Nhập câu (các từ cách nhau 1 dấu cách): ");
        String sentence = sc.nextLine().trim();
        String[] words = sentence.split(" ");
        StringBuilder reversed = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversed.append(words[i]);
            if (i > 0) reversed.append(" ");
        }
        System.out.println("Câu đảo ngược từ: " + reversed.toString());
        System.out.println();
    }

    public static void question16(Scanner sc) {
        System.out.print("Exercise 4 - Q16: Nhập chuỗi: ");
        String str = sc.nextLine();
        System.out.print("Nhập n (>=0): ");
        int n = sc.nextInt();
        sc.nextLine();
        if (n <= 0 || str.length() % n != 0) {
            System.out.println("KO");
        } else {
            int parts = str.length() / n;
            for (int i = 0; i < parts; i++) {
                System.out.println(str.substring(i * n, i * n + n));
            }
        }
        System.out.println();
    }
}