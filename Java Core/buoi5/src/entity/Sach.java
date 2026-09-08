package entity;

public class Sach extends TaiLieu {
    private String tenTacGia;
    private int soTrang;

    public Sach() {
    }

    public Sach(String maTaiLieu, String tenNhaXuatBan, int soBanPhatHanh,
                String tenTacGia, int soTrang) {
        super(maTaiLieu, tenNhaXuatBan, soBanPhatHanh);
        this.tenTacGia = tenTacGia;
        this.soTrang = soTrang;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("===== SÁCH =====");
        System.out.println("Mã tài liệu: " + getMaTaiLieu());
        System.out.println("Tên NXB: " + getTenNhaXuatBan());
        System.out.println("Số bản phát hành: " + getSoBanPhatHanh());
        System.out.println("Tên tác giả: " + tenTacGia);
        System.out.println("Số trang: " + soTrang);
        System.out.println("================");
    }

    @Override
    public String getLoaiTaiLieu() {
        return "Sách";
    }
}