package com.adc.funnyfarmfinal.action.verifyphone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.adc.funnyfarmfinal.R;
import com.adc.funnyfarmfinal.action.BaseActivity;
import com.adc.funnyfarmfinal.action.invalidregisterphone.InvalidRegisterPhoneActivity;
import com.adc.funnyfarmfinal.action.verifyinfologinsuccess.VerifyInfoLoginSuccessActivity;
import com.adc.funnyfarmfinal.common.Common;
import com.adc.funnyfarmfinal.common.SystemDialog;
import com.adc.funnyfarmfinal.middleinterface.apiinterface.ISession;
import com.adc.funnyfarmfinal.middleinterface.apiinterface.IUserRegisteredCheck;
import com.adc.funnyfarmfinal.model.checkuserregister.CheckUserRegisterResult;
import com.adc.funnyfarmfinal.model.checkuserregister.Register;
import com.adc.funnyfarmfinal.model.session.PhoneNumberPost;
import com.adc.funnyfarmfinal.model.session.SessionResult;
import com.adc.funnyfarmfinal.server.CenterCallApi;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;

public class VerifyPhoneActivity extends BaseActivity implements ISession, IUserRegisteredCheck {
    private String phoneNumber,getPhoneNumberView, verificationId;
    private FirebaseAuth mAuth;
    private TextView tvPhoneNumber;
    private TextInputEditText editText;
    private Button btnVerifyCode;
    private LinearLayout lyContainerIvBack;
    private ImageView ivBack;
    private Context context;
    private CenterCallApi centerCallApi;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_code);
        context = this;
        centerCallApi = new CenterCallApi(context);
        mAuth = FirebaseAuth.getInstance();
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        getPhoneNumberView = getIntent().getStringExtra("phoneNumberView");
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("USER_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("phoneNumber", phoneNumber);
        editor.apply();
        initView();
        initEvent();
        sendVerificationCode(phoneNumber);
    }

    // khởi tạo các control cho màn hình
    private void initView(){
        lyContainerIvBack = findViewById(R.id.lyContainerIvBack);
        ivBack = findViewById(R.id.ivBack);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        tvPhoneNumber.setText(getPhoneNumberView);
        editText = findViewById(R.id.editTextCode);
        btnVerifyCode = findViewById(R.id.btnVerifyCode);
    }


    // khởi tạo sự kiện các control màn hình
    private void initEvent(){

        // thoát màn hình
        lyContainerIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // thoát màn hình
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        // xác thực mã code
        btnVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editText.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    editText.setError("Enter code...");
                    editText.requestFocus();
                    return;
                }
                verifyCode(code);
//                Common.token = null;
//                getSession();
            }
        });
    }


    // gọi hàm xác thực mã code với firebase
    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Common.token = null;
                            getSession();
                        } else {
                            SystemDialog.showAlertDialog(context,task.getException().getMessage());
                        }
                    }
                });
    }


    private void sendVerificationCode(String number) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                editText.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(VerifyPhoneActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };


    // lấy session đăng nhập hệ thống
    private void getSession() {
        PhoneNumberPost phoneNumberPost = new PhoneNumberPost(phoneNumber);
        centerCallApi.getSession(this, phoneNumberPost);
    }

    // call back lấy kết quả session trả về
    @Override
    public void getSession(SessionResult sessionResult) {
        Common.token = sessionResult.getSessionDetail().get(0).getToken();
        getUserRegisteredCheck();
    }

    // kiểm tra thông tin số điện thoại của user
    private void getUserRegisteredCheck() {
        PhoneNumberPost phoneNumberPost = new PhoneNumberPost(phoneNumber);
        centerCallApi.getCheckUserRegisterResult(this,phoneNumberPost);
    }

    @Override
    public void getCheckUserRegisterResult(CheckUserRegisterResult checkUserRegisterResult) {
        Register register = checkUserRegisterResult.getRegisterList().get(0);
        if(register.getRegistered() == 1) {
            Intent intent = new Intent(this, VerifyInfoLoginSuccessActivity.class);
            startActivity(intent);
        }else if (register.getRegistered() == 0) {
            Intent intent = new Intent(this, InvalidRegisterPhoneActivity.class);
            startActivity(intent);
        }
    }
}
