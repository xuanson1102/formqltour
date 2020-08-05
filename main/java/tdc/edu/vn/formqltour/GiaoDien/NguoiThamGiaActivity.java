package tdc.edu.vn.formqltour.GiaoDien;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import tdc.edu.vn.formqltour.Adapter.Adapter_DangKyTour;
import tdc.edu.vn.formqltour.Adapter.Adapter_NguoiThamGia;
import tdc.edu.vn.formqltour.Database.DBLoaiTour;
import tdc.edu.vn.formqltour.Database.DBLoaiPDK;
import tdc.edu.vn.formqltour.Database.DBNguoiThamGia;
import tdc.edu.vn.formqltour.Model.NguoiThamGia;
import tdc.edu.vn.formqltour.Model.PhieuDangKy;
import tdc.edu.vn.formqltour.Model.DangKyTour;
import tdc.edu.vn.formqltour.R;

public class NguoiThamGiaActivity extends AppCompatActivity {
    private Button button;
    EditText  txtSoNguoi;
    Spinner txtMaPhieu, txtMaTour;
    Button btnThem, btnXoa, btnSua, btnClear;
    ListView lvDanhSach;
    DBLoaiPDK dbLoaiPDK;
    DBLoaiTour dbLoaiTour;
    ArrayList<NguoiThamGia> data_NguoiThamGia = new ArrayList<>();
    ArrayList<String> data_PDK = new ArrayList<>();
    ArrayAdapter adapter_PDK;
    ArrayList<String> data_MaTour = new ArrayList<>();
    ArrayAdapter adapter_MaTour;
    Adapter_NguoiThamGia adapter_NguoiThamGia;
    int index = -1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoithamgia);
        setControl();
        setEvent();
    }


    private void setEvent() {
        KhoiTao();
        HienThiDL();
        dbLoaiPDK = new DBLoaiPDK(getApplicationContext());
        for(PhieuDangKy pdk : dbLoaiPDK.getDuLieu()){
            data_PDK.add(pdk.getMaPhieu());
        }
        dbLoaiTour = new DBLoaiTour(getApplicationContext());
        for(DangKyTour loaiTour : dbLoaiTour.getDuLieu()){
            data_MaTour.add(loaiTour.getMaTour());
        }
        adapter_PDK = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,data_PDK);
        txtMaPhieu.setAdapter(adapter_PDK);
        adapter_MaTour = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,data_MaTour);
        txtMaTour.setAdapter(adapter_MaTour);
        adapter_NguoiThamGia = new Adapter_NguoiThamGia(this, R.layout.list_nguoithamgia, data_NguoiThamGia);
        lvDanhSach.setAdapter(adapter_NguoiThamGia);



        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etSoNguoi = findViewById(R.id.txtSoNguoi);
                String SoNguoi_check = etSoNguoi.getText().toString();


                    if (SoNguoi_check.trim().equals("")) {
                        etSoNguoi.setError("Trường này phải nhập Số");

                } else {
                  //  NguoiThamGia nguoiThamGia = getMaTour();
                //    data_NguoiThamGia.add(nguoiThamGia);
                   // adapter_NguoiThamGia.notifyDataSetChanged();
                    DBNguoiThamGia dbNguoiThamGia = new DBNguoiThamGia(getApplicationContext());
                    //NguoiThamGia dangKyTour1 = getMaTour();
                    dbNguoiThamGia.Them(getMaTour());
                    HienThiDL();
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    txtMaPhieu.setSelection(0);
                    txtMaTour.setSelection(0);
                    txtSoNguoi.setText("");

                }
            }
        });


        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NguoiThamGia nguoiThamGia = data_NguoiThamGia.get(position);
                txtMaTour.setSelection(data_MaTour.indexOf(nguoiThamGia.getMaTour()));
                txtMaPhieu.setSelection(data_PDK.indexOf(nguoiThamGia.getMaPhieu()));
                txtSoNguoi.setText(nguoiThamGia.getSoNguoi());


                index = position;
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(NguoiThamGiaActivity.this);
                builder.setTitle("Thông báo!!!");
                builder.setMessage("Bạn có muốn xoá không ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data_NguoiThamGia.remove(index);
                        adapter_NguoiThamGia.notifyDataSetChanged();
                        DBNguoiThamGia dbNguoiThamGia = new DBNguoiThamGia(getApplicationContext());
                        NguoiThamGia nguoiThamGia = getMaTour();
                        dbNguoiThamGia.Xoa(nguoiThamGia);
                        Toast.makeText(getApplicationContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                        txtMaPhieu.setSelection(0);
                        txtMaTour.setSelection(0);
                        txtSoNguoi.setText("") ;

                    }


                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });


        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                NguoiThamGia nguoiThamGia = data_NguoiThamGia.get(index);
                nguoiThamGia.setMaTour(txtMaTour.getSelectedItem().toString());
               nguoiThamGia.setMaPhieu(txtMaPhieu.getSelectedItem().toString());
                nguoiThamGia.setSoNguoi(txtSoNguoi.getText().toString());

                adapter_NguoiThamGia.notifyDataSetChanged();
                DBNguoiThamGia dbNguoiThamGia = new DBNguoiThamGia(getApplicationContext());
                NguoiThamGia dangKyTour1 = getMaTour();
                dbNguoiThamGia.Sua(dangKyTour1);
                txtMaPhieu.setSelection(0);
                txtMaTour.setSelection(0);
                txtSoNguoi.setText("");

                Toast.makeText(getApplicationContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMaPhieu.setSelection(0);
                txtMaTour.setSelection(0);
                txtSoNguoi.setText("");

                Toast.makeText(getApplicationContext(), "Đã làm sạch", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private  void HienThiDL()
    {
        DBNguoiThamGia dbNguoiThamGia = new DBNguoiThamGia(this);
        data_NguoiThamGia = dbNguoiThamGia.getDuLieu();
       adapter_NguoiThamGia = new Adapter_NguoiThamGia(this,R.layout.list_nguoithamgia,data_NguoiThamGia);
        lvDanhSach.setAdapter(adapter_NguoiThamGia);
    }
    private NguoiThamGia getMaTour() {
        NguoiThamGia nguoiThamGia = new NguoiThamGia();
        nguoiThamGia.setMaTour(txtMaTour.getSelectedItem().toString());
        nguoiThamGia.setMaPhieu(txtMaPhieu.getSelectedItem().toString());
        nguoiThamGia.setSoNguoi(txtSoNguoi.getText().toString());
        return nguoiThamGia;


    }

    private void KhoiTao() {
        //loai vat tu


        NguoiThamGia nguoiThamGia = new NguoiThamGia();
        data_NguoiThamGia.add(nguoiThamGia);


    }

    private void setControl() {
        txtMaPhieu  = findViewById(R.id.txtMaPhieu);
        txtMaTour = findViewById(R.id.txtMaTour);
        txtSoNguoi = findViewById(R.id.txtSoNguoi);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnClear = findViewById(R.id.btnClear);
        lvDanhSach = findViewById(R.id.lvDanhSach);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnLuu:
                Toast.makeText(getApplicationContext(),"Lưu thành công",Toast.LENGTH_LONG).show();
                break;
            case R.id.mnChuyen:
                Toast.makeText(getApplicationContext(),"Bạn vừa chọn chuyển sang tìm kiếm",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), List_Tour.class);
                startActivity(intent);
                break;
            case R.id.mnThoat:
//                Log.d("text", "Thoát");
                AlertDialog.Builder builder = new AlertDialog.Builder(NguoiThamGiaActivity.this);
                builder.setTitle("Thông báo!!!");
                builder.setMessage("Bạn có muốn thoát không ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
        }
        return true;
    }
}
