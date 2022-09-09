package com.example.spaced;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.share.Share;

public class MoreOptions_Activity extends AppCompatActivity {

    //--->TextView
    private TextView txtView_cancel, txtView_save, txtView_shippingTotal, txtView_orderItem;

    //--->EditText
    private EditText editTxt_postalCode;

    //--->Switch
    private androidx.appcompat.widget.SwitchCompat switch_businessAddress;

    //--->Radio Group
    private RadioGroup rg_timeslots;

    //--->Radio Button
    private RadioButton rb_standard_shipping, rb_shipping_slot1, rb_shipping_slot2, rb_shipping_slot3;

    private UserPreference userPreference;

    private double checkoutPrice, shippingFee;

    private String deliverySlot;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_options);
        intent = getIntent();

        userPreference = (UserPreference) getApplication();

        initWidget();

        transferIntentData();

        checkout_itemAdjustment();

        pageDirectories();

        loadSharedPreference();
    }

    private void transferIntentData() {
        String orderItem = intent.getStringExtra("purchaseItemDetail");
        System.out.println(orderItem);
        txtView_orderItem.setText(orderItem);
    }

    private void deliverySlots() {
        switch (rg_timeslots.getCheckedRadioButtonId())
        {
            case R.id.rb_standard_shipping:
                deliverySlot = "No specific timing";
                System.out.println(deliverySlot);
                break;
            case R.id.rb_shipping_slot1:
                deliverySlot = "9.00am - 12.00pm";
                System.out.println(deliverySlot);
                break;
            case R.id.rb_shipping_slot2:
                deliverySlot = "12.00pm to 5.00pm";
                System.out.println(deliverySlot);
                break;
            case R.id.rb_shipping_slot3:
                deliverySlot = "5.00pm to 9:00pm";
                System.out.println(deliverySlot);
                break;
        }
    }


    private void priceAdjustments() {

        if (checkoutPrice > 8000)
        {
            shippingFee = 0;

        }
        else
        {
            shippingFee = 50;
        }
        txtView_shippingTotal.setText("S$" + shippingFee);
    }

    private void checkout_itemAdjustment() {
        priceAdjustments();
    }

    private void pageDirectories() {
        txtView_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        txtView_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadSharedPreference();

                deliverySlots();

                Toast.makeText(MoreOptions_Activity.this, "Changes saved", Toast.LENGTH_SHORT).show();

                onBackPressed();
            }
        });

        switch_businessAddress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    userPreference.setBusinessAddress(UserPreference.BUSINESS_ADDRESS);
                else
                    userPreference.setBusinessAddress(UserPreference.NORMAL_ADDRESS);

                SharedPreferences.Editor editor = getSharedPreferences(UserPreference.PREFERENCE, MODE_PRIVATE).edit();
                editor.putString(UserPreference.BUSINESS_ADDRESS, userPreference.getBusinessAddress());
                editor.apply();
            }
        });
    }


    private void loadSharedPreference() {
        SharedPreferences sharedPreferences = getSharedPreferences(UserPreference.PREFERENCE, MODE_PRIVATE);
        String businessAddress = sharedPreferences.getString(UserPreference.BUSINESS_ADDRESS, UserPreference.NORMAL_ADDRESS);
        userPreference.setBusinessAddress(businessAddress);
    }

    private void initWidget() {
        //--->TextView
        txtView_cancel = findViewById(R.id.txtView_cancel);
        txtView_save = findViewById(R.id.txtView_save);
        txtView_shippingTotal = findViewById(R.id.txtView_shippingTotal);
        txtView_orderItem = findViewById(R.id.txtView_orderItem);

        //--->EditText
        editTxt_postalCode = findViewById(R.id.editTxt_postalCode);

        //--->Switch
        switch_businessAddress = findViewById(R.id.switch_businessAddress);

        //--->Radio Group
        rg_timeslots = findViewById(R.id.rg_timeslots);

        //--->Radio Button
        rb_standard_shipping = findViewById(R.id.rb_standard_shipping);
        rb_shipping_slot1 = findViewById(R.id.rb_shipping_slot1);
        rb_shipping_slot2 = findViewById(R.id.rb_shipping_slot2);
        rb_shipping_slot3 = findViewById(R.id.rb_shipping_slot3);
    }
}