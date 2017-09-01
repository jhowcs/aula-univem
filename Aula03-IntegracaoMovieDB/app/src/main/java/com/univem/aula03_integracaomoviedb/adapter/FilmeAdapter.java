package com.univem.aula03_integracaomoviedb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.univem.aula03_integracaomoviedb.R;
import com.univem.aula03_integracaomoviedb.model.Filme;

import java.util.ArrayList;


public class FilmeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Filme> filmeArrayList;

    public FilmeAdapter() {
        this.filmeArrayList = new ArrayList<>();
    }

    public void setFilmes(ArrayList<Filme> filmes) {
        filmeArrayList.addAll(filmes);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_lista_filme, parent, false);

        return new FilmeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FilmeViewHolder filmeViewHolder = (FilmeViewHolder) holder;

        Filme filme = filmeArrayList.get(position);

        filmeViewHolder.txtNomeFilme.setText(filme.getTitle());
    }

    @Override
    public int getItemCount() {
        return filmeArrayList.size();
    }

    static final class FilmeViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNomeFilme;

        public FilmeViewHolder(View itemView) {
            super(itemView);

            txtNomeFilme = itemView.findViewById(R.id.txtNomeFilme);
        }
    }
}
