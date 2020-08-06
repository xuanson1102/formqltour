package tdc.edu.vn.formqltour.GiaoDien;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import tdc.edu.vn.formqltour.Adapter.Adapter_DangKyTour;
import tdc.edu.vn.formqltour.Database.DBLoaiTour;
import tdc.edu.vn.formqltour.Model.DangKyTour;
import tdc.edu.vn.formqltour.R;

public class List_Tour extends AppCompatActivity {
    ListView lvDanhsach;
    ArrayList<DangKyTour> data_DangKyTour = new ArrayList<>();
    Adapter_DangKyTour adapter_DangKyTour;
    int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listtour);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }


    private void setEvent() {
        DBLoaiTour dbTourr = new DBLoaiTour(this);
        data_DangKyTour = dbTourr.getDuLieu();

        adapter_DangKyTour = new Adapter_DangKyTour(this, R.layout.list_dangkytour, data_DangKyTour);
        lvDanhsach.setAdapter(adapter_DangKyTour);
        registerForContextMenu(lvDanhsach);


    }

    public void CapnhatDL() {
        try {
            DBLoaiTour db = new DBLoaiTour(this);
            adapter_DangKyTour = new Adapter_DangKyTour(this, R.layout.list_dangkytour, db.getDuLieu());
            lvDanhsach.setAdapter(adapter_DangKyTour);
        } catch (Exception ex) {
            lvDanhsach.setVisibility(View.GONE);
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        }

    }

    private void setControl() {
        lvDanhsach = findViewById(R.id.lvDanhSach);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionsearch, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)) {
                    adapter_DangKyTour.filter("");
                    lvDanhsach.clearTextFilter();
                } else {
                    adapter_DangKyTour.filter(s);
                }
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.mn_update:
                Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mn_delete:
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();


                break;
        }
        return super.onContextItemSelected(item);

    }


}
