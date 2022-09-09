package com.example.spaced;

import static android.provider.ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spaced.CountryInventory.Country;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class CardPayment_info_Activity extends AppCompatActivity {

    //--->TextView
    private TextView txtView_cancelPaymentCardInfo, txtView_savePaymentCardInfo, txtView_country;

    //--->SearchView
    private androidx.appcompat.widget.SearchView searchView_address;

    //--->ImageView
    private ImageView img_paymentType;

    //--->EditText
    private EditText editTxt_creditCard, editTxt_securityCode, editTxt_month, editTxt_year,
            editTxt_firstName, editTxt_lastName, editTxt_companyName, editTxt_streetAddress,
            editTxt_apartment, editTxt_postalCode, editTxt_phoneNumber,
            editTxt_alternatePhone, editTxt_Email;

    //--->Switch
    androidx.appcompat.widget.SwitchCompat defaultCC_switch;

    //--->Variable for editText
    private String creditCardNo, securityCode, ccExpiration, expirationMonth, expirationYear, ccFullName, ccFirstName,
            ccLastname, companyName, streetAddress, apartment, postalCode, region, phone, alternatePhone, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_payment_info);

        initWidget();

        get_country();

        ccDisplay();

        pageDirectories();


    }

    private void pageDirectories() {

        //onClickListener for cancel btn
        txtView_cancelPaymentCardInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cancel all credit card info and go back to the previous page
                onBackPressed();
            }
        });

        //onClickListener for save btn
        txtView_savePaymentCardInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Validating each and every before proceeding to save the written info
                validate_ccNumber();
                validate_securityCode();
                validate_expirationDate();
                validate_Name();
                validate_companyName();
                validate_streetAddress();
                validate_apartment();
                validate_postalCode();
                validate_Phone();
                validate_alternativePhone();
                validate_Email();

                validate_overallInput();


            }
        });
    }

    private void validate_overallInput() {
        if (!validate_ccNumber() || !validate_apartment() || !validate_Email() || validate_Name() || validate_Phone() || validate_securityCode()
        || validate_streetAddress() || !validate_expirationDate() || !validate_postalCode())
        {
            return;
        }
        else
        {
            saveCardPaymentInfo();
        }
    }

    private void saveCardPaymentInfo() {
        //TODO: Code this out at a later time
    }

    //Gets the country of the user, however this won't work if there's no sim card in the phone
    private void get_country() {
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        region = tm.getSimCountryIso();

        txtView_country.setText(region);
    }

    private void validate_alternativePhone() {
        alternatePhone = editTxt_alternatePhone.getText().toString();

        if (!alternatePhone.isEmpty())
        {
            if (!Patterns.PHONE.matcher(alternatePhone).matches())
            {
                editTxt_alternatePhone.setError("Please enter a valid alternate phone number");
            }
        }
    }

    private void validate_companyName() {

        /*
        Since Company Name is optional, we wil not do any validation and only storing the data
        */

        companyName = editTxt_companyName.getText().toString();
    }

    private boolean validate_Email() {

        String emailInput = editTxt_Email.getText().toString();

        if (emailInput.isEmpty())
        {
            editTxt_Email.setError("Field cannot be empty.");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches())
        {
            editTxt_Email.setError("Please enter a valid email address");
            return false;
        }
        else
        {
            editTxt_Email.setError(null);

            email = emailInput;
            return true;
        }

    }

    private boolean validate_Phone() {

        String phoneInput = editTxt_phoneNumber.getText().toString();

        if (phoneInput.isEmpty())
        {
            editTxt_phoneNumber.setError("Field cannot be empty");
            return false;
        }
        else if (!Patterns.PHONE.matcher(phoneInput).matches())
        {
            editTxt_phoneNumber.setError("Please enter a valid phone number.");
            return false;
        }
        else
        {
            editTxt_phoneNumber.setError(null);

            phone = phoneInput;
            return true;
        }

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

            postalCode = postalCodeInput;
            return true;
        }
    }

    private boolean validate_apartment() {

        String apartmentInput = editTxt_apartment.getText().toString();

        //Defining own apartment address pattern
        /**
         * Accepts only alphabets and numeric values
         */
        final Pattern apartment_PATTERN =
                Pattern.compile("^[0-9A-Za-z\\s-]+$");

        if (apartmentInput.isEmpty())
        {
            editTxt_apartment.setError("Field cannot be empty.");
            return false;
        }
        else if (!apartment_PATTERN.matcher(apartmentInput).matches())
        {
            editTxt_apartment.setError("Please enter a valid input.");
            return false;
        }
        else
        {
            editTxt_apartment.setError(null);
            apartment = apartmentInput;
            return true;
        }
    }

    private boolean validate_streetAddress() {

        String addressInput = editTxt_streetAddress.getText().toString();

        //Defining own street address pattern
        /**
         * This regex pattern only accepts numeric and alphabetical inputs
         */
        final Pattern streetAddress_PATTERN =
                Pattern.compile("^[0-9A-Za-z\\s-]+$");

        if (addressInput.isEmpty())
        {
            editTxt_streetAddress.setError("This field cannot be empty");
            return false;
        }
        else if (!streetAddress_PATTERN.matcher(addressInput).matches())
        {
            editTxt_streetAddress.setError("Please enter a valid street address.");
            return false;
        }
        else
        {
            editTxt_streetAddress.setError(null);
            streetAddress = addressInput;
            System.out.println(streetAddress);

            return true;
        }
    }

    private boolean validate_Name() {

        String firstNameInput = editTxt_firstName.getText().toString();
        String lastNameInput = editTxt_lastName.getText().toString();

        //Defining own name pattern
        /*
        TODO: Adjust the regex pattern for full name to be the same as the name on the card, no numbers
         and special characters.
         */
        final Pattern ccName_PATTERN =
                Pattern.compile("(?<=\\s|^)[a-zA-Z]*(?=[.,;:]?\\s|$)");

        if (firstNameInput.isEmpty())
        {
            editTxt_firstName.setError("Field cannot be empty");
            return false;
        }
        else if (lastNameInput.isEmpty())
        {
            editTxt_lastName.setError("Field cannot be empty");
            return false;
        }
        else if (!ccName_PATTERN.matcher(firstNameInput).matches())
        {
            editTxt_firstName.setError("Please enter a valid first name.");
            return false;
        }
        else if (!ccName_PATTERN.matcher(lastNameInput).matches())
        {
            editTxt_lastName.setError("Please enter a valid last name.");
            return false;
        }
        else
        {
            editTxt_lastName.setError(null);
            editTxt_firstName.setError(null);

            ccFirstName = firstNameInput;
            ccLastname = lastNameInput;
            ccFullName = ccFirstName + " " + ccLastname;
            System.out.println(ccFullName);
            return true;
        }
    }

    private boolean validate_expirationDate() {

        String expMonthInput = editTxt_month.getText().toString();
        String expYearInput = editTxt_year.getText().toString();


        //Defining our own expirationDate pattern
        /**
         * Matches only digits and nothing else
         */

        final Pattern expDate_PATTERN =
                Pattern.compile("^\\d+$");

        if (expMonthInput.isEmpty())
        {
            editTxt_month.setError("Field cannot be empty");
            return false;
        }
        else if (expYearInput.isEmpty())
        {
            editTxt_year.setError("Field cannot be empty");
            return false;
        }
        else if (!expDate_PATTERN.matcher(expMonthInput).matches())
        {
            editTxt_month.setError("Please enter a valid expiration month");
            return false;
        }
        else if (!expDate_PATTERN.matcher(expYearInput).matches())
        {
            editTxt_year.setError("Please enter a valid expiration year");
            return false;
        }
        else if (expMonthInput.length() != 2)
        {
            editTxt_month.setError("There should only be a 2 digit input for this field");
            return false;
        }
        else if (expYearInput.length() != 2)
        {
            editTxt_year.setError("There should only be a 2 digit input for this field");
            return false;
        }
        else
        {
            editTxt_month.setError(null);
            editTxt_year.setError(null);

            expirationMonth = expMonthInput;
            expirationYear = expYearInput;
            ccExpiration = expirationMonth + "/" + expirationYear;
            System.out.println(ccExpiration);
            return true;
        }

    }

    private boolean validate_securityCode() {

        String securityCodeInput = editTxt_securityCode.getText().toString();

        //Defining our own security code pattern
        /**
         * This regex pattern accepts only numeric values with 3 or 4 digits
         */

        final Pattern securityCode_PATTERN =
                Pattern.compile("/^[0-9]{3,4}$/");

        if (securityCodeInput.isEmpty())
        {
            editTxt_securityCode.setError("Field cannot be empty");
            return false;
        }
        else if (!securityCode_PATTERN.matcher(securityCodeInput).matches())
        {
            return false;
        }
        else
        {
            editTxt_securityCode.setError(null);

            securityCode = securityCodeInput;
            return true;
        }
    }

    private boolean validate_ccNumber() {

        String ccNumberInput = editTxt_creditCard.getText().toString();

        // defining our own credit card pattern
        /**
         *This is a general regular expression used to match Credit Card Numbers
         * (Visa, MasterCard and American Express, Diners Club, Discover, and JCB cards).
         */
        final Pattern creditCard_PATTERN =
                Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?|(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}|3[47][0-9]{13}$/");

        if (ccNumberInput.isEmpty()) {
            editTxt_creditCard.setError("Field cannot be empty");

            return false;
        }
        else if (!creditCard_PATTERN.matcher(ccNumberInput).matches())
        {

            return false;
        }
        else
        {
            /*
            Adjusting the credit card image beside the editText based on the input digits
            If card begins with 4, then it is a Visa
            If the card begins with a 5, then it is a Mastercard
             */

            creditCardNo = ccNumberInput;
            editTxt_creditCard.setError(null);
            return true;
        }
    }

    private void ccDisplay() {

        String ccNumberInput = editTxt_creditCard.getText().toString();

        if (ccNumberInput.isEmpty())
        {
            return;
        }
        else
        {
            String firstNumber = ccNumberInput.substring(0,1);

            if (firstNumber == "5")
            {
                //Credit card entered is a mastercard
                img_paymentType.setImageResource(R.drawable.mastercard_ic);

                ccDisplay();
            }
            else if (firstNumber == "4")
            {
                //Credit card entered is a visa card
                img_paymentType.setImageResource(R.drawable.visa_ic);

                ccDisplay();
            }
            else if (firstNumber == "3")
            {
                //Credit card entered is a amex card
                img_paymentType.setImageResource(R.drawable.amex_ic);

                ccDisplay();
            }
        }
    }

    private void initWidget() {

        //--->TextView
        txtView_cancelPaymentCardInfo = findViewById(R.id.txtView_cancelPaymentCardInfo);
        txtView_savePaymentCardInfo = findViewById(R.id.txtView_savePaymentCardInfo);
        txtView_country = findViewById(R.id.txtView_country);

        //--->SearchView
        searchView_address = findViewById(R.id.searchView_address);

        //--->ImageView
        img_paymentType = findViewById(R.id.img_paymentType);

        //--->EditText
        editTxt_creditCard = findViewById(R.id.editTxt_creditCard);
        editTxt_securityCode = findViewById(R.id.editTxt_securityCode);
        editTxt_month = findViewById(R.id.editTxt_month);
        editTxt_year = findViewById(R.id.editTxt_year);
        editTxt_firstName = findViewById(R.id.editTxt_firstName);
        editTxt_lastName = findViewById(R.id.editTxt_lastName);
        editTxt_companyName = findViewById(R.id.editTxt_companyName);
        editTxt_streetAddress = findViewById(R.id.editTxt_streetAddress);
        editTxt_apartment = findViewById(R.id.editTxt_apartment);
        editTxt_postalCode = findViewById(R.id.editTxt_postalCode);
        editTxt_phoneNumber = findViewById(R.id.editTxt_phoneNumber);
        editTxt_alternatePhone = findViewById(R.id.editTxt_alternatePhone);
        editTxt_Email = findViewById(R.id.editTxt_Email);
    }
}