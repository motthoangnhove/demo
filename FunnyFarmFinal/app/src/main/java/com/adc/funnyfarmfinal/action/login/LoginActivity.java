package com.adc.funnyfarmfinal.action.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.adc.funnyfarmfinal.R;
import com.adc.funnyfarmfinal.action.BaseActivity;
import com.adc.funnyfarmfinal.action.invalidregisterphone.InvalidRegisterPhoneActivity;
import com.adc.funnyfarmfinal.action.login.middleinterface.ILoginViewModel;
import com.adc.funnyfarmfinal.action.login.model.UserLogin;
import com.adc.funnyfarmfinal.action.login.viewmodel.LoginViewModel;
import com.adc.funnyfarmfinal.action.registerphone.RegisterPhoneActivity;
import com.adc.funnyfarmfinal.action.verifyphone.VerifyPhoneActivity;
import com.adc.funnyfarmfinal.application.FunnyFarmApplication;
import com.adc.funnyfarmfinal.common.Cache;
import com.adc.funnyfarmfinal.common.Constant;
import com.adc.funnyfarmfinal.common.SystemDevice;
import com.adc.funnyfarmfinal.common.TranslateText;
import com.adc.funnyfarmfinal.databinding.ActivityLoginBinding;
import br.com.sapereaude.maskedEditText.MaskedEditText;

public class LoginActivity extends BaseActivity {
    private ActivityLoginBinding binding;
    private ILoginViewModel iLoginViewModel;
    private UserLogin userLogin;
    private LinearLayout lyContainerCacheNumberPhone;
    private MaskedEditText edtVerifyPhone;
    private Button buttonLogin;
    private ImageView ivStatusNumberPhone, ivCacheNumberPhone;
    private TextView tvRegisterPhoneNumber;
    private boolean isCachedNumberPhone;
    private Activity activity;
    private Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLifecycleOwner(this);
        iLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.setLogin(iLoginViewModel);
        initView();
        initEvent();
        onObserverDataChange(binding);
        initDataCacheNumberPhone();
    }


    // khởi tạo cache số điện thoại
    private void initDataCacheNumberPhone(){
        if(Cache.hasPreferencePhone(context, Constant.SHARE_PRE_CACHE_PHONE_NUMBER,"numberPhone")) {
            String phoneNumberCache = Cache.loadPreferencePhone(context, Constant.SHARE_PRE_CACHE_PHONE_NUMBER, "numberPhone");
            edtVerifyPhone.setText(phoneNumberCache);
            iLoginViewModel.setPhoneNumber(phoneNumberCache);
            setCheckCacheView(true);
            isCachedNumberPhone = true;
        }
    }


    //khởi tạo giao diện màn hình
    private void initView(){
        lyContainerCacheNumberPhone = (LinearLayout)findViewById(R.id.lyContainerCacheNumberPhone);
        edtVerifyPhone = findViewById(R.id.edtVerifyPhone);
        ivStatusNumberPhone = findViewById(R.id.ivStatusNumberPhone);
        ivCacheNumberPhone = findViewById(R.id.ivCacheNumberPhone);
        buttonLogin = findViewById(R.id.btnLogin);
        tvRegisterPhoneNumber = findViewById(R.id.tvRegisterPhoneNumber);
    }


    //khởi tạo sự kiện cho giao diện màn hình
    private void initEvent(){

        // số điện thoại
        edtVerifyPhone.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                iLoginViewModel.setPhoneNumber(s.toString());
            }
        });

        // số điện thoại
        edtVerifyPhone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(isCachedNumberPhone && !userLogin.getNumberPhone().trim().contains("*")){
                    Cache.savePreferencePhone(context,Constant.SHARE_PRE_CACHE_PHONE_NUMBER,"numberPhone",  userLogin.getNumberPhone());
                }
                SystemDevice.hideSoftKeyboard(activity,edtVerifyPhone);
                return false;
            }
        });

        // vùng chứa bấm cache số điện thoại
        lyContainerCacheNumberPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNumberPhone = userLogin.getNumberPhone();
                if(userNumberPhone != null) {
                    if(!isCachedNumberPhone && userNumberPhone.length() == 16) {
                        setCheckCacheView(true);
                        Cache.savePreferencePhone(context,Constant.SHARE_PRE_CACHE_PHONE_NUMBER,"numberPhone", userNumberPhone);
                        isCachedNumberPhone = true;
                    }else{
                        Cache.removePreferencePhone(context,Constant.SHARE_PRE_CACHE_PHONE_NUMBER,"numberPhone");
                        setCheckCacheView(false);
                        isCachedNumberPhone = false;
                    }
                }
            }
        });

        // cache số điện thoại
        ivCacheNumberPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNumberPhone = userLogin.getNumberPhone();
                if(userNumberPhone != null) {
                    if(!isCachedNumberPhone && userNumberPhone.length() == 16) {
                        setCheckCacheView(true);
                        Cache.savePreferencePhone(context,Constant.SHARE_PRE_CACHE_PHONE_NUMBER,"numberPhone", userNumberPhone);
                        isCachedNumberPhone = true;
                    }else{
                        Cache.removePreferencePhone(context,Constant.SHARE_PRE_CACHE_PHONE_NUMBER,"numberPhone");
                        setCheckCacheView(false);
                        isCachedNumberPhone = false;
                    }
                }
            }
        });

        // đăng nhập
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userLogin.getNumberPhone() != null) {
                    if (userLogin.getNumberPhone().trim().contains("*")) {
                        ivStatusNumberPhone.setBackground(getResources().getDrawable(R.drawable.ic_warm_input));
                        edtVerifyPhone.requestFocus();
                        return;
                    }
                    ((FunnyFarmApplication)context.getApplicationContext()).setPhoneNumber(userLogin.getNumberPhone());
                    String phoneNumber = "+84" + TranslateText.getNumberPhoneFromMask(userLogin.getNumberPhone());
                    Intent intent = new Intent(LoginActivity.this, VerifyPhoneActivity.class);
                    intent.putExtra("phoneNumberView", userLogin.getNumberPhone());
                    intent.putExtra("phoneNumber", phoneNumber);
                    startActivity(intent);
                }
            }
        });


        // đăng ký số điện thoại
        tvRegisterPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterPhoneActivity.class);
                startActivity(intent);
            }
        });
    }

    // cập nhật dữ liệu thay đổi vào view
    private void onObserverDataChange(ActivityLoginBinding binding){
        //nhận dữ liệu thông tin user
        binding.getLogin().getUserLogin().observe(this, new Observer<UserLogin>() {
            @Override
            public void onChanged(@Nullable UserLogin userLoginViewModel) {
                userLogin = iLoginViewModel.getUserLogin().getValue();
                checkStatusNumberPhone();
            }
        });

    }


    // khởi tạo view check ban đầu
    private void setCheckCacheView(boolean cached){
        if(cached){
            ivCacheNumberPhone.setBackground(getResources().getDrawable(R.drawable.ic_check));
        }else{
            ivCacheNumberPhone.setBackground(getResources().getDrawable(R.drawable.ic_not_check));
        }
    }


    // kiểm tra số điện thoại hợp lệ
    private void checkStatusNumberPhone(){
        if(userLogin.getNumberPhone() != null ) {
            if (userLogin.getNumberPhone().trim().contains("*")) {
                ivStatusNumberPhone.setBackground(getResources().getDrawable(R.drawable.ic_warm_input));
            } else {
                ivStatusNumberPhone.setBackground(getResources().getDrawable(R.drawable.ic_tick));
            }
        }
    }
}
