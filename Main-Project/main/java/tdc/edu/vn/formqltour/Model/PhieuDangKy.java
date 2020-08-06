package tdc.edu.vn.formqltour.Model;

public class PhieuDangKy {

    String maPhieu;
    String ngayDangKy;
    String maKhachHang;

    public PhieuDangKy() {
        this.maPhieu = maPhieu;
        this.ngayDangKy = ngayDangKy;
        this.maKhachHang = maKhachHang;
    }
    @Override
    public String toString() {
        return "PhieuDangKy{" +
                "maPhieu='" + maPhieu + '\'' +
                ", ngayDangKy=" + ngayDangKy +
                ", maKhachHang='" + maKhachHang + '\'' +
                '}';
    }
    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getNgayDangKy() {
        return ngayDangKy;
    }

    public void setNgayDangKy(String ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }



}
