package com.example.spaced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Account_Activity extends AppCompatActivity {

    private ImageView btn_back, btn_userName, btn_phone, btn_email, btn_country;

    private TextView txtView_Done, txtView_userName, txtView_phone, txtView_email, txtView_country, txtView_myUsername, txtView_myPhoneNumber,
            txtView_myEmail, txtView_myCountry, txtView_logOut;

    private RelativeLayout rel_userName, rel_phone, rel_email, rel_country;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {
        //Leads user back to the original page
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), YourAccount_Activity.class));
            }
        });

        //Saves changes and lead user back to the previous page
        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), YourAccount_Activity.class));
            }
        });

        //--->onClickListener for update username section
        // These will lead you to the update username page
        btn_userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Update_Username_Activity.class));
            }
        });

        txtView_userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Update_Username_Activity.class));
            }
        });

        txtView_myUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Update_Username_Activity.class));
            }
        });

        rel_userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Update_Username_Activity.class));
            }
        });


        //--->onClickListener for update phone number section
        // These will lead you to the update phone number page
        btn_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Update_PhoneNumber_Activity.class));
            }
        });

        txtView_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Update_PhoneNumber_Activity.class));
            }
        });

        txtView_myPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Update_PhoneNumber_Activity.class));
            }
        });

        rel_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Update_PhoneNumber_Activity.class));
            }
        });


        //--->onClickListener for update or add email section
        // These will lead you to the update or add email page
        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Update_Email_Activity.class));
            }
        });

        txtView_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Update_Email_Activity.class));
            }
        });

        txtView_myEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Update_Email_Activity.class));
            }
        });

        rel_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Update_Email_Activity.class));
            }
        });

        //--->onClickListener for update country section
        // These will lead you to the update country page
        btn_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Country_Activity.class));
            }
        });

        txtView_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Country_Activity.class));
            }
        });

        txtView_myCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Country_Activity.class));
            }
        });

        rel_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Country_Activity.class));
            }
        });

        //Ask for confirmation if the user wants to log out,
        //If yes, log out the user from his account.
        txtView_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(), StartingPage_Activity.class));
                Toast.makeText(Account_Activity.this, "Log out successful", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void initWidget() {
        //--->ImageView
        btn_back = findViewById(R.id.btn_back);
        btn_userName = findViewById(R.id.btn_userName);
        btn_phone = findViewById(R.id.btn_phone);
        btn_email = findViewById(R.id.btn_email);
        btn_country = findViewById(R.id.btn_country);

        //--->TextView
        txtView_Done = findViewById(R.id.txtView_Done);
        txtView_userName = findViewById(R.id.txtView_userName);
        txtView_phone = findViewById(R.id.txtView_phone);
        txtView_email = findViewById(R.id.txtView_email);
        txtView_country = findViewById(R.id.txtView_country);
        txtView_myUsername = findViewById(R.id.txtView_myUsername);
        txtView_myPhoneNumber = findViewById(R.id.txtView_myPhoneNumber);
        txtView_myEmail = findViewById(R.id.txtView_myEmail);
        txtView_myCountry = findViewById(R.id.txtView_myCountry);
        txtView_logOut = findViewById(R.id.txtView_logOut);

        //--->RelativeLayout
        rel_userName = findViewById(R.id.rel_userName);
        rel_phone = findViewById(R.id.rel_phone);
        rel_email = findViewById(R.id.rel_email);
        rel_country = findViewById(R.id.rel_country);
    }
}