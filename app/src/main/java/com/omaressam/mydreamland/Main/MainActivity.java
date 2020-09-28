package com.omaressam.mydreamland.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.omaressam.mydreamland.Model.Dream;
import com.omaressam.mydreamland.Model.DreamImages;
import com.omaressam.mydreamland.R;
import com.omaressam.mydreamland.RecyclerView.MainRecycler.AdapterMain;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    DatabaseReference reference;
    AdapterMain viewAdapter;
    List<Dream> dreamLand;
    ImageView imageView;
    CircleImageView circleImageView;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdView adView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().
                addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        adView.loadAd(adRequest);
        setUpRecyclerView();
        setImageView();
        Firebase();
        DreamImages();

    }

    public void setImageView() {

        imageView = findViewById(R.id.imageView3);

        circleImageView = findViewById(R.id.circleImageView);

    }

    public void setUpRecyclerView() {

        recyclerView = findViewById(R.id.recyclerView2);

        swipeRefreshLayout = findViewById(R.id.swiperefresh);

        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }

    public void Firebase() {

        swipeRefreshLayout.setRefreshing(true);

        dreamLand = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("DreamLand").child("Dream");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {

                    Dream land = ds.getValue(Dream.class);
                    dreamLand.add(land);
                }
                viewAdapter = new AdapterMain(dreamLand);

                viewAdapter.setItemClick(new AdapterMain.ItemClick() {
                    @Override
                    public void onItemClick(int position) {

                        if (position == 0) {

                            Intent i = new Intent(MainActivity.this, CenterStare.class);
                            startActivity(i);

                        } else if (position == 1) {

                            Intent i = new Intent(MainActivity.this, CanterElBahga.class);
                            startActivity(i);

                        } else if (position == 2) {
                            Intent i = new Intent(MainActivity.this, FlagMall.class);
                            startActivity(i);

                        } else if (position == 3) {

                            Intent i = new Intent(MainActivity.this, ServiceDream.class);
                            startActivity(i);
                        }

                    }

                });
                recyclerView.setAdapter(viewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                swipeRefreshLayout.setRefreshing(false);
            }
        });

        swipeRefreshLayout.setRefreshing(false);
    }

    public void DreamImages() {

        swipeRefreshLayout.setRefreshing(true);

        reference = FirebaseDatabase.getInstance().getReference("DreamLand").child("images");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {

                    DreamImages land = ds.getValue(DreamImages.class);

                    assert land != null;
                    Picasso.get()
                            .load(land.getImages())
                            .placeholder(R.drawable.img_placeholder)
                            .into(circleImageView);

                    Picasso.get()
                            .load(land.getImage())
                            .placeholder(R.drawable.img_placeholder)
                            .into(imageView);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                swipeRefreshLayout.setRefreshing(false);
            }
        });
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onRefresh() {

        swipeRefreshLayout.setRefreshing(true);

        DreamImages();
        Firebase();

    }
}