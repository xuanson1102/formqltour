package tdc.edu.vn.formqltour.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "QuanLyTour", null, 2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table LoaiTour( maTour text, loTrinh text , hanhTrinh text, giaTour text )";
        db.execSQL(sql);
        String sql2 = "create table PhieuDangKy( maPhieu text, ngayDangKy text , maKhachHang text )";
        db.execSQL(sql2);
        String sql3 = "create table DiaChi( maKH2 text, tenKH text , diaChi text )";
        db.execSQL(sql3);
        String sql4 = "create table NguoiThamGia( maTour text,  maPhieu text , soNguoi text )";
        db.execSQL(sql4);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
