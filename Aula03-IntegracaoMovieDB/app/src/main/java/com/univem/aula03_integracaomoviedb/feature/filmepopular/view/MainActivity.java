package com.univem.aula03_integracaomoviedb.feature.filmepopular.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;
import com.univem.aula03_integracaomoviedb.R;
import com.univem.aula03_integracaomoviedb.feature.filmepopular.view.adapter.FilmeAdapter;
import com.univem.aula03_integracaomoviedb.feature.filmepopular.presenter.FilmesPopularesPresenter;
import com.univem.aula03_integracaomoviedb.feature.filmepopular.model.Filme;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FilmesPopularesView {

    private static final String TAG = "MainActivity";

    private RecyclerView rvFilmes;
    private LinearLayoutManager linearLayoutManager;
    private FilmeAdapter adapter;
    private int MAX_ITEM_PER_REQUEST = 20;
    private int page = 1;
    private ProgressBar progressBar;

    private FilmesPopularesPresenter presenter = new FilmesPopularesPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvFilmes = findViewById(R.id.rvFilmes);
        progressBar = findViewById(R.id.progress);

        inicializarAdapter();
        inicializarRecyclerView();
        carregarFilmesPopulares(page);
    }

    private void inicializarAdapter() {
        adapter = new FilmeAdapter();
    }

    private void inicializarRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(this);
        rvFilmes.setLayoutManager(linearLayoutManager);
        rvFilmes.setAdapter(adapter);
        rvFilmes.setHasFixedSize(true);
        rvFilmes.addOnScrollListener(criarScrollInfinito());
    }

    private InfiniteScrollListener criarScrollInfinito() {
        return new InfiniteScrollListener(MAX_ITEM_PER_REQUEST, linearLayoutManager) {
            @Override
            public void onScrolledToEnd(int firstVisibleItemPosition) {
                if (presenter.buscaTravada() == false) {
                    carregarFilmesPopulares(page);
                    refreshView(rvFilmes, adapter, firstVisibleItemPosition);
                    Log.d("onScrolledToEnd", "page: " + page );
                }
            }
        };
    }

    @Override
    public void carregarFilmesPopulares(int pagina) {
        presenter.buscarFilmes(pagina);
    }

    @Override
    public void mostrarLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void exibirEmptyStateDeErro() {

    }

    @Override
    public void mostrarDados(ArrayList<Filme> model) {
        adapter.setFilmes(model);
        page++;
    }

    @Override
    public void esconderLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void mostrarEmptyStateDeDados() {

    }
}
