package com.univem.aula03_integracaomoviedb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
    private FilmeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvFilmes = findViewById(R.id.rvFilmes);
        inicializarAdapter();
        inicializarRecyclerView();
        buscarFilmes();
    }

    private void inicializarAdapter() {
        adapter = new FilmeAdapter();
    }

    private void inicializarRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvFilmes.setLayoutManager(linearLayoutManager);
        rvFilmes.setAdapter(adapter);
    }

    private void buscarFilmes() {
        retrofitInstance = FabricaRetrofit.getRetrofitInstance();
        FilmeServico filmeServico = retrofitInstance.create(FilmeServico.class);

        filmeServico
                .getFilmesPopulares("sua_api_key").enqueue(new Callback<FilmeResultados>() {
            @Override
            public void onResponse(Call<FilmeResultados> call, Response<FilmeResultados> response) {
                Log.d(TAG, "onResponse: " + response.toString());
                FilmeResultados body = response.body();

                if (body != null) {
                    adapter.setFilmes(body.getResults());
                }
            }

            @Override
            public void onFailure(Call<FilmeResultados> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }
}
