package com.example.spaced;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class Ticket_ViewPageAdapter extends PagerAdapter {

    LayoutInflater layoutInflater;
    Context context;
    ArrayList<TicketSliderData> ticketSliderDataArrayList;


    public Ticket_ViewPageAdapter(Context context, ArrayList<TicketSliderData> ticketSliderDataArrayList)
    {
        this.context = context;
        this.ticketSliderDataArrayList = ticketSliderDataArrayList;
    }

    @Override
    public int getCount() {
        return ticketSliderDataArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.ticket_slider_layout, container, false);

        TextView SPACE_ID = (TextView) view.findViewById(R.id.SPACE_ID);
        TextView txtView_earth = (TextView) view.findViewById(R.id.txtView_earth);
        TextView txtView_arrivalPlanet = (TextView) view.findViewById(R.id.txtView_arrivalPlanet);
        TextView txtView_country = (TextView) view.findViewById(R.id.txtView_country);
        TextView txtView_arrivalArea = (TextView) view.findViewById(R.id.txtView_arrivalArea);
        TextView txtView_departureTime = (TextView) view.findViewById(R.id.txtView_departureTime);
        TextView txtView_arrivalTime = (TextView) view.findViewById(R.id.txtView_arrivalTime);
        TextView txtView_departureDate = (TextView) view.findViewById(R.id.txtView_departureDate);
        TextView txtView_arrivalDate = (TextView) view.findViewById(R.id.txtView_arrivalDate);
        androidx.cardview.widget.CardView ticket_cardView = (androidx.cardview.widget.CardView) view.findViewById(R.id.ticket_cardView);

        TicketSliderData ticketSliderData = ticketSliderDataArrayList.get(position);
        SPACE_ID.setText(ticketSliderData.getSPACED_ID());
        txtView_earth.setText(ticketSliderData.getDeparture_planet());
        txtView_arrivalPlanet.setText(ticketSliderData.getArrival_planet());
        txtView_country.setText(ticketSliderData.getDeparture_country());
        txtView_arrivalArea.setText(ticketSliderData.getArrival_planet_area());
        txtView_departureTime.setText(ticketSliderData.getDeparture_time());
        txtView_arrivalTime.setText(ticketSliderData.getArrival_time());
        txtView_departureDate.setText(ticketSliderData.getDeparture_date());
        txtView_arrivalDate.setText(ticketSliderData.getArrival_date());

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((CardView)object);
    }
}
