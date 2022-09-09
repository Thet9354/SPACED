package com.example.spaced;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class TicketSchedule_Activity extends AppCompatActivity {

    //--->CardView
    private CardView cardView_1, cardView_2, cardView_3, cardView_4, cardView_5, cardView_6, cardView_7,
            cardView_8, cardView_9, cardView_10;

    //--->ImageView
    private ImageView btn_back, img_flightInfo, img_flightInfo2, img_flightInfo3, img_flightInfo4, img_flightInfo5,
            img_flightInfo6, img_flightInfo7, img_flightInfo8, img_flightInfo9, img_flightInfo10;

    //--->TextView [CardView1]
    private TextView txtView_departureCountry, txtView_departureTiming, flightDuration, txtView_arrivalPlanet,
            txtView_arrivalTiming, txtView_spaceShip;

    //--->TextView [CardView2]
    private TextView txtView_departureCountry2, txtView_departureTiming2, flightDuration2, txtView_arrivalPlanet2,
            txtView_arrivalTiming2, txtView_spaceShip2;

    //--->TextView [CardView3]
    private TextView txtView_departureCountry3, txtView_departureTiming3, flightDuration3, txtView_arrivalPlanet3,
            txtView_arrivalTiming3, txtView_spaceShip3;

    //--->TextView [CardView4]
    private TextView txtView_departureCountry4, txtView_departureTiming4, flightDuration4, txtView_arrivalPlanet4,
            txtView_arrivalTiming4, txtView_spaceShip4;

    //--->TextView [CardView5]
    private TextView txtView_departureCountry5, txtView_departureTiming5, flightDuration5, txtView_arrivalPlanet5,
            txtView_arrivalTiming5, txtView_spaceShip5;

    //--->TextView [CardView6]
    private TextView txtView_departureCountry6, txtView_departureTiming6, flightDuration6, txtView_arrivalPlanet6,
            txtView_arrivalTiming6, txtView_spaceShip6;

    //--->TextView [CardView7]
    private TextView txtView_departureCountry7, txtView_departureTiming7, flightDuration7, txtView_arrivalPlanet7,
            txtView_arrivalTiming7, txtView_spaceShip7;

    //--->TextView [CardView8]
    private TextView txtView_departureCountry8, txtView_departureTiming8, flightDuration8, txtView_arrivalPlanet8,
            txtView_arrivalTiming8, txtView_spaceShip8;

    //--->TextView [CardView9]
    private TextView txtView_departureCountry9, txtView_departureTiming9, flightDuration9, txtView_arrivalPlanet9,
            txtView_arrivalTiming9, txtView_spaceShip9;

    //--->TextView [CardView10]
    private TextView txtView_departureCountry10, txtView_departureTiming10, flightDuration10, txtView_arrivalPlanet10,
            txtView_arrivalTiming10, txtView_spaceShip10;

    //--->CalendarView
    private CalendarView ticket_calendarView;

    //--->Calendar variables
    private int currentYear, currentMonth, currentDate;

    //--->Intent
    private Intent intent;

    //--->Variable for transferred data
    private String NOP, planet, launchpad, planetPrice;

    //--->Variable to store finalized arrivalPlanet, departureTime, arrivalTime, flightDuration, LaunchShipID
    private String arrivalPlanet, departurePlanet, departureTime, arrivalTime, destinationDuration, LaunchShipID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_schedule);

        intent = getIntent();

        transferIntentData();

        initWidget();

        initialCalendarSetting();

        scheduleAdjustment();

        pageDirectories();
    }

    private void initialCalendarSetting() {
        currentYear = Calendar.getInstance().get(Calendar.YEAR);
        currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        currentDate = Calendar.getInstance().get(Calendar.DATE);

        if ((currentDate % 2) == 0) {
            //--->Even date will have a different set of schedule timing than odd dates
            EvenDateSchedule();
        } else if ((currentDate % 2) == 1) {
            //--->Different set of schedule for odd dates
            OddDateSchedule();
        }
    }

    private void scheduleAdjustment() {
        ticket_calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int date) {
                currentYear = Calendar.getInstance().get(Calendar.YEAR);
                currentMonth = Calendar.getInstance().get(Calendar.MONTH);
                currentDate = Calendar.getInstance().get(Calendar.DATE);

                if ((year < currentYear) | (month < currentMonth) | (date < currentDate)) {
                    Toast.makeText(TicketSchedule_Activity.this, "Please select a valid year, month and date", Toast.LENGTH_SHORT).show();
                    cardView_1.setVisibility(View.GONE);
                    cardView_2.setVisibility(View.GONE);
                    cardView_3.setVisibility(View.GONE);
                    cardView_4.setVisibility(View.GONE);
                    cardView_5.setVisibility(View.GONE);
                    cardView_6.setVisibility(View.GONE);
                    cardView_7.setVisibility(View.GONE);
                    cardView_8.setVisibility(View.GONE);
                    cardView_9.setVisibility(View.GONE);
                    cardView_10.setVisibility(View.GONE);
                } else if ((date % 2) == 0) {
                    cardView_1.setVisibility(View.VISIBLE);
                    cardView_2.setVisibility(View.VISIBLE);
                    cardView_3.setVisibility(View.VISIBLE);
                    cardView_4.setVisibility(View.VISIBLE);
                    cardView_5.setVisibility(View.VISIBLE);
                    cardView_6.setVisibility(View.VISIBLE);
                    cardView_7.setVisibility(View.VISIBLE);
                    cardView_8.setVisibility(View.VISIBLE);
                    cardView_9.setVisibility(View.VISIBLE);
                    cardView_10.setVisibility(View.VISIBLE);
                    //--->Even date will have a different set of schedule timing than odd dates
                    EvenDateSchedule();
                } else if ((date % 2) == 1) {
                    cardView_1.setVisibility(View.VISIBLE);
                    cardView_2.setVisibility(View.VISIBLE);
                    cardView_3.setVisibility(View.VISIBLE);
                    cardView_4.setVisibility(View.VISIBLE);
                    cardView_5.setVisibility(View.VISIBLE);
                    cardView_6.setVisibility(View.VISIBLE);
                    cardView_7.setVisibility(View.VISIBLE);
                    cardView_8.setVisibility(View.VISIBLE);
                    cardView_9.setVisibility(View.VISIBLE);
                    cardView_10.setVisibility(View.VISIBLE);
                    //--->Different set of schedule for odd dates
                    OddDateSchedule();
                }
            }
        });
    }

    private void OddDateSchedule() {

        switch (planet) {
            case "Moon":
                oddDateSchedule_Moon();
                break;

            case "Mars":
                oddDateSchedule_Mars();
                break;

            case "Uranus":
                oddDateSchedule_Uranus();
                break;

            case "Mercury":
                oddDateSchedule_Mercury();
                break;

            case "Venus":
                oddDateSchedule_Venus();
                break;

            case "Jupiter":
                oddDateSchedule_Jupiter();
                break;

            case "Saturn":
                oddDateSchedule_Saturn();
                break;

            case "Neptune":
                oddDateSchedule_Neptune();
                break;

            case "Eris":
                oddDateSchedule_Eris();
                break;

            case "Pluto":
                oddDateSchedule_Pluto();
                break;

            case "Makemake":
                oddDateSchedule_Makemake();
                break;

            case "Ceres":
                oddDateSchedule_Ceres();
                break;
        }
    }

    private void oddDateSchedule_Ceres() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_1);
        flightDuration.setText(R.string.Duration_to_Ceres);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_1);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_2);
        flightDuration.setText(R.string.Duration_to_Ceres);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_2);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_3);
        flightDuration.setText(R.string.Duration_to_Ceres);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_3);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_4);
        flightDuration.setText(R.string.Duration_to_Ceres);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_4);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_5);
        flightDuration.setText(R.string.Duration_to_Ceres);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_5);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_6);
        flightDuration.setText(R.string.Duration_to_Ceres);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_6);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_7);
        flightDuration.setText(R.string.Duration_to_Ceres);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_7);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_8);
        flightDuration.setText(R.string.Duration_to_Ceres);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_8);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_9);
        flightDuration.setText(R.string.Duration_to_Ceres);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_9);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_10);
        flightDuration.setText(R.string.Duration_to_Ceres);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_10);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    } //TODO: Settle the departure and arrival timing later

    private void oddDateSchedule_Makemake() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_1);
        flightDuration.setText(R.string.Duration_to_Makemake);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_1);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_2);
        flightDuration.setText(R.string.Duration_to_Makemake);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_2);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_3);
        flightDuration.setText(R.string.Duration_to_Makemake);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_3);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_4);
        flightDuration.setText(R.string.Duration_to_Makemake);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_4);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_5);
        flightDuration.setText(R.string.Duration_to_Makemake);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_5);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_6);
        flightDuration.setText(R.string.Duration_to_Makemake);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_6);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_7);
        flightDuration.setText(R.string.Duration_to_Makemake);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_7);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_8);
        flightDuration.setText(R.string.Duration_to_Makemake);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_8);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_9);
        flightDuration.setText(R.string.Duration_to_Makemake);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_9);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_10);
        flightDuration.setText(R.string.Duration_to_Makemake);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_10);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    } //TODO: Settle the departure and arrival timing later

    private void oddDateSchedule_Pluto() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_1);
        flightDuration.setText(R.string.Duration_to_Pluto);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_1);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_2);
        flightDuration.setText(R.string.Duration_to_Pluto);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_2);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_3);
        flightDuration.setText(R.string.Duration_to_Pluto);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_3);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_4);
        flightDuration.setText(R.string.Duration_to_Pluto);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_4);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_5);
        flightDuration.setText(R.string.Duration_to_Pluto);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_5);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_6);
        flightDuration.setText(R.string.Duration_to_Pluto);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_6);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_7);
        flightDuration.setText(R.string.Duration_to_Pluto);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_7);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_8);
        flightDuration.setText(R.string.Duration_to_Pluto);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_8);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_9);
        flightDuration.setText(R.string.Duration_to_Pluto);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_9);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_10);
        flightDuration.setText(R.string.Duration_to_Pluto);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_10);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    } //TODO: Settle the departure and arrival timing later

    private void oddDateSchedule_Eris() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_1);
        flightDuration.setText(R.string.Duration_to_Eris);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_1);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_2);
        flightDuration.setText(R.string.Duration_to_Eris);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_2);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_3);
        flightDuration.setText(R.string.Duration_to_Eris);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_3);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_4);
        flightDuration.setText(R.string.Duration_to_Eris);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_4);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_5);
        flightDuration.setText(R.string.Duration_to_Eris);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_5);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_6);
        flightDuration.setText(R.string.Duration_to_Eris);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_6);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_7);
        flightDuration.setText(R.string.Duration_to_Eris);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_7);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_8);
        flightDuration.setText(R.string.Duration_to_Eris);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_8);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_9);
        flightDuration.setText(R.string.Duration_to_Eris);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_9);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_10);
        flightDuration.setText(R.string.Duration_to_Eris);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_10);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    } //TODO: Settle the departure and arrival timing later

    private void oddDateSchedule_Neptune() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_1);
        flightDuration.setText(R.string.Duration_to_Neptune);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_1);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_2);
        flightDuration.setText(R.string.Duration_to_Neptune);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_2);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_3);
        flightDuration.setText(R.string.Duration_to_Neptune);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_3);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_4);
        flightDuration.setText(R.string.Duration_to_Neptune);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_4);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_5);
        flightDuration.setText(R.string.Duration_to_Neptune);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_5);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_6);
        flightDuration.setText(R.string.Duration_to_Neptune);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_6);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_7);
        flightDuration.setText(R.string.Duration_to_Neptune);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_7);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_8);
        flightDuration.setText(R.string.Duration_to_Neptune);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_8);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_9);
        flightDuration.setText(R.string.Duration_to_Neptune);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_9);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_10);
        flightDuration.setText(R.string.Duration_to_Neptune);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_10);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    } //TODO: Settle the departure and arrival timing later

    private void oddDateSchedule_Saturn() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_1);
        flightDuration.setText(R.string.Duration_to_Saturn);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_1);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_2);
        flightDuration.setText(R.string.Duration_to_Saturn);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_2);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_3);
        flightDuration.setText(R.string.Duration_to_Saturn);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_3);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_4);
        flightDuration.setText(R.string.Duration_to_Saturn);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_4);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_5);
        flightDuration.setText(R.string.Duration_to_Saturn);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_5);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_6);
        flightDuration.setText(R.string.Duration_to_Saturn);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_6);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_7);
        flightDuration.setText(R.string.Duration_to_Saturn);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_7);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_8);
        flightDuration.setText(R.string.Duration_to_Saturn);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_8);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_9);
        flightDuration.setText(R.string.Duration_to_Saturn);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_9);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_10);
        flightDuration.setText(R.string.Duration_to_Saturn);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_10);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    } //TODO: Settle the departure and arrival timing later

    private void oddDateSchedule_Jupiter() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_1);
        flightDuration.setText(R.string.Duration_to_Jupiter);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_1);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_2);
        flightDuration.setText(R.string.Duration_to_Jupiter);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_2);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_3);
        flightDuration.setText(R.string.Duration_to_Jupiter);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_3);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_4);
        flightDuration.setText(R.string.Duration_to_Jupiter);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_4);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_5);
        flightDuration.setText(R.string.Duration_to_Jupiter);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_5);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_6);
        flightDuration.setText(R.string.Duration_to_Jupiter);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_6);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_7);
        flightDuration.setText(R.string.Duration_to_Jupiter);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_7);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_8);
        flightDuration.setText(R.string.Duration_to_Jupiter);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_8);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_9);
        flightDuration.setText(R.string.Duration_to_Jupiter);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_9);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_10);
        flightDuration.setText(R.string.Duration_to_Jupiter);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_10);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    } //TODO: Settle the departure and arrival timing later

    private void oddDateSchedule_Venus() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_1);
        flightDuration.setText(R.string.Duration_to_Venus);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_1);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_2);
        flightDuration.setText(R.string.Duration_to_Venus);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_2);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_3);
        flightDuration.setText(R.string.Duration_to_Venus);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_3);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_4);
        flightDuration.setText(R.string.Duration_to_Venus);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_4);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_5);
        flightDuration.setText(R.string.Duration_to_Venus);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_5);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_6);
        flightDuration.setText(R.string.Duration_to_Venus);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_6);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_7);
        flightDuration.setText(R.string.Duration_to_Venus);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_7);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_8);
        flightDuration.setText(R.string.Duration_to_Venus);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_8);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_9);
        flightDuration.setText(R.string.Duration_to_Venus);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_9);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_10);
        flightDuration.setText(R.string.Duration_to_Venus);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_10);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    } //TODO: Settle the departure and arrival timing later

    private void oddDateSchedule_Mercury() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_1);
        flightDuration.setText(R.string.Duration_to_Mercury);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_1);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_2);
        flightDuration.setText(R.string.Duration_to_Mercury);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_2);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_3);
        flightDuration.setText(R.string.Duration_to_Mercury);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_3);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_4);
        flightDuration.setText(R.string.Duration_to_Mercury);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_4);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_5);
        flightDuration.setText(R.string.Duration_to_Mercury);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_5);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_6);
        flightDuration.setText(R.string.Duration_to_Mercury);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_6);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_7);
        flightDuration.setText(R.string.Duration_to_Mercury);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_7);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_8);
        flightDuration.setText(R.string.Duration_to_Mercury);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_8);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_9);
        flightDuration.setText(R.string.Duration_to_Mercury);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_9);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_10);
        flightDuration.setText(R.string.Duration_to_Mercury);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_10);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    } //TODO: Settle the departure and arrival timing later

    private void oddDateSchedule_Uranus() {
        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_1);
        flightDuration.setText(R.string.Duration_to_Uranus);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_1);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_2);
        flightDuration.setText(R.string.Duration_to_Uranus);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_2);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_3);
        flightDuration.setText(R.string.Duration_to_Uranus);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_3);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_4);
        flightDuration.setText(R.string.Duration_to_Uranus);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_4);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_5);
        flightDuration.setText(R.string.Duration_to_Uranus);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_5);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_6);
        flightDuration.setText(R.string.Duration_to_Uranus);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_6);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_7);
        flightDuration.setText(R.string.Duration_to_Uranus);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_7);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_8);
        flightDuration.setText(R.string.Duration_to_Uranus);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_8);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_9);
        flightDuration.setText(R.string.Duration_to_Uranus);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_9);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_10);
        flightDuration.setText(R.string.Duration_to_Uranus);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_10);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    } //TODO: Settle the departure and arrival timing later

    private void oddDateSchedule_Mars() {
        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_1);
        flightDuration.setText(R.string.Duration_to_Mars);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_1);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_2);
        flightDuration.setText(R.string.Duration_to_Mars);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_2);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_3);
        flightDuration.setText(R.string.Duration_to_Mars);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_3);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_4);
        flightDuration.setText(R.string.Duration_to_Mars);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_4);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_5);
        flightDuration.setText(R.string.Duration_to_Mars);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_5);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_6);
        flightDuration.setText(R.string.Duration_to_Mars);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_6);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_7);
        flightDuration.setText(R.string.Duration_to_Mars);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_7);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_8);
        flightDuration.setText(R.string.Duration_to_Mars);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_8);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_9);
        flightDuration.setText(R.string.Duration_to_Mars);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_9);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_10);
        flightDuration.setText(R.string.Duration_to_Mars);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_10);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    } //TODO: Settle the departure and arrival timing later

    private void oddDateSchedule_Moon() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_1);
        flightDuration.setText(R.string.Duration_to_Moon);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_1);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_2);
        flightDuration.setText(R.string.Duration_to_Moon);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_2);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_3);
        flightDuration.setText(R.string.Duration_to_Moon);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_3);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_4);
        flightDuration.setText(R.string.Duration_to_Moon);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_4);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_5);
        flightDuration.setText(R.string.Duration_to_Moon);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_5);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_6);
        flightDuration.setText(R.string.Duration_to_Moon);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_6);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_7);
        flightDuration.setText(R.string.Duration_to_Moon);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_7);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_8);
        flightDuration.setText(R.string.Duration_to_Moon);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_8);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_9);
        flightDuration.setText(R.string.Duration_to_Moon);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_9);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_10);
        flightDuration.setText(R.string.Duration_to_Moon);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_10);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    } //TODO: Settle the departure and arrival timing later

    private void EvenDateSchedule() {

        switch (planet) {
            case "Moon":
                evenDateSchedule_Moon();
                break;

            case "Mars":
                evenDateSchedule_Mars();
                break;

            case "Uranus":
                evenDateSchedule_Uranus();
                break;

            case "Mercury":
                evenDateSchedule_Mercury();
                break;

            case "Venus":
                evenDateSchedule_Venus();
                break;

            case "Jupiter":
                evenDateSchedule_Jupiter();
                break;

            case "Saturn":
                evenDateSchedule_Saturn();
                break;

            case "Neptune":
                evenDateSchedule_Neptune();
                break;

            case "Eris":
                evenDateSchedule_Eris();
                break;

            case "Pluto":
                evenDateSchedule_Pluto();
                break;

            case "Makemake":
                evenDateSchedule_Makemake();
                break;

            case "Ceres":
                evenDateSchedule_Ceres();
                break;
        }

    } //TODO: Settle the departure and arrival timing later

    private void evenDateSchedule_Ceres() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_11);
        flightDuration.setText(R.string.Duration_to_Ceres);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_11);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_12);
        flightDuration.setText(R.string.Duration_to_Ceres);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_12);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_13);
        flightDuration.setText(R.string.Duration_to_Ceres);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_13);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_14);
        flightDuration.setText(R.string.Duration_to_Ceres);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_14);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_15);
        flightDuration.setText(R.string.Duration_to_Ceres);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_15);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_16);
        flightDuration.setText(R.string.Duration_to_Ceres);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_16);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_17);
        flightDuration.setText(R.string.Duration_to_Ceres);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_17);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_18);
        flightDuration.setText(R.string.Duration_to_Ceres);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_18);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_19);
        flightDuration.setText(R.string.Duration_to_Ceres);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_19);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_20);
        flightDuration.setText(R.string.Duration_to_Ceres);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_20);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    }

    private void evenDateSchedule_Makemake() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_11);
        flightDuration.setText(R.string.Duration_to_Makemake);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_11);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_12);
        flightDuration.setText(R.string.Duration_to_Makemake);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_12);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_13);
        flightDuration.setText(R.string.Duration_to_Makemake);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_13);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_14);
        flightDuration.setText(R.string.Duration_to_Makemake);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_14);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_15);
        flightDuration.setText(R.string.Duration_to_Makemake);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_15);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_16);
        flightDuration.setText(R.string.Duration_to_Makemake);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_16);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_17);
        flightDuration.setText(R.string.Duration_to_Makemake);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_17);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_18);
        flightDuration.setText(R.string.Duration_to_Makemake);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_18);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_19);
        flightDuration.setText(R.string.Duration_to_Makemake);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_19);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_20);
        flightDuration.setText(R.string.Duration_to_Makemake);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_20);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    }

    private void evenDateSchedule_Pluto() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_11);
        flightDuration.setText(R.string.Duration_to_Pluto);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_11);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_12);
        flightDuration.setText(R.string.Duration_to_Pluto);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_12);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_13);
        flightDuration.setText(R.string.Duration_to_Pluto);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_13);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_14);
        flightDuration.setText(R.string.Duration_to_Pluto);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_14);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_15);
        flightDuration.setText(R.string.Duration_to_Pluto);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_15);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_16);
        flightDuration.setText(R.string.Duration_to_Pluto);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_16);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_17);
        flightDuration.setText(R.string.Duration_to_Pluto);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_17);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_18);
        flightDuration.setText(R.string.Duration_to_Pluto);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_18);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_19);
        flightDuration.setText(R.string.Duration_to_Pluto);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_19);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_20);
        flightDuration.setText(R.string.Duration_to_Pluto);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_20);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    }

    private void evenDateSchedule_Eris() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_11);
        flightDuration.setText(R.string.Duration_to_Eris);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_11);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_12);
        flightDuration.setText(R.string.Duration_to_Eris);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_12);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_13);
        flightDuration.setText(R.string.Duration_to_Eris);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_13);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_14);
        flightDuration.setText(R.string.Duration_to_Eris);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_14);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_15);
        flightDuration.setText(R.string.Duration_to_Eris);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_15);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_16);
        flightDuration.setText(R.string.Duration_to_Eris);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_16);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_17);
        flightDuration.setText(R.string.Duration_to_Eris);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_17);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_18);
        flightDuration.setText(R.string.Duration_to_Eris);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_18);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_19);
        flightDuration.setText(R.string.Duration_to_Eris);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_19);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_20);
        flightDuration.setText(R.string.Duration_to_Eris);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_20);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    }

    private void evenDateSchedule_Neptune() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_11);
        flightDuration.setText(R.string.Duration_to_Neptune);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_11);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_12);
        flightDuration.setText(R.string.Duration_to_Neptune);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_12);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_13);
        flightDuration.setText(R.string.Duration_to_Neptune);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_13);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_14);
        flightDuration.setText(R.string.Duration_to_Neptune);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_14);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_15);
        flightDuration.setText(R.string.Duration_to_Neptune);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_15);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_16);
        flightDuration.setText(R.string.Duration_to_Neptune);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_16);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_17);
        flightDuration.setText(R.string.Duration_to_Neptune);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_17);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_18);
        flightDuration.setText(R.string.Duration_to_Neptune);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_18);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_19);
        flightDuration.setText(R.string.Duration_to_Neptune);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_19);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_20);
        flightDuration.setText(R.string.Duration_to_Neptune);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_20);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    }

    private void evenDateSchedule_Saturn() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_11);
        flightDuration.setText(R.string.Duration_to_Saturn);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_11);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_12);
        flightDuration.setText(R.string.Duration_to_Saturn);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_12);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_13);
        flightDuration.setText(R.string.Duration_to_Saturn);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_13);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_14);
        flightDuration.setText(R.string.Duration_to_Saturn);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_14);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_15);
        flightDuration.setText(R.string.Duration_to_Saturn);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_15);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_16);
        flightDuration.setText(R.string.Duration_to_Saturn);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_16);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_17);
        flightDuration.setText(R.string.Duration_to_Saturn);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_17);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_18);
        flightDuration.setText(R.string.Duration_to_Saturn);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_18);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_19);
        flightDuration.setText(R.string.Duration_to_Saturn);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_19);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_20);
        flightDuration.setText(R.string.Duration_to_Saturn);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_20);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    }

    private void evenDateSchedule_Jupiter() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_11);
        flightDuration.setText(R.string.Duration_to_Jupiter);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_11);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_12);
        flightDuration.setText(R.string.Duration_to_Jupiter);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_12);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_13);
        flightDuration.setText(R.string.Duration_to_Jupiter);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_13);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_14);
        flightDuration.setText(R.string.Duration_to_Jupiter);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_14);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_15);
        flightDuration.setText(R.string.Duration_to_Jupiter);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_15);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_16);
        flightDuration.setText(R.string.Duration_to_Jupiter);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_16);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_17);
        flightDuration.setText(R.string.Duration_to_Jupiter);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_17);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_18);
        flightDuration.setText(R.string.Duration_to_Jupiter);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_18);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_19);
        flightDuration.setText(R.string.Duration_to_Jupiter);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_19);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_20);
        flightDuration.setText(R.string.Duration_to_Jupiter);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_20);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    }

    private void evenDateSchedule_Venus() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_11);
        flightDuration.setText(R.string.Duration_to_Venus);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_11);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_12);
        flightDuration.setText(R.string.Duration_to_Venus);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_12);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_13);
        flightDuration.setText(R.string.Duration_to_Venus);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_13);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_14);
        flightDuration.setText(R.string.Duration_to_Venus);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_14);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_15);
        flightDuration.setText(R.string.Duration_to_Venus);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_15);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_16);
        flightDuration.setText(R.string.Duration_to_Venus);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_16);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_17);
        flightDuration.setText(R.string.Duration_to_Venus);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_17);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_18);
        flightDuration.setText(R.string.Duration_to_Venus);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_18);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_19);
        flightDuration.setText(R.string.Duration_to_Venus);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_19);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_20);
        flightDuration.setText(R.string.Duration_to_Venus);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_20);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    }

    private void evenDateSchedule_Mercury() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_11);
        flightDuration.setText(R.string.Duration_to_Mercury);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_11);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_12);
        flightDuration.setText(R.string.Duration_to_Mercury);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_12);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_13);
        flightDuration.setText(R.string.Duration_to_Mercury);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_13);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_14);
        flightDuration.setText(R.string.Duration_to_Mercury);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_14);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_15);
        flightDuration.setText(R.string.Duration_to_Mercury);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_15);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_16);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_16);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_17);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_17);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_18);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_18);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_19);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_19);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_20);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_20);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    }

    private void evenDateSchedule_Uranus() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_11);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_11);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_12);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_12);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_13);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_13);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_14);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_14);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_15);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_15);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_16);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_16);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_17);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_17);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_18);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_18);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_19);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_19);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_20);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_20);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    }

    private void evenDateSchedule_Mars() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_11);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_11);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_12);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_12);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_13);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_13);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_14);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_14);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_15);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_15);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_16);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_16);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_17);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_17);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_18);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_18);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_19);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_19);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_20);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_20);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    }

    private void evenDateSchedule_Moon() {

        //--->[CardView1]
        txtView_departureCountry.setText("Earth");
        txtView_departureTiming.setText(R.string.Departure_time_11);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet.setText(planet);
        txtView_arrivalTiming.setText(R.string.Arrival_time_11);
        txtView_spaceShip.setText(R.string.SPACED_ID_1);

        //--->[CardView2]
        txtView_departureCountry2.setText("Earth");
        txtView_departureTiming2.setText(R.string.Departure_time_12);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet2.setText(planet);
        txtView_arrivalTiming2.setText(R.string.Arrival_time_12);
        txtView_spaceShip2.setText(R.string.SPACED_ID_2);

        //--->[CardView3]
        txtView_departureCountry3.setText("Earth");
        txtView_departureTiming3.setText(R.string.Departure_time_13);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet3.setText(planet);
        txtView_arrivalTiming3.setText(R.string.Arrival_time_13);
        txtView_spaceShip3.setText(R.string.SPACED_ID_3);

        //--->[CardView4]
        txtView_departureCountry4.setText("Earth");
        txtView_departureTiming4.setText(R.string.Departure_time_14);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet4.setText(planet);
        txtView_arrivalTiming4.setText(R.string.Arrival_time_14);
        txtView_spaceShip4.setText(R.string.SPACED_ID_4);

        //--->[CardView5]
        txtView_departureCountry5.setText("Earth");
        txtView_departureTiming5.setText(R.string.Departure_time_15);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet5.setText(planet);
        txtView_arrivalTiming5.setText(R.string.Arrival_time_15);
        txtView_spaceShip5.setText(R.string.SPACED_ID_5);

        //--->[CardView6]
        txtView_departureCountry6.setText("Earth");
        txtView_departureTiming6.setText(R.string.Departure_time_16);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet6.setText(planet);
        txtView_arrivalTiming6.setText(R.string.Arrival_time_16);
        txtView_spaceShip6.setText(R.string.SPACED_ID_6);

        //--->[CardView7]
        txtView_departureCountry7.setText("Earth");
        txtView_departureTiming7.setText(R.string.Departure_time_17);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet7.setText(planet);
        txtView_arrivalTiming7.setText(R.string.Arrival_time_17);
        txtView_spaceShip7.setText(R.string.SPACED_ID_7);

        //--->[CardView8]
        txtView_departureCountry8.setText("Earth");
        txtView_departureTiming8.setText(R.string.Departure_time_18);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet8.setText(planet);
        txtView_arrivalTiming8.setText(R.string.Arrival_time_18);
        txtView_spaceShip8.setText(R.string.SPACED_ID_8);

        //--->[CardView9]
        txtView_departureCountry9.setText("Earth");
        txtView_departureTiming9.setText(R.string.Departure_time_19);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet9.setText(planet);
        txtView_arrivalTiming9.setText(R.string.Arrival_time_19);
        txtView_spaceShip9.setText(R.string.SPACED_ID_9);

        //--->[CardView10]
        txtView_departureCountry10.setText("Earth");
        txtView_departureTiming10.setText(R.string.Departure_time_20);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet10.setText(planet);
        txtView_arrivalTiming10.setText(R.string.Arrival_time_20);
        txtView_spaceShip10.setText(R.string.SPACED_ID_10);
    }

    private void transferIntentData() {

        NOP = intent.getStringExtra("NOP");
        planet = intent.getStringExtra("Planet");
        launchpad = intent.getStringExtra("LaunchPad");
        planetPrice = intent.getStringExtra("Price");

        System.out.println(NOP);
        System.out.println(planet);
        System.out.println(launchpad);
        System.out.println(planetPrice);
    }

    private void pageDirectories() {

        //--->onCLickListener for cardView1
        cardView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TicketSchedule_Activity.this);
                builder.setMessage("Are you sure you want to proceed?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Save the data and move on to the next page

                        departurePlanet = txtView_departureCountry.getText().toString();
                        departureTime = txtView_departureTiming.getText().toString();
                        arrivalPlanet = txtView_arrivalPlanet.getText().toString();
                        arrivalTime = txtView_arrivalTiming.getText().toString();
                        destinationDuration = flightDuration.getText().toString();
                        LaunchShipID = txtView_spaceShip.getText().toString();

                        System.out.println(departurePlanet);
                        System.out.println(departureTime);
                        System.out.println(arrivalPlanet);
                        System.out.println(arrivalTime);
                        System.out.println(destinationDuration);
                        System.out.println(LaunchShipID);

                        savedData();


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

        //--->onCLickListener for cardView2
        cardView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TicketSchedule_Activity.this);
                builder.setMessage("Are you sure you want to proceed?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Save the data and move on to the next page

                        departurePlanet = txtView_departureCountry2.getText().toString();
                        departureTime = txtView_departureTiming2.getText().toString();
                        arrivalPlanet = txtView_arrivalPlanet2.getText().toString();
                        arrivalTime = txtView_arrivalTiming2.getText().toString();
                        destinationDuration = flightDuration2.getText().toString();
                        LaunchShipID = txtView_spaceShip2.getText().toString();

                        System.out.println(departurePlanet);
                        System.out.println(departureTime);
                        System.out.println(arrivalPlanet);
                        System.out.println(arrivalTime);
                        System.out.println(destinationDuration);
                        System.out.println(LaunchShipID);

                        savedData();


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

        //--->onCLickListener for cardView3
        cardView_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TicketSchedule_Activity.this);
                builder.setMessage("Are you sure you want to proceed?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Save the data and move on to the next page

                        departurePlanet = txtView_departureCountry3.getText().toString();
                        departureTime = txtView_departureTiming3.getText().toString();
                        arrivalPlanet = txtView_arrivalPlanet3.getText().toString();
                        arrivalTime = txtView_arrivalTiming3.getText().toString();
                        destinationDuration = flightDuration3.getText().toString();
                        LaunchShipID = txtView_spaceShip3.getText().toString();

                        System.out.println(departurePlanet);
                        System.out.println(departureTime);
                        System.out.println(arrivalPlanet);
                        System.out.println(arrivalTime);
                        System.out.println(destinationDuration);
                        System.out.println(LaunchShipID);

                        savedData();


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

        //--->onCLickListener for cardView4
        cardView_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TicketSchedule_Activity.this);
                builder.setMessage("Are you sure you want to proceed?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Save the data and move on to the next page

                        departurePlanet = txtView_departureCountry4.getText().toString();
                        departureTime = txtView_departureTiming4.getText().toString();
                        arrivalPlanet = txtView_arrivalPlanet4.getText().toString();
                        arrivalTime = txtView_arrivalTiming4.getText().toString();
                        destinationDuration = flightDuration4.getText().toString();
                        LaunchShipID = txtView_spaceShip4.getText().toString();

                        System.out.println(departurePlanet);
                        System.out.println(departureTime);
                        System.out.println(arrivalPlanet);
                        System.out.println(arrivalTime);
                        System.out.println(destinationDuration);
                        System.out.println(LaunchShipID);

                        savedData();


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

        //--->onCLickListener for cardView5
        cardView_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TicketSchedule_Activity.this);
                builder.setMessage("Are you sure you want to proceed?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Save the data and move on to the next page

                        departurePlanet = txtView_departureCountry5.getText().toString();
                        departureTime = txtView_departureTiming5.getText().toString();
                        arrivalPlanet = txtView_arrivalPlanet5.getText().toString();
                        arrivalTime = txtView_arrivalTiming5.getText().toString();
                        destinationDuration = flightDuration5.getText().toString();
                        LaunchShipID = txtView_spaceShip5.getText().toString();

                        System.out.println(departurePlanet);
                        System.out.println(departureTime);
                        System.out.println(arrivalPlanet);
                        System.out.println(arrivalTime);
                        System.out.println(destinationDuration);
                        System.out.println(LaunchShipID);

                        savedData();


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

        //--->onCLickListener for cardView6
        cardView_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TicketSchedule_Activity.this);
                builder.setMessage("Are you sure you want to proceed?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Save the data and move on to the next page

                        departurePlanet = txtView_departureCountry6.getText().toString();
                        departureTime = txtView_departureTiming6.getText().toString();
                        arrivalPlanet = txtView_arrivalPlanet6.getText().toString();
                        arrivalTime = txtView_arrivalTiming6.getText().toString();
                        destinationDuration = flightDuration6.getText().toString();
                        LaunchShipID = txtView_spaceShip6.getText().toString();

                        System.out.println(departurePlanet);
                        System.out.println(departureTime);
                        System.out.println(arrivalPlanet);
                        System.out.println(arrivalTime);
                        System.out.println(destinationDuration);
                        System.out.println(LaunchShipID);

                        savedData();


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

        //--->onCLickListener for cardView7
        cardView_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TicketSchedule_Activity.this);
                builder.setMessage("Are you sure you want to proceed?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Save the data and move on to the next page

                        departurePlanet = txtView_departureCountry7.getText().toString();
                        departureTime = txtView_departureTiming7.getText().toString();
                        arrivalPlanet = txtView_arrivalPlanet7.getText().toString();
                        arrivalTime = txtView_arrivalTiming7.getText().toString();
                        destinationDuration = flightDuration7.getText().toString();
                        LaunchShipID = txtView_spaceShip7.getText().toString();

                        System.out.println(departurePlanet);
                        System.out.println(departureTime);
                        System.out.println(arrivalPlanet);
                        System.out.println(arrivalTime);
                        System.out.println(destinationDuration);
                        System.out.println(LaunchShipID);

                        savedData();


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

        //--->onCLickListener for cardView8
        cardView_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TicketSchedule_Activity.this);
                builder.setMessage("Are you sure you want to proceed?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Save the data and move on to the next page

                        departurePlanet = txtView_departureCountry8.getText().toString();
                        departureTime = txtView_departureTiming8.getText().toString();
                        arrivalPlanet = txtView_arrivalPlanet8.getText().toString();
                        arrivalTime = txtView_arrivalTiming8.getText().toString();
                        destinationDuration = flightDuration8.getText().toString();
                        LaunchShipID = txtView_spaceShip8.getText().toString();

                        System.out.println(departurePlanet);
                        System.out.println(departureTime);
                        System.out.println(arrivalPlanet);
                        System.out.println(arrivalTime);
                        System.out.println(destinationDuration);
                        System.out.println(LaunchShipID);

                        savedData();


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

        //--->onCLickListener for cardView9
        cardView_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TicketSchedule_Activity.this);
                builder.setMessage("Are you sure you want to proceed?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Save the data and move on to the next page

                        departurePlanet = txtView_departureCountry9.getText().toString();
                        departureTime = txtView_departureTiming9.getText().toString();
                        arrivalPlanet = txtView_arrivalPlanet9.getText().toString();
                        arrivalTime = txtView_arrivalTiming9.getText().toString();
                        destinationDuration = flightDuration9.getText().toString();
                        LaunchShipID = txtView_spaceShip9.getText().toString();

                        System.out.println(departurePlanet);
                        System.out.println(departureTime);
                        System.out.println(arrivalPlanet);
                        System.out.println(arrivalTime);
                        System.out.println(destinationDuration);
                        System.out.println(LaunchShipID);

                        savedData();


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

        //--->onCLickListener for cardView10
        cardView_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TicketSchedule_Activity.this);
                builder.setMessage("Are you sure you want to proceed?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Save the data and move on to the next page

                        departurePlanet = txtView_departureCountry10.getText().toString();
                        departureTime = txtView_departureTiming10.getText().toString();
                        arrivalPlanet = txtView_arrivalPlanet10.getText().toString();
                        arrivalTime = txtView_arrivalTiming10.getText().toString();
                        destinationDuration = flightDuration10.getText().toString();
                        LaunchShipID = txtView_spaceShip10.getText().toString();

                        System.out.println(departurePlanet);
                        System.out.println(departureTime);
                        System.out.println(arrivalPlanet);
                        System.out.println(arrivalTime);
                        System.out.println(destinationDuration);
                        System.out.println(LaunchShipID);

                        savedData();


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

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void savedData() {
        Intent intent = new Intent(getApplicationContext(), Selected_Flight_Details_Activity.class);
        intent.putExtra("departurePlanet", departurePlanet);
        intent.putExtra("departureTime", departureTime);
        intent.putExtra("arrivalPlanet", arrivalPlanet);
        intent.putExtra("arrivalTime", arrivalTime);
        intent.putExtra("destinationDuration", destinationDuration);
        intent.putExtra("LaunchShipID", LaunchShipID);
        intent.putExtra("NOP", NOP);
        intent.putExtra("planet", planet);
        intent.putExtra("launchpad", launchpad);
        intent.putExtra("planetPrice", planetPrice);

        startActivity(intent);
    }

    private void initWidget() {

        //--->CardView
        cardView_1 = findViewById(R.id.cardView_1);
        cardView_2 = findViewById(R.id.cardView_2);
        cardView_3 = findViewById(R.id.cardView_3);
        cardView_4 = findViewById(R.id.cardView_4);
        cardView_5 = findViewById(R.id.cardView_5);
        cardView_6 = findViewById(R.id.cardView_6);
        cardView_7 = findViewById(R.id.cardView_7);
        cardView_8 = findViewById(R.id.cardView_8);
        cardView_9 = findViewById(R.id.cardView_9);
        cardView_10 = findViewById(R.id.cardView_10);

        //--->ImageView
        btn_back = findViewById(R.id.btn_back);
        img_flightInfo = findViewById(R.id.img_flightInfo);
        img_flightInfo2 = findViewById(R.id.img_flightInfo2);
        img_flightInfo3 = findViewById(R.id.img_flightInfo3);
        img_flightInfo4 = findViewById(R.id.img_flightInfo4);
        img_flightInfo5 = findViewById(R.id.img_flightInfo5);
        img_flightInfo6 = findViewById(R.id.img_flightInfo6);
        img_flightInfo7 = findViewById(R.id.img_flightInfo7);
        img_flightInfo8 = findViewById(R.id.img_flightInfo8);
        img_flightInfo9 = findViewById(R.id.img_flightInfo9);
        img_flightInfo10 = findViewById(R.id.img_flightInfo10);

        //--->TextView [CardView1]
        txtView_departureCountry = findViewById(R.id.txtView_departureCountry);
        txtView_departureTiming = findViewById(R.id.txtView_departureTiming);
        flightDuration = findViewById(R.id.flightDuration);
        txtView_arrivalPlanet = findViewById(R.id.txtView_arrivalPlanet);
        txtView_arrivalTiming = findViewById(R.id.txtView_arrivalTiming);
        txtView_spaceShip = findViewById(R.id.txtView_spaceShip);

        //--->TextView [CardView2]
        txtView_departureCountry2 = findViewById(R.id.txtView_departureCountry2);
        txtView_departureTiming2 = findViewById(R.id.txtView_departureTiming2);
        flightDuration2 = findViewById(R.id.flightDuration2);
        txtView_arrivalPlanet2 = findViewById(R.id.txtView_arrivalPlanet2);
        txtView_arrivalTiming2 = findViewById(R.id.txtView_arrivalTiming2);
        txtView_spaceShip2 = findViewById(R.id.txtView_spaceShip2);

        //--->TextView [CardView3]
        txtView_departureCountry3 = findViewById(R.id.txtView_departureCountry3);
        txtView_departureTiming3 = findViewById(R.id.txtView_departureTiming3);
        flightDuration3 = findViewById(R.id.flightDuration3);
        txtView_arrivalPlanet3 = findViewById(R.id.txtView_arrivalPlanet3);
        txtView_arrivalTiming3 = findViewById(R.id.txtView_arrivalTiming3);
        txtView_spaceShip3 = findViewById(R.id.txtView_spaceShip3);

        //--->TextView [CardView4]
        txtView_departureCountry4 = findViewById(R.id.txtView_departureCountry4);
        txtView_departureTiming4 = findViewById(R.id.txtView_departureTiming4);
        flightDuration4 = findViewById(R.id.flightDuration4);
        txtView_arrivalPlanet4 = findViewById(R.id.txtView_arrivalPlanet4);
        txtView_arrivalTiming4 = findViewById(R.id.txtView_arrivalTiming4);
        txtView_spaceShip4 = findViewById(R.id.txtView_spaceShip4);

        //--->TextView [CardView5]
        txtView_departureCountry5 = findViewById(R.id.txtView_departureCountry5);
        txtView_departureTiming5 = findViewById(R.id.txtView_departureTiming5);
        flightDuration5 = findViewById(R.id.flightDuration5);
        txtView_arrivalPlanet5 = findViewById(R.id.txtView_arrivalPlanet5);
        txtView_arrivalTiming5 = findViewById(R.id.txtView_arrivalTiming5);
        txtView_spaceShip5 = findViewById(R.id.txtView_spaceShip5);

        //--->TextView [CardView6]
        txtView_departureCountry6 = findViewById(R.id.txtView_departureCountry6);
        txtView_departureTiming6 = findViewById(R.id.txtView_departureTiming6);
        flightDuration6 = findViewById(R.id.flightDuration6);
        txtView_arrivalPlanet6 = findViewById(R.id.txtView_arrivalPlanet6);
        txtView_arrivalTiming6 = findViewById(R.id.txtView_arrivalTiming6);
        txtView_spaceShip6 = findViewById(R.id.txtView_spaceShip6);

        //--->TextView [CardView7]
        txtView_departureCountry7 = findViewById(R.id.txtView_departureCountry7);
        txtView_departureTiming7 = findViewById(R.id.txtView_departureTiming7);
        flightDuration7 = findViewById(R.id.flightDuration7);
        txtView_arrivalPlanet7 = findViewById(R.id.txtView_arrivalPlanet7);
        txtView_arrivalTiming7 = findViewById(R.id.txtView_arrivalTiming7);
        txtView_spaceShip7 = findViewById(R.id.txtView_spaceShip7);

        //--->TextView [CardView8]
        txtView_departureCountry8 = findViewById(R.id.txtView_departureCountry8);
        txtView_departureTiming8 = findViewById(R.id.txtView_departureTiming8);
        flightDuration8 = findViewById(R.id.flightDuration8);
        txtView_arrivalPlanet8 = findViewById(R.id.txtView_arrivalPlanet8);
        txtView_arrivalTiming8 = findViewById(R.id.txtView_arrivalTiming8);
        txtView_spaceShip8 = findViewById(R.id.txtView_spaceShip8);

        //--->TextView [CardView9]
        txtView_departureCountry9 = findViewById(R.id.txtView_departureCountry9);
        txtView_departureTiming9 = findViewById(R.id.txtView_departureTiming9);
        flightDuration9 = findViewById(R.id.flightDuration9);
        txtView_arrivalPlanet9 = findViewById(R.id.txtView_arrivalPlanet9);
        txtView_arrivalTiming9 = findViewById(R.id.txtView_arrivalTiming9);
        txtView_spaceShip9 = findViewById(R.id.txtView_spaceShip9);

        //--->TextView [CardView10]
        txtView_departureCountry10 = findViewById(R.id.txtView_departureCountry10);
        txtView_departureTiming10 = findViewById(R.id.txtView_departureTiming10);
        flightDuration10 = findViewById(R.id.flightDuration10);
        txtView_arrivalPlanet10 = findViewById(R.id.txtView_arrivalPlanet10);
        txtView_arrivalTiming10 = findViewById(R.id.txtView_arrivalTiming10);
        txtView_spaceShip10 = findViewById(R.id.txtView_spaceShip10);

        //--->CalendarView
        ticket_calendarView = findViewById(R.id.ticket_calendarView);

    }
}

