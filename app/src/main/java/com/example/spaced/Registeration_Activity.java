package com.example.spaced;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class Registeration_Activity extends AppCompatActivity {

    private EditText editTxt_userName, editTxt_email, editTxt_password, editTxt_confirmPassword;
    private Button btn_signIn;
    private ImageView img_facebook, img_google, img_linkedin;

    private String mUsername, mEmail, mPassword, mConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Store values
                mUsername = editTxt_userName.getText().toString();
                mEmail = editTxt_email.getText().toString();
                mPassword = editTxt_password.getText().toString();
                mConfirmPassword = editTxt_confirmPassword.getText().toString();

                validateUsername();
                validateEmail();
                validatePassword();
                validateConfirmPassword();
                addData();
            }
        });
    }

    private void addData() {
        if (!validateUsername() | !validateEmail() | !validatePassword() | !validateConfirmPassword())
        {
            return;
        }
        else {
            // Add to personal SQLite database
            // Add to Google Firebase database
            // Move on to next activity page
            Intent intent = new Intent(getApplicationContext(), PersonalDecloration_Activity.class);
            intent.putExtra("editTxt_userName", mUsername);
            intent.putExtra("editTxt_email", mEmail);
            intent.putExtra("editTxt_password", mPassword);
            startActivity(intent);
        }
    }

    private boolean validateConfirmPassword() {

        // If password is not the same as confirm password
        // Error and invalid
        if (mPassword.equals(mConfirmPassword))
        {
            return true;
        }
        else
            return false;
    }

    private boolean validatePassword() {
        // defining our own password pattern
        final Pattern PASSWORD_PATTERN =
                Pattern.compile("^" +
                        "(?=.*[@#$%^&+=])" +     // at least 1 special character
                        "(?=\\S+$)" +            // no white spaces
                        ".{9,}" +                // at least 4 characters
                        "$");

        // if password field is empty
        // it will display error message "Field can not be empty"
        if (mPassword.isEmpty())
        {
            editTxt_password.setError("Field can not be empty");
            return false;
        }

        // if password does not matches to the pattern
        // it will display an error message "Password is too weak"
        else if (!PASSWORD_PATTERN.matcher(mPassword).matches())
        {
            editTxt_password.setError("Password is too weak");
            return false;
        }
        else
        {
            editTxt_password.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        if (mEmail.isEmpty())
        {
            editTxt_email.setError("Field can not be empty");
            return false;
        }

        // Matching the input email to a predefined email pattern
        else if (!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches())
        {
            editTxt_email.setError("Please enter a valid email address");
            return false;
        }
        else
        {
            editTxt_email.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        // defining our own username pattern
        final Pattern USERNAME_PATTERN2 =
                Pattern.compile("^" +
                        "(?=.*[@#$%^&+=])" +     // at least 1 special character
                        "(?=\\S+$)" +            // no white spaces
                        ".{5,}" +                // at least 4 characters
                        "$");

        if (mUsername.isEmpty())
        {
            editTxt_userName.setError("Field can not be empty");
            return false;
        }

        // Matching the input email to a predefined email pattern
        else if (!USERNAME_PATTERN2.matcher(mUsername).matches())
        {
            editTxt_userName.setError("Please enter a valid username");
            return false;
        }
        else
        {
            editTxt_userName.setError(null);
            return true;
        }
    }

    private void initWidget() {
        editTxt_userName = findViewById(R.id.editTxt_userName);
        editTxt_email = findViewById(R.id.editTxt_email);
        editTxt_password = findViewById(R.id.editTxt_password);
        editTxt_confirmPassword = findViewById(R.id.editTxt_confirmPassword);

        btn_signIn = findViewById(R.id.btn_signIn);

        img_facebook = findViewById(R.id.img_facebook);
        img_google = findViewById(R.id.img_google);
        img_linkedin = findViewById(R.id.img_linkedin);
    }
}