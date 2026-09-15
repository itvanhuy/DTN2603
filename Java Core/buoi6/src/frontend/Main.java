package frontend;

import untils.JDButils;
import backend.QLAccount;
import backend.QLDepartment;
import backend.QLPosition;
import entity.Account;
import entity.Department;
import entity.Position;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static QLAccount accountManager = new QLAccount();
    private static QLDepartment departmentManager = new QLDepartment();
    private static QLPosition positionManager = new QLPosition();

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
                    case 4:
                        displayAllData();
                        break;
                    case 0:
                        System.out.println("\nCam on ban da su dung chuong trinh!");
                        JDButils.closeConnection();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Lua chon khong hop le! Vui long chon lai.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("          QUAN LY HE THONG DTN2603 - OOP");
        System.out.println("=".repeat(60));
        System.out.println("1. Quan ly Account");
        System.out.println("2. Quan ly Department");
        System.out.println("3. Quan ly Position");
        System.out.println("4. Hien thi toan bo du lieu");
        System.out.println("0. Thoat");
        System.out.print("\nChon chuc nang: ");
    }

    private static void accountMenu() {
        while (true) {
            System.out.println("\n" + "-".repeat(40));
            System.out.println("        QUAN LY ACCOUNT");
            System.out.println("-".repeat(40));
            System.out.println("1. Hien thi tat ca Account");
            System.out.println("2. Tim Account theo ID");
            System.out.println("3. Them Account moi");
            System.out.println("4. Cap nhat Account");
            System.out.println("5. Xoa Account");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        accountManager.displayAccountsAsTable();
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
                        System.out.println("Lua chon khong hop le!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!");
            }
        }
    }

    private static void findAccountById() {
        System.out.print("Nhap Account ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        Account acc = accountManager.getAccountById(id);
        if (acc != null) {
            System.out.println("\nThong tin Account:");
            System.out.println("=".repeat(120));
            System.out.printf("| %-4s | %-25s | %-20s | %-25s | %-8s | %-8s | %-12s |%n",
                    "ID", "Email", "Username", "Fullname", "Dept ID", "Pos ID", "Create Date");
            System.out.println("=".repeat(120));
            System.out.println(acc);
            System.out.println("=".repeat(120));
        } else {
            System.out.println("Khong tim thay Account voi ID = " + id);
        }
    }

    private static void addAccount() {
        System.out.println("\n=== THEM ACCOUNT MOI ===");
        Account account = new Account();

        System.out.print("Email: ");
        account.setEmail(scanner.nextLine());

        System.out.print("Username: ");
        account.setUsername(scanner.nextLine());

        System.out.print("Fullname: ");
        account.setFullname(scanner.nextLine());

        System.out.print("Department ID: ");
        account.setDepartmentId(Integer.parseInt(scanner.nextLine()));

        System.out.print("Position ID: ");
        account.setPositionId(Integer.parseInt(scanner.nextLine()));

        if (accountManager.addAccount(account)) {
            System.out.println("Them Account thanh cong!");
        } else {
            System.out.println("Them Account that bai! Kiem tra lai du lieu.");
        }
    }

    private static void updateAccount() {
        System.out.println("\n=== CAP NHAT ACCOUNT ===");
        System.out.print("Nhap Account ID can cap nhat: ");
        int id = Integer.parseInt(scanner.nextLine());

        Account account = accountManager.getAccountById(id);
        if (account == null) {
            System.out.println("Khong tim thay Account voi ID = " + id);
            return;
        }

        System.out.println("\nThong tin hien tai:");
        accountManager.displayAccountsAsTable();

        System.out.println("\nNhap thong tin moi (de trong neu khong thay doi):");

        System.out.print("Email (" + account.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) account.setEmail(email);

        System.out.print("Username (" + account.getUsername() + "): ");
        String username = scanner.nextLine();
        if (!username.isEmpty()) account.setUsername(username);

        System.out.print("Fullname (" + account.getFullname() + "): ");
        String fullname = scanner.nextLine();
        if (!fullname.isEmpty()) account.setFullname(fullname);

        System.out.print("Department ID (" + account.getDepartmentId() + "): ");
        String deptId = scanner.nextLine();
        if (!deptId.isEmpty()) account.setDepartmentId(Integer.parseInt(deptId));

        System.out.print("Position ID (" + account.getPositionId() + "): ");
        String posId = scanner.nextLine();
        if (!posId.isEmpty()) account.setPositionId(Integer.parseInt(posId));

        if (accountManager.updateAccount(account)) {
            System.out.println("Cap nhat Account thanh cong!");
        } else {
            System.out.println("Cap nhat Account that bai!");
        }
    }

    private static void deleteAccount() {
        System.out.print("Nhap Account ID can xoa: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Ban co chac chan muon xoa Account ID = " + id + "? (y/n): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            if (accountManager.deleteAccount(id)) {
                System.out.println("Xoa Account thanh cong!");
            } else {
                System.out.println("Xoa Account that bai! Kiem tra rang buoc khoa ngoai.");
            }
        } else {
            System.out.println("Da huy thao tac xoa.");
        }
    }

    private static void departmentMenu() {
        while (true) {
            System.out.println("\n" + "-".repeat(40));
            System.out.println("        QUAN LY DEPARTMENT");
            System.out.println("-".repeat(40));
            System.out.println("1. Hien thi tat ca Department");
            System.out.println("2. Tim Department theo ID");
            System.out.println("3. Them Department moi");
            System.out.println("4. Cap nhat Department");
            System.out.println("5. Xoa Department");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        departmentManager.displayDepartmentsAsTable();
                        break;
                    case 2:
                        System.out.print("Nhap Department ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Department dept = departmentManager.getDepartmentById(id);
                        if (dept != null) {
                            System.out.println("\nThong tin Department:");
                            System.out.println("=".repeat(40));
                            System.out.printf("| %-4s | %-30s |%n", "ID", "Department Name");
                            System.out.println("=".repeat(40));
                            System.out.println(dept);
                            System.out.println("=".repeat(40));
                        } else {
                            System.out.println("Khong tim thay Department voi ID = " + id);
                        }
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
                        System.out.println("Lua chon khong hop le!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!");
            }
        }
    }

    private static void addDepartment() {
        System.out.println("\n=== THEM DEPARTMENT MOI ===");
        Department dept = new Department();
        System.out.print("Ten Department: ");
        dept.setDepartmentName(scanner.nextLine());

        if (departmentManager.addDepartment(dept)) {
            System.out.println("Them Department thanh cong!");
        } else {
            System.out.println("Them Department that bai!");
        }
    }

    private static void updateDepartment() {
        System.out.println("\n=== CAP NHAT DEPARTMENT ===");
        System.out.print("Nhap Department ID can cap nhat: ");
        int id = Integer.parseInt(scanner.nextLine());

        Department dept = departmentManager.getDepartmentById(id);
        if (dept == null) {
            System.out.println("Khong tim thay Department!");
            return;
        }

        System.out.println("Thong tin hien tai:");
        departmentManager.displayDepartmentsAsTable();

        System.out.print("Ten moi (" + dept.getDepartmentName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) dept.setDepartmentName(name);

        if (departmentManager.updateDepartment(dept)) {
            System.out.println("Cap nhat Department thanh cong!");
        } else {
            System.out.println("Cap nhat Department that bai!");
        }
    }

    private static void deleteDepartment() {
        System.out.print("Nhap Department ID can xoa: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Ban co chac chan muon xoa? (y/n): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            if (departmentManager.deleteDepartment(id)) {
                System.out.println("Xoa Department thanh cong!");
            } else {
                System.out.println("Xoa Department that bai! Kiem tra rang buoc khoa ngoai.");
            }
        } else {
            System.out.println("Da huy thao tac xoa.");
        }
    }

    private static void positionMenu() {
        while (true) {
            System.out.println("\n" + "-".repeat(40));
            System.out.println("        QUAN LY POSITION");
            System.out.println("-".repeat(40));
            System.out.println("1. Hien thi tat ca Position");
            System.out.println("2. Tim Position theo ID");
            System.out.println("3. Them Position moi");
            System.out.println("4. Cap nhat Position");
            System.out.println("5. Xoa Position");
            System.out.println("0. Quay lai");
            System.out.print("Chon: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        positionManager.displayPositionsAsTable();
                        break;
                    case 2:
                        System.out.print("Nhap Position ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Position pos = positionManager.getPositionById(id);
                        if (pos != null) {
                            System.out.println("\nThong tin Position:");
                            System.out.println("=".repeat(25));
                            System.out.printf("| %-4s | %-15s |%n", "ID", "Position Name");
                            System.out.println("=".repeat(25));
                            System.out.println(pos);
                            System.out.println("=".repeat(25));
                        } else {
                            System.out.println("Khong tim thay Position voi ID = " + id);
                        }
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
                        System.out.println("Lua chon khong hop le!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!");
            }
        }
    }

    private static void addPosition() {
        System.out.println("\n=== THEM POSITION MOI ===");
        Position pos = new Position();
        System.out.print("Ten Position (DEV/TEST/SCRUM_MASTER/PM): ");
        pos.setPositionName(scanner.nextLine().toUpperCase());

        if (positionManager.addPosition(pos)) {
            System.out.println("Them Position thanh cong!");
        } else {
            System.out.println("Them Position that bai!");
        }
    }

    private static void updatePosition() {
        System.out.println("\n=== CAP NHAT POSITION ===");
        System.out.print("Nhap Position ID can cap nhat: ");
        int id = Integer.parseInt(scanner.nextLine());

        Position pos = positionManager.getPositionById(id);
        if (pos == null) {
            System.out.println("Khong tim thay Position!");
            return;
        }

        System.out.println("Thong tin hien tai:");
        positionManager.displayPositionsAsTable();

        System.out.print("Ten moi (" + pos.getPositionName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) pos.setPositionName(name.toUpperCase());

        if (positionManager.updatePosition(pos)) {
            System.out.println("Cap nhat Position thanh cong!");
        } else {
            System.out.println("Cap nhat Position that bai!");
        }
    }

    private static void deletePosition() {
        System.out.print("Nhap Position ID can xoa: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Ban co chac chan muon xoa? (y/n): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            if (positionManager.deletePosition(id)) {
                System.out.println("Xoa Position thanh cong!");
            } else {
                System.out.println("Xoa Position that bai! Kiem tra rang buoc khoa ngoai.");
            }
        } else {
            System.out.println("Da huy thao tac xoa.");
        }
    }

    private static void displayAllData() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("        HIEN THI TOAN BO DU LIEU");
        System.out.println("=".repeat(60));

        System.out.println("\n1. DANH SACH ACCOUNT");
        accountManager.displayAccountsAsTable();

        System.out.println("\n2. DANH SACH DEPARTMENT");
        departmentManager.displayDepartmentsAsTable();

        System.out.println("\n3. DANH SACH POSITION");
        positionManager.displayPositionsAsTable();
    }
}