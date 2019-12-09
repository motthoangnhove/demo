package com.adc.funnyfarmfinal.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;

import com.adc.funnyfarmfinal.R;
import com.adc.funnyfarmfinal.action.login.LoginActivity;

public class SystemDialog {
    // hiển thị dilog xử lý tiến trình request
    public static Dialog showProcessDialog(Context context){
        Dialog progressDialog = new Dialog(context);
        ProgressBar dialog = new ProgressBar(context);
        dialog.setVisibility(View.VISIBLE);
        progressDialog.setContentView(dialog);
        progressDialog.setCancelable(false);
        return progressDialog;
    }
    //hiển thị dialog thông báo chung và nút Ok
    public static AlertDialog showAlertDialog(Context context, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(context.getResources().getString(R.string.title_notification))
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton(context.getResources().getString(R.string.label_button_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
        return alertDialog;
    }
    // hiển thị thông báo mất session
    public static AlertDialog showAlertDialogSessionLose(final Context context,String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(context.getResources().getString(R.string.title_notification))
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(context.getResources().getString(R.string.label_button_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent myIntent = new Intent(context, LoginActivity.class);
                        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(myIntent);
                    }
                }).show();
        return alertDialog;
    }

}
