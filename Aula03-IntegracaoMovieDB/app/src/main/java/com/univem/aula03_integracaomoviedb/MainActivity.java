package com.univem.aula03_integracaomoviedb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.github.pwittchen.infinitescroll.library.InfiniteScrollListener;
import com.univem.aula03_integracaomoviedb.adapter.FilmeAdapter;
import com.univem.aula03_integracaomoviedb.api.FabricaRetrofit;
import com.univem.aula03_integracaomoviedb.api.FilmeServico;
import com.univem.aula03_integracaomoviedb.model.FilmeResultados;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Retrofit retrofitInstance;

    private RecyclerView rvFilmes;
    private LinearLayoutManager linearLayoutManager;
    private FilmeAdapter adapter;
    private int MAX_ITEM_PER_REQUEST = 20;
    private int page = 1;
    private boolean travarBusca = false;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvFilmes = findViewById(R.id.rvFilmes);
        progressBar = findViewById(R.id.progress);
        inicializarAdapter();
        inicializarRecyclerView();
        buscarFilmes();
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

    private void buscarFilmes() {
        travarBusca = true;

        retrofitInstance = FabricaRetrofit.getRetrofitInstance();
        FilmeServico filmeServico = retrofitInstance.create(FilmeServico.class);

        exibirLoading();

        filmeServico
                .getFilmesPopulares("pt-BR", "sua_key_aqui", page).enqueue(new Callback<FilmeResultados>() {
            @Override
            public void onResponse(Call<FilmeResultados> call, Response<FilmeResultados> response) {
                FilmeResultados body = response.body();

                if (body != null) {
                    adapter.setFilmes(body.getResults());
                }

                esconderLoading();
                travarBusca = false;
                ++page;
            }

            @Override
            public void onFailure(Call<FilmeResultados> call, Throwable t) {
                esconderLoading();
                travarBusca = false;
            }
        });
    }

    private InfiniteScrollListener criarScrollInfinito() {
        return new InfiniteScrollListener(MAX_ITEM_PER_REQUEST, linearLayoutManager) {
            @Override
            public void onScrolledToEnd(int firstVisibleItemPosition) {
                if (travarBusca == false) {
                    buscarFilmes();
                    refreshView(rvFilmes, adapter, firstVisibleItemPosition);
                    Log.d("onScrolledToEnd", "page: " + page );
                }
            }
        };
    }

    private void exibirLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void esconderLoading() {
        progressBar.setVisibility(View.GONE);
    }
}
