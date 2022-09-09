package com.example.spaced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Security_and_account_Activity extends AppCompatActivity {

    private ImageView btn_back, img_security, btn_security, img_apps, btn_apps_and_sessions, img_connectedAcc, btn_connectedAcc;

    private TextView txtView_Done, txtView_security, txtView_apps_and_sessions, txtView_connectedAcc;

    private LinearLayout LL_security, LL_apps_and_sessions, LL_connectedAcc;

    private RelativeLayout rel_security, rel_apps_and_sessions, rel_connectedAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_and_account);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {

        //Leads you to the previous page
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingsPage_Activity.class));
            }
        });

        //Saves changes and leads to the previous page
        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingsPage_Activity.class));
            }
        });

        //--->onClickListener for security section
        // These will lead you to the security page
        img_security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Security_Activity.class));
            }
        });

        btn_security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Security_Activity.class));
            }
        });

        txtView_security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Security_Activity.class));
            }
        });

        LL_security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Security_Activity.class));

            }
        });

        rel_security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Security_Activity.class));
            }
        });

        //--->onClickListener for Apps and sessions section
        // These will lead you to the apps and sessions page
        //TODO: Create a website for SPACED to link this to
        img_apps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_apps_and_sessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        txtView_apps_and_sessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        LL_apps_and_sessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        rel_apps_and_sessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //--->onClickListener for connected accounts section
        // These will lead you to the connected accounts page
        img_connectedAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Connected_account_Activity.class));
            }
        });

        btn_connectedAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Connected_account_Activity.class));
            }
        });

        txtView_connectedAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Connected_account_Activity.class));
            }
        });

        LL_connectedAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Connected_account_Activity.class));
            }
        });

        rel_connectedAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Connected_account_Activity.class));
            }
        });
    }

    private void initWidget() {
        //--->ImageView
        btn_back = findViewById(R.id.btn_back);
        img_security = findViewById(R.id.img_security);
        btn_security = findViewById(R.id.btn_security);
        img_apps = findViewById(R.id.img_apps);
        btn_apps_and_sessions = findViewById(R.id.btn_apps_and_sessions);
        img_connectedAcc = findViewById(R.id.img_connectedAcc);
        btn_connectedAcc = findViewById(R.id.btn_connectedAcc);

        //--->TextView
        txtView_Done = findViewById(R.id.txtView_Done);
        txtView_security = findViewById(R.id.txtView_security);
        txtView_apps_and_sessions = findViewById(R.id.txtView_apps_and_sessions);
        txtView_connectedAcc = findViewById(R.id.txtView_connectedAcc);

        //--->LinearLayout
        LL_security = findViewById(R.id.LL_security);
        LL_apps_and_sessions = findViewById(R.id.LL_apps_and_sessions);
        LL_connectedAcc = findViewById(R.id.LL_connectedAcc);

        //--->RelativeLayout
        rel_security = findViewById(R.id.rel_security);
        rel_apps_and_sessions = findViewById(R.id.rel_apps_and_sessions);
        rel_connectedAcc = findViewById(R.id.rel_connectedAcc);


    }
}