package com.example.spaced;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Moon_Activity extends AppCompatActivity {

//    private ImageView btn_next;

    private ViewPager mSlideViewPager;
    private DrawerLayout parent;
    private ViewPageAdapter viewPageAdapter;

    private NavigationView nav_view;
    private ActionBarDrawerToggle drawerToggle;

    private TextView[] dots;

    private TextView txtView_planet, txtView_description, txtView_climate, txtView_climateText, txtView_physical, txtView_physicalText;

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
        setContentView(R.layout.activity_moon);

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
                        startActivity(new Intent(getApplicationContext(), MainMenuPage_Activity.class));
                        break;
                    }
                    case R.id.planets:
                    {
                        //---> Since we are already in the planet page, closing thr navigation bar will do
                        onBackPressed();
                        break;
                    }
                    case R.id.tickets:
                    {
                        //---> Lead to the ticket page

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

        txtView_planet = findViewById(R.id.txtView_planet);
        txtView_description = findViewById(R.id.txtView_description);
        txtView_climate = findViewById(R.id.txtView_climate);
        txtView_climateText = findViewById(R.id.txtView_climateText);
        txtView_physical = findViewById(R.id.txtView_physical);
        txtView_physicalText = findViewById(R.id.txtView_physicalText);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        parent = (DrawerLayout) findViewById(R.id.parent);
        viewPageAdapter = new ViewPageAdapter(this);
        mSlideViewPager.setAdapter(viewPageAdapter);

        mSlideViewPager.addOnPageChangeListener(viewListener);


        nav_view = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(Moon_Activity.this, parent, R.string.open, R.string.close);
        parent.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if (parent.isDrawerOpen(GravityCompat.START))
        {
            parent.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            switch (position)
            {
                case 0:
                    txtView_planet.setText(R.string.The_Moon);
                    txtView_description.setText(R.string.The_Moon_Description);
                    txtView_climateText.setText(R.string.Moon_climate);
                    txtView_physicalText.setText(R.string.Moon_physical);
                    break;

                case 1:
                    txtView_planet.setText(R.string.Mars);
                    txtView_description.setText(R.string.Mars_Description);
                    txtView_climateText.setText(R.string.Mars_climate);
                    txtView_physicalText.setText(R.string.Mars_physical);
                    break;

                case 2:
                    txtView_planet.setText(R.string.Uranus);
                    txtView_description.setText(R.string.Uranus_Description);
                    txtView_climateText.setText(R.string.Uranus_climate);
                    txtView_physicalText.setText(R.string.Uranus_physical);
                    break;

                case 3:
                    txtView_planet.setText(R.string.Mercury);
                    txtView_description.setText(R.string.Mercury_Description);
                    txtView_climateText.setText(R.string.Mercury_climate);
                    txtView_physicalText.setText(R.string.Mercury_physical);
                    break;

                case 4:
                    txtView_planet.setText(R.string.Venus);
                    txtView_description.setText(R.string.Venus_Description);
                    txtView_climateText.setText(R.string.Venus_climate);
                    txtView_physicalText.setText(R.string.Venus_physical);
                    break;

                case 5:
                    txtView_planet.setText(R.string.Jupiter);
                    txtView_description.setText(R.string.Jupiter_Description);
                    txtView_climateText.setText(R.string.Jupiter_climate);
                    txtView_physicalText.setText(R.string.Jupiter_physical);
                    break;

                case 6:
                    txtView_planet.setText(R.string.Saturn);
                    txtView_description.setText(R.string.Saturn_Description);
                    txtView_climateText.setText(R.string.Saturn_climate);
                    txtView_physicalText.setText(R.string.Saturn_physical);
                    break;

                case 7:
                    txtView_planet.setText(R.string.Neptune);
                    txtView_description.setText(R.string.Neptune_Description);
                    txtView_climateText.setText(R.string.Neptune_climate);
                    txtView_physicalText.setText(R.string.Neptune_physical);
                    break;

                case 8:
                    txtView_planet.setText(R.string.Eris);
                    txtView_description.setText(R.string.Eris_Description);
                    txtView_climateText.setText(R.string.Eris_climate);
                    txtView_physicalText.setText(R.string.Eris_physical);
                    break;

                case 9:
                    txtView_planet.setText(R.string.Pluto);
                    txtView_description.setText(R.string.Pluto_Description);
                    txtView_climateText.setText(R.string.Pluto_climate);
                    txtView_physicalText.setText(R.string.Pluto_physical);
                    break;

                case 10:
                    txtView_planet.setText(R.string.Makemake);
                    txtView_description.setText(R.string.Makemake_Description);
                    txtView_climateText.setText(R.string.Makemake_climate);
                    txtView_physicalText.setText(R.string.Makemake_physical);
                    break;

                case 11:
                    txtView_planet.setText(R.string.Ceres);
                    txtView_description.setText(R.string.Ceres_Description);
                    txtView_climateText.setText(R.string.Ceres_climate);
                    txtView_physicalText.setText(R.string.Ceres_physical);
                    break;

            }



        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}