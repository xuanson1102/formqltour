package tdc.edu.vn.formqltour.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tdc.edu.vn.formqltour.Model.DiaChi;

public class DBDiaChi {
    DBHelper dbHelper;
    public DBDiaChi(Context context) {
        dbHelper = new DBHelper((Context) context);
    }

    public void Them(DiaChi diaChi) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaKH2", diaChi.getMaKH2());
        values.put("TenKH", diaChi.getTenKH());
        values.put("DiaChi", diaChi.getDiaChi());
        db.insert("DiaChi", null, values);

    }

    public void Xoa(DiaChi diaChi) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql3 ="Delete from DiaChi where MaKH2 = '"+ diaChi.getMaKH2()+"'";
        db.execSQL(sql3);

    }

    public void Sua(DiaChi diaChi) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaKH2", diaChi.getMaKH2());
        values.put("TenKH", diaChi.getTenKH());
        values.put("DiaChi", diaChi.getDiaChi());

        db.update("DiaChi", values, "MaKH2 ='" + diaChi.getMaKH2() + "'", null);

    }

    public ArrayList<DiaChi> getDuLieu() {
        ArrayList<DiaChi> data = new ArrayList<>();
        String sql3 = "select * from DiaChi";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql3, null);
        try{
            cursor.moveToFirst();
            do {
               DiaChi diaChi = new DiaChi();
                diaChi.setMaKH2(cursor.getString(0));
                diaChi.setTenKH(cursor.getString(1));
                diaChi.setDiaChi(cursor.getString(2));
                data.add(diaChi);
            }while (cursor.moveToNext());
        }catch (Exception ex){

        }

        return data;

    }


}
