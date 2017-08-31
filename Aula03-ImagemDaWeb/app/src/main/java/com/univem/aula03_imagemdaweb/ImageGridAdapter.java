package com.univem.aula03_imagemdaweb;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ActionFigure> actionFigureList;

    public ImageGridAdapter(List<ActionFigure> actionFigureList) {
        this.actionFigureList = actionFigureList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_actionfigure, parent, false);

        return new ImageGridVH(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImageGridVH imageGridVH = (ImageGridVH) holder;

        ActionFigure actionFigure = actionFigureList.get(position);

        loadImage(imageGridVH, actionFigure);
        imageGridVH.txtActionFigureName.setText(actionFigure.getName());
    }

    private void loadImage(ImageGridVH imageGridVH, ActionFigure actionFigure) {
        Context context = imageGridVH.imgActionFigure.getContext();

        Picasso.with(context)
                .load(actionFigure.getUrlImage())
                .into(imageGridVH.imgActionFigure);
    }

    @Override
    public int getItemCount() {
        return actionFigureList.size();
    }

    static final class ImageGridVH extends RecyclerView.ViewHolder {

        private ImageView imgActionFigure;
        private TextView txtActionFigureName;

        public ImageGridVH(View itemView) {
            super(itemView);

            imgActionFigure = itemView.findViewById(R.id.imgActionFigure);
            txtActionFigureName = itemView.findViewById(R.id.txtActionFigureName);
        }
    }
}
