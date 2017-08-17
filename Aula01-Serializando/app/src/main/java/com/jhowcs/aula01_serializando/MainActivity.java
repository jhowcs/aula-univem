package com.jhowcs.aula01_serializando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String EXTRA_STOCK = "extra_stock";

    // TODO: 8/16/17 crie 2 campos do tipo EditText

    private Button btnActivity2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnActivity2 = (Button) findViewById(R.id.btnActivity2);
        //// TODO: 8/16/17 inicialize os campos de texto criados

        btnActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 8/16/17 crie uma instância de StockSerializable com os valores informados nos edittexts

                // TODO: 8/16/17 chame o método newIntent estático criado em MainActivity2 dentro de startActivity()
                abrirActivity2();
            }
        });
    }

    // TODO: 8/16/17 remova este método
    private void abrirActivity2() {
        Intent it = new Intent(this, MainActivity2.class);
        it.putExtra(EXTRA_STOCK, new StockSerializable("USIM5", 5.89));
        startActivity(it);
    }
}
