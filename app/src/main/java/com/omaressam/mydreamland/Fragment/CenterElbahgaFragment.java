package com.omaressam.mydreamland.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.omaressam.mydreamland.Model.DreamLand;
import com.omaressam.mydreamland.R;
import com.omaressam.mydreamland.RecyclerView.SanterElShamaly.ViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class CenterElbahgaFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    DatabaseReference reference;
    ViewAdapter viewAdapter;
    List<DreamLand> dreamLands;
    SwipeRefreshLayout swipeRefreshLayout;
    NavController navController6;
    ImageView imageView;

    public CenterElbahgaFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View view = inflater.inflate(R.layout.fragment_center_elbahga, container, false);

        setRecyclerView(view);
        setup(view);
        setFirebase();
        return view;
    }
    private void setup (View view) {

        imageView= view.findViewById(R.id.arrow_back);

        imageView.setOnClickListener(v -> navController6.popBackStack());

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController6 = Navigation.findNavController(view);
    }

    public void setRecyclerView (View view){


        recyclerView = view.findViewById(R.id.CenterElGharby);

        swipeRefreshLayout = view.findViewById(R.id.swipe);

        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void setFirebase() {
        swipeRefreshLayout.setRefreshing(true);

        dreamLands = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("DreamLand").child("SanterElGanoby");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren())
                {
                    DreamLand land = ds.getValue(DreamLand.class);
                    dreamLands.add(land);
                }
                viewAdapter = new ViewAdapter(dreamLands);

                viewAdapter.OnClick(position -> {

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

  /*  @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onContextItemSelected(item);

        switch (item.getItemId()) {
            case 100:
                Uri number = Uri.parse("tel:123");
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
                return true;

            case 101:
                Uri number2 = Uri.parse("tel:01009843926");
                Intent callIntent2 = new Intent(Intent.ACTION_DIAL, number2);
                startActivity(callIntent2);
                return true;

        }
        return true;*/


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        setFirebase();
    }
}