package tdc.edu.vn.formqltour.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tdc.edu.vn.formqltour.Model.PhieuDangKy;

public class DBLoaiPDK {
    DBHelper dbHelper;
    public DBLoaiPDK(Context context) {
        dbHelper = new DBHelper((Context) context);
    }

    public void Them(PhieuDangKy phieuDangKy) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaPhieu", phieuDangKy.getMaPhieu());
        values.put("NgayDangKy",phieuDangKy.getNgayDangKy());
        values.put("MaKhachHang",phieuDangKy.getMaKhachHang());

        db.insert("PhieuDangKy", null, values);

    }

    public void Xoa(PhieuDangKy phieuDangKy) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql2 ="Delete from PhieuDangKy where MaPhieu = '"+ phieuDangKy.getMaPhieu()+"'";
        db.execSQL(sql2);

    }

    public void Sua(PhieuDangKy phieuDangKy) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaPhieu",phieuDangKy.getMaPhieu());
        values.put("NgayDangKy",phieuDangKy.getNgayDangKy());
        values.put("MaKhachHang", phieuDangKy.getMaKhachHang());
        db.update("PhieuDangKy", values, "MaPhieu ='" + phieuDangKy.getMaPhieu() + "'", null);

    }

    public ArrayList<PhieuDangKy> getDuLieu() {
        ArrayList<PhieuDangKy> data = new ArrayList<>();
        String sql2 = "select * from PhieuDangKy";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql2, null);
            try{

            cursor.moveToFirst();

            do {
                PhieuDangKy phieuDangKy = new PhieuDangKy();
                phieuDangKy.setMaPhieu(cursor.getString(0));
                phieuDangKy.setNgayDangKy(cursor.getString(1));
                phieuDangKy.setMaKhachHang(cursor.getString(2));
                data.add(phieuDangKy);
            }
            while (cursor.moveToNext());

        }
            catch (Exception ex){

            }
        return data;
    }

    }


