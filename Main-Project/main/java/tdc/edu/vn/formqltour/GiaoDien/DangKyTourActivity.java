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
import tdc.edu.vn.formqltour.Database.DBLoaiTour;
import tdc.edu.vn.formqltour.Model.DangKyTour;
import tdc.edu.vn.formqltour.R;

public class DangKyTourActivity extends AppCompatActivity {
    EditText txtMaTour, txtLoTrinh,txtGiaTour;
    Spinner spHanhTrinh;
    Button btnThem, btnXoa, btnSua, btnClear;
    ListView lvDanhSach;
    ArrayList<DangKyTour> data_Tour = new ArrayList<>();
    ArrayList<String> data_LoaiTour = new ArrayList<String>();
    ArrayAdapter adapter_LoaiTour;
    Adapter_DangKyTour adapter_Tour;
    int index = -1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangkytour);
        setControl();
        setEvent();
    }


    private void setEvent() {
        KhoiTao();
        HienThiDL();
        adapter_LoaiTour = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,data_LoaiTour);
        spHanhTrinh.setAdapter(adapter_LoaiTour);



        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etMaTour = (EditText) findViewById(R.id.txtMaTour);
                String MaTour_check = etMaTour.getText().toString();
                EditText etLoTrinh = (EditText) findViewById(R.id.txtLoTrinh);
                String LoTrinh_check = etLoTrinh.getText().toString();
                EditText etGiaTour = (EditText) findViewById(R.id.txtGiaTour);
                String GiaTour_check = etGiaTour.getText().toString();

                if (MaTour_check.trim().equals("") ) {

                    etMaTour.setError("Thiếu Trường Mã tour");

                } else if (LoTrinh_check.trim().equals("")) {
                    {
                        etLoTrinh.setError("Thiếu Trường Lộ Trình");

                    }
                } else if (GiaTour_check.trim().equals("")) {
                    etGiaTour.setError("Trường này phải nhập số");

                } else {
//                    DangKyTour dangKyTour = getMaTour();
//                    data_Tour.add(dangKyTour);
//                    adapter_Tour.notifyDataSetChanged();
                    DBLoaiTour DBLoaiTour = new DBLoaiTour(getApplicationContext());
//                    DangKyTour dangKyTour1 = getMaTour();
                    DBLoaiTour.Them(getMaTour());
                    HienThiDL();
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    txtMaTour.setText("");
                    txtLoTrinh.setText("");
                    txtGiaTour.setText("");
                    spHanhTrinh.setSelection(0);
                }
            }
        });


        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DangKyTour dangKyTour = data_Tour.get(position);
                txtMaTour.setText(dangKyTour.getMaTour());
                txtLoTrinh.setText(dangKyTour.getLoTrinh());
                txtGiaTour.setText(dangKyTour.getGiaTour());

                spHanhTrinh.setSelection(data_LoaiTour.indexOf(dangKyTour.getHanhTrinh()));
                index = position;
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DangKyTourActivity.this);
                builder.setTitle("Thông báo!!!");
                builder.setMessage("Bạn có muốn xoá không ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data_Tour.remove(index);
                        adapter_Tour.notifyDataSetChanged();
                        DBLoaiTour DBLoaiTour = new DBLoaiTour(getApplicationContext());
                        DangKyTour dangKyTour = getMaTour();
                        DBLoaiTour.Xoa(dangKyTour);
                        Toast.makeText(getApplicationContext(), "Xoá thành công", Toast.LENGTH_SHORT).show();
                        txtMaTour.setText("");
                        txtLoTrinh.setText("");
                        txtGiaTour.setText("") ;
                        spHanhTrinh.setSelection(0);
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

                DangKyTour dangKyTour = data_Tour.get(index);
                dangKyTour.setMaTour(txtMaTour.getText().toString());
                dangKyTour.setLoTrinh(txtLoTrinh.getText().toString());
                dangKyTour.setHanhTrinh(spHanhTrinh.getSelectedItem().toString());
                dangKyTour.setGiaTour(txtGiaTour.getText().toString());

                adapter_Tour.notifyDataSetChanged();
                DBLoaiTour DBLoaiTour = new DBLoaiTour(getApplicationContext());
                DangKyTour dangKyTour1 = getMaTour();
                DBLoaiTour.Sua(dangKyTour1);
                txtMaTour.setText("");
                txtLoTrinh.setText("");
                txtGiaTour.setText("");
                spHanhTrinh.setSelection(0);
                Toast.makeText(getApplicationContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMaTour.setText("");
                txtLoTrinh.setText("");
                txtGiaTour.setText("");
                spHanhTrinh.setSelection(0);
                Toast.makeText(getApplicationContext(), "Đã làm sạch", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private  void HienThiDL()
    {
        DBLoaiTour DBLoaiTour = new DBLoaiTour(this);
        data_Tour = DBLoaiTour.getDuLieu();
        adapter_Tour = new Adapter_DangKyTour(this,R.layout.list_dangkytour,data_Tour);
        lvDanhSach.setAdapter(adapter_Tour);
    }
    private DangKyTour getMaTour() {
        DangKyTour dangKyTour = new DangKyTour();
        dangKyTour.setMaTour(txtMaTour.getText().toString());
        dangKyTour.setLoTrinh(txtLoTrinh.getText().toString());
        dangKyTour.setHanhTrinh(spHanhTrinh.getSelectedItem().toString());
        dangKyTour.setGiaTour(txtGiaTour.getText().toString());
        return dangKyTour;


    }

    private void KhoiTao() {
        //loai vat tu
        data_LoaiTour.add("3 ngày 2 đêm");
        data_LoaiTour.add("2 ngày 2 đêm");
        data_LoaiTour.add("2 ngày 1 đêm");
        data_LoaiTour.add("4 ngày 4 đêm");
        data_LoaiTour.add("4 ngày 3 đêm");

        DangKyTour dangKyTour = new DangKyTour();
        data_Tour.add(dangKyTour);


    }

    private void setControl() {
        txtMaTour = findViewById(R.id.txtMaTour);
        txtLoTrinh = findViewById(R.id.txtLoTrinh);
        spHanhTrinh = findViewById(R.id.spHanhTrinh);
        txtGiaTour = findViewById(R.id.txtGiaTour);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(DangKyTourActivity.this);
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
