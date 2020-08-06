package tdc.edu.vn.formqltour.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import tdc.edu.vn.formqltour.Model.NguoiThamGia;

public class DBNguoiThamGia {
    DBHelper dbHelper;
    public DBNguoiThamGia(Context context) {
        dbHelper = new DBHelper((Context) context);
    }

    public void Them(NguoiThamGia nguoiThamGia) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaTour",nguoiThamGia.getMaTour());
        values.put("MaPhieu",nguoiThamGia.getMaPhieu());
        values.put("SoNguoi",nguoiThamGia.getSoNguoi());
        db.insert("NguoiThamGia", null, values);

    }

    public void Xoa(NguoiThamGia nguoiThamGia) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql4 ="Delete from NguoiThamGia where MaTour= '"+nguoiThamGia.getMaTour()+"'";
        db.execSQL(sql4);

    }

    public void Sua(NguoiThamGia nguoiThamGia) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaTour",nguoiThamGia.getMaTour());
        values.put("MaPhieu",nguoiThamGia.getMaPhieu());
        values.put("SoNguoi", nguoiThamGia.getSoNguoi());
        db.update("NguoiThamGia", values, "MaTour ='" + nguoiThamGia.getMaTour() + "'", null);

    }

    public ArrayList<NguoiThamGia> getDuLieu() {
        ArrayList<NguoiThamGia> data = new ArrayList<>();
        String sql4 = "select * from NguoiThamGia";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql4, null);
        try{
            cursor.moveToFirst();
            do {
                NguoiThamGia nguoiThamGia = new NguoiThamGia();
                nguoiThamGia.setMaTour(cursor.getString(0));
                nguoiThamGia.setMaPhieu(cursor.getString(1));
                nguoiThamGia.setSoNguoi(cursor.getString(2));
                data.add(nguoiThamGia);
            }while (cursor.moveToNext());
        }catch (Exception ex){

        }

        return data;

    }
}
