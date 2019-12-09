package com.adc.funnyfarmfinal.action.registerphone.middleinterface;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import com.adc.funnyfarmfinal.action.registerphone.model.RegisterPhone;

public interface IRegisterPhoneViewModel {
    MutableLiveData<RegisterPhone> getUserRegisterPhone();
    void setUserName(String userName);
    void setPhoneNumberView(String phoneNumber);
    void setPhoneNumber(String phoneNumber);
    void setContext(Context context);
    void userRegister();
}
