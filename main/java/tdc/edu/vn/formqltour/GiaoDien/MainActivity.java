package tdc.edu.vn.formqltour.GiaoDien;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import tdc.edu.vn.formqltour.R;

public class MainActivity extends AppCompatActivity {
    private Button btnDKTour, btnDangKy, btnDSThamGia, btnExit, btnDSDiaChi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LoadingDialog loadingDialog   = new LoadingDialog(MainActivity.this);
       btnDKTour = findViewById(R.id.btnDSMaTour);
        btnDangKy = findViewById(R.id.btnDSDangKy);
        btnDSThamGia = findViewById(R.id.btnDSThamGia);
        btnExit = findViewById(R.id.btnExit);
        btnDSDiaChi = findViewById(R.id.btnDSDiaChi);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo!!!");
                builder.setMessage("Bạn có muốn thoát không ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
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
        btnDKTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.statLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                },3000);
                Toast.makeText(getApplicationContext(), "Bạn đang vào trang quản lý tour", Toast.LENGTH_SHORT).show();
                openDKTour();
            }
        });
        btnDSThamGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.statLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                },3000);
                Toast.makeText(getApplicationContext(), "Bạn đang vào trang quản lý Người tham gia", Toast.LENGTH_SHORT).show();
                openDSThamGia();
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.statLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                },3000);
                Toast.makeText(getApplicationContext(), "Bạn đang vào trang quản lý đăng ký", Toast.LENGTH_SHORT).show();
                openPhieuDK();
            }
        });
        btnDSDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.statLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                },3000);
                Toast.makeText(getApplicationContext(), "Bạn đang vào trang địa chỉ", Toast.LENGTH_SHORT).show();
                openDSDiaChi();
            }
        });
        }
    public void openDKTour(){
        Intent intent = new Intent(this,DangKyTourActivity.class)   ;
        startActivity(intent);
    }
    public void openDSDiaChi(){
        Intent intent = new Intent(this,DiaChiActivity.class)   ;
        startActivity(intent);
    }
    public void openDSThamGia(){
        Intent intent = new Intent(this,NguoiThamGiaActivity.class)   ;
        startActivity(intent);
    }    public void openPhieuDK(){
        Intent intent = new Intent(this,PhieuDangKyActivity.class)   ;
        startActivity(intent);
    }
}
