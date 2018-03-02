package com.univem.aula01_spinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // TODO 02 adicionar um campo (field) do tipo Spinner como privado (private)
    //lebrete de declaração de campo em Java: <visibilidade> <tipo> <nome do campo>
    //visibilidade: public, private, protected, vazio (default)

    List<String> personagensLaCasaDePapel = Arrays.asList("Tokyo", "Professor", "Moscow",
            "Nairobi", "Rio", "Berlim", "Oslo", "Helsinque", "Denver");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO 03 realizar o bind do xml com campo Spinner

        // TODO 04 criar uma instância de ArrayAdapter e popula-lo com nossa lista de //personagens de La Casa de Papel (personagensLaCasaDePapel)

        // TODO 05 setar o adapter criado no passo anterior ao nosso Spinner
    }
}
