package com.omaressam.mydreamland.RecyclerView.MainRecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.omaressam.mydreamland.Model.Dream;
import com.omaressam.mydreamland.R;


import java.util.List;

public class AdapterMain extends RecyclerView.Adapter<HolderMain> {

    private final List<Dream> dreamLand;
    private ItemClick ItemClick;

    public void setItemClick (ItemClick onItemClick) {
        this.ItemClick = onItemClick;
    }

    public AdapterMain(List<Dream> dreamLand) {
        this.dreamLand = dreamLand;
    }

    public interface ItemClick {
        void onItemClick(int position);
    }
    @NonNull
    @Override
    public HolderMain onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new HolderMain(v,ItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMain holder, int position) {

        holder.bindView(dreamLand.get(position));

    }

    @Override
    public int getItemCount() {
        return dreamLand.size();
    }
}
