package com.example.spaced;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class ShippingInfo_Activity extends AppCompatActivity {

    //--->TextView
    private TextView txtView_cancelShippingInfo, txtView_saveShippingInfo, txtView_country, txtView_phone;

    //--->EditText
    private EditText editTxt_firstName, editTxt_lastName, editTxt_streetAddress, editTxt_apartment,
            editTxt_postalCode, editTxt_companyName;

    //--->Switch
    private androidx.appcompat.widget.SwitchCompat SPACEDAcc_switch;

    //--->SearchView
    private androidx.appcompat.widget.SearchView searchView_address;

    //--->Variables to store values
    private String SI_firstName, SI_lastName, SI_fullname, SI_streetAddress, SI_apartment, SI_postalCode,
            SI_companyName, SI_region, SI_phoneNo;

    private Boolean savedTo_SPACEDAcc;

    private UserPreference userPreference;

    private String users_ShippingAddress, customer_name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_info);

        userPreference = (UserPreference) getApplication();

        initWidget();

        pageDirectories();

        loadSharedPreference();
    }

    private void loadSharedPreference() {
        SharedPreferences sharedPreferences = getSharedPreferences(UserPreference.PREFERENCE, MODE_PRIVATE);
        String normalAddress = sharedPreferences.getString(UserPreference.NORMAL_ADDRESS, UserPreference.NORMAL_ADDRESS);
        userPreference.setNormalAddress(normalAddress);
    }

    private void pageDirectories() {

        //onClickListener for cancel btn
        txtView_cancelShippingInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //onClickListener for save btn
        txtView_saveShippingInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate_name();
                validate_companyName();
                validate_streetAddress();
                validate_Apt();
                validate_postalCode();
                validate_region();
                validate_phone();
                validate_overallInputs();
            }
        });
    }

    private void validate_overallInputs() {

        if (!validate_name() || !validate_streetAddress() || !validate_Apt() || !validate_postalCode() || !validate_region() || validate_phone())
        {
            Toast.makeText(this, "Something went wrong, please check the information you have   entered", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            saveShippingInfoData();

        }
    }

    //TODO: DO this at a later time
    private void saveShippingInfoData() {
        //Putting all the inputs together to create a shipping address
        users_ShippingAddress = SI_streetAddress + " " + SI_apartment + " " + SI_postalCode;
        System.out.println(users_ShippingAddress);

        //Save inside google firebase firestore, SQLite database and sharedPreference

        //Saving customer's address NORMAL_ADDRESS in sharedPreference as default
        loadSharedPreference();

        userPreference.setNormalAddress(users_ShippingAddress);

        SharedPreferences.Editor editor = getSharedPreferences(UserPreference.PREFERENCE, MODE_PRIVATE).edit();
        editor.putString(UserPreference.NORMAL_ADDRESS, userPreference.getNormalAddress());
        editor.apply();

        //TODO: Save inside google firebase firestore, SQLite database

        startActivity(new Intent(getApplicationContext(), Payment_Activity.class));
    }

    private boolean validate_phone() {

        String phoneInput = txtView_phone.getText().toString();

        if (phoneInput.isEmpty())
        {
            txtView_phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //---> Asking user if he/she wants to update their mobile number in the settings page
                    AlertDialog.Builder builder = new AlertDialog.Builder(ShippingInfo_Activity.this);
                    builder.setTitle("Missing phone number");
                    builder.setMessage("Hey there, it seems like we're missing one crucial detail, Phone Number.");
                    builder.setNegativeButton("Update Phone Number", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(getApplicationContext(), Update_PhoneNumber_Activity.class));
                        }
                    });
                    builder.setPositiveButton("Maybe next time", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(getApplicationContext(), StartingPage_Activity.class));
                        }
                    });
                    builder.create().show();
                }
            });
        }
        else
        {
            SI_phoneNo = phoneInput;
            System.out.println(SI_phoneNo);

        }
        return true;
    }

    private boolean validate_region() {

        String regionInput = txtView_country.getText().toString();

        if(regionInput.isEmpty())
        {
            txtView_country.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //---> Asking user if he/she wants to update their mobile number in the settings page
                    AlertDialog.Builder builder = new AlertDialog.Builder(ShippingInfo_Activity.this);
                    builder.setTitle("Missing region");
                    builder.setMessage("Hey there, it seems like we're missing one crucial detail, your region.");
                    builder.setNegativeButton("Update country", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(getApplicationContext(), Country_Activity.class));
                        }
                    });
                    builder.setPositiveButton("Maybe next time", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(getApplicationContext(), StartingPage_Activity.class));
                        }
                    });
                    builder.create().show();
                }
            });
        }
        else
        {
            SI_region = regionInput;
            System.out.println(SI_region);

        }
        return true;

    }

    private boolean validate_postalCode() {

        String postalCodeInput = editTxt_postalCode.getText().toString();

        //Defining own postal code pattern
        /**
         * Only accepts numeric values
         *
         * Only accepts 6 digit zipcode
         */
        final Pattern postalCode_PATTERN =
                Pattern.compile("^[0-9]{6}(?:-[0-9]{4})?$");

        if (postalCodeInput.isEmpty())
        {
            editTxt_postalCode.setError("Field cannot be empty");

            return false;
        }
        else if (!postalCode_PATTERN.matcher(postalCodeInput).matches())
        {
            editTxt_postalCode.setError("Please enter a valid postal code");

            return false;
        }
        else
        {
            editTxt_postalCode.setError(null);
            SI_postalCode = postalCodeInput;
            System.out.println(SI_postalCode);

            return true;
        }
    }

    private boolean validate_Apt() {
        String aptInput = editTxt_apartment.getText().toString();

        //Defining own apartment address pattern
        /**
         * Accepts only alphabets and numeric values
         */
        final Pattern apartment_PATTERN =
                Pattern.compile("^[0-9A-Za-z\\s-]+$");

        if (aptInput.isEmpty())
        {
            editTxt_apartment.setError("Field cannot be empty");
            return false;
        }
        else if (!apartment_PATTERN.matcher(aptInput).matches())
        {
            editTxt_apartment.setError("Please enter a valid apartment");
            return false;
        }
        else
        {
            editTxt_apartment.setError(null);
            SI_apartment = aptInput;
            System.out.println(SI_apartment);

            return true;
        }
    }

    private boolean validate_streetAddress() {

        String streetAddressInput = editTxt_streetAddress.getText().toString();

        //Defining own street address pattern
        /*
        TODO: Adjust the regex pattern for street address to include numbers and alphabets
         , and specific characters.
         */
        final Pattern streetAddress_PATTERN =
                Pattern.compile("^[0-9A-Za-z\\s-]+$");

        if (streetAddressInput.isEmpty())
        {
            editTxt_streetAddress.setError("Field cannot be empty");
            return false;
        }
        else if (!streetAddress_PATTERN.matcher(streetAddressInput).matches())
        {
            editTxt_streetAddress.setError("Please enter a valid street address");
            return false;
        }
        else
        {
            editTxt_streetAddress.setError(null);
            SI_streetAddress = streetAddressInput;
            System.out.println(SI_streetAddress);

            return true;
        }
    }

    private void validate_companyName() {

        SI_companyName = editTxt_companyName.getText().toString();
        System.out.println(SI_companyName);

    }

    private boolean validate_name() {
        String firstNameInput = editTxt_firstName.getText().toString();
        String lastNameInput = editTxt_lastName.getText().toString();

        //Defining own name pattern

        /**
         * Assume words are entirely made up of alphabetical characters A-Z, upper case and lower case
         *
         * Ignore all strings that contain non-alphabetical characters or symbols
         *
         * Assumes some punctuation like periods or commas are to be ignored but the preceding word should be captured.
         */
        final Pattern SIName_PATTERN =
                Pattern.compile("(?<=\\s|^)[a-zA-Z]*(?=[.,;:]?\\s|$)");

        if (firstNameInput.isEmpty())
        {
            editTxt_firstName.setError("Field cannot be empty.");
            return false;
        }
        else if (!SIName_PATTERN.matcher(firstNameInput).matches())
        {
            editTxt_firstName.setError("Please enter a valid first name");
            return false;
        }
        else
        {
            editTxt_lastName.setError(null);
            SI_firstName = firstNameInput;

            if (lastNameInput.isEmpty())
            {
                editTxt_lastName.setError("Field cannot be empty.");
                return false;
            }
            else if (!SIName_PATTERN.matcher(lastNameInput).matches())
            {
                editTxt_lastName.setError("Please enter a valid last name");
                return false;
            }
            else
            {
                SI_lastName = lastNameInput;
                editTxt_lastName.setError(null);

                System.out.println(SI_firstName);
                System.out.println(SI_lastName);
                return true;
            }
        }
    }

    private void initWidget() {

        //--->TextView
        txtView_cancelShippingInfo = findViewById(R.id.txtView_cancelShippingInfo);
        txtView_saveShippingInfo = findViewById(R.id.txtView_saveShippingInfo);
        txtView_country = findViewById(R.id.txtView_country);
        txtView_phone = findViewById(R.id.txtView_phone);

        //--->EditText
        editTxt_firstName = findViewById(R.id.editTxt_firstName);
        editTxt_lastName = findViewById(R.id.editTxt_lastName);
        editTxt_streetAddress = findViewById(R.id.editTxt_streetAddress);
        editTxt_apartment = findViewById(R.id.editTxt_apartment);
        editTxt_postalCode = findViewById(R.id.editTxt_postalCode);
        editTxt_companyName = findViewById(R.id.editTxt_companyName);

        //--->Switch
        SPACEDAcc_switch = findViewById(R.id.SPACEDAcc_switch);

        //--->SearchView
        searchView_address = findViewById(R.id.searchView_address);
    }
}