package com.example.spaced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Accessibility_display_language_Activity extends AppCompatActivity {

    private ImageView btn_back, img_Accessibility, btn_accessibility, img_display_and_sound, btn_display_and_sound, img_languages, btn_languages,
            img_dataUsage, btn_dataUsage;

    private TextView txtView_Done, txtView_Accessibility, txtView_display_and_sound, txtView_languages, txtView_dataUsage;

    private LinearLayout LL_accessibility, LL_display_sound, LL_languages, LL_dataUsage;

    private RelativeLayout rel_accessibility, rel_display_and_sound, rel_languages, rel_dataUsage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessibility_display_language);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {
        //Lead user to the previous page
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingsPage_Activity.class));
            }
        });


        //Save changes and lead user to the main settings page
        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingsPage_Activity.class));
            }
        });


        //--->onClickListener for accessibility section
        // These will lead you to the accessibility page
        img_Accessibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Accessibility_Activity.class));
            }
        });

        btn_accessibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Accessibility_Activity.class));
            }
        });

        txtView_Accessibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Accessibility_Activity.class));
            }
        });

        LL_accessibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Accessibility_Activity.class));
            }
        });

        rel_accessibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Accessibility_Activity.class));
            }
        });


        //--->onClickListener for display and sound section
        // These will lead you to the display and sound page
        img_display_and_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Display_sound_Activity.class));
            }
        });

        btn_display_and_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Display_sound_Activity.class));
            }
        });

        txtView_display_and_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Display_sound_Activity.class));
            }
        });

        LL_display_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Display_sound_Activity.class));
            }
        });

        rel_display_and_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Display_sound_Activity.class));
            }
        });


        //--->onClickListener for languages section
        // These will lead you to the languages page
        img_languages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Languages_Activity.class));
            }
        });

        btn_languages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Languages_Activity.class));
            }
        });

        txtView_languages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Languages_Activity.class));
            }
        });

        LL_languages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Languages_Activity.class));
            }
        });

        rel_languages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Languages_Activity.class));
            }
        });


        //--->onClickListener for data usage section
        // These will lead you to the data usage page
        img_dataUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Data_Usage_Activity.class));
            }
        });

        btn_dataUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Data_Usage_Activity.class));
            }
        });

        txtView_dataUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Data_Usage_Activity.class));
            }
        });

        LL_dataUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Data_Usage_Activity.class));
            }
        });

        rel_dataUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Data_Usage_Activity.class));
            }
        });
    }

    private void initWidget() {
        //--->ImageView
        btn_back = findViewById(R.id.btn_back);
        img_Accessibility = findViewById(R.id.img_Accessibility);
        btn_accessibility = findViewById(R.id.btn_accessibility);
        img_display_and_sound = findViewById(R.id.img_display_and_sound);
        btn_display_and_sound = findViewById(R.id.btn_display_and_sound);
        img_languages = findViewById(R.id.img_languages);
        btn_languages = findViewById(R.id.btn_languages);
        img_dataUsage = findViewById(R.id.img_dataUsage);
        btn_dataUsage = findViewById(R.id.btn_dataUsage);

        //--->TextView
        txtView_Done = findViewById(R.id.txtView_Done);
        txtView_Accessibility = findViewById(R.id.txtView_Accessibility);
        txtView_display_and_sound = findViewById(R.id.txtView_display_and_sound);
        txtView_languages = findViewById(R.id.txtView_languages);
        txtView_dataUsage = findViewById(R.id.txtView_dataUsage);

        //--->LinearLayout
        LL_accessibility = findViewById(R.id.LL_accessibility);
        LL_display_sound = findViewById(R.id.LL_display_sound);
        LL_languages = findViewById(R.id.LL_languages);
        LL_dataUsage = findViewById(R.id.LL_dataUsage);

        //--->RelativeLayout
        rel_accessibility = findViewById(R.id.rel_accessibility);
        rel_display_and_sound = findViewById(R.id.rel_display_and_sound);
        rel_languages = findViewById(R.id.rel_languages);
        rel_dataUsage = findViewById(R.id.rel_dataUsage);

    }
}