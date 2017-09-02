package com.univem.aula03_integracaomoviedb.feature.filmepopular.view;

import com.univem.aula03_integracaomoviedb.feature.filmepopular.model.Filme;

import java.util.ArrayList;

/**
 * Created by jonathan on 9/2/17.
 */

public interface FilmesPopularesView {

    void carregarFilmesPopulares(int pagina);

    void mostrarLoading();

    void exibirEmptyStateDeErro();

    void mostrarDados(ArrayList<Filme> model);

    void esconderLoading();

    void mostrarEmptyStateDeDados();

}
