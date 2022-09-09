package com.example.spaced;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainNavigationPage_Activity extends AppCompatActivity {

    private ChipNavigationBar side_menu;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment = null;
    private RelativeLayout LL_mainNav;

    private ChangeBounds changeBounds = new ChangeBounds();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_navigation_page);

        initWidget();


    }

    private void pageDirectories() {
        if (fragment == null)
        {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.LL_mainNav, new HomeFragment());
            fragmentTransaction.commit();
            side_menu.setItemSelected(R.id.home, true);
        }

        side_menu.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i)
                {
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;

                    case R.id.planets:
                        //Add fragment later
                        break;

                    case R.id.tickets:
                        //Add fragment later
                        break;

                    case R.id.MyFlight:
                        fragment = new MyFlightFragment();
                        break;

                    case R.id.Settings:
                        //Add fragment later
                        break;
                }
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.LL_mainNav, fragment);
                fragmentTransaction.commit();
            }
        });
    }

    private void initWidget() {
        //--->Chip Navigation
        side_menu = findViewById(R.id.side_menu);

        //--->LinearLayout
        LL_mainNav = findViewById(R.id.LL_mainNav);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.LL_mainNav, new HomeFragment());
        fragmentTransaction.commit();
        side_menu.setItemSelected(R.id.home, true);

        pageDirectories();
    }
}