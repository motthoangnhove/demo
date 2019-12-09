package com.adc.funnyfarmfinal.action.invalidregisterphone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.adc.funnyfarmfinal.R;
import com.adc.funnyfarmfinal.action.BaseActivity;
import com.adc.funnyfarmfinal.action.registerphone.RegisterPhoneActivity;

public class InvalidRegisterPhoneActivity extends BaseActivity {
    private LinearLayout lyContainerIvClose;
    private ImageView ivClose;
    private Button btnRegisterPhoneNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invalid_register_phone);
        initView();
        initEvent();
    }


    //khởi tạo giao diện màn hình
    private void initView() {
       lyContainerIvClose = findViewById(R.id.lyContainerIvClose);
       ivClose = findViewById(R.id.ivClose);
       btnRegisterPhoneNumber = findViewById(R.id.btnRegisterPhoneNumber);
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

        // đăng ký số điện thoại vào hệ thống
        btnRegisterPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InvalidRegisterPhoneActivity.this, RegisterPhoneActivity.class);
                startActivity(intent);
            }
        });
    }
}
