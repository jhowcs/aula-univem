package com.univem.aula01_login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int RC_MAIN = 10;
    private Button btnDetalheProduto;

    private RecyclerView rvListaProduto;
    private ProdutoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDetalheProduto = (Button) findViewById(R.id.btnDetalheProduto);

        btnDetalheProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetalheProdutoActivity.class);
                startActivityForResult(intent, RC_MAIN);
            }
        });

        setupAdapter();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        rvListaProduto = (RecyclerView) findViewById(R.id.rvListaProduto);
        rvListaProduto.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvListaProduto.setLayoutManager(linearLayoutManager);
    }

    private void setupAdapter() {
        adapter = new ProdutoAdapter(getListaDeProdutosEstatica());
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_MAIN && resultCode == Activity.RESULT_OK) {
            String stringExtra = data.getStringExtra("meu_extra");
            Log.d(TAG, stringExtra);
        }
    }

    public List<Produto> getListaDeProdutosEstatica() {
        List<Produto> listaProduto = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            listaProduto.add(new Produto("Produto " + i, i));
        }

        return listaProduto;
    }
}
