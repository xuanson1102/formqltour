package tdc.edu.vn.formqltour.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import tdc.edu.vn.formqltour.Model.PhieuDangKy;
import tdc.edu.vn.formqltour.R;

public class Adapter_PhieuDangKy extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<PhieuDangKy> data;
    ArrayList<PhieuDangKy>data_danhsach;
    public Adapter_PhieuDangKy(Context  context, int resource, ArrayList<PhieuDangKy> data) {
        super(context, resource);
        this.context=context;
        this.resource= resource;
        this.data= data;
        this.data_danhsach = new ArrayList<PhieuDangKy>();
        this.data_danhsach.addAll(data);

    }



    @Override
    public int getCount() {

        return data.size();
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
       View view= LayoutInflater.from(context).inflate(resource,null);
        ImageView imgLoai= view.findViewById(R.id.imgshow);
        TextView tvMaPhieu = view.findViewById(R.id.tvMaPhieu);
        TextView tvNgayDK = view.findViewById(R.id.tvNgayDK);
        TextView tvMaKH = view.findViewById(R.id.tvMaKH);
        PhieuDangKy phieuDangKy = data.get(position);
        if (phieuDangKy.getMaKhachHang().equals("KH01"))
        {
            imgLoai.setImageResource(R.drawable.travel1);
        }
        else if (phieuDangKy.getMaKhachHang().equals("KH02"))
        {
            imgLoai.setImageResource(R.drawable.travel2);
        }
        else if (phieuDangKy.getMaKhachHang().equals("KH03"))
        {
            imgLoai.setImageResource(R.drawable.travel3);
        }
        else if(phieuDangKy.getMaKhachHang().equals("KH04"))
        {
            imgLoai.setImageResource(R.drawable.travel4);
        }
        else if(phieuDangKy.getMaKhachHang().equals(""))
        {
            imgLoai.setImageResource(R.drawable.travel5);
        }

        tvMaPhieu.setText(phieuDangKy.getMaPhieu());
        tvNgayDK.setText(phieuDangKy.getNgayDangKy());
        tvMaKH.setText(phieuDangKy.getMaKhachHang());

       return  view;
    }


    public void filter (String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        data.clear();
        if(charText.length() == 0)
        {
            data.addAll(data_danhsach);

        }
        else {
            for (PhieuDangKy model : data_danhsach)
            {
                if (model.getMaPhieu().toLowerCase(Locale.getDefault())
                        .contains(charText))
                {
                    data.add(model);}

                }
        }
        notifyDataSetChanged();
    }
}
