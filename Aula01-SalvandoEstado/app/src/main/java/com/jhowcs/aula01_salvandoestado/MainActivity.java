package com.jhowcs.aula01_salvandoestado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtNome;
    private Button btnAdicionar;
    private ListView lvNomes;
    private View emptyStateView;

    private ArrayList<String> listaDeNomes;
    private ArrayAdapter<String> arrayAdapter;

    private static final String STATE_LISTA = "state_lista";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNome = (EditText) findViewById(R.id.edtNome);
        btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
        lvNomes = (ListView) findViewById(R.id.lvNomes);
        emptyStateView = findViewById(R.id.empty_view);

        btnAdicionar.setOnClickListener(this);

        if (savedInstanceState == null) {
            listaDeNomes = new ArrayList<>();
        } else {
            listaDeNomes =  savedInstanceState.getStringArrayList(STATE_LISTA);
        }

        inicializaAdapterLista();
    }

    private void inicializaAdapterLista() {
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(STATE_LISTA, listaDeNomes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pesquisa_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_item_pesquisa) {
            Toast.makeText(this, "Clicou em pesquisar", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }
}
