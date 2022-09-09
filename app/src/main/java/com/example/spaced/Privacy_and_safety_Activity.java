package com.example.spaced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Privacy_and_safety_Activity extends AppCompatActivity {

    private ImageView btn_back, img_adPreference, btn_adsPreference, img_offSPACED, btn_offSPACED, img_dataSharing,
            btn_dataSharing, img_locationInfo, btn_locationInfo, btn_privacyCenter, btn_privacyPolicy, btn_contactUs;

    private TextView txtView_Done, txtView_adsPreference, txtView_offSPACED, txtView_dataSharing, txtView_locationInfo, txtView_privacyCenter,
            txtView_privacyPolicy, txtView_contactUs;

    private LinearLayout LL_adsPreferences, LL_activity, LL_dataSharing, LL_locationInfo;

    private RelativeLayout rel_adPreference, rel_off_spacedActivity, rel_dataSharing, rel_locationInfo, rel_privacy, rel_privacyPolicy, rel_contactus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_and_safety);

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

        //Saved changes and lead user to the previous page
        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SettingsPage_Activity.class));
            }
        });

        //--->onClickListener for Ads preference section
        // These will lead you to the ads preference page
        img_adPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), adPreference_Activity.class));
            }
        });

        btn_adsPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), adPreference_Activity.class));
            }
        });

        txtView_adsPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), adPreference_Activity.class));
            }
        });

        LL_adsPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), adPreference_Activity.class));
            }
        });

        rel_adPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), adPreference_Activity.class));
            }
        });


        //--->onClickListener for Off-SPACED activity section
        // These will lead you to the Off-SPACED activity page
        img_offSPACED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Off_SPACED_Activity.class));
            }
        });

        btn_offSPACED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Off_SPACED_Activity.class));
            }
        });

        txtView_adsPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Off_SPACED_Activity.class));
            }
        });

        LL_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Off_SPACED_Activity.class));
            }
        });

        rel_off_spacedActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Off_SPACED_Activity.class));
            }
        });


        //--->onClickListener for Data sharing section
        // These will lead you to the Data sharing activity page
        img_dataSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DataSharing_Activity.class));
            }
        });

        btn_dataSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DataSharing_Activity.class));
            }
        });

        txtView_dataSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DataSharing_Activity.class));
            }
        });

        LL_dataSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DataSharing_Activity.class));
            }
        });

        rel_dataSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DataSharing_Activity.class));
            }
        });


        //--->onClickListener for Location information section
        // These will lead you to the Location information activity page
        img_locationInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Location_Information_Activity.class));
            }
        });

        btn_locationInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Location_Information_Activity.class));
            }
        });

        txtView_locationInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Location_Information_Activity.class));
            }
        });

        LL_locationInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Location_Information_Activity.class));
            }
        });

        rel_locationInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Location_Information_Activity.class));
            }
        });


        //--->onClickListener for Privacy center activity section
        // These will lead you to the Privacy center activity page
        //TODO: create webpage for this section to be linked
        btn_privacyCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        txtView_privacyCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        rel_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        //--->onClickListener for Privacy policy activity section
        // These will lead you to the Privacy policy activity page
        btn_privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PrivacyPolicy_Activity.class));
            }
        });

        txtView_privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PrivacyPolicy_Activity.class));
            }
        });

        rel_privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PrivacyPolicy_Activity.class));
            }
        });


        //--->onClickListener for contact us activity section
        // These will lead you to the contact us activity page
        //TODO: create webpage for this section to be linked
        btn_contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        txtView_contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        rel_contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }

    private void initWidget() {
        //--->ImageView
        btn_back = findViewById(R.id.btn_back);
        img_adPreference = findViewById(R.id.img_adPreference);
        btn_adsPreference = findViewById(R.id.btn_adsPreference);
        img_offSPACED = findViewById(R.id.img_offSPACED);
        btn_offSPACED = findViewById(R.id.btn_offSPACED);
        img_dataSharing = findViewById(R.id.img_dataSharing);
        btn_dataSharing = findViewById(R.id.btn_dataSharing);
        img_locationInfo = findViewById(R.id.img_locationInfo);
        btn_locationInfo = findViewById(R.id.btn_locationInfo);
        btn_privacyCenter = findViewById(R.id.btn_privacyCenter);
        btn_privacyPolicy = findViewById(R.id.btn_privacyPolicy);
        btn_contactUs = findViewById(R.id.btn_contactUs);

        //--->TextView
        txtView_Done = findViewById(R.id.txtView_Done);
        txtView_adsPreference = findViewById(R.id.txtView_adsPreference);
        txtView_offSPACED = findViewById(R.id.txtView_offSPACED);
        txtView_dataSharing = findViewById(R.id.txtView_dataSharing);
        txtView_locationInfo = findViewById(R.id.txtView_locationInfo);
        txtView_privacyCenter = findViewById(R.id.txtView_privacyCenter);
        txtView_privacyPolicy = findViewById(R.id.txtView_privacyPolicy);
        txtView_contactUs = findViewById(R.id.txtView_contactUs);

        //--->LinearLayout
        LL_adsPreferences = findViewById(R.id.LL_adsPreferences);
        LL_activity = findViewById(R.id.LL_activity);
        LL_dataSharing = findViewById(R.id.LL_dataSharing);
        LL_locationInfo = findViewById(R.id.LL_locationInfo);

        //--->RelativeLayout
        rel_adPreference = findViewById(R.id.rel_adPreference);
        rel_off_spacedActivity = findViewById(R.id.rel_off_spacedActivity);
        rel_dataSharing = findViewById(R.id.rel_dataSharing);
        rel_locationInfo = findViewById(R.id.rel_locationInfo);
        rel_privacy = findViewById(R.id.rel_privacy);
        rel_privacyPolicy = findViewById(R.id.rel_privacyPolicy);
        rel_contactus = findViewById(R.id.rel_contactus);
    }
}