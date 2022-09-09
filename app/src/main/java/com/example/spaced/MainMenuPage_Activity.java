package com.example.spaced;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainMenuPage_Activity extends AppCompatActivity {

    private DrawerLayout drawer_layout;
    private NavigationView nav_view;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_page);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                    {
                        //---> Lead to the home page
                        onBackPressed();
                        break;
                    }
                    case R.id.planets:
                    {
                        //---> Lead to the planet page
                        startActivity(new Intent(getApplicationContext(), Moon_Activity.class));
                        break;
                    }
                    case R.id.tickets:
                    {
                        //---> Lead to the ticket page
                        startActivity(new Intent(getApplicationContext(), Ticket_Activity.class));
                        break;
                    }
                    case R.id.MyFlight:
                    {
                        //---> Lead to the MyCart page
                        startActivity(new Intent(getApplicationContext(), MyFlight_Activity.class));
                        break;
                    }
                    case R.id.Settings:
                        startActivity(new Intent(getApplicationContext(), SettingsPage_Activity.class));
                        break;
                }
                return false;
            }
        });
    }

    private void initWidget() {
        drawer_layout = findViewById(R.id.drawer_layout);
        nav_view = findViewById(R.id.nav_view);

        drawerToggle = new ActionBarDrawerToggle(MainMenuPage_Activity.this, drawer_layout, R.string.open, R.string.close);
        drawer_layout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START))
        {
            drawer_layout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
}