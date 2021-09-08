package com.example.geniussearch.API;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIFactory {

    public static final String SORT_BY_POPULARITY = "popularity";
    public static final String RAD_KEY = "56ce2e36d8msh8e21e2fce855962p1cfe86jsn5abaa566032c";
    public static final String RAD_API_HOST = "genius.p.rapidapi.com";
    private static final String BASE_URL_SEARCH = "https://genius.p.rapidapi.com/";

    private Retrofit retrofit;
    private static APIFactory apiFactory;

    private APIFactory() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL_SEARCH)
                .build();
    }

    public static APIFactory getInstance() {
        if (apiFactory == null) {
            apiFactory = new APIFactory();
        }
        return apiFactory;
    }

    public APIServices apiServices() {
        return retrofit.create(APIServices.class);
    }
}
