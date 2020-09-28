package com.omaressam.mydreamland.RecyclerView.SanterElShamaly;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.omaressam.mydreamland.Model.DreamLand;
import com.omaressam.mydreamland.R;

import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<DreamLand> dreamLands;
    private OnItemClick listener;

    public ViewAdapter(List<DreamLand> dreamLands) {
        this.dreamLands = dreamLands;
    }

    public void OnClick  (OnItemClick listener) {
        this.listener = listener;
    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(v,listener);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bindView(dreamLands.get(position));

    }

    @Override
    public int getItemCount() {

        return dreamLands.size();

    }

}
