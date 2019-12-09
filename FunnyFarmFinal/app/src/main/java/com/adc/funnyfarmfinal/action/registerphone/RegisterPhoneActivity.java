package com.adc.funnyfarmfinal.action.registerphone;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.adc.funnyfarmfinal.R;
import com.adc.funnyfarmfinal.action.BaseActivity;
import com.adc.funnyfarmfinal.action.login.LoginActivity;
import com.adc.funnyfarmfinal.action.registerphone.middleinterface.IRegisterPhoneViewModel;
import com.adc.funnyfarmfinal.action.registerphone.model.RegisterPhone;
import com.adc.funnyfarmfinal.action.registerphone.viewmodel.RegisterPhoneViewModel;
import com.adc.funnyfarmfinal.action.verifyphone.VerifyPhoneActivity;
import com.adc.funnyfarmfinal.common.SystemDevice;
import com.adc.funnyfarmfinal.common.SystemDialog;
import com.adc.funnyfarmfinal.common.TranslateText;
import com.adc.funnyfarmfinal.databinding.ActivityRegisterPhoneBinding;
import br.com.sapereaude.maskedEditText.MaskedEditText;

public class RegisterPhoneActivity extends BaseActivity {
    private ActivityRegisterPhoneBinding binding;
    private IRegisterPhoneViewModel iRegisterPhoneViewModel;
    private RegisterPhone registerPhone;
    private LinearLayout lyContainerIvClose;
    private ImageView ivClose;
    private TextView tvNavigationLogin;
    private EditText edtUserName;
    private MaskedEditText edtPhoneNumber;
    private Activity activity;
    private Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_phone);
        binding.setLifecycleOwner(this);
        iRegisterPhoneViewModel = ViewModelProviders.of(this).get(RegisterPhoneViewModel.class);
        iRegisterPhoneViewModel.setContext(context);
        binding.setRegisterphone(iRegisterPhoneViewModel);
        initView();
        initEvent();
        onObserverDataChange(binding);
    }


    //khởi tạo giao diện màn hình
    private void initView() {
        edtUserName = findViewById(R.id.edtUserName);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        lyContainerIvClose = findViewById(R.id.lyContainerIvClose);
        ivClose = findViewById(R.id.ivClose);
        tvNavigationLogin = findViewById(R.id.tvNavigationLogin);
    }


    //khởi tạo sự kiện cho giao diện màn hình
    private void initEvent() {
        // thoát màn hình
        lyContainerIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // thoát màn hình
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // nhập họ và tên
        edtUserName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                iRegisterPhoneViewModel.setUserName(edtUserName.getText().toString());
                SystemDevice.hideSoftKeyboard(activity,edtUserName);
                return false;
            }
        });

        // nhập số điện thoại
        edtPhoneNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(!edtPhoneNumber.getText().toString().trim().contains("*")){
                    requestPermissionGetImei();
                 }
                SystemDevice.hideSoftKeyboard(activity,edtPhoneNumber);
                return false;
            }
        });

        // chuyển sang màn hình mới
        tvNavigationLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterPhoneActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }


    // cập nhật dữ liệu thay đổi vào view
    private void onObserverDataChange(ActivityRegisterPhoneBinding binding){
        //nhận dữ liệu thông tin user đăng ký
        binding.getRegisterphone().getUserRegisterPhone().observe(this, new Observer<RegisterPhone>() {
            @Override
            public void onChanged(@Nullable RegisterPhone registerPhoneViewModel) {
                registerPhone = registerPhoneViewModel;
                if(registerPhone.getUserRegisterResult()!= null){
                    Intent intent = new Intent(activity, VerifyPhoneActivity.class);
                    intent.putExtra("phoneNumberView", registerPhone.getPhoneNumberView());
                    intent.putExtra("phoneNumber", registerPhone.getPhoneNumber());
                    startActivity(intent);
                }
            }
        });

    }

    // yêu cầu quyền lấy imei thiết bị
    private void requestPermissionGetImei(){
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_PHONE_STATE},
                1);
    }

    // kết quả yêu cầu quyền lấy imei
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        String phoneNumber = "+84" + TranslateText.getNumberPhoneFromMask(edtPhoneNumber.getText().toString());
                        iRegisterPhoneViewModel.setPhoneNumberView(edtPhoneNumber.getText().toString());
                        iRegisterPhoneViewModel.setPhoneNumber(phoneNumber);
                        iRegisterPhoneViewModel.userRegister();
                } else {
                    SystemDialog.showAlertDialog(this, "Bạn không cấp quyền lấy thông tin IMEI thiết bị");
                }
                return;
            }
        }
    }

}
