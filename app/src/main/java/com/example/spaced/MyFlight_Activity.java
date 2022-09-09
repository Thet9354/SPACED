package com.example.spaced;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MyFlight_Activity extends AppCompatActivity {

    private ChipNavigationBar bottom_nav_bar;
    private RelativeLayout fragment_container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_my_flight);

        initWidget();

    }

    private void initWidget() {
        fragment_container = findViewById(R.id.fragment_container);
        bottom_nav_bar = findViewById(R.id.bottom_nav_bar);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new  MyFlightFragment()).commit();
        bottom_nav_bar.setItemSelected(R.id.bottom_nav_ticket, true);
        bottomMenu();
    }

    //Don't touch yet
    private void bottomMenu() {

        bottom_nav_bar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch(i)
                {
                    case R.id.bottom_nav_ticket:
                        fragment = new MyFlightFragment();
                        break;

                    case R.id.bottom_nav_schedule:
                        fragment = new Schedule_Fragment();
                        break;

                    case R.id.bottom_nav_shop:
                        fragment = new ShopFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

            }
        });

    }
}