package com.taranasubscriptionmanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.taranasubscriptionmanager.modelviewui.dashboard.DashboardFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new DashboardFragment())
                    .commit();
        }
    }
}