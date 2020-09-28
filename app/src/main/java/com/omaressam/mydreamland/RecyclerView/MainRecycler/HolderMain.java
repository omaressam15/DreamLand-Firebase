package com.omaressam.mydreamland.RecyclerView.MainRecycler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.omaressam.mydreamland.Model.Dream;
import com.omaressam.mydreamland.R;
import com.squareup.picasso.Picasso;

public class HolderMain extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView name ;

    public HolderMain(@NonNull View itemView,final AdapterMain.ItemClick onItemClick ) {

        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                onItemClick.onItemClick(position);

            }
        });

        initView();

    }

    private void initView() {
        imageView = itemView.findViewById(R.id.imageView2);
        name = itemView.findViewById(R.id.textView);

    }

    void bindView(Dream dreamLand) {

        Picasso.get()
                .load(dreamLand.getImage())
                .placeholder(R.drawable.img_placeholder)
                .into(imageView);

        name.setText(dreamLand.getName());
    }

}
