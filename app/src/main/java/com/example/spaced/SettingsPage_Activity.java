package com.example.spaced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SettingsPage_Activity extends AppCompatActivity {

    private androidx.appcompat.widget.SearchView searchView_settings;
    private RelativeLayout rel_yourAccount, rel_security_and_account_access, rel_privacy_and_safety, rel_notifications, rel_accessibility, rel_additionalResources;
    private ImageView img_yourAccount, img_security, img_privacy_and_safety, img_notifications, img_accessibility, img_additionalResources;
    private ImageView btn_yourAccount, btn_security_and_account_access, btn_privacy_and_safety, btn_notifications, btn_accessibility, btn_additionalResources, btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

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

        //--->onClickListeners for Your account section
        rel_yourAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lead to Your account page
                startActivity(new Intent(getApplicationContext(), YourAccount_Activity.class));
            }
        });

        img_yourAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lead to Your account page
                startActivity(new Intent(getApplicationContext(), YourAccount_Activity.class));
            }
        });

        btn_yourAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lead to Your account section
                startActivity(new Intent(getApplicationContext(), YourAccount_Activity.class));
            }
        });


        //--->onClickListener for Security and account access section
        // These will lead to the Security and account access page
        rel_security_and_account_access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Security_and_account_Activity.class));
            }
        });

        img_security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Security_and_account_Activity.class));
            }
        });

        btn_security_and_account_access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Security_and_account_Activity.class));
            }
        });


        //--->onClickListener for Privacy and safety section
        // These will lead you to the Privacy and safety page
        rel_privacy_and_safety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Privacy_and_safety_Activity.class));
            }
        });

        img_privacy_and_safety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Privacy_and_safety_Activity.class));
            }
        });

        btn_privacy_and_safety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Privacy_and_safety_Activity.class));
            }
        });


        //--->onClickListener for notification section
        // These will lead you to the notification page
        rel_notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Notification_Activity.class));
            }
        });

        img_notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Notification_Activity.class));
            }
        });

        btn_notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Notification_Activity.class));
            }
        });


        //--->onClickListener for accessibility section
        // These will lead you to the accessibility page
        rel_accessibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Accessibility_display_language_Activity.class));
            }
        });

        img_accessibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Accessibility_display_language_Activity.class));
            }
        });

        btn_accessibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Accessibility_display_language_Activity.class));
            }
        });


        //--->onClickListener for additional resources
        // These will lead you to the additional resource page
        rel_additionalResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Additional_Resources_Activity.class));
            }
        });

        img_additionalResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Additional_Resources_Activity.class));
            }
        });

        btn_additionalResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Additional_Resources_Activity.class));
            }
        });
    }

    private void initWidget() {
        searchView_settings = findViewById(R.id.searchView_settings);
        searchView_settings.clearFocus();

        rel_yourAccount = findViewById(R.id.rel_yourAccount);
        rel_security_and_account_access = findViewById(R.id.rel_security_and_account_access);
        rel_privacy_and_safety = findViewById(R.id.rel_privacy_and_safety);
        rel_notifications = findViewById(R.id.rel_notifications);
        rel_accessibility = findViewById(R.id.rel_accessibility);
        rel_additionalResources = findViewById(R.id.rel_additionalResources);

        img_yourAccount = findViewById(R.id.img_yourAccount);
        img_security = findViewById(R.id.img_security);
        img_privacy_and_safety = findViewById(R.id.img_privacy_and_safety);
        img_notifications = findViewById(R.id.img_notifications);
        img_accessibility = findViewById(R.id.img_accessibility);
        img_additionalResources = findViewById(R.id.img_additionalResources);

        btn_yourAccount = findViewById(R.id.btn_yourAccount);
        btn_security_and_account_access = findViewById(R.id.btn_security_and_account_access);
        btn_privacy_and_safety = findViewById(R.id.btn_privacy_and_safety);
        btn_notifications = findViewById(R.id.btn_notifications);
        btn_accessibility = findViewById(R.id.btn_accessibility);
        btn_additionalResources = findViewById(R.id.btn_additionalResources);
        btn_back = findViewById(R.id.btn_back);
    }
}