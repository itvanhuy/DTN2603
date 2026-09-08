package entity;

public class Bao extends TaiLieu {
    private String ngayPhatHanh;

    public Bao() {
    }

    public Bao(String maTaiLieu, String tenNhaXuatBan, int soBanPhatHanh,
               String ngayPhatHanh) {
        super(maTaiLieu, tenNhaXuatBan, soBanPhatHanh);
        this.ngayPhatHanh = ngayPhatHanh;
    }

    public String getNgayPhatHanh() {
        return ngayPhatHanh;
    }

    public void setNgayPhatHanh(String ngayPhatHanh) {
        this.ngayPhatHanh = ngayPhatHanh;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("===== BÁO =====");
        System.out.println("Mã tài liệu: " + getMaTaiLieu());
        System.out.println("Tên NXB: " + getTenNhaXuatBan());
        System.out.println("Số bản phát hành: " + getSoBanPhatHanh());
        System.out.println("Ngày phát hành: " + ngayPhatHanh);
        System.out.println("===============");
    }

    @Override
    public String getLoaiTaiLieu() {
        return "Báo";
    }
}