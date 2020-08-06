package tdc.edu.vn.formqltour.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tdc.edu.vn.formqltour.Model.DangKyTour;

public class DBLoaiTour {
    DBHelper dbHelper;
    public DBLoaiTour(Context context) {
        dbHelper = new DBHelper((Context) context);
    }

    public void Them(DangKyTour dangKyTour) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaTour", dangKyTour.getMaTour());
        values.put("LoTrinh", dangKyTour.getLoTrinh());
        values.put("HanhTrinh", dangKyTour.getHanhTrinh());
        values.put("GiaTour", dangKyTour.getGiaTour());
        db.insert("LoaiTour", null, values);

    }

    public void Xoa(DangKyTour dangKyTour) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql ="Delete from LoaiTour where MaTour= '"+ dangKyTour.getMaTour()+"'";
        db.execSQL(sql);

    }

    public void Sua(DangKyTour dangKyTour) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaTour", dangKyTour.getMaTour());
        values.put("LoTrinh", dangKyTour.getLoTrinh());
        values.put("HanhTrinh", dangKyTour.getHanhTrinh());
        values.put("GiaTour", dangKyTour.getGiaTour());
        db.update("DangKyTour", values, "MaTour ='" + dangKyTour.getMaTour() + "'", null);

    }

    public ArrayList<DangKyTour> getDuLieu() {
        ArrayList<DangKyTour> data = new ArrayList<>();
        String sql = "select * from LoaiTour";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try{
            cursor.moveToFirst();
            do {
                DangKyTour dangKyTour = new DangKyTour();
                dangKyTour.setMaTour(cursor.getString(0));
                dangKyTour.setLoTrinh(cursor.getString(1));
                dangKyTour.setHanhTrinh(cursor.getString(2));
                dangKyTour.setGiaTour(cursor.getString(3));
            data.add(dangKyTour);
            }while (cursor.moveToNext());
        }catch (Exception ex){

        }

        return data;

    }


}
