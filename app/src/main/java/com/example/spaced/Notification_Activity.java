package com.example.spaced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Notification_Activity extends AppCompatActivity {

    private ImageView btn_back, img_filters, btn_filters, img_preference, btn_preference;

    private TextView txtView_Done, txtView_filters, txtView_preference;

    private LinearLayout LL_filters, LL_preference;

    private RelativeLayout rel_filters, rel_preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {
        //Lead you to the previous page
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingsPage_Activity.class));
            }
        });

        //save changes and lead user to the Main setting page
        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingsPage_Activity.class));
            }
        });


        //--->onClickListener for filter section
        // These will lead you to the filter page
        img_filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Filters_Activity.class));
            }
        });

        btn_filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Filters_Activity.class));
            }
        });

        txtView_filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Filters_Activity.class));
            }
        });

        LL_filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Filters_Activity.class));
            }
        });

        rel_filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Filters_Activity.class));
            }
        });


        //--->onClickListener for preference section
        // These will lead you to the preference page
        img_preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Preference_Activity.class));
            }
        });

        btn_preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Preference_Activity.class));
            }
        });

        txtView_preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Preference_Activity.class));
            }
        });

        LL_preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Preference_Activity.class));
            }
        });

        rel_preference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Preference_Activity.class));
            }
        });
    }

    private void initWidget() {
        //--->ImageView
        btn_back = findViewById(R.id.btn_back);
        img_filters = findViewById(R.id.img_filters);
        btn_filters = findViewById(R.id.btn_filters);
        img_preference = findViewById(R.id.img_preference);
        btn_preference = findViewById(R.id.btn_preference);

        //--->TextView
        txtView_Done = findViewById(R.id.txtView_Done);
        txtView_filters = findViewById(R.id.txtView_filters);
        txtView_preference = findViewById(R.id.txtView_preference);

        //--->LinearLayout
        LL_filters = findViewById(R.id.LL_filters);
        LL_preference = findViewById(R.id.LL_preference);

        //--->RelativeLayout
        rel_filters = findViewById(R.id.rel_filters);
        rel_preference = findViewById(R.id.rel_preference);

    }
}