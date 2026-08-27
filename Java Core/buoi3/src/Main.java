import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int mainChoice, subChoice;

        while (true) {
            System.out.println("\n========== MENU CHÍNH ==========");
            System.out.println("1. Exercise 1 - Datatype Casting");
            System.out.println("2. Exercise 2 - Default Value");
            System.out.println("3. Exercise 3 - Boxing & Unboxing");
            System.out.println("4. Exercise 4 - String Handling");
            System.out.println("5. Exercise 5 - Object's Method");
            System.out.println("0. Thoát");
            System.out.print("Chọn exercise (0-5): ");
            mainChoice = scanner.nextInt();
            scanner.nextLine();

            if (mainChoice == 0) {
                System.out.println("Cảm ơn bạn đã sử dụng chương trình. Tạm biệt!");
                scanner.close();
                return;
            }

            if (mainChoice < 1 || mainChoice > 5) {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                continue;
            }

            while (true) {
                System.out.println("\n--- Exercise " + mainChoice + " ---");
                System.out.println("Chọn câu hỏi cần thực thi:");
                switch (mainChoice) {
                    case 1:
                        System.out.println("1. Question 1");
                        System.out.println("2. Question 2");
                        System.out.println("3. Question 3");
                        System.out.println("4. Question 4");
                        System.out.println("0. Quay lại menu chính");
                        break;
                    case 2:
                        System.out.println("1. Question 1");
                        System.out.println("0. Quay lại menu chính");
                        break;
                    case 3:
                        System.out.println("1. Question 1");
                        System.out.println("2. Question 2");
                        System.out.println("3. Question 3");
                        System.out.println("0. Quay lại menu chính");
                        break;
                    case 4:
                        System.out.println("1. Question 1");
                        System.out.println("2. Question 2");
                        System.out.println("3. Question 3");
                        System.out.println("4. Question 4");
                        System.out.println("5. Question 5");
                        System.out.println("6. Question 6");
                        System.out.println("7. Question 7");
                        System.out.println("8. Question 8");
                        System.out.println("9. Question 9");
                        System.out.println("10. Question 10");
                        System.out.println("11. Question 11");
                        System.out.println("12. Question 12");
                        System.out.println("13. Question 13");
                        System.out.println("14. Question 14");
                        System.out.println("15. Question 15");
                        System.out.println("16. Question 16");
                        System.out.println("0. Quay lại menu chính");
                        break;
                    case 5:
                        System.out.println("1. Question 1");
                        System.out.println("2. Question 2");
                        System.out.println("3. Question 3");
                        System.out.println("4. Question 4");
                        System.out.println("5. Question 5");
                        System.out.println("6. Question 6");
                        System.out.println("7. Question 7");
                        System.out.println("0. Quay lại menu chính");
                        break;
                }

                System.out.print("Chọn câu hỏi (0 để quay lại): ");
                subChoice = scanner.nextInt();
                scanner.nextLine();
                if (subChoice == 0) {
                    break;
                }
                switch (mainChoice) {
                    case 1:
                        switch (subChoice) {
                            case 1:
                                Exercise1.question1();
                                break;
                            case 2:
                                Exercise1.question2();
                                break;
                            case 3:
                                Exercise1.question3();
                                break;
                            case 4:
                                Exercise1.question4(scanner);
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ.");
                        }
                        break;
                    case 2:
                        if (subChoice == 1) Exercise2.question1();
                        else System.out.println("Lựa chọn không hợp lệ.");
                        break;
                    case 3:
                        switch (subChoice) {
                            case 1:
                                Exercise3.question1();
                                break;
                            case 2:
                                Exercise3.question2();
                                break;
                            case 3:
                                Exercise3.question3();
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ.");
                        }
                        break;
                    case 4:
                        switch (subChoice) {
                            case 1:
                                Exercise4.question1(scanner);
                                break;
                            case 2:
                                Exercise4.question2(scanner);
                                break;
                            case 3:
                                Exercise4.question3(scanner);
                                break;
                            case 4:
                                Exercise4.question4(scanner);
                                break;
                            case 5:
                                Exercise4.question5(scanner);
                                break;
                            case 6:
                                Exercise4.question6(scanner);
                                break;
                            case 7:
                                Exercise4.question7(scanner);
                                break;
                            case 8:
                                Exercise4.question8();
                                break;
                            case 9:
                                Exercise4.question9();
                                break;
                            case 10:
                                Exercise4.question10(scanner);
                                break;
                            case 11:
                                Exercise4.question11(scanner);
                                break;
                            case 12:
                                Exercise4.question12(scanner);
                                break;
                            case 13:
                                Exercise4.question13(scanner);
                                break;
                            case 14:
                                Exercise4.question14(scanner);
                                break;
                            case 15:
                                Exercise4.question15(scanner);
                                break;
                            case 16:
                                Exercise4.question16(scanner);
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ.");
                        }
                        break;
                    case 5:
                        switch (subChoice) {
                            case 1:
                                Exercise5.question1();
                                break;
                            case 2:
                                Exercise5.question2();
                                break;
                            case 3:
                                Exercise5.question3();
                                break;
                            case 4:
                                Exercise5.question4();
                                break;
                            case 5:
                                Exercise5.question5();
                                break;
                            case 6:
                                Exercise5.question6();
                                break;
                            case 7:
                                Exercise5.question7();
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ.");
                        }
                        break;
                }
            }
        }
    }
}