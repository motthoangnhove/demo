package com.adc.funnyfarmfinal.server;

import android.content.Context;

import com.adc.funnyfarmfinal.common.Common;
import com.adc.funnyfarmfinal.common.Constant;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static String API_PATH = Constant.URL_SERVER;
    public static Retrofit getClient(Context context){
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(new NetworkConnectionInterceptor(context));
        if(Common.token == null){
            client.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest  = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " + Common.authKey)
                            .addHeader("x-cdata-authtoken",Common.authKey)
                            .build();
                    return chain.proceed(newRequest);
                }
            });
        }else{
            client.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest  = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " + Common.token)
                            .addHeader("x-cdata-authtoken",Common.token)
                            .build();
                    return chain.proceed(newRequest);
                }
            });
        }
        client.protocols(Arrays.asList(Protocol.HTTP_1_1))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true).build();

        return new Retrofit.Builder().baseUrl(API_PATH).client(client.build())
                .addConverterFactory(GsonConverterFactory.create()).build();

    }
}
