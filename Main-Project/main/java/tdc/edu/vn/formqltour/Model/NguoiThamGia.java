package tdc.edu.vn.formqltour.Model;

public class NguoiThamGia {
    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getMaTour() {
        return maTour;
    }

    public void setMaTour(String maTour) {
        this.maTour = maTour;
    }

    public String getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(String soNguoi) {
        this.soNguoi = soNguoi;
    }

    public NguoiThamGia() {
        this.maPhieu = maPhieu;
        this.maTour = maTour;
        this.soNguoi = soNguoi;
    }

    @Override
    public String toString() {
        return "NguoiThamGia{" +
                "maPhieu='" + maPhieu + '\'' +
                ", maTour='" + maTour + '\'' +
                ", soNguoi='" + soNguoi + '\'' +
                '}';
    }

    String maPhieu;
    String maTour;
    String soNguoi;
}
