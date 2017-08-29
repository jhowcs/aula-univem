package com.univem.aula01_login;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Produto> produtoList;

    public ProdutoAdapter(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list_produto, parent, false);

        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Produto produto = produtoList.get(position);
        ProdutoViewHolder viewHolder = (ProdutoViewHolder) holder;
        viewHolder.txtDescricao.setText(produto.getDescricao());
        viewHolder.txtValor.setText(String.valueOf(produto.getValor()));
    }

    @Override
    public int getItemCount() {
        return produtoList == null
                ? 0
                : produtoList.size();
    }

    final class ProdutoViewHolder extends RecyclerView.ViewHolder {

        private TextView txtDescricao;
        private TextView txtValor;

        public ProdutoViewHolder(View itemView) {
            super(itemView);

            txtDescricao = itemView.findViewById(R.id.txtDescricao);
            txtValor = itemView.findViewById(R.id.txtValor);
        }
    }
}
