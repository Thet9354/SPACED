package com.example.spaced;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class TicketViewPageAdapter extends PagerAdapter {

    Context context;

    int image[] = {
            R.drawable.moon_transparent,
            R.drawable.mars_transparent,
            R.drawable.uranus_transparent,
            R.drawable.mercury_transparent,
            R.drawable.venus_transparent,
            R.drawable.jupiter_transparent,
            R.drawable.saturn_transparent,
            R.drawable.neptune_transparent,
            R.drawable.eris_transparent,
            R.drawable.pluto_transparent,
            R.drawable.makemake_transparent,
            R.drawable.ceres_transparent
    };


    public TicketViewPageAdapter(Ticket_Activity context)
    {
        this.context = context;
    }

    @Override
    public int getCount() {
        return image.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slider_planet = (ImageView) view.findViewById(R.id.img_planet);

        slider_planet.setImageResource(image[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout)object);
    }
}
