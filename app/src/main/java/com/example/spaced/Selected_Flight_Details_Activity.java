package com.example.spaced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Pattern;

public class Selected_Flight_Details_Activity extends AppCompatActivity {

    //--->ImageView
    private ImageView btn_back;

    //--->RelativeLayout
    private RelativeLayout rel_passenger1, rel_passenger2, rel_passenger3, rel_passenger4, rel_passenger5;

    //--->EditText
    private EditText editTxt_passenger1_fullname, editTxt_passenger1_email, editTxt_passenger1_phoneNumber,
            editTxt_passenger2_fullname, editTxt_passenger2_email, editTxt_passenger2_phoneNumber,
            editTxt_passenger3_fullname, editTxt_passenger3_email, editTxt_passenger3_phoneNumber,
            editTxt_passenger4_fullname, editTxt_passenger4_email, editTxt_passenger4_phoneNumber,
            editTxt_passenger5_fullname, editTxt_passenger5_email, editTxt_passenger5_phoneNumber;

    //--->DatePicker
    private DatePicker datePicker_passenger1, datePicker_passenger2, datePicker_passenger3, datePicker_passenger4, datePicker_passenger5;

    //--->Button
    private Button btn_proceedPayment;

    //--->Intent
    private Intent intent;

    //--->Variable for main passenger
    private String mainPassenger_fullname, mainPassenger_email, mainPassenger_phoneNumber, mainPassengerDOB;
    private int personalForms;

    //--->Variable for passenger 2
    private String passenger2_fullname, passenger2_email, passenger2_phoneNumber, passenger2_DOB;

    //--->Variable for passenger 3
    private String passenger3_fullname, passenger3_email, passenger3_phoneNumber, passenger3_DOB;

    //--->Variable for passenger 4
    private String passenger4_fullname, passenger4_email, passenger4_phoneNumber, passenger4_DOB;

    //--->Variable for passenger 5
    private String passenger5_fullname, passenger5_email, passenger5_phoneNumber, passenger5_DOB;

    //--->Variable for transferred data
    private String NOP, planet, launchpad, planetPrice,
            arrivalPlanet, departurePlanet, departureTime, arrivalTime, destinationDuration, LaunchShipID;

