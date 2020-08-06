package tdc.edu.vn.formqltour.GiaoDien;

import android.app.Activity;

import android.app.AlertDialog;
import android.view.LayoutInflater;

import tdc.edu.vn.formqltour.R;

class LoadingDialog {
      Activity activity;
    private AlertDialog alertDialog;
    LoadingDialog(Activity myactivity){
        activity = myactivity;
    }
    void statLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_loading,null));
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();
    }
    void dismissDialog(){

        alertDialog.dismiss();
    }
}
