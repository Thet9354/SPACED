package com.example.spaced;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MercuryPage_Activity extends AppCompatActivity {

    private ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mercury_page);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initWidget() {
        btn_back = findViewById(R.id.btn_back);
    }
}