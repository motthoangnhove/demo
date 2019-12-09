package com.adc.funnyfarmfinal.server;

import com.adc.funnyfarmfinal.common.Constant;
import com.adc.funnyfarmfinal.model.checkuserregister.CheckUserRegisterResult;
import com.adc.funnyfarmfinal.model.registerphonenumber.UserRegisterPost;
import com.adc.funnyfarmfinal.model.registerphonenumber.UserRegisterResult;
import com.adc.funnyfarmfinal.model.session.PhoneNumberPost;
import com.adc.funnyfarmfinal.model.session.SessionResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HttpClient {

    @POST(Constant.LINK_REQUEST+"_Sessions")
    Call<SessionResult> session(@Body PhoneNumberPost body);

    @POST(Constant.LINK_REQUEST+"_UserRegisteredCheck")
    Call<CheckUserRegisterResult> getCheckUserRegisterResult(@Body PhoneNumberPost body);

    @POST(Constant.LINK_REQUEST+"_UserRegister")
    Call<UserRegisterResult> userRegister(@Body UserRegisterPost body);

}
