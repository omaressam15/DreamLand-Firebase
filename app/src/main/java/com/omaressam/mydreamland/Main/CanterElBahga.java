package com.omaressam.mydreamland.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

public class CanterElBahga extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    DatabaseReference reference;
    ViewAdapter viewAdapter;
    List<DreamLand> dreamLands;
    SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        setRecyclerView();
        setup();
        setFirebase();

    }

    private void setup () {
        Toolbar toolbar =findViewById(R.id.toolbar2);
        toolbar.setNavigationIcon(R.drawable.arrow_back_24);
        toolbar.setTitle("سنتر البهجة");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void setRecyclerView (){

        recyclerView = findViewById(R.id.CenterElGharby);

        swipeRefreshLayout = findViewById(R.id.swipe);

        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

                viewAdapter.OnClick(new ViewAdapter.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {

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

    @Override
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
        return true;

    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        setFirebase();
    }
}