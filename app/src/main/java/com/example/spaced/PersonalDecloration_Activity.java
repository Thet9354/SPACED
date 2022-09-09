package com.example.spaced;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spaced.CountryInventory.CountryData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class PersonalDecloration_Activity extends AppCompatActivity {

    private EditText editTxt_Fullname, editTxt_mobileNumber;
    private com.example.spaced.CustomSpinner spinner_country;
    private DatePicker datePicker_DOB;
    private TextView txtView_welcomeMsg;
    private androidx.appcompat.widget.AppCompatButton btn_register;

    private CountryAdapter countryAdapter;

    private UserModel userModel;

    private UserDataBaseHelper dataBaseHelper;

    private Intent intent;

    private String mCountry, mUsername, mEmail, mPassword, mFullname, mPhoneNumber, mDOB;
    private int currentYear, userAge, isAgeValid;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://spaced-768d2-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference databaseReference  = database.getReference().child("users");

    FirebaseFirestore fireStoreDB = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_decloration);

        intent = getIntent();

        initWidget();

        countrySpinner();

        pageDirectories();
    }

    private void pageDirectories() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Storing values inside variables
                mFullname = editTxt_Fullname.getText().toString();
                mPhoneNumber = editTxt_mobileNumber.getText().toString();

                //--->Validation process
                validateFullname();
                validatePhoneNumber();
                validateDateOfBirth();
                addData();
            }
        });
    }

    private void addData() {
        if (!validateFullname() | !validatePhoneNumber() | !validateDateOfBirth())
        {
            return;
        }
        else
        {
            try {
                userModel = new UserModel(-1, mUsername, mEmail, mPhoneNumber, mPassword, mFullname, mCountry, mDOB);
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();


            }
            catch (Exception e)
            {
                Toast.makeText(this, "Error creating account, please try again", Toast.LENGTH_SHORT).show();
                userModel = new UserModel(-1, "error", "error", "error", "error", "error", "error", "error");
            }

            dataBaseHelper = new UserDataBaseHelper(PersonalDecloration_Activity.this);
            boolean success = dataBaseHelper.addOneUser(userModel);


            // Adding data into google firebase realtime database
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // Checking if email and phone number is not registered before

                    if (snapshot.hasChild(mPhoneNumber))
                    {
                        //---> Asking user if he/she wants to log in to existing account
                        AlertDialog.Builder builder = new AlertDialog.Builder(PersonalDecloration_Activity.this);
                        builder.setTitle("SPACED");
                        builder.setMessage("Hey there, it seems like there's an existing account with the same phone number.");
                        builder.setNegativeButton("Register a new account", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getApplicationContext(), Registeration_Activity.class));
                            }
                        });
                        builder.setPositiveButton("Log in to existing account", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(getApplicationContext(), StartingPage_Activity.class));
                            }
                        });
                        builder.create().show();
                    }
                    else
                    {
                        //sending data to firebase Realtime Database
                        //we are using email and phone number as a unique identity of every user.
                        //so all the other details of the user comes under email mainly.

                        //--->Adding the user's personal information to firebase
                        databaseReference.child(mPhoneNumber).child("User's Information").child("Full Name").setValue(mFullname);
                        databaseReference.child(mPhoneNumber).child("User's Information").child("Email").setValue(mEmail);
                        databaseReference.child(mPhoneNumber).child("User's Information").child("Phone Number").setValue(mPhoneNumber);
                        databaseReference.child(mPhoneNumber).child("User's Information").child("Password").setValue(mPassword);
                        databaseReference.child(mPhoneNumber).child("User's Information").child("Username").setValue(mUsername);
                        databaseReference.child(mPhoneNumber).child("User's Information").child("Country").setValue(mCountry);
                        databaseReference.child(mPhoneNumber).child("User's Information").child("Date Of Birth").setValue(mDOB);

                        //--->Adding the default setting preference for the user to the real time database
                        /**
                         USER -> MOBILE NO. -> USER'S SETTINGS -> SECURITY & ACCOUNT ACCESS -> SECURITY -> TWO FACTOR AUTHENTICATION AND PASSWORD RESET
                         */
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Security and Account Access").child("Security").child("Two Factor Authentication").child("Authentication App").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Security and Account Access").child("Security").child("Two Factor Authentication").child("Security Key").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Security and Account Access").child("Security").child("Password Reset Protection").setValue("OFF");


                        /**
                         USER -> MOBILE NO. -> USER'S SETTINGS -> Privacy and Safety
                         */
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Privacy and Safety").child("Ad Preferences").child("Personalized Ads").setValue("ON");

                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Privacy and Safety").child("Off-SPACED activity").child("See SPACED content across web").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Privacy and Safety").child("Off-SPACED activity").child("Personalized Identity").setValue("ON");

                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Privacy and Safety").child("Data Sharing").child("Data Sharing with Business Partners").setValue("ON");

                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Privacy and Safety").child("Location Information").child("Personalized Based On Place").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Privacy and Safety").child("Location Information").child("Precise Location").setValue("ON");


                        /**
                         USER -> MOBILE NO. -> USER'S SETTINGS -> NOTIFICATION
                         */
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Notification").child("Filters").child("Quality filter").setValue("ON");

                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Notification").child("Preference").child("Push Notifications").child("Topics").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Notification").child("Preference").child("Push Notifications").child("News").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Notification").child("Preference").child("Push Notifications").child("In-App Recommendations").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Notification").child("Preference").child("Push Notifications").child("Spaces").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Notification").child("Preference").child("Push Notifications").child("First Look At New Features").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Notification").child("Preference").child("Push Notifications").child("Crisis and Emergency Alert").setValue("ON");

                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Notification").child("Preference").child("SMS Notifications").child("News").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Notification").child("Preference").child("SMS Notifications").child("First Look At New Features").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Notification").child("Preference").child("SMS Notifications").child("Crisis and Emergency Alert").setValue("ON");

                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Notification").child("Preference").child("Email Notifications").child("Topics").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Notification").child("Preference").child("Email Notifications").child("Updates on SPACED Product").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Notification").child("Preference").child("Email Notifications").child("Tips On Getting More Out Of SPACED").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Notification").child("Preference").child("Email Notifications").child("Things You Missed").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Notification").child("Preference").child("Email Notifications").child("Partner Products and Third Party Services").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Notification").child("Preference").child("Email Notifications").child("Research Survey").setValue("ON");



                        /**
                         USER -> MOBILE NO. -> USER'S SETTINGS -> ACCESSIBILITY,DISPLAY AND LANGUAGES -> ACCESSIBILITY
                         */
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Accessibility Display and Languages").child("Accessibility").child("Pronounce Hashtag").setValue("OFF");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Accessibility Display and Languages").child("Accessibility").child("Include Username Timeline").setValue("OFF");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Accessibility Display and Languages").child("Accessibility").child("Read Shortened URLs").setValue("OFF");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Accessibility Display and Languages").child("Accessibility").child("Increase Color Contrast").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Accessibility Display and Languages").child("Accessibility").child("Motion").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Accessibility Display and Languages").child("Accessibility").child("Open Links in Reader View").setValue("ON");


                        /**
                         USER -> MOBILE NO. -> USER'S SETTINGS -> ACCESSIBILITY,DISPLAY AND LANGUAGES -> DISPLAY AND SOUND
                         */
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Accessibility Display and Languages").child("Display and Sound").child("Media Previews").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Accessibility Display and Languages").child("Display and Sound").child("Dark Mode").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Accessibility Display and Languages").child("Display and Sound").child("Media Preview").setValue("ON");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Accessibility Display and Languages").child("Display and Sound").child("Sound Reader View").setValue("ON");


                        /**
                         USER -> MOBILE NO. -> USER'S SETTINGS -> ACCESSIBILITY,DISPLAY AND LANGUAGES -> LANGUAGES
                         */
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Accessibility Display and Languages").child("Languages").child("Language").setValue("English");


                        /**
                         USER -> MOBILE NO. -> USER'S SETTINGS -> ACCESSIBILITY,DISPLAY AND LANGUAGES -> DATA USAGE
                         */
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Accessibility Display and Languages").child("Data Usage").child("Data Saver").setValue("OFF");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Accessibility Display and Languages").child("Data Usage").child("High Quality Images").setValue("On Cellular Of Wifi");
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Accessibility Display and Languages").child("Data Usage").child("High Quality Videos").setValue("On Cellular of Wifi");


                        /**
                         USER -> MOBILE NO. -> USER'S SETTINGS -> ADDITIONAL RESOURCES -> SEND CRASH REPORTS
                         */
                        databaseReference.child(mPhoneNumber).child("User's Information").child("User's Settings").child("Additional Resources").child("Crash Report").setValue("ON");


                        Toast.makeText(PersonalDecloration_Activity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainMenuPage_Activity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            //Implementing data into google firebase firestore
            Map<String, Object> user = new HashMap<>();
            user.put("Username", mUsername);
            user.put("Email", mEmail);
            user.put("Phone Number", mPhoneNumber);
            user.put("Password", mPassword);
            user.put("Full Name", mFullname);
            user.put("Country", mCountry);
            user.put("Date Of Birth", mDOB);

            user.put("Include Username Timeline", "ON");
            user.put("Increase Color Contrast", "OFF");
            user.put("Motion", "OFF");
            user.put("Open Links in Reader View", "OFF");
            user.put("Pronounce Hashtag", "ON");
            user.put("Read Shortened URLs", "ON");

            user.put("Data Saver", "ON");
            user.put("High Quality Images", "On Cellular Of Wifi");
            user.put("High Quality Videos", "On Cellular Of Wifi");

            user.put("Dark Mode", "ON");
            user.put("Media Preview", "ON");
            user.put("Media Previews", "ON");
            user.put("Sound Reader View", "ON");

            user.put("Language", "English");

            user.put("Crash Report", "OFF");

            user.put("Quality filter", "ON");

            user.put("Partner Products and Third Party Services", "ON");
            user.put("Research Survey", "ON");
            user.put("Things You Missed", "ON");
            user.put("Tips On Getting More Out Of SPACED", "ON");
            user.put("Topics", "ON");
            user.put("Updates on SPACED Product", "ON");

            user.put("Crisis and Emergency Alert", "ON");
            user.put("First Look At New Features", "ON");
            user.put("In-App Recommendations", "ON");
            user.put("News", "ON");
            user.put("Spaces", "ON");
            user.put("Topic", "ON");

            user.put("SMS Crisis and Emergency Alert", "ON");
            user.put("SMS First Look At New Features", "ON");
            user.put("SMS News", "ON");

            user.put("Personalized Ads", "ON");

            user.put("Data Sharing with Business Partners", "ON");

            user.put("Personalized Based On Place", "ON");
            user.put("Precise Location", "ON");

            user.put("Personalized Identity", "ON");
            user.put("See SPACED content across web", "ON");

            user.put("Password Reset Protection", "OFF");

            user.put("Authentication App", "ON");
            user.put("Security Key", "ON");


            fireStoreDB.collection("user")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(PersonalDecloration_Activity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(PersonalDecloration_Activity.this, "Registration unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    }

    private boolean validateDateOfBirth() {
        currentYear = Calendar.getInstance().get(Calendar.YEAR);
        userAge = datePicker_DOB.getYear();
        isAgeValid = currentYear - userAge;
        
        if (isAgeValid < 20)
        {
            Toast.makeText(this, "You have not meet the minimum age requirement", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            String userDate = String.valueOf(datePicker_DOB.getDayOfMonth());
            String userMonth = String.valueOf(datePicker_DOB.getMonth());
            String userYear = String.valueOf(datePicker_DOB.getYear());

            mDOB = userDate + "/" + userMonth + "/" + userYear;

            return true;
        }
    }

    private boolean validatePhoneNumber() {

        if (mPhoneNumber.isEmpty())
        {
            editTxt_mobileNumber.setError("Field cannot be empty");
            return false;
        }
        // Matching the input email to a predefined email pattern
        else if (!Patterns.PHONE.matcher(mPhoneNumber).matches())
        {
            editTxt_mobileNumber.setError("Please enter a valid mobile number");
            return false;
        }
        else
        {
            editTxt_mobileNumber.setError(null);
            return true;
        }
    }

    private boolean validateFullname() {

        //Defining our own Full Name pattern
        final Pattern FULLNAME_PATTERN =
                Pattern.compile("^" +
                        "(?=.*[@#$%^&+=])" +     // at least 1 special character
                        "$");

        // Validating if the Full Name entered is valid
        if (mFullname.isEmpty())
        {
            editTxt_Fullname.setError("Field cannot be empty.");
            return false;
        }
        // if fullname matches the pattern
        // it will display an error message "Invalid Name"
        else if (FULLNAME_PATTERN.matcher(mFullname).matches())
        {
            editTxt_Fullname.setError("Invalid Full Name");
            return false;
        }
        else
        {
            editTxt_Fullname.setError(null);
            return true;
        }
    }

    private void countrySpinner() {
        countryAdapter = new CountryAdapter(PersonalDecloration_Activity.this, CountryData.getCountryList());
        spinner_country.setAdapter(countryAdapter);
        spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                mCountry = spinner_country.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initWidget() {
        editTxt_Fullname = findViewById(R.id.editTxt_Fullname);
        editTxt_mobileNumber = findViewById(R.id.editTxt_mobileNumber);

        spinner_country = findViewById(R.id.spinner_country);

        datePicker_DOB = findViewById(R.id.datePicker_DOB);

        btn_register = findViewById(R.id.btn_register);

        txtView_welcomeMsg = findViewById(R.id.txtView_welcomeMsg);

        //---> Bring values from previous slide here
        mUsername = intent.getStringExtra("editTxt_userName");
        mEmail = intent.getStringExtra("editTxt_email");
        mPassword = intent.getStringExtra("editTxt_password");

        //---> Set welcome message text
        txtView_welcomeMsg.setText("Hello " + mUsername);
    }
}