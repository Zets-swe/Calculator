package ru.zets_swe.calculator;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
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
import ru.zets_swe.calculator.fragments.FragmentPipes;
import ru.zets_swe.calculator.fragments.FragmentTanks;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentPipes fragmentPipes;
    FragmentTanks fragmentTanks;
    FragmentAbout fragmentAbout;

    SharedPreferences sPref;
    Configuration config = new Configuration();

    final String LANGUAGE = "language";
    final String RUS_LANGUAGE = "ru";
    final String EN_LANGUAGE = "en";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sPref = getPreferences(MODE_PRIVATE);
        String lang = sPref.getString(LANGUAGE, Locale.getDefault().getDisplayLanguage().toString());
        Toast.makeText(MainActivity.this, lang, Toast.LENGTH_SHORT).show();
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

        fragmentPipes = new FragmentPipes();
        fragmentTanks = new FragmentTanks();
        fragmentAbout = new FragmentAbout();

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
        MenuItem item = menu.findItem(R.id.action_language);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_language) {
            setLanguage();
            super.recreate();
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

        } else if (id == R.id.nav_filler) {

        } else if (id == R.id.nav_bmm) {

        } else if (id == R.id.nav_sprayballs) {

        } else if (id == R.id.nav_poultry) {

        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void setLanguage() {
        String lan = sPref.getString(LANGUAGE, "").toString();
        SharedPreferences.Editor ed = sPref.edit();

        if (lan.equals(RUS_LANGUAGE)) {
            ed.putString(LANGUAGE, EN_LANGUAGE);
            ed.commit();
            Log.d("Lan=", sPref.getString(LANGUAGE, "").toString());
        } else {
            ed.putString(LANGUAGE, RUS_LANGUAGE);
            ed.commit();
            Log.d("Lan=", sPref.getString(LANGUAGE, "").toString());
        }


    }


}
