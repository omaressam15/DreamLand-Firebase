package com.omaressam.mydreamland.Main;

import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.omaressam.mydreamland.R;
import com.suddenh4x.ratingdialog.AppRating;
import com.suddenh4x.ratingdialog.preferences.RatingThreshold;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RatingApp();
        initUI();

    }

    @SuppressLint("NonConstantResourceId")
    void initUI() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        NavController navController = Navigation.findNavController(this,R.id.main_frameLayout);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }

  /*  private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frameLayout, fragment);
        fragmentTransaction.commit();

    }
*/
    private void RatingApp() {
        new AppRating.Builder(this)
                .setMinimumLaunchTimes(5)
                .setMinimumDays(2)
                .setMinimumLaunchTimesToShowAgain(5)
                .setMinimumDaysToShowAgain(4)
                .setRatingThreshold(RatingThreshold.FIVE)
                .showIfMeetsConditions();

    }

}