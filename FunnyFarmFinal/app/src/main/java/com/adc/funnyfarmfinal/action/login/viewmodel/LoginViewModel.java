package com.adc.funnyfarmfinal.action.login.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.adc.funnyfarmfinal.action.login.middleinterface.ILoginViewModel;
import com.adc.funnyfarmfinal.action.login.model.UserLogin;

public class LoginViewModel extends ViewModel implements ILoginViewModel {
    private MutableLiveData<UserLogin> userLogin;
    public LoginViewModel() {
        UserLogin initUserLogin = new UserLogin();
        userLogin = new MutableLiveData();
        userLogin.setValue(initUserLogin);
    }


    @Override
    public MutableLiveData<UserLogin> getUserLogin() {
        return userLogin;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        UserLogin userData = userLogin.getValue();
        userData.setNumberPhone(phoneNumber);
        userLogin.setValue(userData);
    }

}
