package com.example.spaced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class YourAccount_Activity extends AppCompatActivity {

    private ImageView btn_back, img_accInfo, btn_accInfo, img_changePassword, btn_changePassword, img_download,
            btn_dataArchive, img_deactivate, btn_deactivateAcc;

    private TextView txtView_Done, txtView_accInfo, txtView_changePassword, txtView_dataArchive, txtView_deactivateAcc;

    private LinearLayout LL_accInfo, LL_changePassword, LL_dataArchive, LL_deactivateAcc;

    private RelativeLayout rel_accInfo, rel_changePassword, rel_dataArchive, rel_deactivateAcc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_account);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {

        //--->Lead you to the previous page
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingsPage_Activity.class));
            }
        });

        //--->Saves changes and lead you to the previous page
        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingsPage_Activity.class));
            }
        });

        //--->onClickListener for Account information section
        // These will lead you to the Account information page
        img_accInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Account_Activity.class));
            }
        });

        btn_accInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Account_Activity.class));
            }
        });

        txtView_accInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Account_Activity.class));
            }
        });

        LL_accInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Account_Activity.class));
            }
        });

        rel_accInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Account_Activity.class));
            }
        });


        //--->onClickListener for Change your password section
        // These will lead you to the Change your password page
        img_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ChangePassword_Activity.class));
            }
        });

        btn_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ChangePassword_Activity.class));
            }
        });

        txtView_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ChangePassword_Activity.class));
            }
        });

        LL_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ChangePassword_Activity.class));
            }
        });

        rel_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ChangePassword_Activity.class));
            }
        });


        //--->onClickListener for Archive data section
        // These will lead you to the Archive data page
        //TODO: Create a website version of SPACED
        img_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_dataArchive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        txtView_dataArchive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        LL_dataArchive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        rel_dataArchive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        //--->onClickListener for Deactivate your account section
        // These will lead you to the deactivate account page
        img_deactivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Deactivate_Account_Activity.class));
            }
        });

        btn_deactivateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Deactivate_Account_Activity.class));
            }
        });

        txtView_deactivateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Deactivate_Account_Activity.class));
            }
        });

        LL_deactivateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Deactivate_Account_Activity.class));
            }
        });

        rel_deactivateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Deactivate_Account_Activity.class));
            }
        });
    }

    private void initWidget() {
        //--->ImageView
        btn_back = findViewById(R.id.btn_back);
        img_accInfo = findViewById(R.id.img_accInfo);
        btn_accInfo = findViewById(R.id.btn_accInfo);
        img_changePassword = findViewById(R.id.img_changePassword);
        btn_changePassword = findViewById(R.id.btn_changePassword);
        img_download = findViewById(R.id.img_download);
        btn_dataArchive = findViewById(R.id.btn_dataArchive);
        img_deactivate = findViewById(R.id.img_deactivate);
        btn_deactivateAcc = findViewById(R.id.btn_deactivateAcc);

        //--->TextView
        txtView_Done = findViewById(R.id.txtView_Done);
        txtView_accInfo = findViewById(R.id.txtView_accInfo);
        txtView_changePassword = findViewById(R.id.txtView_changePassword);
        txtView_dataArchive = findViewById(R.id.txtView_dataArchive);
        txtView_deactivateAcc = findViewById(R.id.txtView_deactivateAcc);

        //--->LinearLayout
        LL_accInfo = findViewById(R.id.LL_accInfo);
        LL_changePassword = findViewById(R.id.LL_changePassword);
        LL_dataArchive = findViewById(R.id.LL_dataArchive);
        LL_deactivateAcc = findViewById(R.id.LL_deactivateAcc);

        //--->RelativeLayout
        rel_accInfo = findViewById(R.id.rel_accInfo);
        rel_changePassword = findViewById(R.id.rel_changePassword);
        rel_dataArchive = findViewById(R.id.rel_dataArchive);
        rel_deactivateAcc = findViewById(R.id.rel_deactivateAcc);


    }
}