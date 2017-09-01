package com.univem.aula03_integracaomoviedb.api;


import com.univem.aula03_integracaomoviedb.model.FilmeResultados;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FilmeServico {

    @GET("movie/popular")
    Call<FilmeResultados> getFilmesPopulares(@Query("language") String language,
                                             @Query("api_key") String apiKey,
                                             @Query("page") int page);
}
