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

import tdc.edu.vn.formqltour.Model.DiaChi;
import tdc.edu.vn.formqltour.R;

public class Adapter_DiaChi extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<DiaChi> data;
    ArrayList<DiaChi>data_danhsach;
    public Adapter_DiaChi(Context  context, int resource, ArrayList<DiaChi> data) {
        super(context, resource);
        this.context=context;
        this.resource= resource;
        this.data= data;
        this.data_danhsach = new ArrayList<>();
        this.data_danhsach.addAll(data);

    }



    @Override
    public int getCount() {

        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(resource,null);
        ImageView imgLoai= view.findViewById(R.id.imgshow);
        TextView tvMaKH2 = view.findViewById(R.id.tvMaKH);
        TextView tvTenKH = view.findViewById(R.id.tvTenKH);
        TextView tvDiaChi = view.findViewById(R.id.tvDiaChi);
        DiaChi diaChi = data.get(position);
        if (diaChi.getMaKH2().equals("KH01"))
        {
            imgLoai.setImageResource(R.drawable.travel1);
        }
        else if (diaChi.getMaKH2().equals("KH02"))
        {
            imgLoai.setImageResource(R.drawable.travel2);
        }
        else if (diaChi.getMaKH2().equals("KH03"))
        {
            imgLoai.setImageResource(R.drawable.travel3);
        }
        else if(diaChi.getMaKH2().equals("KH04"))
        {
            imgLoai.setImageResource(R.drawable.travel4);
        }
        else if(diaChi.getMaKH2().equals("KH05"))
        {
            imgLoai.setImageResource(R.drawable.travel5);
        }
        tvMaKH2.setText(diaChi.getMaKH2());
        tvTenKH.setText(diaChi.getTenKH());
        tvDiaChi.setText(diaChi.getDiaChi());
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
            for (DiaChi model : data_danhsach)
            {
                if (model.getMaKH2().toLowerCase(Locale.getDefault())
                        .contains(charText))
                {
                    data.add(model);}

            }
        }
        notifyDataSetChanged();
    }
}
