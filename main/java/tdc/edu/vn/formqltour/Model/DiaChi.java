package tdc.edu.vn.formqltour.Model;

public class DiaChi {
    public String getMaKH2() {
        return maKH2;
    }

    public void setMaKH2(String maKH2) {
        this.maKH2 = maKH2;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "DiaChi{" +
                "maKH2='" + maKH2 + '\'' +
                ", tenKH='" + tenKH + '\'' +
                ", diaChi='" + diaChi + '\'' +
                '}';
    }


    String maKH2;
    String tenKH;
    String diaChi;
}
