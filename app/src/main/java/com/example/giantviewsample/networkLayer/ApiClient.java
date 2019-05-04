package com.example.giantviewsample.networkLayer;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit mRetrofitClient;

    private static final String BASE_URL = "https://newsapi.org/";



    public static Retrofit getInstance(){

        if (mRetrofitClient == null){
            mRetrofitClient = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return mRetrofitClient;

    }


    public static ApiInterface getApiInterface(){
        return getInstance().create(ApiInterface.class);
    }

}
