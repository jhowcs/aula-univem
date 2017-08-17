package com.jhowcs.aula01_serializando;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity2 extends AppCompatActivity {

    private static final String EXTRA_STOCK = "extra_stock";
    private static final String TAG = "MainActivity2";

    // TODO: 8/16/17 crie 2 campos do tipo TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // TODO: 8/16/17 inicialize os campos de texto criados

        StockSerializable stock
                = (StockSerializable) getIntent().getSerializableExtra(EXTRA_STOCK);



        Log.d(TAG, stock.toString());
    }

    // TODO: 8/16/17 crie um método que receba um stockSerializable e preencha o textViews

    // TODO: 8/16/17 adicione um método público estático newIntent(StockSerializable stock) que retorne uma intent
}
