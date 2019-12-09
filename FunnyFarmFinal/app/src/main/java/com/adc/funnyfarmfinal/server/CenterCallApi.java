package com.adc.funnyfarmfinal.server;

import android.app.Dialog;
import android.content.Context;

import com.adc.funnyfarmfinal.R;
import com.adc.funnyfarmfinal.common.SystemDialog;
import com.adc.funnyfarmfinal.common.SystemError;
import com.adc.funnyfarmfinal.middleinterface.apiinterface.ISession;
import com.adc.funnyfarmfinal.middleinterface.apiinterface.IUserRegister;
import com.adc.funnyfarmfinal.middleinterface.apiinterface.IUserRegisteredCheck;
import com.adc.funnyfarmfinal.model.checkuserregister.CheckUserRegisterResult;
import com.adc.funnyfarmfinal.model.checkuserregister.Register;
import com.adc.funnyfarmfinal.model.error.ApiErrorResult;
import com.adc.funnyfarmfinal.model.registerphonenumber.UserRegister;
import com.adc.funnyfarmfinal.model.registerphonenumber.UserRegisterPost;
import com.adc.funnyfarmfinal.model.registerphonenumber.UserRegisterResult;
import com.adc.funnyfarmfinal.model.session.PhoneNumberPost;
import com.adc.funnyfarmfinal.model.session.SessionDetail;
import com.adc.funnyfarmfinal.model.session.SessionResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CenterCallApi {
    private Context context;
    private static HttpClient httpClient;

    public CenterCallApi(Context context) {
        this.context = context;
        if (httpClient == null) {
            httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
        }
    }

    // api lấy session
    public void getSession(final ISession iSession, final PhoneNumberPost phoneNumberPost) {
        final Dialog pd = SystemDialog.showProcessDialog(context);
        pd.show();
        try {
            HttpClient httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            Call<SessionResult> callback = httpClient.session(phoneNumberPost);
            callback.enqueue(new Callback<SessionResult>() {
                @Override
                public void onResponse(Call<SessionResult> call, Response<SessionResult> response) {
                    if (response != null && response.code() == 200) {
                        try {
                             SessionResult result = response.body();
                             SessionDetail sessionDetail = result.getSessionDetail().get(0);
                            if (sessionDetail.getErrorCode() == 0)
                                iSession.getSession(result);
                            else
                                SystemDialog.showAlertDialog(context, sessionDetail.getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (response.code() == 401) {
                        SystemDialog.showAlertDialogSessionLose(context, context.getResources().getString(R.string.label_session_lose));
                    } else {
                        ApiErrorResult errorResult = SystemError.parseError(response);
                        try {
                            SystemDialog.showAlertDialog(context, errorResult.getError().getMessage());
                        }catch(Exception e){
                            SystemDialog.showAlertDialog(context, response.toString());
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<SessionResult> call, Throwable t) {
                    pd.dismiss();
                    if (t instanceof NoConnectivityException) {
                        SystemDialog.showAlertDialog(context, context.getResources().getString(R.string.title_message_not_connect_internet));
                    } else {
                        SystemDialog.showAlertDialog(context, t.getMessage());
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // api kiểm tra thông tin user
    public void getCheckUserRegisterResult(final IUserRegisteredCheck iUserRegisteredCheck, final PhoneNumberPost phoneNumberPost) {
        final Dialog pd = SystemDialog.showProcessDialog(context);
        pd.show();
        try {
            HttpClient httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            Call<CheckUserRegisterResult> callback = httpClient.getCheckUserRegisterResult(phoneNumberPost);
            callback.enqueue(new Callback<CheckUserRegisterResult>() {
                @Override
                public void onResponse(Call<CheckUserRegisterResult> call, Response<CheckUserRegisterResult> response) {
                    if (response != null && response.code() == 200) {
                        try {
                            CheckUserRegisterResult result = response.body();
                            Register register = result.getRegisterList().get(0);
                            if (register.getErrorCode() == 0)
                                 iUserRegisteredCheck.getCheckUserRegisterResult(result);
                            else
                                SystemDialog.showAlertDialog(context, register.getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (response.code() == 401) {
                        SystemDialog.showAlertDialogSessionLose(context, context.getResources().getString(R.string.label_session_lose));
                    } else {
                        ApiErrorResult errorResult = SystemError.parseError(response);
                        try {
                            SystemDialog.showAlertDialog(context, errorResult.getError().getMessage());
                        }catch(Exception e){
                            SystemDialog.showAlertDialog(context, response.toString());
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<CheckUserRegisterResult> call, Throwable t) {
                    pd.dismiss();
                    if (t instanceof NoConnectivityException) {
                        SystemDialog.showAlertDialog(context, context.getResources().getString(R.string.title_message_not_connect_internet));
                    } else {
                        SystemDialog.showAlertDialog(context, t.getMessage());
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // api đăng ký thông tin user
    public void userRegisterResult(final IUserRegister iUserRegister, final UserRegisterPost userRegisterPost) {
        final Dialog pd = SystemDialog.showProcessDialog(context);
        pd.show();
        try {
            HttpClient httpClient = RetrofitClient.getClient(context).create(HttpClient.class);
            Call<UserRegisterResult> callback = httpClient.userRegister(userRegisterPost);
            callback.enqueue(new Callback<UserRegisterResult>() {
                @Override
                public void onResponse(Call<UserRegisterResult> call, Response<UserRegisterResult> response) {
                    if (response != null && response.code() == 200) {
                        try {
                            UserRegisterResult result = response.body();
                            UserRegister userRegister = result.getUserRegisterList().get(0);
                            if (userRegister.getErrorCode() == 0)
                                iUserRegister.userRegister(result);
                            else
                                SystemDialog.showAlertDialog(context, userRegister.getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (response.code() == 401) {
                        SystemDialog.showAlertDialogSessionLose(context, context.getResources().getString(R.string.label_session_lose));
                    } else {
                        ApiErrorResult errorResult = SystemError.parseError(response);
                        try {
                            SystemDialog.showAlertDialog(context, errorResult.getError().getMessage());
                        }catch(Exception e){
                            SystemDialog.showAlertDialog(context, response.toString());
                        }
                    }
                    pd.dismiss();
                }

                @Override
                public void onFailure(Call<UserRegisterResult> call, Throwable t) {
                    pd.dismiss();
                    if (t instanceof NoConnectivityException) {
                        SystemDialog.showAlertDialog(context, context.getResources().getString(R.string.title_message_not_connect_internet));
                    } else {
                        SystemDialog.showAlertDialog(context, t.getMessage());
                    }
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
