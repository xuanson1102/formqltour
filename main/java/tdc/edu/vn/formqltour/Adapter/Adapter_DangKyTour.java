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
import tdc.edu.vn.formqltour.Model.DangKyTour;
import tdc.edu.vn.formqltour.R;

public class Adapter_DangKyTour extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<DangKyTour> data;
    ArrayList<DangKyTour>data_danhsach;
    public Adapter_DangKyTour(Context  context, int resource, ArrayList<DangKyTour> data) {
        super(context, resource);
        this.context=context;
        this.resource= resource;
        this.data= data;
        this.data_danhsach = new ArrayList<DangKyTour>();
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
        TextView tvMaTour = view.findViewById(R.id.tvMaTour);
        TextView tvLoTrinh = view.findViewById(R.id.tvLoTrinh);
        TextView tvHanhTrinh = view.findViewById(R.id.tvHanhTrinh);
        TextView tvGiaTour = view.findViewById(R.id.tvGiaTour);
        DangKyTour tourr = data.get(position);
        if (tourr.getHanhTrinh().equals("3 ngày 2 đêm"))
        {
            imgLoai.setImageResource(R.drawable.travel1);
        }
        else if (tourr.getHanhTrinh().equals("2 ngày 2 đêm"))
        {
            imgLoai.setImageResource(R.drawable.travel2);
        }
        else if (tourr.getHanhTrinh().equals("2 ngày 1 đêm"))
        {
            imgLoai.setImageResource(R.drawable.travel3);
        }
        else if(tourr.getHanhTrinh().equals("4 ngày 4 đêm"))
        {
            imgLoai.setImageResource(R.drawable.travel4);
        }
        else if(tourr.getHanhTrinh().equals("4 ngày 3 đêm"))
        {
            imgLoai.setImageResource(R.drawable.travel5);
        }


        tvMaTour.setText(tourr.getMaTour());
        tvLoTrinh.setText(tourr.getLoTrinh());
        tvHanhTrinh.setText(tourr.getHanhTrinh());
        tvGiaTour.setText(tourr.getGiaTour());
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
            for (DangKyTour model : data_danhsach)
            {
                if (model.getMaTour().toLowerCase(Locale.getDefault())
                        .contains(charText))
                {
                    data.add(model);}

                }
        }
        notifyDataSetChanged();
    }
}
