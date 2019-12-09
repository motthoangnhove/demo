package com.adc.funnyfarmfinal.action.login.middleinterface;

import androidx.lifecycle.MutableLiveData;

import com.adc.funnyfarmfinal.action.login.model.UserLogin;

public interface ILoginViewModel {
    MutableLiveData<UserLogin> getUserLogin();
    void setPhoneNumber(String phoneNumber);
}
