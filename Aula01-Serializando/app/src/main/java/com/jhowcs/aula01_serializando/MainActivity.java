package com.jhowcs.aula01_serializando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String EXTRA_STOCK = "extra_stock";

    private Button btnActivity2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnActivity2 = (Button) findViewById(R.id.btnActivity2);

        btnActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity2();
            }
        });
    }

    private void abrirActivity2() {
        Intent it = new Intent(this, MainActivity2.class);
        it.putExtra(EXTRA_STOCK, new StockSerializable("USIM5", 5.89));
        startActivity(it);
    }
}
