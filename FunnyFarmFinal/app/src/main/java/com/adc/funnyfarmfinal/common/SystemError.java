package com.adc.funnyfarmfinal.common;

import com.adc.funnyfarmfinal.model.error.ApiErrorResult;
import com.google.gson.Gson;

import retrofit2.Response;

public class SystemError {
    // xử lý lỗi gọi api
    public static ApiErrorResult parseError(Response<?> response) {
        ApiErrorResult error = new ApiErrorResult();
        try {
            Gson gson = new Gson();
            error = gson.fromJson(response.errorBody().charStream(), ApiErrorResult.class);
        } catch (Exception e) {
        }
        return error;
    }

}
