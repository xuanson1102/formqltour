package tdc.edu.vn.formqltour.Model;

public class DangKyTour {


    public String getGiaTour() {
        return giaTour;
    }

    public void setGiaTour(String giaTour) {
        this.giaTour = giaTour;
    }

    public String getMaTour() {
        return maTour;
    }

    public void setMaTour(String maTour) {
        this.maTour = maTour;
    }

    public String getLoTrinh() {
        return loTrinh;
    }

    public void setLoTrinh(String loTrinh) {
        this.loTrinh = loTrinh;
    }

    public String getHanhTrinh() {
        return hanhTrinh;
    }

    public void setHanhTrinh(String hanhTrinh) {
        this.hanhTrinh = hanhTrinh;
    }


    @Override
    public String toString() {
        return "DangKyTour{" +
                "maTour='" + maTour + '\'' +
                ", loTrinh='" + loTrinh + '\'' +
                ", hanhTrinh='" + hanhTrinh + '\'' +
                ", giaTour='" + giaTour + '\'' +
                '}';
    }

    String maTour;
    String loTrinh;
    String hanhTrinh;
    String giaTour;



}

