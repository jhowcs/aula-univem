package com.univem.aula03_integracaomoviedb.feature.filmepopular.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FilmeResultados {
    private int page;

    private ArrayList<Filme> results;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    public ArrayList<Filme> getResults() {
        return results;
    }
}
