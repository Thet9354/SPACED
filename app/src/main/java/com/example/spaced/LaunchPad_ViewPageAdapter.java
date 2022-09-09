package com.example.spaced;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class LaunchPad_ViewPageAdapter extends PagerAdapter {

    Context context;

    int launchPad[] = {
            R.string.Leicester,
            R.string.Toulouse,
            R.string.Noordwijk,
            R.string.Washington,
            R.string.Wallasey,
            R.string.Transinne,
            R.string.Kennedy
    };

    public LaunchPad_ViewPageAdapter(Context context)
    {
        this.context = context;
    }

    @Override
    public int getCount() {
        return launchPad.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.launchpad_slider_layout, container, false);

        TextView txtView_launchPad = (TextView) view.findViewById(R.id.txtView_launchPad);


        txtView_launchPad.setText(launchPad[position]);


        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout)object);
    }
}
