package entity;

public class TapChi extends TaiLieu {
    private int soPhatHanh;
    private int thangPhatHanh;

    public TapChi() {
    }

    public TapChi(String maTaiLieu, String tenNhaXuatBan, int soBanPhatHanh,
                  int soPhatHanh, int thangPhatHanh) {
        super(maTaiLieu, tenNhaXuatBan, soBanPhatHanh);
        this.soPhatHanh = soPhatHanh;
        this.thangPhatHanh = thangPhatHanh;
    }

    public int getSoPhatHanh() {
        return soPhatHanh;
    }

    public void setSoPhatHanh(int soPhatHanh) {
        this.soPhatHanh = soPhatHanh;
    }

    public int getThangPhatHanh() {
        return thangPhatHanh;
    }

    public void setThangPhatHanh(int thangPhatHanh) {
        this.thangPhatHanh = thangPhatHanh;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("===== TẠP CHÍ =====");
        System.out.println("Mã tài liệu: " + getMaTaiLieu());
        System.out.println("Tên NXB: " + getTenNhaXuatBan());
        System.out.println("Số bản phát hành: " + getSoBanPhatHanh());
        System.out.println("Số phát hành: " + soPhatHanh);
        System.out.println("Tháng phát hành: " + thangPhatHanh);
        System.out.println("==================");
    }

    @Override
    public String getLoaiTaiLieu() {
        return "Tạp chí";
    }
}