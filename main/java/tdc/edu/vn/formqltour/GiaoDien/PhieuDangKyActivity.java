package tdc.edu.vn.formqltour.GiaoDien;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import tdc.edu.vn.formqltour.Adapter.Adapter_PhieuDangKy;
import tdc.edu.vn.formqltour.Database.DBLoaiPDK;
import tdc.edu.vn.formqltour.Model.PhieuDangKy;
import tdc.edu.vn.formqltour.R;

public class PhieuDangKyActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText  txtNgayDK,txtMaKH, txtMaPDK;
    Button btnThem, btnXoa, btnSua, btnClear,btnDate;
    ListView lvDanhSach;
ArrayList<PhieuDangKy> data_PhieuDK = new ArrayList<>();
    ArrayAdapter adapter_PhieuDangKy;
    int index = -1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieudangky);
        setControl();
        setEvent();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String CurrentDateString = DateFormat.getDateInstance().format(c.getTime());
        EditText editTextNgayDK = findViewById(R.id.txtNgayDK);
        editTextNgayDK.setText(CurrentDateString);
    }


    private void setEvent() {
        KhoiTao();
        HienThiDL();
        adapter_PhieuDangKy = new Adapter_PhieuDangKy(this, R.layout.list_phieudangky,data_PhieuDK );
        lvDanhSach.setAdapter(adapter_PhieuDangKy);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etMaPhieu = (EditText) findViewById(R.id.txtMaPDK);
                String MaPhieu_check = etMaPhieu.getText().toString();
              EditText etNgayDK = (EditText) findViewById(R.id.txtNgayDK);
                String NgayDK_check = etNgayDK.getText().toString();
                EditText etMaKH = (EditText) findViewById(R.id.txtMaKH);
                String MaKH_check = etMaKH.getText().toString();

                if (MaPhieu_check.trim().equals("") ) {
                    etMaPhieu.setError("Thiếu Trường Mã Phiếu");

                } else if (NgayDK_check.trim().equals("")) {
                  {
                      etNgayDK.setError("Thiếu Trường Ngày Đăng Ký");

                   }
                }
                else if (MaKH_check.trim().equals("")) {
                    etMaKH.setError("Trường này phải nhập Mã Khách Hàng");

                } else {

                    //PhieuDangKy phieuDangKy = getMaPhieu();
                   // data_PhieuDK.add(phieuDangKy);
                  //  adapter_PhieuDangKy.notifyDataSetChanged();
                    DBLoaiPDK bdPhieuDK = new DBLoaiPDK(getApplicationContext());
                //   PhieuDangKy phieuDangKy1 = getMaPhieu();
                    bdPhieuDK.Them(getMaPhieu());
                    HienThiDL();
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    txtMaPDK.setText("");
                    txtNgayDK.setText("");
                    txtMaKH.setText("");

                }
            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "datePicker");
            }
        });


        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PhieuDangKy phieuDangKy = data_PhieuDK.get(position);
                txtMaPDK.setText(phieuDangKy.getMaPhieu());
              txtNgayDK.setText(phieuDangKy.getNgayDangKy());
                txtMaKH.setText(phieuDangKy.getMaKhachHang());
        index = position;
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PhieuDangKyActivity.this);
                builder.setTitle("Thông báo!!!");
                builder.setMessage("Bạn có muốn xoá không ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data_PhieuDK.remove(index);
                        adapter_PhieuDangKy.notifyDataSetChanged();
                        DBLoaiPDK bdLoaiPDK = new DBLoaiPDK(getApplicationContext());
                        PhieuDangKy phieuDangKy = getMaPhieu();
                        bdLoaiPDK.Xoa(phieuDangKy);
                        Toast.makeText(getApplicationContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                        txtMaPDK.setText("");
                        txtNgayDK.setText("");
                        txtMaKH.setText("") ;

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

                PhieuDangKy phieuDangKy = data_PhieuDK.get(index);
                phieuDangKy.setMaPhieu(txtMaPDK.getText().toString());
                phieuDangKy.setNgayDangKy(txtNgayDK.getText().toString());
                phieuDangKy.setMaKhachHang(txtMaKH.getText().toString());
                adapter_PhieuDangKy.notifyDataSetChanged();
                DBLoaiPDK dbLoaiPDK = new DBLoaiPDK(getApplicationContext());
                PhieuDangKy phieuDangKy1 = getMaPhieu();
                dbLoaiPDK.Sua(phieuDangKy1);
                txtMaPDK.setText("");
                txtNgayDK.setText("");
                txtMaKH.setText("");

                Toast.makeText(getApplicationContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMaPDK.setText("");
                txtNgayDK.setText("");
                txtMaKH.setText("");

                Toast.makeText(getApplicationContext(), "Đã làm sạch", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private  void HienThiDL()
    {
        DBLoaiPDK bdLoaiPDK = new DBLoaiPDK(this);
        data_PhieuDK = bdLoaiPDK.getDuLieu();
        adapter_PhieuDangKy = new Adapter_PhieuDangKy(this,R.layout.list_phieudangky,data_PhieuDK);
        lvDanhSach.setAdapter(adapter_PhieuDangKy);
    }
    private PhieuDangKy getMaPhieu() {
        PhieuDangKy phieuDangKy = new PhieuDangKy();
        phieuDangKy.setMaPhieu(txtMaPDK.getText().toString());
        phieuDangKy.setNgayDangKy(txtNgayDK.getText().toString());
        phieuDangKy.setMaKhachHang(txtMaKH.getText().toString());
        return phieuDangKy;


    }

    private void KhoiTao() {

        PhieuDangKy phieuDangKy = new PhieuDangKy();
        data_PhieuDK.add(phieuDangKy);


    }

    private void setControl() {
        txtMaPDK = findViewById(R.id.txtMaPDK);
        txtNgayDK = findViewById(R.id.txtNgayDK);
        txtMaKH = findViewById(R.id.txtMaKH);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnDate = findViewById(R.id.btnDate);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(PhieuDangKyActivity.this);
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
