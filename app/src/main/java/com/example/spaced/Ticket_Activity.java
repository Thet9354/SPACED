package com.example.spaced;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Ticket_Activity extends AppCompatActivity {

    private ViewPager planet_slideViewpager, launchPad_slideViewpager;

    private TicketViewPageAdapter ticketViewPageAdapter;
    private LaunchPad_ViewPageAdapter launchPad_viewPageAdapter;

    private NavigationView nav_view;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout parent;

    private int intAdult = 1;
    private String adults;

    private TextView txtView_adultNumber, txtView_planetName, txtView_planetPrice;
    private ImageView btn_addAdventurer, btn_minusAdventurer, btn_leftArrow, btn_rightArrow, btn_planetMoreInfo;
    private Button btn_proceed;

    //--->Variable to store finalized number of passenger, planet and launchpad
    private String selectedPlanet, selectedLaunchPad, selectedNumberOfPassengers, planetPrice;

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
        setContentView(R.layout.activity_ticket);

        initWidget();

        pageDirectories();

    }

    private void pageDirectories() {

        //--->NavigationItemSelectedListener for nav_view
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
                        //---> Lead to the planet page
                        startActivity(new Intent(getApplicationContext(), Moon_Activity.class));
                        break;
                    }
                    case R.id.tickets:
                    {
                        //---> Lead to the ticket page
                        onBackPressed();
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

        //--->setOnClickListener for btn_leftArrow
        btn_leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) > 0){

                    launchPad_slideViewpager.setCurrentItem(getitem(-1),true);

                }

            }
        });

        //--->setOnClickListener for btn_rightArrow
        btn_rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) < 7)
                    launchPad_slideViewpager.setCurrentItem(getitem(1),true);
                else {
                    finish();
                }

            }
        });

        //--->onClickListener for btn_addAdventurer
        btn_addAdventurer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intAdult = intAdult + 1;
                adults = String.valueOf(intAdult);
                txtView_adultNumber.setText(adults);
            }
        });

        //--->onClickListener for btn_minusAdventurer
        btn_minusAdventurer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intAdult = intAdult - 1;
                if (intAdult < 1)
                    intAdult = 1;
                adults = String.valueOf(intAdult);
                txtView_adultNumber.setText(adults);

            }
        });

        //--->onClickListener for btn_proceed
        btn_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Ticket_Activity.this);
                builder.setMessage("Are you sure you want to proceed?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Proceed as per normal

                        selectedNumberOfPassengers();

                        selectedPlanet();

                        selectedLaunchPad();

                        saveData();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Don't do anything and go back to the same page
                    }
                });

                builder.create().show();
            }
        });
    }

    private void selectedNumberOfPassengers() {
        selectedNumberOfPassengers = txtView_adultNumber.getText().toString();
        System.out.println(selectedNumberOfPassengers);
    }

    private void saveData() {
        //Save values and transfer it to the next page, next page will defer base on the transferred values
        if (selectedPlanet.isEmpty() | selectedLaunchPad.isEmpty() | selectedNumberOfPassengers.isEmpty())
        {
            Toast.makeText(this, "An error has occurred please try again", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), TicketSchedule_Activity.class);
            intent.putExtra("NOP", selectedNumberOfPassengers);
            intent.putExtra("Planet", selectedPlanet);
            intent.putExtra("LaunchPad", selectedLaunchPad);
            intent.putExtra("Price", planetPrice);
            startActivity(intent);
        }

    }

    private void selectedLaunchPad() {


        switch (selectedLaunchPad)
        {
            case "Leicester Space Center":
                selectedLaunchPad = "Leicester Space Center";
                break;

            case "Toulouse Space Center":
                selectedLaunchPad = "Toulouse Space Center";
                break;

            case "Noordwijk Space Center":
                selectedLaunchPad = "Noordwijk Space Center";
                break;

            case "Washington Space Center":
                selectedLaunchPad = "Washington Space Center";
                break;

            case "Wallasey Space Port":
                selectedLaunchPad = "Wallasey Space Port";
                break;

            case "Transinne Euro Space":
                selectedLaunchPad = "Transinne Euro Space";
                break;

            case "Kennedy Space Center":
                selectedLaunchPad = "Kennedy Space Center";
                break;
        }
    }

    private void selectedPlanet() {

        selectedPlanet = txtView_planetName.getText().toString();

        //---> Store value and send that value to the next page
        switch (selectedPlanet)
        {
            case "Moon":
                selectedPlanet = "Moon";
                planetPrice = "2999";
                System.out.println(selectedPlanet);
                break;

            case "Mars":
                selectedPlanet = "Mars";
                planetPrice = "5999";
                System.out.println(selectedPlanet);
                break;

            case "Uranus":
                selectedPlanet = "Uranus";
                planetPrice = "3999";
                System.out.println(selectedPlanet);
                break;

            case "Mercury":
                selectedPlanet = "Mercury";
                planetPrice = "9999";
                System.out.println(selectedPlanet);
                break;

            case "Venus":
                selectedPlanet = "Venus";
                planetPrice = "10,099";
                System.out.println(selectedPlanet);
                break;

            case "Jupiter":
                selectedPlanet = "Jupiter";
                planetPrice = "4999";
                System.out.println(selectedPlanet);
                break;

            case "Saturn":
                selectedPlanet = "Saturn";
                planetPrice = "5999";
                System.out.println(selectedPlanet);
                break;

            case "Neptune":
                selectedPlanet = "Neptune";
                planetPrice = "7999";
                System.out.println(selectedPlanet);
                break;

            case "Pluto":
                selectedPlanet = "Pluto";
                planetPrice = "10099";
                System.out.println(selectedPlanet);
                break;

            case "Makemake":
                selectedPlanet = "Makemake";
                planetPrice = "13999";
                System.out.println(selectedPlanet);
                break;

            case "Ceres":
                selectedPlanet = "Ceres";
                planetPrice = "15899";
                System.out.println(selectedPlanet);
                break;
        }
    }

    private void initWidget() {
        //--->ImageView
        btn_addAdventurer = findViewById(R.id.btn_addAdventurer);
        btn_minusAdventurer = findViewById(R.id.btn_minusAdventurer);
        btn_leftArrow = findViewById(R.id.btn_leftArrow);
        btn_rightArrow = findViewById(R.id.btn_rightArrow);
        btn_planetMoreInfo = findViewById(R.id.btn_planetMoreInfo);

        btn_leftArrow.setVisibility(View.INVISIBLE);

        //--->DrawerLayout
        parent = (DrawerLayout) findViewById(R.id.parent);

        //--->TextView
        txtView_adultNumber = findViewById(R.id.txtView_adultNumber);
        txtView_planetName = findViewById(R.id.txtView_planetName);
        txtView_planetPrice = findViewById(R.id.txtView_planetPrice);

        //--->Button
        btn_proceed = findViewById(R.id.btn_proceed);

        //--->ViewPager
        planet_slideViewpager = (ViewPager) findViewById(R.id.planet_slideViewpager);
        ticketViewPageAdapter = new TicketViewPageAdapter(this);
        planet_slideViewpager.setAdapter(ticketViewPageAdapter);
        planet_slideViewpager.addOnPageChangeListener(viewListener);

        launchPad_slideViewpager = (ViewPager) findViewById(R.id.launchPad_slideViewpager);
        launchPad_viewPageAdapter = new LaunchPad_ViewPageAdapter(this);
        launchPad_slideViewpager.setAdapter(launchPad_viewPageAdapter);
        launchPad_slideViewpager.addOnPageChangeListener(launchPadViewListener);

        //--->NavigationView
        nav_view = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(Ticket_Activity.this, parent, R.string.open, R.string.close);
        parent.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            //--->Lead user to the respective planet page
            //TODO: Create individual page for each respective planet
            switch (position)
            {
                case 0:

                    selectedPlanet = "Moon";
                    System.out.println(selectedPlanet);

                    txtView_planetName.setText(R.string.The_Moon);
                    txtView_planetPrice.setText(R.string.Moon_Price);

                    btn_planetMoreInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getApplicationContext(), MoonPage_Activity.class));
                        }
                    });
                    break;

                case 1:

                    selectedPlanet = "Mars";
                    System.out.println(selectedPlanet);

                    txtView_planetName.setText(R.string.Mars);
                    txtView_planetPrice.setText(R.string.Mars_price);

                    btn_planetMoreInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getApplicationContext(), MarsPage_Activity.class));
                        }
                    });
                    break;

                case 2:

                    selectedPlanet = "Uranus";
                    System.out.println(selectedPlanet);

                    txtView_planetName.setText(R.string.Uranus);
                    txtView_planetPrice.setText(R.string.Uranus_price);

                    btn_planetMoreInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getApplicationContext(), UranusPage_Activity.class));
                        }
                    });
                    break;

                case 3:

                    selectedPlanet = "Mercury";
                    System.out.println(selectedPlanet);

                    txtView_planetName.setText(R.string.Mercury);
                    txtView_planetPrice.setText(R.string.Mercury_price);

                    btn_planetMoreInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getApplicationContext(), MercuryPage_Activity.class));
                        }
                    });
                    break;

                case 4:

                    selectedPlanet = "Venus";
                    System.out.println(selectedPlanet);

                    txtView_planetName.setText(R.string.Venus);
                    txtView_planetPrice.setText(R.string.Venus_price);

                    btn_planetMoreInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getApplicationContext(), VenusPage_Activity.class));
                        }
                    });
                    break;

                case 5:

                    selectedPlanet = "Jupiter";
                    System.out.println(selectedPlanet);

                    txtView_planetName.setText(R.string.Jupiter);
                    txtView_planetPrice.setText(R.string.Jupiter_price);

                    btn_planetMoreInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getApplicationContext(), JupiterPage_Activity.class));
                        }
                    });
                    break;

                case 6:

                    selectedPlanet = "Saturn";
                    System.out.println(selectedPlanet);

                    txtView_planetName.setText(R.string.Saturn);
                    txtView_planetPrice.setText(R.string.Saturn_price);

                    btn_planetMoreInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getApplicationContext(), SaturnPage_Activity.class));
                        }
                    });
                    break;

                case 7:

                    selectedPlanet = "Neptune";
                    System.out.println(selectedPlanet);

                    txtView_planetName.setText(R.string.Neptune);
                    txtView_planetPrice.setText(R.string.Neptune_price);

                    btn_planetMoreInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getApplicationContext(), NeptunePage_Activity.class));
                        }
                    });
                    break;

                case 8:

                    selectedPlanet = "Eris";
                    System.out.println(selectedPlanet);

                    txtView_planetName.setText(R.string.Eris);
                    txtView_planetPrice.setText(R.string.Eris_price);

                    btn_planetMoreInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getApplicationContext(), ErisPage_Activity.class));
                        }
                    });
                    break;

                case 9:

                    selectedPlanet = "Pluto";
                    System.out.println(selectedPlanet);

                    txtView_planetName.setText(R.string.Pluto);
                    txtView_planetPrice.setText(R.string.Pluto_price);

                    btn_planetMoreInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getApplicationContext(), PlutoPage_Activity.class));
                        }
                    });
                    break;

                case 10:

                    selectedPlanet = "Makemake";
                    System.out.println(selectedPlanet);

                    txtView_planetName.setText(R.string.Makemake);
                    txtView_planetPrice.setText(R.string.Makemake_price);

                    btn_planetMoreInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getApplicationContext(), MakemakePage_Activity.class));
                        }
                    });
                    break;

                case 11:

                    selectedPlanet = "Ceres";
                    System.out.println(selectedPlanet);

                    txtView_planetName.setText(R.string.Ceres);
                    txtView_planetPrice.setText(R.string.Ceres_price);

                    btn_planetMoreInfo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(getApplicationContext(), CeresPage_Activity.class));
                        }
                    });
                    break;
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    ViewPager.OnPageChangeListener launchPadViewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position > 0)
                btn_leftArrow.setVisibility(View.VISIBLE);
            else
                btn_leftArrow.setVisibility(View.INVISIBLE);


            if (position == 6)
                btn_rightArrow.setVisibility(View.INVISIBLE);
            else
                btn_rightArrow.setVisibility(View.VISIBLE);


            switch (position)
            {
                case 0:
                    selectedLaunchPad = "Leicester Space Center";
                    System.out.println(selectedLaunchPad);
                    break;

                case 1:
                    selectedLaunchPad = "Toulouse Space Center";
                    System.out.println(selectedLaunchPad);
                    break;

                case 2:
                    selectedLaunchPad = "Noordwijk Space Center";
                    System.out.println(selectedLaunchPad);
                    break;

                case 3:
                    selectedLaunchPad = "Washington Space Center";
                    System.out.println(selectedLaunchPad);
                    break;

                case 4:
                    selectedLaunchPad = "Wallasey Space Port";
                    System.out.println(selectedLaunchPad);
                    break;

                case 5:
                    selectedLaunchPad = "Transinne Euro Space";
                    System.out.println(selectedLaunchPad);
                    break;

                case 6:
                    selectedLaunchPad = "Kennedy Space Center";
                    System.out.println(selectedLaunchPad);
                    break;
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int i){

        return launchPad_slideViewpager.getCurrentItem() + i;
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
}