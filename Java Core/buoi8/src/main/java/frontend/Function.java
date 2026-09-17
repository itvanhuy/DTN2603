package frontend;

import backend.controller.AccountController;
import entity.Account;
import entity.Department;
import entity.Position;
import entity.PositionName;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Function {
    private static final int MIN_LENGTH = 6;
    private static final int MAX_LENGTH = 100;
    private static final Pattern GMAIL_PATTERN =
            Pattern.compile("(?i)^[a-z0-9._%+-]+@gmail\\.com$");

    private final Scanner scanner = new Scanner(System.in);
    private final AccountController accountController = new AccountController();

    public void menu() {
        while (true) {
            System.out.println("\n=== QUAN LY ACCOUNT ===");
            System.out.println("1. Hien thi account");
            System.out.println("2. Tim account theo ID");
            System.out.println("3. Them account");
            System.out.println("4. Cap nhat username theo ID");
            System.out.println("5. Xoa account theo ID");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            switch (scanner.nextLine().trim()) {
                case "1":
                    accountController.displayAccountsAsTable();
                    break;
                case "2":
                    findAccount();
                    break;
                case "3":
                    addAccount();
                    break;
                case "4":
                    updateUsername();
                    break;
                case "5":
                    deleteAccount();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }

    private void addAccount() {
        Account account = new Account();
        account.setUsername(readUsername("Username: "));
        account.setEmail(readEmail("Email: "));
        account.setFullName(readFullName("Fullname: "));
        account.setDepartment(new Department(readPositiveId("Department ID: "), ""));
        account.setPosition(new Position(readPositiveId("Position ID: "), PositionName.DEV));

        if (accountController.addAccount(account)) {
            System.out.println("Them account thanh cong.");
        } else {
            System.out.println("Them that bai: username/email trung hoac department/position khong ton tai.");
        }
    }

    private void updateUsername() {
        int id = readPositiveId("Account ID: ");
        Account account = accountController.getAccountById(id);
        if (account == null) {
            System.out.println("Account khong ton tai.");
            return;
        }

        account.setUsername(readUsername("Username moi: "));
        if (accountController.updateAccount(account)) {
            System.out.println("Cap nhat username thanh cong.");
        } else {
            System.out.println("Cap nhat that bai: username bi trung hoac khong hop le.");
        }
    }

    private void deleteAccount() {
        int id = readPositiveId("Account ID: ");
        System.out.print("Xac nhan xoa (y/n): ");
        if (!scanner.nextLine().trim().equalsIgnoreCase("y")) {
            System.out.println("Da huy thao tac.");
            return;
        }

        if (accountController.deleteAccount(id)) {
            System.out.println("Xoa account thanh cong.");
        } else {
            System.out.println("Xoa that bai: account khong ton tai.");
        }
    }

    private void findAccount() {
        Account account = accountController.getAccountById(readPositiveId("Account ID: "));
        if (account == null) {
            System.out.println("Account khong ton tai.");
        } else {
            System.out.println(account);
        }
    }

    private String readUsername(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (isValidUsername(value)) {
                return value;
            }
            System.out.println("Username phai dai hon 6 va ngan hon 100 ky tu.");
        }
    }

    private String readEmail(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (isValidEmail(value)) {
                return value;
            }
            System.out.println("Email phai dai hon 6, ngan hon 100 ky tu va co dang example@gmail.com.");
        }
    }

    private String readFullName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (isValidFullName(value)) {
                return value;
            }
            System.out.println("Fullname phai dai hon 6 va ngan hon 100 ky tu.");
        }
    }

    private int readPositiveId(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value > 0) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
                // Continue asking until a positive integer is entered.
            }
            System.out.println("ID phai la so nguyen lon hon 0.");
        }
    }

    public static boolean isValidUsername(String username) {
        return hasValidLength(username);
    }

    public static boolean isValidEmail(String email) {
        return hasValidLength(email) && GMAIL_PATTERN.matcher(email.trim()).matches();
    }

    public static boolean isValidFullName(String fullName) {
        return hasValidLength(fullName);
    }

    private static boolean hasValidLength(String value) {
        if (value == null) {
            return false;
        }
        int length = value.trim().length();
        return length > MIN_LENGTH && length < MAX_LENGTH;
    }
}
