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

import tdc.edu.vn.formqltour.Adapter.Adapter_DiaChi;
import tdc.edu.vn.formqltour.Database.DBDiaChi;
import tdc.edu.vn.formqltour.Database.DBLoaiPDK;
import tdc.edu.vn.formqltour.Model.DiaChi;
import tdc.edu.vn.formqltour.Model.PhieuDangKy;
import tdc.edu.vn.formqltour.R;

public class DiaChiActivity extends AppCompatActivity {
    EditText  txtTenKH,txtDiaChi;
    Spinner txtMaKH2;
    Button btnThem, btnXoa, btnSua, btnClear;
    ListView lvDanhSach;
    ArrayList<DiaChi> data_DiaChi = new ArrayList<>();
    ArrayList<String> data_MaKH = new ArrayList<>();
    DBLoaiPDK dbLoaiPDK;
    ArrayAdapter adapter_MaKH;
    Adapter_DiaChi adapter_DiaChi;
    int index = -1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diachi);
        setControl();
        setEvent();
    }


    private void setEvent() {
        KhoiTao();
        HienThiDL();
        dbLoaiPDK = new DBLoaiPDK(getApplicationContext());
        for(PhieuDangKy pdk : dbLoaiPDK.getDuLieu()){
            data_MaKH.add(pdk.getMaKhachHang());
        }
        adapter_MaKH = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,data_MaKH);
        txtMaKH2.setAdapter(adapter_MaKH);
        adapter_DiaChi = new Adapter_DiaChi(this, R.layout.list_diachi, data_DiaChi);
        lvDanhSach.setAdapter(adapter_DiaChi);


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etDiaChi = (EditText) findViewById(R.id.txtDiaChi);
                String DiaChi_check = etDiaChi.getText().toString();
                EditText etTenKH = (EditText) findViewById(R.id.txtTenKH);
                String TenKH_check = etTenKH.getText().toString();

                if (DiaChi_check.trim().equals("") ) {
                    etDiaChi.setError("Thiếu Trường Địa Chỉ");

                } else if (TenKH_check.trim().equals("")) {
                    {
                        etTenKH.setError("Thiếu Trường Tên Khách Hàng");

                    }
                } else {
                  //  DiaChi diaChi = getMaKH();
                    //data_DiaChi.add(diaChi);
                  //  adapter_DiaChi.notifyDataSetChanged();
                    DBDiaChi dbDiaChi = new DBDiaChi(getApplicationContext());
                   // DiaChi diaChi1 = getMaKH();
                    dbDiaChi.Them(getMaKH());
                    HienThiDL();
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    txtMaKH2.setSelection(0);
                    txtTenKH.setText("");
                    txtDiaChi.setText("");

                }
            }
        });


        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DiaChi diaChi = data_DiaChi.get(position);
                txtMaKH2.setSelection(data_MaKH.indexOf(diaChi.getMaKH2()));
                txtTenKH.setText(diaChi.getTenKH());
                txtDiaChi.setText(diaChi.getDiaChi());

                index = position;
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DiaChiActivity.this);
                builder.setTitle("Thông báo!!!");
                builder.setMessage("Bạn có muốn xoá không ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data_DiaChi.remove(index);
                        adapter_DiaChi.notifyDataSetChanged();
                        DBDiaChi dbDiaChi = new DBDiaChi(getApplicationContext());
                        DiaChi diaChi = getMaKH();
                        dbDiaChi.Xoa(diaChi);
                        Toast.makeText(getApplicationContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                        txtMaKH2.setSelection(0);
                        txtTenKH.setText("");
                        txtDiaChi.setText("");
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

                DiaChi diaChi = data_DiaChi.get(index);
                diaChi.setTenKH(txtTenKH.getText().toString());
                diaChi.setDiaChi(txtDiaChi.getText().toString());
                diaChi.setMaKH2(txtMaKH2.getSelectedItem().toString());
                adapter_DiaChi.notifyDataSetChanged();
                DBDiaChi dbDiaChi = new DBDiaChi(getApplicationContext());
                DiaChi diaChi1 = getMaKH();
                dbDiaChi.Sua(diaChi1);
                txtMaKH2.setSelection(0);
                txtTenKH.setText("");
                txtDiaChi.setText("");
                Toast.makeText(getApplicationContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMaKH2.setSelection(0);
                txtTenKH.setText("");
                txtDiaChi.setText("");
                Toast.makeText(getApplicationContext(), "Đã làm sạch", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private  void HienThiDL()
    {
        DBDiaChi dbDiaChi = new DBDiaChi(this);
        data_DiaChi = dbDiaChi.getDuLieu();
        adapter_DiaChi = new Adapter_DiaChi(this,R.layout.list_diachi,data_DiaChi);
        lvDanhSach.setAdapter(adapter_DiaChi);
    }
    private DiaChi getMaKH() {
        DiaChi diaChi = new DiaChi();
        diaChi.setMaKH2(txtMaKH2.getSelectedItem().toString());
        diaChi.setTenKH(txtTenKH.getText().toString());
        diaChi.setDiaChi(txtDiaChi.getText().toString());

        return diaChi;


    }

    private void KhoiTao() {
        //loai vat tu
        DiaChi diaChi = new DiaChi();
        data_DiaChi.add(diaChi);


    }

    private void setControl() {
        txtMaKH2 = findViewById(R.id.txtMaKH2);
        txtTenKH = findViewById(R.id.txtTenKH);
        txtDiaChi = findViewById(R.id.txtDiaChi);
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
                Intent intent = new Intent(getApplicationContext(), List_DiaChi.class);
                startActivity(intent);
                break;
            case R.id.mnThoat:
//                Log.d("text", "Thoát");
                AlertDialog.Builder builder = new AlertDialog.Builder(DiaChiActivity.this);
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
