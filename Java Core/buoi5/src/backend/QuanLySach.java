package backend;

import entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuanLySach {
    private List<TaiLieu> danhSachTaiLieu;
    private Scanner scanner;

    public QuanLySach() {
        this.danhSachTaiLieu = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // a) Thêm mới tài liệu
    public void themTaiLieu() {
        System.out.println("\n----- THÊM TÀI LIỆU MỚI -----");
        System.out.println("1. Thêm Sách");
        System.out.println("2. Thêm Tạp chí");
        System.out.println("3. Thêm Báo");
        System.out.print("Chọn loại tài liệu (1-3): ");
        int choice = Integer.parseInt(scanner.nextLine());

        System.out.print("Nhập mã tài liệu: ");
        String maTaiLieu = scanner.nextLine();

        if (timTaiLieuTheoMa(maTaiLieu) != null) {
            System.out.println("Mã tài liệu đã tồn tại! Vui lòng nhập mã khác.");
            return;
        }

        System.out.print("Nhập tên nhà xuất bản: ");
        String tenNXB = scanner.nextLine();
        System.out.print("Nhập số bản phát hành: ");
        int soBanPH = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                themSach(maTaiLieu, tenNXB, soBanPH);
                break;
            case 2:
                themTapChi(maTaiLieu, tenNXB, soBanPH);
                break;
            case 3:
                themBao(maTaiLieu, tenNXB, soBanPH);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    private void themSach(String maTaiLieu, String tenNXB, int soBanPH) {
        System.out.print("Nhập tên tác giả: ");
        String tenTacGia = scanner.nextLine();
        System.out.print("Nhập số trang: ");
        int soTrang = Integer.parseInt(scanner.nextLine());

        Sach sach = new Sach(maTaiLieu, tenNXB, soBanPH, tenTacGia, soTrang);
        danhSachTaiLieu.add(sach);
        System.out.println("Đã thêm sách thành công!");
    }

    private void themTapChi(String maTaiLieu, String tenNXB, int soBanPH) {
        System.out.print("Nhập số phát hành: ");
        int soPhatHanh = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập tháng phát hành (1-12): ");
        int thangPhatHanh = Integer.parseInt(scanner.nextLine());

        TapChi tapChi = new TapChi(maTaiLieu, tenNXB, soBanPH, soPhatHanh, thangPhatHanh);
        danhSachTaiLieu.add(tapChi);
        System.out.println("Đã thêm tạp chí thành công!");
    }

    private void themBao(String maTaiLieu, String tenNXB, int soBanPH) {
        System.out.print("Nhập ngày phát hành (dd/MM/yyyy): ");
        String ngayPhatHanh = scanner.nextLine();

        Bao bao = new Bao(maTaiLieu, tenNXB, soBanPH, ngayPhatHanh);
        danhSachTaiLieu.add(bao);
        System.out.println("Đã thêm báo thành công!");
    }

    // b) Xoá tài liệu theo mã
    public void xoaTaiLieuTheoMa() {
        System.out.print("\nNhập mã tài liệu cần xoá: ");
        String maTaiLieu = scanner.nextLine();

        TaiLieu taiLieu = timTaiLieuTheoMa(maTaiLieu);
        if (taiLieu != null) {
            danhSachTaiLieu.remove(taiLieu);
            System.out.println("Đã xoá tài liệu có mã: " + maTaiLieu);
        } else {
            System.out.println("Không tìm thấy tài liệu có mã: " + maTaiLieu);
        }
    }

    // c) Hiển thị thông tin tài liệu
    public void hienThiTatCaTaiLieu() {
        if (danhSachTaiLieu.isEmpty()) {
            System.out.println("\nDanh sách tài liệu trống!");
            return;
        }

        System.out.println("\n----- DANH SÁCH TÀI LIỆU -----");
        for (TaiLieu taiLieu : danhSachTaiLieu) {
            taiLieu.hienThiThongTin();
            System.out.println();
        }
    }

    // d) Tìm kiếm tài liệu theo loại
    public void timKiemTheoLoai() {
        if (danhSachTaiLieu.isEmpty()) {
            System.out.println("\nDanh sách tài liệu trống!");
            return;
        }

        System.out.println("\n----- TÌM KIẾM THEO LOẠI -----");
        System.out.println("1. Sách");
        System.out.println("2. Tạp chí");
        System.out.println("3. Báo");
        System.out.print("Chọn loại tài liệu (1-3): ");
        int choice = Integer.parseInt(scanner.nextLine());

        String loaiCanTim;
        switch (choice) {
            case 1:
                loaiCanTim = "Sách";
                break;
            case 2:
                loaiCanTim = "Tạp chí";
                break;
            case 3:
                loaiCanTim = "Báo";
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
                return;
        }

        boolean timThay = false;
        System.out.println("\n----- KẾT QUẢ TÌM KIẾM (" + loaiCanTim + ") -----");
        for (TaiLieu taiLieu : danhSachTaiLieu) {
            if (taiLieu.getLoaiTaiLieu().equals(loaiCanTim)) {
                taiLieu.hienThiThongTin();
                System.out.println();
                timThay = true;
            }
        }

        if (!timThay) {
            System.out.println("Không tìm thấy " + loaiCanTim + " nào trong danh sách!");
        }
    }

    private TaiLieu timTaiLieuTheoMa(String maTaiLieu) {
        for (TaiLieu taiLieu : danhSachTaiLieu) {
            if (taiLieu.getMaTaiLieu().equals(maTaiLieu)) {
                return taiLieu;
            }
        }
        return null;
    }
}