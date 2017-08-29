package com.univem.aula01_login;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DetalheProdutoActivity extends AppCompatActivity {

    private Button btnForcaResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_produto);

        btnForcaResultado = (Button) findViewById(R.id.btnForcaResultado);

        btnForcaResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent();
                it.putExtra("meu_extra", "Univem");
                setResult(Activity.RESULT_OK, it);
                finish();
            }
        });
    }
}
