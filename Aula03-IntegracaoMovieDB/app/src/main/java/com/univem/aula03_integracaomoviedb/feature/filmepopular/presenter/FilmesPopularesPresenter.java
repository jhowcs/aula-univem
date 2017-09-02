package com.univem.aula03_integracaomoviedb.feature.filmepopular.presenter;

import com.univem.aula03_integracaomoviedb.feature.filmepopular.view.FilmesPopularesView;
import com.univem.aula03_integracaomoviedb.api.FabricaRetrofit;
import com.univem.aula03_integracaomoviedb.api.FilmeServico;
import com.univem.aula03_integracaomoviedb.feature.filmepopular.model.FilmeResultados;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FilmesPopularesPresenter {

    private Retrofit retrofitInstance;

    private FilmesPopularesView view;
    private boolean travarBusca;

    public FilmesPopularesPresenter(FilmesPopularesView view) {
        this.view = view;
        retrofitInstance = FabricaRetrofit.getRetrofitInstance();
    }

    public void buscarFilmes(final int pagina) {
        travarBusca = true;
        FilmeServico filmeServico = retrofitInstance.create(FilmeServico.class);

        view.mostrarLoading();

        filmeServico
                .getFilmesPopulares("pt-BR", "", pagina).enqueue(new Callback<FilmeResultados>() {
            @Override
            public void onResponse(Call<FilmeResultados> call, Response<FilmeResultados> response) {
                FilmeResultados body = response.body();

                if (body != null) {
                    view.mostrarDados(body.getResults());
                }

                view.esconderLoading();
                travarBusca = false;
            }

            @Override
            public void onFailure(Call<FilmeResultados> call, Throwable t) {
                view.esconderLoading();
                travarBusca = false;
            }
        });
    }

    public boolean buscaTravada() {
        return travarBusca;
    }
}
