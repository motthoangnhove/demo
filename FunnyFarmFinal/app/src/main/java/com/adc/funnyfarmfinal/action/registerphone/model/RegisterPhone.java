package com.adc.funnyfarmfinal.action.registerphone.model;

import com.adc.funnyfarmfinal.model.registerphonenumber.UserRegisterResult;

public class RegisterPhone {
    private String userName;
    private String phoneNumber;
    private String phoneNumberView;
    private UserRegisterResult userRegisterResult;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumberView() {
        return phoneNumberView;
    }

    public void setPhoneNumberView(String phoneNumberView) {
        this.phoneNumberView = phoneNumberView;
    }

    public UserRegisterResult getUserRegisterResult() {
        return userRegisterResult;
    }

    public void setUserRegisterResult(UserRegisterResult userRegisterResult) {
        this.userRegisterResult = userRegisterResult;
    }
}
