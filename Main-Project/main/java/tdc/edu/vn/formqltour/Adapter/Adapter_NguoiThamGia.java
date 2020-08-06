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

import tdc.edu.vn.formqltour.Model.NguoiThamGia;
import tdc.edu.vn.formqltour.R;

public class Adapter_NguoiThamGia extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<NguoiThamGia > data;
    ArrayList<NguoiThamGia>data_danhsach;
    public Adapter_NguoiThamGia(Context  context, int resource, ArrayList<NguoiThamGia> data) {
        super(context, resource);
        this.context=context;
        this.resource= resource;
        this.data= data;
        this.data_danhsach = new ArrayList<NguoiThamGia >();
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
        TextView tvMaPhieu = view.findViewById(R.id.tvMaPhieu);
        TextView tvMaTour = view.findViewById(R.id.tvMaTour);
        TextView tvSoNguoi = view.findViewById(R.id.tvSoNguoi);
        NguoiThamGia  nguoiThamGia = data.get(position);

        if (nguoiThamGia.getSoNguoi().equals("10"))
        {
            imgLoai.setImageResource(R.drawable.travel1);
        }
        else if (nguoiThamGia.getSoNguoi().equals("20"))
        {
            imgLoai.setImageResource(R.drawable.travel2);
        }
        else if (nguoiThamGia.getSoNguoi().equals("30"))
        {
            imgLoai.setImageResource(R.drawable.travel3);
        }
        else if(nguoiThamGia.getSoNguoi().equals("40"))
        {
            imgLoai.setImageResource(R.drawable.travel4);
        }
        else if(nguoiThamGia.getSoNguoi().equals("50"))
        {
            imgLoai.setImageResource(R.drawable.travel5);
        }
        tvMaPhieu.setText(nguoiThamGia.getMaPhieu());
        tvMaTour.setText(nguoiThamGia.getMaTour());
        tvSoNguoi.setText(nguoiThamGia.getSoNguoi());
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
            for (NguoiThamGia model : data_danhsach)
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
