package com.taranasubscriptionmanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.taranasubscriptionmanager.ui.dashboard.DashboardFragment;
import com.taranasubscriptionmanager.ui.users.UserFragment;
import com.taranasubscriptionmanager.ui.delivery.DeliveryFragment;
import com.taranasubscriptionmanager.ui.reports.ReportsFragment;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottom_navigation);

        // Default screen
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new DashboardFragment())
                    .commit();
        }

        // Bottom Navigation Click
        bottomNav.setOnItemSelectedListener(item -> {

            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.nav_dashboard) {
                selectedFragment = new DashboardFragment();
            }
            else if (item.getItemId() == R.id.nav_users) {
                selectedFragment = new UserFragment();
            }
            else if (item.getItemId() == R.id.nav_delivery) {
                selectedFragment = new DeliveryFragment();
            }
            else if (item.getItemId() == R.id.nav_reports) {
                selectedFragment = new ReportsFragment();
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, selectedFragment)
                    .commit();

            return true;
        });
    }
}