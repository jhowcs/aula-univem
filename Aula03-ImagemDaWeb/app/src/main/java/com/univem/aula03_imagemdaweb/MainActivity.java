package com.univem.aula03_imagemdaweb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvActionFigure;
    ImageGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvActionFigure = findViewById(R.id.rvActionFigure);

        setupAdapter();
    }

    private void setupAdapter() {
        adapter = new ImageGridAdapter(getStaticList());

        GridLayoutManager glm = new GridLayoutManager(this, 2);
        rvActionFigure.setLayoutManager(glm);
        rvActionFigure.setAdapter(adapter);
    }

    public List<ActionFigure> getStaticList() {
        List<ActionFigure> list = new ArrayList<>();

        list.add(new ActionFigure("Stress Tech Android",
                "http://www.deadzebra.com/wp-content/uploads/2013/11/StressTech_Android_Front_800.jpg"));

        list.add(new ActionFigure("Dicktator Android",
                "https://images-na.ssl-images-amazon.com/images/I/41qNITWgk1L._SX355_.jpg"));

        list.add(new ActionFigure("Sem nome",
                "https://garimpodoze.r.worldssl.net/media/catalog/product/cache/1/thumbnail/600x/17f82f742ffe127f42dca9de82fb58b1/b/o/boneco_android_-_s_rie_2_-_blind_box_-_toy_art_-_1_unidade_7.jpg"));

        list.add(new ActionFigure("Snow Android",
                "https://i.pinimg.com/736x/4e/90/80/4e9080b4de63b1a9610ee3745d300bc1--bot-action-figures.jpg"));

        list.add(new ActionFigure("Evil Android",
                "http://www.notcot.com/images/2010/02/android6.jpg"));

        return list;
    }
}