    //--->Number of data to add
    private int data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_flight_details);

        intent = getIntent();

        initWidget();

        transferIntentData();

        NOPDisplay(); //--->Number of passenger detail forms to display

        pageDirectories();
    }

    private void transferIntentData() {
        NOP = intent.getStringExtra("NOP");
        planet = intent.getStringExtra("planet");
        launchpad = intent.getStringExtra("launchpad");
        planetPrice = intent.getStringExtra("planetPrice");

        arrivalPlanet = intent.getStringExtra("arrivalPlanet");
        departurePlanet = intent.getStringExtra("departurePlanet");
        LaunchShipID = intent.getStringExtra("LaunchShipID");
        departureTime = intent.getStringExtra("departureTime");
        arrivalTime = intent.getStringExtra("arrivalTime");
        destinationDuration = intent.getStringExtra("destinationDuration");

        System.out.println(NOP);
        System.out.println(planet);
        System.out.println(launchpad);
        System.out.println(planetPrice);

        System.out.println(arrivalPlanet);
        System.out.println(departurePlanet);
        System.out.println(LaunchShipID);
        System.out.println(departureTime);
        System.out.println(arrivalTime);
        System.out.println(destinationDuration);

    }

    private void NOPDisplay() {
        switch (NOP)
        {
            case "1":
                rel_passenger1.setVisibility(View.VISIBLE);
                rel_passenger2.setVisibility(View.GONE);
                rel_passenger3.setVisibility(View.GONE);
                rel_passenger4.setVisibility(View.GONE);
                rel_passenger5.setVisibility(View.GONE);
                personalForms = 1;
                break;

            case "2":
                rel_passenger1.setVisibility(View.VISIBLE);
                rel_passenger2.setVisibility(View.VISIBLE);
                rel_passenger3.setVisibility(View.GONE);
                rel_passenger4.setVisibility(View.GONE);
                rel_passenger5.setVisibility(View.GONE);
                personalForms = 2;
                break;

            case "3":
                rel_passenger1.setVisibility(View.VISIBLE);
                rel_passenger2.setVisibility(View.VISIBLE);
                rel_passenger3.setVisibility(View.VISIBLE);
                rel_passenger4.setVisibility(View.GONE);
                rel_passenger5.setVisibility(View.GONE);
                personalForms = 3;
                break;

            case "4":
                rel_passenger1.setVisibility(View.VISIBLE);
                rel_passenger2.setVisibility(View.VISIBLE);
                rel_passenger3.setVisibility(View.VISIBLE);
                rel_passenger4.setVisibility(View.VISIBLE);
                rel_passenger5.setVisibility(View.GONE);
                personalForms = 4;
                break;

            case "5":
                rel_passenger1.setVisibility(View.VISIBLE);
                rel_passenger2.setVisibility(View.VISIBLE);
                rel_passenger3.setVisibility(View.VISIBLE);
                rel_passenger4.setVisibility(View.VISIBLE);
                rel_passenger5.setVisibility(View.VISIBLE);
                personalForms = 5;
                break;

        }
    }//--->Deciding on how any passenger personal info detail forms to be visible

    private void pageDirectories() {

        btn_proceedPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateNOP();

                addData();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }//--->Clicks that will lead users to different page and perform various functions

    private void addData() {

        switch (data)
        {
            case 1:

                addPassenger1Data();

                break;

            case 2:

                addPassenger1Data();

                addPassenger2Data();
                break;

            case 3:

                addPassenger1Data();

                addPassenger2Data();

                addPassenger3Data();
                break;

            case 4:

                addPassenger1Data();

                addPassenger2Data();

                addPassenger3Data();

                addPassenger4Data();
                break;

            case 5:

                addPassenger1Data();

                addPassenger2Data();

                addPassenger3Data();

                addPassenger4Data();

                addPassenger5Data();
                break;
        }
    }//--->Decide how many passenger's details data needed to be stored and transferred to the next page

    //TODO: edit the respective addPassengerData to store all the data to intent to next page later

    private void addPassenger5Data() {
        if (!validatePassenger5_DOB() | !validatePassenger5_email() | !validatePassenger5_fullName() | !validatePassenger5_phoneNumber())
        {
            Toast.makeText(this, "Something went wrong with adding passenger 5 details, please try again later", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), Payment_Activity.class);

            intent.putExtra("editTxt_passenger5_fullname", passenger5_fullname);
            intent.putExtra("editTxt_passenger5_email", passenger5_email);
            intent.putExtra("editTxt_passenger5_phoneNumber", passenger5_phoneNumber);
            intent.putExtra("datePicker_passenger5", passenger5_DOB);

            startActivity(intent);
        }
    }//--->Transfer data from passenger 5 to the next page

    private void addPassenger4Data() {
        if (!validatePassenger4_DOB() | !validatePassenger4_email() | !validatePassenger4_fullName() | !validatePassenger4_phoneNumber())
        {
            Toast.makeText(this, "Something went wrong with adding passenger 4 details, please try again later", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), Payment_Activity.class);

            intent.putExtra("editTxt_passenger4_fullname", passenger4_fullname);
            intent.putExtra("editTxt_passenger4_email", passenger4_email);
            intent.putExtra("editTxt_passenger4_phoneNumber", passenger4_phoneNumber);
            intent.putExtra("datePicker_passenger4", passenger4_DOB);

            startActivity(intent);
        }
    }//--->Transfer data from passenger 4 to the next page

    private void addPassenger3Data() {
        if (!validatePassenger3_DOB() | !validatePassenger3_email() | !validatePassenger3_fullName() | !validatePassenger3_phoneNumber())
        {
            Toast.makeText(this, "Something went wrong with adding passenger 3 details, please try again later", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), Payment_Activity.class);
            intent.putExtra("editTxt_passenger3_fullname", passenger3_fullname);
            intent.putExtra("editTxt_passenger3_email", passenger3_email);
            intent.putExtra("editTxt_passenger3_phoneNumber", passenger3_phoneNumber);
            intent.putExtra("datePicker_passenger3", passenger3_DOB);
            startActivity(intent);
        }
    }//--->Transfer data from passenger 3 to the next page

    private void addPassenger2Data() {
        if (!validatePassenger2_DOB() | !validatePassenger2_email() | !validatePassenger2_fullName() | !validatePassenger2_phoneNumber())
        {
            Toast.makeText(this, "Something went wrong with adding passenger 2 details, please try again later", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), Payment_Activity.class);

            intent.putExtra("editTxt_passenger2_fullname", passenger2_fullname);
            intent.putExtra("editTxt_passenger2_email", passenger2_email);
            intent.putExtra("editTxt_passenger2_phoneNumber", passenger2_phoneNumber);
            intent.putExtra("datePicker_passenger2", passenger2_DOB);

            startActivity(intent);
        }
    }//--->Transfer data from passenger 2 to the next page

    private void addPassenger1Data() {
        if (!validatePassenger1_DOB() | !validatePassenger1_email() | !validatePassenger1_fullName() | !validatePassenger1_phoneNumber())
        {
            Toast.makeText(this, "Something went wrong with adding passenger 1 details, please try again later", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), Payment_Activity.class);

            intent.putExtra("editTxt_passenger1_fullname", mainPassenger_fullname);
            intent.putExtra("editTxt_passenger1_email", mainPassenger_email);
            intent.putExtra("editTxt_passenger1_phoneNumber", mainPassenger_phoneNumber);
            intent.putExtra("datePicker_passenger1", mainPassengerDOB);

            intent.putExtra("NOP", NOP);
            intent.putExtra("Planet", planet);
            intent.putExtra("LaunchPad", launchpad);

            intent.putExtra("planetPrice", planetPrice);
            intent.putExtra("arrivalPlanet", arrivalPlanet);
            intent.putExtra("departurePlanet", departurePlanet);
            intent.putExtra("arrivalTime", arrivalTime);
            intent.putExtra("destinationDuration", destinationDuration);
            intent.putExtra("LaunchShipID", LaunchShipID);

            //--->Checking output in terminal
            System.out.println(mainPassenger_fullname);
            System.out.println(mainPassenger_email);
            System.out.println(mainPassenger_phoneNumber);
            System.out.println(mainPassengerDOB);

            System.out.println(NOP);
            System.out.println(planet);
            System.out.println(launchpad);

            System.out.println(planetPrice);
            System.out.println(arrivalPlanet);
            System.out.println(departurePlanet);
            System.out.println(arrivalTime);
            System.out.println(destinationDuration);
            System.out.println(LaunchShipID);

            startActivity(intent);
        }
    }//--->Transfer data from passenger 1 to the next page

    private void validateNOP() {
        switch (personalForms)
        {
            case 1:
                validatePassenger1();

                data = 1;
                break;

            case 2:
                validatePassenger1();

                validatePassenger2();

                data = 2;
                break;

            case 3:
                validatePassenger1();

                validatePassenger2();

                validatePassenger3();

                data = 3;
                break;

            case 4:
                validatePassenger1();

                validatePassenger2();

                validatePassenger3();

                validatePassenger4();

                data = 4;
                break;

            case 5:
                validatePassenger1();

                validatePassenger2();

                validatePassenger3();

                validatePassenger4();

                validatePassenger5();

                data = 5;
                break;
        }
    }//--->Provide info on how many personal pages should be visible based on the number passengers selected

    private void validatePassenger5() {

        validatePassenger5_fullName();
        validatePassenger5_phoneNumber();
        validatePassenger5_email();
        validatePassenger5_DOB();
    }//--->Ensure and validate passenger 5's personal details

    private boolean validatePassenger5_fullName() {

        //Defining our own Full Name pattern
        final Pattern FULLNAME_PATTERN =
                Pattern.compile("^" +
                        "(?=.*[@#$%^&+=])" +     // at least 1 special character
                        "$");

        String fullName_input = editTxt_passenger5_fullname.getText().toString();

        if (fullName_input.isEmpty())
        {
            editTxt_passenger5_fullname.setError("Field cannot be empty");
            return false;
        }
        // Matching the input fullname to a predefined email pattern
        else if (FULLNAME_PATTERN.matcher(fullName_input).matches())
        {
            editTxt_passenger5_fullname.setError("Please enter a valid name");
            return false;
        }
        else
        {
            editTxt_passenger5_fullname.setError(null);
            passenger5_fullname = fullName_input;
            return true;
        }
    }//--->Ensure and validate passenger 5's full name

    private boolean validatePassenger5_phoneNumber() {

        String phoneNumber_input = editTxt_passenger5_phoneNumber.getText().toString();

        if (phoneNumber_input.isEmpty())
        {
            editTxt_passenger5_phoneNumber.setError("Field cannot be empty");
            return false;
        }
        // Matching the input email to a predefined email pattern
        else if (!Patterns.PHONE.matcher(phoneNumber_input).matches())
        {
            editTxt_passenger5_phoneNumber.setError("Please enter a valid phone number");
            return false;
        }
        else
        {
            editTxt_passenger5_phoneNumber.setError(null);
            passenger5_phoneNumber = phoneNumber_input;
            return true;
        }
    }//--->Ensure and validate passenger 5's phone number

    private boolean validatePassenger5_email() {

        String email_Input = editTxt_passenger5_email.getText().toString();

        if (email_Input.isEmpty())
        {
            editTxt_passenger5_email.setError("Field cannot be empty");
            return false;
        }

        // Matching the input email to a predefined email pattern
        else if (!Patterns.EMAIL_ADDRESS.matcher(email_Input).matches())
        {
            editTxt_passenger5_email.setError("Please enter a valid email address");
            return false;
        }
        else
        {
            editTxt_passenger5_email.setError(null);
            passenger5_email = email_Input;
            return true;
        }
    }//--->Ensure and validate passenger 5's email address

    private boolean validatePassenger5_DOB() {

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker_passenger5.getYear();
        int isAgeValid = currentYear - userAge;

        if (isAgeValid < 20)
        {
            Toast.makeText(this, "Passenger 1 does not meet the minimum age requirement", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            String userDate = String.valueOf(datePicker_passenger5.getDayOfMonth());
            String userMonth = String.valueOf(datePicker_passenger5.getMonth());
            String userYear = String.valueOf(datePicker_passenger5.getYear());

            passenger5_DOB = userDate + "/" + userMonth + "/" + userYear;

            return true;
        }
    }//--->Ensure and validate passenger 5's date of birth

    private void validatePassenger4() {

        validatePassenger4_fullName();
        validatePassenger4_phoneNumber();
        validatePassenger4_email();
        validatePassenger4_DOB();
    }//--->Ensure and validate passenger 4's personal info details

    private boolean validatePassenger4_fullName() {

        //Defining our own Full Name pattern
        final Pattern FULLNAME_PATTERN =
                Pattern.compile("^" +
                        "(?=.*[@#$%^&+=])" +     // at least 1 special character
                        "$");

        String fullName_input = editTxt_passenger4_fullname.getText().toString();

        if (fullName_input.isEmpty())
        {
            editTxt_passenger4_fullname.setError("Field cannot be empty");
            return false;
        }
        // Matching the input fullname to a predefined email pattern
        else if (FULLNAME_PATTERN.matcher(fullName_input).matches())
        {
            editTxt_passenger4_fullname.setError("Please enter a valid name");
            return false;
        }
        else
        {
            editTxt_passenger4_fullname.setError(null);
            passenger4_fullname = fullName_input;
            return true;
        }
    }//--->Ensure and validate passenger 4's full name

    private boolean validatePassenger4_phoneNumber() {

        String phoneNumber_input = editTxt_passenger4_phoneNumber.getText().toString();

        if (phoneNumber_input.isEmpty())
        {
            editTxt_passenger4_phoneNumber.setError("Field cannot be empty");
            return false;
        }
        // Matching the input email to a predefined email pattern
        else if (!Patterns.PHONE.matcher(phoneNumber_input).matches())
        {
            editTxt_passenger4_phoneNumber.setError("Please enter a valid phone number");
            return false;
        }
        else
        {
            editTxt_passenger4_phoneNumber.setError(null);
            passenger4_phoneNumber = phoneNumber_input;
            return true;
        }
    }//--->Ensure and validate passenger 4's phone number

    private boolean validatePassenger4_email() {

        String email_Input = editTxt_passenger4_email.getText().toString();

        if (email_Input.isEmpty())
        {
            editTxt_passenger4_email.setError("Field cannot be empty");
            return false;
        }

        // Matching the input email to a predefined email pattern
        else if (!Patterns.EMAIL_ADDRESS.matcher(email_Input).matches())
        {
            editTxt_passenger4_email.setError("Please enter a valid email address");
            return false;
        }
        else
        {
            editTxt_passenger4_email.setError(null);
            passenger4_email = email_Input;
            return true;
        }
    }//--->Ensure and validate passenger 4's email address

    private boolean validatePassenger4_DOB() {

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker_passenger4.getYear();
        int isAgeValid = currentYear - userAge;

        if (isAgeValid < 20)
        {
            Toast.makeText(this, "Passenger 1 does not meet the minimum age requirement", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            String userDate = String.valueOf(datePicker_passenger4.getDayOfMonth());
            String userMonth = String.valueOf(datePicker_passenger4.getMonth());
            String userYear = String.valueOf(datePicker_passenger4.getYear());

            passenger4_DOB = userDate + "/" + userMonth + "/" + userYear;

            return true;
        }
    }//--->Ensure and validate passenger 4's date of birth

    private void validatePassenger3() {

        validatePassenger3_fullName();
        validatePassenger3_phoneNumber();
        validatePassenger3_email();
        validatePassenger3_DOB();
    }//--->Ensure and validate passenger 3's personal details

    private boolean validatePassenger3_fullName() {

        //Defining our own Full Name pattern
        final Pattern FULLNAME_PATTERN =
                Pattern.compile("^" +
                        "(?=.*[@#$%^&+=])" +     // at least 1 special character
                        "$");

        String fullName_input = editTxt_passenger3_fullname.getText().toString();

        if (fullName_input.isEmpty())
        {
            editTxt_passenger3_fullname.setError("Field cannot be empty");
            return false;
        }
        // Matching the input fullname to a predefined email pattern
        else if (FULLNAME_PATTERN.matcher(fullName_input).matches())
        {
            editTxt_passenger3_fullname.setError("Please enter a valid name");
            return false;
        }
        else
        {
            editTxt_passenger3_fullname.setError(null);
            passenger3_fullname = fullName_input;
            return true;
        }
    }//--->Ensure and validate passenger 3's full name

    private boolean validatePassenger3_phoneNumber() {

        String phoneNumber_input = editTxt_passenger3_phoneNumber.getText().toString();

        if (phoneNumber_input.isEmpty())
        {
            editTxt_passenger3_phoneNumber.setError("Field cannot be empty");
            return false;
        }
        // Matching the input email to a predefined email pattern
        else if (!Patterns.PHONE.matcher(phoneNumber_input).matches())
        {
            editTxt_passenger3_phoneNumber.setError("Please enter a valid phone number");
            return false;
        }
        else
        {
            editTxt_passenger3_phoneNumber.setError(null);
            passenger3_phoneNumber = phoneNumber_input;
            return true;
        }
    }//--->Ensure and validate passenger 3's phone number

    private boolean validatePassenger3_email() {

        String email_Input = editTxt_passenger3_email.getText().toString();

        if (email_Input.isEmpty())
        {
            editTxt_passenger3_email.setError("Field cannot be empty");
            return false;
        }

        // Matching the input email to a predefined email pattern
        else if (!Patterns.EMAIL_ADDRESS.matcher(email_Input).matches())
        {
            editTxt_passenger3_email.setError("Please enter a valid email address");
            return false;
        }
        else
        {
            editTxt_passenger3_email.setError(null);
            passenger3_email = email_Input;
            return true;
        }
    }//--->Ensure and validate passenger 3's email

    private boolean validatePassenger3_DOB() {

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker_passenger3.getYear();
        int isAgeValid = currentYear - userAge;

        if (isAgeValid < 20)
        {
            Toast.makeText(this, "Passenger 1 does not meet the minimum age requirement", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            String userDate = String.valueOf(datePicker_passenger3.getDayOfMonth());
            String userMonth = String.valueOf(datePicker_passenger3.getMonth());
            String userYear = String.valueOf(datePicker_passenger3.getYear());

            passenger3_DOB = userDate + "/" + userMonth + "/" + userYear;

            return true;
        }
    }//--->Ensure and validate passenger 3's date of birth

    private void validatePassenger2() {

        validatePassenger2_fullName();
        validatePassenger2_phoneNumber();
        validatePassenger2_email();
        validatePassenger2_DOB();
    }//--->Ensure and validate passenger 2's personal info details

    private boolean validatePassenger2_fullName() {

        //Defining our own Full Name pattern
        final Pattern FULLNAME_PATTERN =
                Pattern.compile("^" +
                        "(?=.*[@#$%^&+=])" +     // at least 1 special character
                        "$");

        String fullName_input = editTxt_passenger2_fullname.getText().toString();

        if (fullName_input.isEmpty())
        {
            editTxt_passenger2_fullname.setError("Field cannot be empty");
            return false;
        }
        // Matching the input fullname to a predefined email pattern
        else if (FULLNAME_PATTERN.matcher(fullName_input).matches())
        {
            editTxt_passenger2_fullname.setError("Please enter a valid name");
            return false;
        }
        else
        {
            editTxt_passenger2_fullname.setError(null);
            passenger2_fullname = fullName_input;
            return true;
        }
    }//--->Ensure and validate passenger 2's fullname

    private boolean validatePassenger2_phoneNumber() {

        String phoneNumber_input = editTxt_passenger2_phoneNumber.getText().toString();

        if (phoneNumber_input.isEmpty())
        {
            editTxt_passenger2_phoneNumber.setError("Field cannot be empty");
            return false;
        }
        // Matching the input email to a predefined email pattern
        else if (!Patterns.PHONE.matcher(phoneNumber_input).matches())
        {
            editTxt_passenger2_phoneNumber.setError("Please enter a valid phone number");
            return false;
        }
        else
        {
            editTxt_passenger2_phoneNumber.setError(null);
            passenger2_phoneNumber = phoneNumber_input;
            return true;
        }
    }//--->Ensure and validate passenger 2's phone number

    private boolean validatePassenger2_email() {

        String email_Input = editTxt_passenger2_email.getText().toString();

        if (email_Input.isEmpty())
        {
            editTxt_passenger2_email.setError("Field cannot be empty");
            return false;
        }

        // Matching the input email to a predefined email pattern
        else if (!Patterns.EMAIL_ADDRESS.matcher(email_Input).matches())
        {
            editTxt_passenger2_email.setError("Please enter a valid email address");
            return false;
        }
        else
        {
            editTxt_passenger2_email.setError(null);
            passenger2_email = email_Input;
            return true;
        }
    }//--->Ensure and validate passenger 2's email

    private boolean validatePassenger2_DOB() {

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker_passenger2.getYear();
        int isAgeValid = currentYear - userAge;

        if (isAgeValid < 20)
        {
            Toast.makeText(this, "Passenger 1 does not meet the minimum age requirement", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            String userDate = String.valueOf(datePicker_passenger1.getDayOfMonth());
            String userMonth = String.valueOf(datePicker_passenger1.getMonth());
            String userYear = String.valueOf(datePicker_passenger1.getYear());

            passenger2_DOB = userDate + "/" + userMonth + "/" + userYear;

            return true;
        }
    }//--->Ensure and validate passenger 2's date of birth

    private void validatePassenger1() {

        validatePassenger1_fullName();
        validatePassenger1_phoneNumber();
        validatePassenger1_email();
        validatePassenger1_DOB();
    }//--->Ensure and validate passenger 1's personal info details

    private boolean validatePassenger1_fullName() {

        //Defining our own Full Name pattern
        final Pattern FULLNAME_PATTERN =
                Pattern.compile("^" +
                        "(?=.*[@#$%^&+=])" +     // at least 1 special character
                        "$");

        String fullName_input = editTxt_passenger1_fullname.getText().toString();

        if (fullName_input.isEmpty())
        {
            editTxt_passenger1_fullname.setError("Field cannot be empty");
            return false;
        }
        // Matching the input fullname to a predefined email pattern
        else if (FULLNAME_PATTERN.matcher(fullName_input).matches())
        {
            editTxt_passenger1_fullname.setError("Please enter a valid name");
            return false;
        }
        else
        {
            editTxt_passenger1_fullname.setError(null);
            mainPassenger_fullname = fullName_input;
            return true;
        }
    }//--->Ensure and validate passenger 1's fullname

    private boolean validatePassenger1_phoneNumber() {

        String phoneNumber_input = editTxt_passenger1_phoneNumber.getText().toString();

        if (phoneNumber_input.isEmpty())
        {
            editTxt_passenger1_phoneNumber.setError("Field cannot be empty");
            return false;
        }
        // Matching the input email to a predefined email pattern
        else if (!Patterns.PHONE.matcher(phoneNumber_input).matches())
        {
            editTxt_passenger1_phoneNumber.setError("Please enter a valid phone number");
            return false;
        }
        else
        {
            editTxt_passenger1_phoneNumber.setError(null);
            mainPassenger_phoneNumber = phoneNumber_input;
            return true;
        }
    }//--->Ensure and validate passenger 1's phone number

    private boolean validatePassenger1_email() {

        String email_Input = editTxt_passenger1_email.getText().toString();

        if (email_Input.isEmpty())
        {
            editTxt_passenger1_email.setError("Field cannot be empty");
            return false;
        }

        // Matching the input email to a predefined email pattern
        else if (!Patterns.EMAIL_ADDRESS.matcher(email_Input).matches())
        {
            editTxt_passenger1_email.setError("Please enter a valid email address");
            return false;
        }
        else
        {
            editTxt_passenger1_email.setError(null);
            mainPassenger_email = email_Input;
            return true;
        }
    }//--->Ensure and validate passenger 1's email

    private boolean validatePassenger1_DOB() {

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker_passenger1.getYear();
        int isAgeValid = currentYear - userAge;

        if (isAgeValid < 20)
        {
            Toast.makeText(this, "Passenger 1 does not meet the minimum age requirement", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            String userDate = String.valueOf(datePicker_passenger1.getDayOfMonth());
            String userMonth = String.valueOf(datePicker_passenger1.getMonth());
            String userYear = String.valueOf(datePicker_passenger1.getYear());

            mainPassengerDOB = userDate + "/" + userMonth + "/" + userYear;

            return true;
        }
    }//--->Ensure and validate passenger 1's date of birth

    private void initWidget() {
        //--->ImageView
        btn_back = findViewById(R.id.btn_back);

        //--->RelativeLayout
        rel_passenger1 = findViewById(R.id.rel_passenger1);
        rel_passenger2 = findViewById(R.id.rel_passenger2);
        rel_passenger3 = findViewById(R.id.rel_passenger3);
        rel_passenger4 = findViewById(R.id.rel_passenger4);
        rel_passenger5 = findViewById(R.id.rel_passenger5);

        //--->EditText
        editTxt_passenger1_fullname = findViewById(R.id.editTxt_passenger1_fullname);
        editTxt_passenger2_fullname = findViewById(R.id.editTxt_passenger2_fullname);
        editTxt_passenger3_fullname = findViewById(R.id.editTxt_passenger3_fullname);
        editTxt_passenger4_fullname = findViewById(R.id.editTxt_passenger4_fullname);
        editTxt_passenger5_fullname = findViewById(R.id.editTxt_passenger5_fullname);

        editTxt_passenger1_email = findViewById(R.id.editTxt_passenger1_email);
        editTxt_passenger2_email = findViewById(R.id.editTxt_passenger2_email);
        editTxt_passenger3_email = findViewById(R.id.editTxt_passenger3_email);
        editTxt_passenger4_email = findViewById(R.id.editTxt_passenger4_email);
        editTxt_passenger5_email = findViewById(R.id.editTxt_passenger5_email);

        editTxt_passenger1_phoneNumber = findViewById(R.id.editTxt_passenger1_phoneNumber);
        editTxt_passenger2_phoneNumber = findViewById(R.id.editTxt_passenger2_phoneNumber);
        editTxt_passenger3_phoneNumber = findViewById(R.id.editTxt_passenger3_phoneNumber);
        editTxt_passenger4_phoneNumber = findViewById(R.id.editTxt_passenger4_phoneNumber);
        editTxt_passenger5_phoneNumber = findViewById(R.id.editTxt_passenger5_phoneNumber);

        //--->DatePicker
        datePicker_passenger1 = findViewById(R.id.datePicker_passenger1);
        datePicker_passenger2 = findViewById(R.id.datePicker_passenger2);
        datePicker_passenger3 = findViewById(R.id.datePicker_passenger3);
        datePicker_passenger4 = findViewById(R.id.datePicker_passenger4);
        datePicker_passenger5 = findViewById(R.id.datePicker_passenger5);

        //--->Button
        btn_proceedPayment = findViewById(R.id.btn_proceedPayment);
    }
}