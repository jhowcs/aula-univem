package com.univem.aula03_integracaomoviedb.api;


import android.support.annotation.VisibleForTesting;

import retrofit2.Retrofit;

public class FabricaRetrofit {
    private static ProvedorRetrofit provedorRetrofit = new ProvedorRetrofitDoApp();

    @VisibleForTesting
    public static void setProvedorRetrofit(ProvedorRetrofit provedorRetrofit) {
        FabricaRetrofit.provedorRetrofit = provedorRetrofit;
    }

    public static Retrofit getRetrofitInstance() {
        return provedorRetrofit.getRetroInstance();
    }
}
