package com.jhowcs.aula01_salvandoestado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtNome;
    private Button btnAdicionar;
    private ListView lvNomes;
    private View emptyStateView;

    private List<String> listaDeNomes;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNome = (EditText) findViewById(R.id.edtNome);
        btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
        lvNomes = (ListView) findViewById(R.id.lvNomes);
        emptyStateView = findViewById(R.id.empty_view);

        btnAdicionar.setOnClickListener(this);
        inicializaAdapterLista();
    }

    private void inicializaAdapterLista() {
        listaDeNomes = new ArrayList<>();

        arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listaDeNomes);

        lvNomes.setAdapter(arrayAdapter);
        lvNomes.setEmptyView(emptyStateView);
    }

    @Override
    public void onClick(View view) {
        final String nomeDigitado = edtNome.getText().toString();
        if (TextUtils.isEmpty(nomeDigitado))
            return;

        listaDeNomes.add(nomeDigitado);
        notificarAdapterDasMudancas();
    }

    // Notifica nosso adapter para indicar que um novo nome foi carregado
    // e portanto a lista deve carregar o novo valor
    private void notificarAdapterDasMudancas() {
        arrayAdapter.notifyDataSetChanged();
    }
}
