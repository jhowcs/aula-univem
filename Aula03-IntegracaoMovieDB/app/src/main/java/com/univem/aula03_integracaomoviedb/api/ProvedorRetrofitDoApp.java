package com.univem.aula03_integracaomoviedb.api;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProvedorRetrofitDoApp implements ProvedorRetrofit {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    @Override
    public Retrofit getRetroInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
