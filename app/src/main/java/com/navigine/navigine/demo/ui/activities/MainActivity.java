package com.navigine.navigine.demo.ui.activities;


import static com.navigine.navigine.demo.utils.Constants.LOCATION_CHANGED;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.navigine.idl.java.NavigineSdk;
import com.navigine.navigine.demo.R;
import com.navigine.navigine.demo.application.NavigineApp;
import com.navigine.navigine.demo.models.BeaconMock;
import com.navigine.navigine.demo.models.UserSession;
import com.navigine.navigine.demo.ui.custom.navigation.SavedBottomNavigationView;
import com.navigine.navigine.demo.utils.NavigineSdkManager;
import com.navigine.navigine.demo.viewmodel.SharedViewModel;
import com.navigine.sdk.Navigine;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedViewModel viewModel = null;

    private SavedBottomNavigationView mBottomNavigation = null;
    private List<Integer> navGraphIds = null;

    private void selectLocation(int locationId) {
        NavigineSdkManager.LocationManager.setLocationId(locationId);
        getApplicationContext().sendBroadcast(new Intent(LOCATION_CHANGED));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewModel();
        initNavigationView();
        //TODO => ini yang di tambahkan
        selectLocation(UserSession.LOCATION_ID);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {

        NavigineSdkManager.RouteManager.cancelTarget();
        NavigineSdkManager.RouteManager.clearTargets();

        finish();
    }


    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(SharedViewModel.class);
    }

    private void initNavigationView() {
        mBottomNavigation = findViewById(R.id.main__bottom_navigation);
        navGraphIds = Arrays.asList(

                R.navigation.navigation_navigation,
                R.navigation.navigation_locations,
                R.navigation.navigation_debug,
                R.navigation.navigation_profile);

        mBottomNavigation.setupWithNavController(
                navGraphIds,
                getSupportFragmentManager(),
                R.id.nav_host_fragment_activity_main,
                getIntent());
    }


    private void addBeaconGenerator() {
        NavigineSdkManager.MeasurementManager.addBeaconGenerator(BeaconMock.UUID,
                BeaconMock.MAJOR,
                BeaconMock.MINOR,
                BeaconMock.POWER,
                BeaconMock.TIMEOUT,
                BeaconMock.RSS_MIN,
                BeaconMock.RSS_MAX
        );
    }
}