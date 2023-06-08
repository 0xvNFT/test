package com.tk88congcu03phat.tk88.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tk88congcu03phat.tk88.R;
import com.tk88congcu03phat.tk88.data.model.ImageMain;

import java.util.ArrayList;
import java.util.List;

public class ListedHomeImageAdapter extends RecyclerView.Adapter<ListedHomeImageAdapter.ViewHolder> {
    public onClick onClick;

    public void setOnClick(onClick onClick) {
        this.onClick = onClick;
    }

    public List<ImageMain> listData = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.initData(listData.get(position));
        holder.itemView.setOnClickListener(view -> {
                    if (onClick != null) {
                        onClick.onCLick(listData.get(position));
                    }
                }
        );
    }

    public void clearData() {
        listData.clear();
        notifyDataSetChanged();
    }

    public void addData(List<ImageMain> list) {
        listData.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageMain);
        }

        public void initData(ImageMain item) {
            imageView.setImageResource(item.paths);
        }
    }

    public interface onClick {
        void onCLick(ImageMain item);
    }
}
