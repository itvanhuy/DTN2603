package frontend;

import backend.controller.AccountController;
import backend.controller.DepartmentController;
import backend.controller.PositionController;
import common.StringCommon;
import entity.Account;
import entity.Department;
import entity.Position;
import entity.PositionName;

import java.util.Locale;
import java.util.Scanner;

public class Function {
    private static final int MIN_LENGTH = 6;
    private static final int MAX_LENGTH = 100;
    private final Scanner scanner = new Scanner(System.in);
    private final AccountController accountController = new AccountController();
    private final DepartmentController departmentController = new DepartmentController();
    private final PositionController positionController = new PositionController();

    public void menu() {
        while (true) {
            System.out.println("\n=== QUAN LY ACCOUNT ===");
            System.out.println("1. Hien thi account");
            System.out.println("2. Tim account theo ID");
            System.out.println("3. Them account");
            System.out.println("4. Cap nhat username theo ID");
            System.out.println("5. Xoa account theo ID");
            System.out.println("6. Import account tu file CSV");
            System.out.println("7. Quan ly Department");
            System.out.println("8. Quan ly Position");
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
                case "6":
                    importAccountsFromCsv();
                    break;
                case "7":
                    manageDepartment();
                    break;
                case "8":
                    managePosition();
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

    private void importAccountsFromCsv() {
        System.out.print("Nhap duong dan file CSV: ");
        String filePath = scanner.nextLine().trim();
        System.out.println(accountController.importCSV(filePath));
    }

    private void manageDepartment() {
        while (true) {
            System.out.println("\n=== QUAN LY DEPARTMENT ===");
            System.out.println("1. Hien thi department");
            System.out.println("2. Tim department theo ID");
            System.out.println("3. Them department");
            System.out.println("4. Cap nhat department theo ID");
            System.out.println("5. Xoa department theo ID");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");

            switch (scanner.nextLine().trim()) {
                case "1":
                    departmentController.displayDepartmentsAsTable();
                    break;
                case "2":
                    findDepartment();
                    break;
                case "3":
                    addDepartment();
                    break;
                case "4":
                    updateDepartment();
                    break;
                case "5":
                    deleteDepartment();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }

    private void managePosition() {
        while (true) {
            System.out.println("\n=== QUAN LY POSITION ===");
            System.out.println("1. Hien thi position");
            System.out.println("2. Tim position theo ID");
            System.out.println("3. Them position");
            System.out.println("4. Cap nhat position theo ID");
            System.out.println("5. Xoa position theo ID");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");

            switch (scanner.nextLine().trim()) {
                case "1":
                    positionController.displayPositionsAsTable();
                    break;
                case "2":
                    findPosition();
                    break;
                case "3":
                    addPosition();
                    break;
                case "4":
                    updatePosition();
                    break;
                case "5":
                    deletePosition();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }

    private void addDepartment() {
        Department department = new Department();
        department.setName(readDepartmentName("Department name: "));
        if (departmentController.addDepartment(department)) {
            System.out.println("Them department thanh cong.");
        } else {
            System.out.println("Them department that bai: ten bi trung hoac khong hop le.");
        }
    }

    private void updateDepartment() {
        int id = readPositiveId("Department ID: ");
        Department department = departmentController.getDepartmentById(id);
        if (department == null) {
            System.out.println("Department khong ton tai.");
            return;
        }
        department.setName(readDepartmentName("Department name moi: "));
        if (departmentController.updateDepartment(department)) {
            System.out.println("Cap nhat department thanh cong.");
        } else {
            System.out.println("Cap nhat department that bai: ten bi trung hoac khong hop le.");
        }
    }

    private void deleteDepartment() {
        int id = readPositiveId("Department ID: ");
        System.out.print("Xac nhan xoa (y/n): ");
        if (!scanner.nextLine().trim().equalsIgnoreCase("y")) {
            System.out.println("Da huy thao tac.");
            return;
        }
        if (departmentController.deleteDepartment(id)) {
            System.out.println("Xoa department thanh cong.");
        } else {
            System.out.println("Xoa department that bai: department khong ton tai.");
        }
    }

    private void findDepartment() {
        Department department = departmentController.getDepartmentById(readPositiveId("Department ID: "));
        if (department == null) {
            System.out.println("Department khong ton tai.");
        } else {
            System.out.println(department);
        }
    }

    private void addPosition() {
        Position position = new Position();
        position.setName(readPositionName("Position name: "));
        if (positionController.addPosition(position)) {
            System.out.println("Them position thanh cong.");
        } else {
            System.out.println("Them position that bai: vi tri bi trung hoac khong hop le.");
        }
    }

    private void updatePosition() {
        int id = readPositiveId("Position ID: ");
        Position position = positionController.getPositionById(id);
        if (position == null) {
            System.out.println("Position khong ton tai.");
            return;
        }
        position.setName(readPositionName("Position name moi: "));
        if (positionController.updatePosition(position)) {
            System.out.println("Cap nhat position thanh cong.");
        } else {
            System.out.println("Cap nhat position that bai: vi tri bi trung hoac khong hop le.");
        }
    }

    private void deletePosition() {
        int id = readPositiveId("Position ID: ");
        System.out.print("Xac nhan xoa (y/n): ");
        if (!scanner.nextLine().trim().equalsIgnoreCase("y")) {
            System.out.println("Da huy thao tac.");
            return;
        }
        if (positionController.deletePosition(id)) {
            System.out.println("Xoa position thanh cong.");
        } else {
            System.out.println("Xoa position that bai: position khong ton tai.");
        }
    }

    private void findPosition() {
        Position position = positionController.getPositionById(readPositiveId("Position ID: "));
        if (position == null) {
            System.out.println("Position khong ton tai.");
        } else {
            System.out.println(position);
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

    private String readDepartmentName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (value != null && !value.isEmpty() && value.length() >= 2 && value.length() <= 100) {
                return value;
            }
            System.out.println("Department name phai tu 2 den 100 ky tu.");
        }
    }

    private PositionName readPositionName(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (value == null || value.isEmpty()) {
                System.out.println("Position name khong duoc de trong.");
                continue;
            }
            try {
                String normalized = value.replace('-', '_').replace(' ', '_').toUpperCase(Locale.ROOT);
                if (normalized.equals("SCRUMMASTER")) {
                    normalized = "SCRUM_MASTER";
                }
                return PositionName.valueOf(normalized);
            } catch (IllegalArgumentException e) {
                System.out.println("Position name phai la mot trong: DEV, TEST, PM, SCRUM_MASTER.");
            }
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
        return hasValidLength(email) && email.trim().matches(StringCommon.EMAIL_REGEX);
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
