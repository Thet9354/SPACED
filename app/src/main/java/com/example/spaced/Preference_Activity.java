package com.example.spaced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Preference_Activity extends AppCompatActivity {

    private ImageView btn_back, btn_pushNotification, btn_smsNotifications, btn_emailNotifications;

    private TextView txtView_Done, txtView_pushNotifications, txtView_smsNotifications, txtView_emailNotifications;

    private RelativeLayout rel_pushNotifications, rel_smsNotifications, rel_emailNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {
        //Discard changes and lead the user to the previous page
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Notification_Activity.class));
            }
        });

        //Save changes and lead the user to the previous page
        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Notification_Activity.class));
            }
        });


        //--->onClickListener for Push notification section
        // These will lead you to the Push notification page
        btn_pushNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Push_Notification_Activity.class));
            }
        });

        txtView_pushNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Push_Notification_Activity.class));
            }
        });

        rel_pushNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Push_Notification_Activity.class));
            }
        });


        //--->onClickListener for SMS notification section
        // These will lead you to the SMS notification page
        btn_smsNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SMS_Notification_Activity.class));
            }
        });

        txtView_smsNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SMS_Notification_Activity.class));
            }
        });


        //--->onClickListener for email notification section
        // These will lead you to the email notification page
        btn_emailNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(new Intent(getApplicationContext(), Email_Notification_Activity.class)));
            }
        });

        txtView_emailNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(new Intent(getApplicationContext(), Email_Notification_Activity.class)));
            }
        });

        rel_emailNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(new Intent(getApplicationContext(), Email_Notification_Activity.class)));
            }
        });
    }

    private void initWidget() {
        //--->Imageview
        btn_back = findViewById(R.id.btn_back);
        btn_pushNotification = findViewById(R.id.btn_pushNotification);
        btn_smsNotifications = findViewById(R.id.btn_smsNotifications);
        btn_emailNotifications = findViewById(R.id.btn_emailNotifications);

        //--->TextView
        txtView_Done = findViewById(R.id.txtView_Done);
        txtView_pushNotifications = findViewById(R.id.txtView_pushNotifications);
        txtView_smsNotifications = findViewById(R.id.txtView_smsNotifications);
        txtView_emailNotifications = findViewById(R.id.txtView_emailNotifications);

        //--->RelativeLayout
        rel_pushNotifications = findViewById(R.id.rel_pushNotifications);
        rel_smsNotifications = findViewById(R.id.rel_smsNotifications);
        rel_emailNotifications = findViewById(R.id.rel_emailNotifications);

    }
}