package com.omaressam.mydreamland.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
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


public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    DatabaseReference reference;
    AdapterMain viewAdapter;
    NavController navController;
    List<Dream> dreamLand;
    ImageView imageView;
    CircleImageView circleImageView;
    SwipeRefreshLayout swipeRefreshLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        setUpAdMob(view);
        setUpRecyclerView(view);
        setImageView(view);
        Firebase();
        DreamImages();
        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    public void setUpAdMob(View view) {

        final AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (mAdView.getVisibility() == View.GONE) {
                    mAdView.setVisibility(View.VISIBLE);
                }
            }
        });
        mAdView.loadAd(adRequest);

    }

    public void setImageView(View view) {
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);


        imageView = view.findViewById(R.id.imageView3);

        circleImageView = view.findViewById(R.id.circleImageView);

    }

    public void setUpRecyclerView(View view) {

        recyclerView = view.findViewById(R.id.recyclerView2);

        swipeRefreshLayout = view.findViewById(R.id.swiperefresh);

        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

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

                viewAdapter.setItemClick(position -> {

                    if (position == 0) {

                        navController.navigate(R.id.action_home_to_centerStarFragment);

                    } else if (position == 1) {

                        navController.navigate(R.id.action_home_to_centerElbahgaFragment);

                    } else if (position == 2) {

                        navController.navigate(R.id.action_home_to_flagMallFragment);


                    } else if (position == 3) {

                        navController.navigate(R.id.action_home_to_serviceFragment);

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