package com.univem.aula03_integracaomoviedb.feature.filmepopular.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.univem.aula03_integracaomoviedb.R;
import com.univem.aula03_integracaomoviedb.feature.filmepopular.model.Filme;

import java.util.ArrayList;


public class FilmeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_DADOS = 1;
    private static final int VIEW_TYPE_EMPTY = 2;

    private ArrayList<Filme> filmeArrayList;

    public FilmeAdapter() {
        this.filmeArrayList = new ArrayList<>();
    }

    public void setFilmes(ArrayList<Filme> filmes) {
        filmeArrayList.addAll(filmes);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (filmeArrayList == null || filmeArrayList.isEmpty()) {
            return VIEW_TYPE_EMPTY;
        }

        return VIEW_TYPE_DADOS;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_DADOS) {

            view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_lista_filme, parent, false);
            return new FilmeViewHolder(view);

        } else {
            view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_lista_empty_state, parent, false);

            return new EmptyStateViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FilmeViewHolder) {
            FilmeViewHolder filmeViewHolder = (FilmeViewHolder) holder;

            Filme filme = filmeArrayList.get(position);

            filmeViewHolder.txtNomeFilme.setText(filme.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        if (filmeArrayList == null || filmeArrayList.isEmpty()) {
            return 1;
        }
        return filmeArrayList.size();
    }

    static final class FilmeViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNomeFilme;

        public FilmeViewHolder(View itemView) {
            super(itemView);

            txtNomeFilme = itemView.findViewById(R.id.txtNomeFilme);
        }
    }

    static final class EmptyStateViewHolder extends  RecyclerView.ViewHolder {

        public EmptyStateViewHolder(View itemView) {
            super(itemView);
        }
    }
}
