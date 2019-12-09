package com.adc.funnyfarmfinal.action.registerphone.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.adc.funnyfarmfinal.action.registerphone.middleinterface.IRegisterPhoneViewModel;
import com.adc.funnyfarmfinal.action.registerphone.model.RegisterPhone;
import com.adc.funnyfarmfinal.middleinterface.apiinterface.IUserRegister;
import com.adc.funnyfarmfinal.model.device.DeviceInfo;
import com.adc.funnyfarmfinal.model.registerphonenumber.UserRegisterPost;
import com.adc.funnyfarmfinal.model.registerphonenumber.UserRegisterResult;
import com.adc.funnyfarmfinal.server.CenterCallApi;

public class RegisterPhoneViewModel extends ViewModel implements IRegisterPhoneViewModel, IUserRegister {
    private MutableLiveData<RegisterPhone> registerPhone;
    private CenterCallApi centerCallApi;
    private Context context;
    public RegisterPhoneViewModel() {
        RegisterPhone initRegisterPhone = new RegisterPhone();
        registerPhone = new MutableLiveData();
        registerPhone.setValue(initRegisterPhone);
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
        centerCallApi = new CenterCallApi(context);
    }

    @Override
    public MutableLiveData<RegisterPhone> getUserRegisterPhone() {
        return registerPhone;
    }

    @Override
    public void setUserName(String userName) {
        RegisterPhone registerPhoneData = registerPhone.getValue();
        registerPhoneData.setUserName(userName);
        registerPhone.setValue(registerPhoneData);
    }

    @Override
    public void setPhoneNumberView(String phoneNumber) {
        RegisterPhone registerPhoneData = registerPhone.getValue();
        registerPhoneData.setPhoneNumberView(phoneNumber);
        registerPhone.setValue(registerPhoneData);
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        RegisterPhone registerPhoneData = registerPhone.getValue();
        registerPhoneData.setPhoneNumber(phoneNumber);
        registerPhone.setValue(registerPhoneData);
    }

    @Override
    public void userRegister() {
        DeviceInfo deviceInfo = new DeviceInfo(context);
        RegisterPhone registerPhoneData = registerPhone.getValue();
        UserRegisterPost userRegisterPost = new UserRegisterPost(registerPhoneData.getUserName(), registerPhoneData.getPhoneNumber(),deviceInfo.GetDeviceIMEI(),"Android");
        centerCallApi.userRegisterResult(this,userRegisterPost);
    }

    @Override
    public void userRegister(UserRegisterResult userRegisterResult) {
        RegisterPhone registerPhoneData = registerPhone.getValue();
        registerPhoneData.setUserRegisterResult(userRegisterResult);
        registerPhone.setValue(registerPhoneData);
    }
}
