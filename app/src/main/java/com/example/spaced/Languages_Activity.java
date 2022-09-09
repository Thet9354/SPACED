package com.example.spaced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Languages_Activity extends AppCompatActivity {

    private ImageView btn_back, btn_recommendations;

    private TextView txtView_Done, txtView_recommendations;

    private LinearLayout LL_recommendations;

    private RelativeLayout rel_recommendations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {
        //Discard changes and lead user to the previous page
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Accessibility_display_language_Activity.class));
            }
        });

        //Save changes and lead user to the previous page
        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Accessibility_display_language_Activity.class));
            }
        });

        //--->onClickListener for languages recommendation section
        // These will lead you to the languages recommendation page
        btn_recommendations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        txtView_recommendations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        rel_recommendations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        LL_recommendations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initWidget() {
        //--->Imageview
        btn_back = findViewById(R.id.btn_back);
        btn_recommendations = findViewById(R.id.btn_recommendations);

        //--->TextView
        txtView_Done = findViewById(R.id.txtView_Done);
        txtView_recommendations = findViewById(R.id.txtView_recommendations);

        //--->LinearLayout
        LL_recommendations = findViewById(R.id.LL_recommendations);

        //--->RelativeLayout
        rel_recommendations = findViewById(R.id.rel_recommendations);
    }
}