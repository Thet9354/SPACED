package com.example.spaced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Mars_Activity extends AppCompatActivity {

    private ImageView btn_nextPlanet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mars);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {
        btn_nextPlanet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_nextPlanet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(), Uranus_Activity.class));
                    }
                });
            }
        });
    }

    private void initWidget() {
        btn_nextPlanet = findViewById(R.id.btn_nextPlanet);
    }
}