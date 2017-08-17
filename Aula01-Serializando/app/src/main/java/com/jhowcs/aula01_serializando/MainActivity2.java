package com.jhowcs.aula01_serializando;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity2 extends AppCompatActivity {

    private static final String EXTRA_STOCK = "extra_stock";
    private static final String TAG = "MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        StockSerializable stock
                = (StockSerializable) getIntent().getSerializableExtra(EXTRA_STOCK);

        Log.d(TAG, stock.toString());
    }
}
