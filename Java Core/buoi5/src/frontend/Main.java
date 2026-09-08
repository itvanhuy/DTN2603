package frontend;

import backend.QuanLySach;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        QuanLySach quanLySach = new QuanLySach();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n========== HỆ THỐNG QUẢN LÝ THƯ VIỆN ==========");
            System.out.println("1. Thêm mới tài liệu");
            System.out.println("2. Xoá tài liệu theo mã");
            System.out.println("3. Hiển thị tất cả tài liệu");
            System.out.println("4. Tìm kiếm tài liệu theo loại");
            System.out.println("5. Thoát chương trình");
            System.out.println("=============================================");
            System.out.print("Chọn chức năng (1-5): ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    quanLySach.themTaiLieu();
                    break;
                case 2:
                    quanLySach.xoaTaiLieuTheoMa();
                    break;
                case 3:
                    quanLySach.hienThiTatCaTaiLieu();
                    break;
                case 4:
                    quanLySach.timKiemTheoLoai();
                    break;
                case 5:
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
            }
        } while (choice != 5);

        scanner.close();
    }
}