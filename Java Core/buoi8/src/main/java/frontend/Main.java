package frontend;

import backend.controller.AccountController;
import backend.controller.DepartmentController;
import backend.controller.PositionController;
import entity.Account;
import entity.Department;
import entity.Position;
import entity.PositionName;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final AccountController accountController = new AccountController();
    private static final DepartmentController departmentController = new DepartmentController();
    private static final PositionController positionController = new PositionController();

    public static void main(String[] args) {
        while (true) {
            showMainMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        accountMenu();
                        break;
                    case 2:
                        departmentMenu();
                        break;
                    case 3:
                        positionMenu();
                        break;
                    case 0:
                        System.out.println("\nCảm ơn bạn đã sử dụng chương trình!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("       QUẢN LÝ        ");
        System.out.println("=".repeat(60));
        System.out.println("1. Quản lý Account");
        System.out.println("2. Quản lý Department");
        System.out.println("3. Quản lý Position");
        System.out.println("0. Thoát");
        System.out.print("Chọn chức năng: ");
    }

    private static void accountMenu() {
        while (true) {
            System.out.println("\n" + "-".repeat(40));
            System.out.println("        QUẢN LÝ ACCOUNT");
            System.out.println("-".repeat(40));
            System.out.println("1. Hiển thị tất cả account");
            System.out.println("2. Tìm account theo ID");
            System.out.println("3. Thêm account mới");
            System.out.println("4. Cập nhật account");
            System.out.println("5. Xóa account");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        accountController.displayAccountsAsTable();
                        break;
                    case 2:
                        findAccountById();
                        break;
                    case 3:
                        addAccount();
                        break;
                    case 4:
                        updateAccount();
                        break;
                    case 5:
                        deleteAccount();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
            }
        }
    }

    private static void departmentMenu() {
        while (true) {
            System.out.println("\n" + "-".repeat(40));
            System.out.println("      QUẢN LÝ DEPARTMENT");
            System.out.println("-".repeat(40));
            System.out.println("1. Hiển thị tất cả department");
            System.out.println("2. Tìm department theo ID");
            System.out.println("3. Thêm department mới");
            System.out.println("4. Cập nhật department");
            System.out.println("5. Xóa department");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        departmentController.displayDepartmentsAsTable();
                        break;
                    case 2:
                        findDepartmentById();
                        break;
                    case 3:
                        addDepartment();
                        break;
                    case 4:
                        updateDepartment();
                        break;
                    case 5:
                        deleteDepartment();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
            }
        }
    }

    private static void positionMenu() {
        while (true) {
            System.out.println("\n" + "-".repeat(40));
            System.out.println("       QUẢN LÝ POSITION");
            System.out.println("-".repeat(40));
            System.out.println("1. Hiển thị tất cả position");
            System.out.println("2. Tìm position theo ID");
            System.out.println("3. Thêm position mới");
            System.out.println("4. Cập nhật position");
            System.out.println("5. Xóa position");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        positionController.displayPositionsAsTable();
                        break;
                    case 2:
                        findPositionById();
                        break;
                    case 3:
                        addPosition();
                        break;
                    case 4:
                        updatePosition();
                        break;
                    case 5:
                        deletePosition();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
            }
        }
    }

    private static void findAccountById() {
        System.out.print("Nhập Account ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Account account = accountController.getAccountById(id);

            if (account != null) {
                System.out.println("\nThông tin Account:");
                System.out.println("=".repeat(120));
                System.out.printf("| %-4s | %-20s | %-20s | %-25s | %-15s | %-15s | %-12s |%n",
                        "ID", "Email", "Username", "Fullname", "Department", "Position", "Create Date");
                System.out.println("=".repeat(120));
                System.out.println(account);
                System.out.println("=".repeat(120));
            } else {
                System.out.println("Không tìm thấy Account với ID = " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("ID phải là số nguyên!");
        }
    }

    private static void findDepartmentById() {
        System.out.print("Nhập Department ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Department department = departmentController.getDepartmentById(id);

            if (department != null) {
                System.out.println("\nThông tin Department:");
                System.out.println("=".repeat(40));
                System.out.printf("| %-4s | %-20s |%n", "ID", "Department Name");
                System.out.println("=".repeat(40));
                System.out.println(department);
                System.out.println("=".repeat(40));
            } else {
                System.out.println("Không tìm thấy Department với ID = " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("ID phải là số nguyên!");
        }
    }

    private static void findPositionById() {
        System.out.print("Nhập Position ID: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Position position = positionController.getPositionById(id);

            if (position != null) {
                System.out.println("\nThông tin Position:");
                System.out.println("=".repeat(40));
                System.out.printf("| %-4s | %-15s |%n", "ID", "Position Name");
                System.out.println("=".repeat(40));
                System.out.println(position);
                System.out.println("=".repeat(40));
            } else {
                System.out.println("Không tìm thấy Position với ID = " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("ID phải là số nguyên!");
        }
    }

    private static void addAccount() {
        System.out.println("\n=== THÊM ACCOUNT MỚI ===");
        Account account = new Account();

        System.out.print("Email: ");
        account.setEmail(scanner.nextLine());

        System.out.print("Username: ");
        account.setUsername(scanner.nextLine());

        System.out.print("Fullname: ");
        account.setFullName(scanner.nextLine());

        System.out.print("Department ID: ");
        int departmentId = Integer.parseInt(scanner.nextLine());
        account.setDepartment(new Department(departmentId, ""));

        System.out.print("Position ID: ");
        int positionId = Integer.parseInt(scanner.nextLine());
        account.setPosition(new Position(positionId, PositionName.DEV));
        account.setCreateDate(LocalDate.now());

        if (accountController.addAccount(account)) {
            System.out.println("Thêm Account thành công!");
        } else {
            System.out.println("Thêm Account thất bại! Vui lòng kiểm tra dữ liệu.");
        }
    }

    private static void addDepartment() {
        System.out.println("\n=== THÊM DEPARTMENT MỚI ===");
        System.out.print("Tên Department: ");
        String name = scanner.nextLine();

        Department department = new Department();
        department.setName(name.trim());

        if (departmentController.addDepartment(department)) {
            System.out.println("Thêm Department thành công!");
        } else {
            System.out.println("Thêm Department thất bại!");
        }
    }

    private static void addPosition() {
        System.out.println("\n=== THÊM POSITION MỚI ===");
        System.out.print("Tên Position (DEV, TEST, SCRUM_MASTER, PM): ");
        String name = scanner.nextLine();

        try {
            Position position = new Position();
            position.setName(PositionName.valueOf(name.trim().toUpperCase()));

            if (positionController.addPosition(position)) {
                System.out.println("Thêm Position thành công!");
            } else {
                System.out.println("Thêm Position thất bại!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Tên Position không hợp lệ! Chỉ chấp nhận: DEV, TEST, SCRUM_MASTER, PM");
        }
    }

    private static void updateAccount() {
        System.out.println("\n=== CẬP NHẬT ACCOUNT ===");
        System.out.print("Nhập Account ID cần cập nhật: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Account account = accountController.getAccountById(id);

            if (account == null) {
                System.out.println("Không tìm thấy Account với ID = " + id);
                return;
            }

            System.out.println("\nThông tin hiện tại:");
            accountController.displayAccountsAsTable();

            System.out.println("Nhập thông tin mới (bỏ trống nếu không đổi):");

            System.out.print("Email (" + account.getEmail() + "): ");
            String email = scanner.nextLine();
            if (!email.trim().isEmpty()) {
                account.setEmail(email.trim());
            }

            System.out.print("Username (" + account.getUsername() + "): ");
            String username = scanner.nextLine();
            if (!username.trim().isEmpty()) {
                account.setUsername(username.trim());
            }

            System.out.print("Fullname (" + account.getFullName() + "): ");
            String fullname = scanner.nextLine();
            if (!fullname.trim().isEmpty()) {
                account.setFullName(fullname.trim());
            }

            System.out.print("Department ID (" + account.getDepartment().getId() + "): ");
            String departmentId = scanner.nextLine();
            if (!departmentId.trim().isEmpty()) {
                account.setDepartment(new Department(Integer.parseInt(departmentId.trim()), ""));
            }

            System.out.print("Position ID (" + account.getPosition().getId() + "): ");
            String positionId = scanner.nextLine();
            if (!positionId.trim().isEmpty()) {
                account.setPosition(new Position(Integer.parseInt(positionId.trim()), PositionName.DEV));
            }

            if (accountController.updateAccount(account)) {
                System.out.println("Cập nhật Account thành công!");
            } else {
                System.out.println("Cập nhật Account thất bại!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Dữ liệu nhập không hợp lệ!");
        }
    }

    private static void updateDepartment() {
        System.out.println("\n=== CẬP NHẬT DEPARTMENT ===");
        System.out.print("Nhập Department ID cần cập nhật: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Department department = departmentController.getDepartmentById(id);

            if (department == null) {
                System.out.println("Không tìm thấy Department với ID = " + id);
                return;
            }

            System.out.print("Tên Department mới (" + department.getName() + "): ");
            String newName = scanner.nextLine();
            if (!newName.trim().isEmpty()) {
                department.setName(newName.trim());
            }

            if (departmentController.updateDepartment(department)) {
                System.out.println("Cập nhật Department thành công!");
            } else {
                System.out.println("Cập nhật Department thất bại!");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID phải là số nguyên!");
        }
    }

    private static void updatePosition() {
        System.out.println("\n=== CẬP NHẬT POSITION ===");
        System.out.print("Nhập Position ID cần cập nhật: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Position position = positionController.getPositionById(id);

            if (position == null) {
                System.out.println("Không tìm thấy Position với ID = " + id);
                return;
            }

            System.out.print("Tên Position mới (" + position.getName() + "): ");
            String newName = scanner.nextLine();
            try {
                if (!newName.trim().isEmpty()) {
                    position.setName(PositionName.valueOf(newName.trim().toUpperCase()));
                }

                if (positionController.updatePosition(position)) {
                    System.out.println("Cập nhật Position thành công!");
                } else {
                    System.out.println("Cập nhật Position thất bại!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Tên Position không hợp lệ! Chỉ chấp nhận: DEV, TEST, SCRUM_MASTER, PM");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID phải là số nguyên!");
        }
    }

    private static void deleteAccount() {
        System.out.print("Nhập Account ID cần xóa: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Bạn có chắc chắn muốn xóa Account ID = " + id + "? (y/n): ");
            String confirm = scanner.nextLine();

            if (!confirm.equalsIgnoreCase("y")) {
                System.out.println("Đã hủy thao tác xóa.");
                return;
            }

            if (accountController.deleteAccount(id)) {
                System.out.println("Xóa Account thành công!");
            } else {
                System.out.println("Xóa Account thất bại! Kiểm tra khóa ngoại hoặc ID không tồn tại.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID phải là số nguyên!");
        }
    }

    private static void deleteDepartment() {
        System.out.print("Nhập Department ID cần xóa: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Bạn có chắc chắn muốn xóa Department ID = " + id + "? (y/n): ");
            String confirm = scanner.nextLine();

            if (!confirm.equalsIgnoreCase("y")) {
                System.out.println("Đã hủy thao tác xóa.");
                return;
            }

            if (departmentController.deleteDepartment(id)) {
                System.out.println("Xóa Department thành công!");
            } else {
                System.out.println("Xóa Department thất bại! Kiểm tra khóa ngoại hoặc ID không tồn tại.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID phải là số nguyên!");
        }
    }

    private static void deletePosition() {
        System.out.print("Nhập Position ID cần xóa: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Bạn có chắc chắn muốn xóa Position ID = " + id + "? (y/n): ");
            String confirm = scanner.nextLine();

            if (!confirm.equalsIgnoreCase("y")) {
                System.out.println("Đã hủy thao tác xóa.");
                return;
            }

            if (positionController.deletePosition(id)) {
                System.out.println("Xóa Position thành công!");
            } else {
                System.out.println("Xóa Position thất bại! Kiểm tra khóa ngoại hoặc ID không tồn tại.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID phải là số nguyên!");
        }
    }
}
