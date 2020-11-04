package com.omaressam.mydreamland.RecyclerView.SanterElShamaly;

import android.content.Intent;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.omaressam.mydreamland.Model.DreamLand;
import com.omaressam.mydreamland.R;
import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

    ImageView imageView;
    CardView cardView;
    TextView name, number;

    public ViewHolder(@NonNull final View itemView, final ViewAdapter.OnItemClick listener) {

        super(itemView);
        initView();
        itemView.setOnClickListener(view -> {

            int position = getAdapterPosition();
            listener.onItemClick(position);

            String phone = number.getText().toString();

            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:"+phone));
            itemView.getContext().startActivity(callIntent);

        });
    }

    private void initView() {
        imageView = itemView.findViewById(R.id.imageView);
        name = itemView.findViewById(R.id.name);
        number = itemView.findViewById(R.id.number);
        cardView = itemView.findViewById(R.id.CardView);
      //  cardView.setOnCreateContextMenuListener(this);

    }
    void bindView(DreamLand dreamLand) {

        Picasso.get()
                .load(dreamLand.getImage())
                .placeholder(R.drawable.img_placeholder)
                .into(imageView);

        name.setText(dreamLand.getName());

        //number.setText(Math.toIntExact(dreamLand.getNumber()));
        number.setText(dreamLand.getNumber());
    }


    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

        contextMenu.setHeaderTitle("Call");
        contextMenu.add(getAdapterPosition(),100,1,"1");
        contextMenu.add(getAdapterPosition(),101,2,"2");
    }
}
