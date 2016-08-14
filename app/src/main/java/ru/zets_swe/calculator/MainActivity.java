package ru.zets_swe.calculator;

import android.Manifest;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Locale;

import ru.zets_swe.calculator.fragments.FragmentAbout;
import ru.zets_swe.calculator.fragments.FragmentBottleWasher;
import ru.zets_swe.calculator.fragments.FragmentFiller;
import ru.zets_swe.calculator.fragments.FragmentLosses;
import ru.zets_swe.calculator.fragments.FragmentPipes;
import ru.zets_swe.calculator.fragments.FragmentPoultry;
import ru.zets_swe.calculator.fragments.FragmentSettings;
import ru.zets_swe.calculator.fragments.FragmentSprayballs;
import ru.zets_swe.calculator.fragments.FragmentTanks;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentPipes fragmentPipes;
    FragmentTanks fragmentTanks;
    FragmentLosses fragmentLosses;
    FragmentFiller fragmentFiller;
    FragmentBottleWasher fragmentBottleWasher;
    FragmentSprayballs fragmentSprayballs;
    FragmentPoultry fragmentPoultry;
    FragmentAbout fragmentAbout;
    FragmentSettings fragmentSettings;

    SharedPreferences sPref;
    Configuration config = new Configuration();

    final String LANGUAGE = "language";
    final String RUS_LANGUAGE = "ru";
    final String EN_LANGUAGE = "en";
    private final int MY_PERMISSIONS_REQUEST_CODE = 1;


    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }


    private void setPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sPref = getPreferences(MODE_PRIVATE);
        String lang = sPref.getString(LANGUAGE, Locale.getDefault().getLanguage());
        /*Toast.makeText(MainActivity.this, lang, Toast.LENGTH_SHORT).show();*/
        Locale myLoc = new Locale(lang, lang.toUpperCase());
        config.locale = myLoc;
        getResources().updateConfiguration(config, null);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawer.openDrawer(GravityCompat.START);

        fragmentPipes = new FragmentPipes();
        fragmentTanks = new FragmentTanks();
        fragmentLosses = new FragmentLosses();
        fragmentFiller = new FragmentFiller();
        fragmentSprayballs = new FragmentSprayballs();
        fragmentBottleWasher = new FragmentBottleWasher();
        fragmentPoultry = new FragmentPoultry();
        fragmentAbout = new FragmentAbout();
        fragmentSettings = new FragmentSettings();


        if (checkPermissions()) {
           Toast.makeText(MainActivity.this, "Разрешения уже получены", Toast.LENGTH_SHORT).show();
        } else {
            setPermissions();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != MY_PERMISSIONS_REQUEST_CODE) {
            return;
        }
        boolean isGranted = true;
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
                break;
            }
        }

        if (isGranted) {
            Toast.makeText(MainActivity.this, "Разрешения получены", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "В разрешениях отказано", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        String lan = sPref.getString(LANGUAGE, "").toString();
        MenuItem item = menu.findItem(R.id.settings);
        if (lan.equals(RUS_LANGUAGE)) {
            item.setIcon(R.drawable.ic_russian_flag_24dp);
        } else {
            item.setIcon(R.drawable.ic_english_flag_24dp);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.settings) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragmentSettings);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (id == R.id.action_about) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragmentAbout);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (id == R.id.nav_pipes) {
            fragmentTransaction.replace(R.id.container, fragmentPipes);
        } else if (id == R.id.nav_tanks) {
            fragmentTransaction.replace(R.id.container, fragmentTanks);
        } else if (id == R.id.nav_losses) {
            fragmentTransaction.replace(R.id.container, fragmentLosses);
        } else if (id == R.id.nav_filler) {
            fragmentTransaction.replace(R.id.container, fragmentFiller);
        } else if (id == R.id.nav_bmm) {
            fragmentTransaction.replace(R.id.container, fragmentBottleWasher);
        } else if (id == R.id.nav_sprayballs) {
            fragmentTransaction.replace(R.id.container, fragmentSprayballs);
        } else if (id == R.id.nav_poultry) {
            fragmentTransaction.replace(R.id.container, fragmentPoultry);
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
