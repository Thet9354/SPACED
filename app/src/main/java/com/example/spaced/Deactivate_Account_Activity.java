package com.example.spaced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Deactivate_Account_Activity extends AppCompatActivity {

    private TextView txtView_deactivate, txtView_Done;

    private ImageView btn_back;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    private String Deactivate = "OFF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deactivate_account);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

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


        txtView_deactivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Deactivate = "ON";
                txtView_deactivate.setEnabled(false);
                finish();
            }
        });
    }

    private void initWidget() {
        txtView_deactivate = findViewById(R.id.txtView_deactivate);

        txtView_Done = findViewById(R.id.txtView_Done);

        btn_back = findViewById(R.id.btn_back);
    }
}