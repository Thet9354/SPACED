package com.example.spaced;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Update_Email_Activity extends AppCompatActivity {

    private ImageView btn_back;

    private TextView txtView_Done;

    private EditText editTxt_newEmail, editTxt_password, editTxt_currentEmail;

    private ProgressBar authentication_progressBar;

    private Button btn_authenticate, btn_update;

    private LinearLayout LL_authentication, LL_newEmail;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://spaced-768d2-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference databaseReference  = database.getReference().child("users");

    FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();


    private String currentEmail, newEmail, userPwd, mPhoneNumber;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_email);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {
        //Ask user if he wishes to discard changes.
        //If yes, discard changes and lead the user to the previous page
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //Validate all the details entered
        //Ask confirmation if user wishes to update his/her email
        //If yes, save all changes and lead the user to the previous page
        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Account_Activity.class));
            }
        });

        btn_authenticate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateCurrentEmail();
                validatePassword();
                authenticate();
            }
        });
    }

    private void authenticate() {

        if (!validateCurrentEmail() | !validatePassword())
        {
            return;
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Verification");
            builder.setMessage("We need some extra verification before we can proceed with the changes");

            View view = getLayoutInflater().inflate(R.layout.phone_number_verification, null);
            EditText editTxt_mobileNumber;
            Button btn_verify;
            editTxt_mobileNumber = view.findViewById(R.id.editTxt_mobileNumber);
            btn_verify = view.findViewById(R.id.btn_verify);

            btn_verify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mPhoneNumber = editTxt_mobileNumber.getText().toString();

                    if (mPhoneNumber.isEmpty())
                    {
                        editTxt_mobileNumber.setError("Field cannot be empty");
                    }
                    else
                    {
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.hasChild(mPhoneNumber))
                                {
                                    final String getEmail = snapshot.child(mPhoneNumber).child("User's Information").child("Email").getValue(String.class);
                                    final String getPassword = snapshot.child(mPhoneNumber).child("User's Information").child("Password").getValue(String.class);

                                    if ((getEmail.equals(currentEmail) && (getPassword.equals(userPwd))))
                                    {
                                        //Finished authenticating
                                        builder.setCancelable(true);

                                        btn_authenticate.setEnabled(false);
                                        editTxt_currentEmail.setEnabled(false);
                                        editTxt_password.setEnabled(false);

                                        editTxt_newEmail.setEnabled(true);
                                        btn_update.setEnabled(true);

                                        btn_update.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {

                                                validateNewEmail();

                                                updateEmail();
                                            }
                                        });

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
            });

            builder.setView(view).create().show();
        }
    }

    private void updateEmail() {
        if (!validateNewEmail())
        {
            return;
        }
        else
        {
            //Update email column in SQLite database
            //Update email for the user in google firebase database
            //If no problem faced with update, let user know update was successful & go to previous page.

            //--->Updating data in firebase realtime db
            HashMap user = new HashMap();
            user.put("Email", newEmail);

            databaseReference.child(mPhoneNumber).child("User's Information")
                    .updateChildren(user).addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {

                            if (task.isSuccessful())
                            {
                                //--->Updating data in firestore DB
                                firestoreDB.collection("user")
                                        .whereEqualTo("Email", currentEmail)
                                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                                if (task.isSuccessful() && !task.getResult().isEmpty())
                                                {
                                                    DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                                                    String documentID = documentSnapshot.getId();
                                                    firestoreDB.collection("user")
                                                            .document(documentID)
                                                            .update(user)
                                                            .addOnSuccessListener(new OnSuccessListener() {
                                                                @Override
                                                                public void onSuccess(Object o) {
                                                                    Toast.makeText(Update_Email_Activity.this, "Email address has been successfully updated", Toast.LENGTH_SHORT).show();
                                                                    //TODO: Update SQLite DB later
                                                                }
                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    Toast.makeText(Update_Email_Activity.this, "An error occurred durind update, please try again later", Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                }
                                                else
                                                {
                                                    Toast.makeText(Update_Email_Activity.this, "An error occurred during update, please try again later", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                            else
                                Toast.makeText(Update_Email_Activity.this, "An error occurred, please try again later", Toast.LENGTH_SHORT).show();

                        }
                    });
        }
    }

    private boolean validatePassword() {

        userPwd = editTxt_password.getText().toString();

        final Pattern Password_PATTERN =
                Pattern.compile("^" +
                        "(?=.*[@#$%^&+=])" +     // at least 1 special character
                        "(?=\\S+$)" +            // no white spaces
                        ".{8,}" +                // at least 8 characters
                        "$");

        if (userPwd.isEmpty())
        {
            editTxt_password.setError("Field cannot be empty");
            return false;
        }
        else if (!Password_PATTERN.matcher(userPwd).matches())
        {
            editTxt_password.setError("Please enter a valid password");
            return false;
        }
        else
        {
            editTxt_password.setError(null);
            return true;
        }
    }

    private boolean validateNewEmail() {

        newEmail = editTxt_newEmail.getText().toString();

        if (newEmail.isEmpty())
        {
            editTxt_newEmail.setError("Field cannot be empty");
            return false;
        }
        else if (newEmail.equals(currentEmail))
        {
            editTxt_newEmail.setError("New email cannot be the same as the current email");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(currentEmail).matches())
        {
            editTxt_newEmail.setError("Please enter a valid email address");
            return false;
        }
        else
        {
            editTxt_newEmail.setError(null);
            return true;
        }
    }

    private boolean validateCurrentEmail() {
        //Access firebase database to retrieve the user's username
        currentEmail = editTxt_currentEmail.getText().toString();

        if (currentEmail.isEmpty())
        {
            editTxt_currentEmail.setError("Field cannot be empty");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(currentEmail).matches())
        {
            editTxt_currentEmail.setError("Please enter a valid email address");
            return false;
        }
        else
        {
            editTxt_currentEmail.setError(null);
            return true;
        }
    }

    private void initWidget() {
        //--->ImageView
        btn_back = findViewById(R.id.btn_back);

        //--->TextView
        txtView_Done = findViewById(R.id.txtView_Done);

        //--->EditText
        editTxt_newEmail = findViewById(R.id.editTxt_newEmail);
        editTxt_password = findViewById(R.id.editTxt_password);
        editTxt_currentEmail = findViewById(R.id.editTxt_currentEmail);

        //--->Button
        btn_authenticate = findViewById(R.id.btn_authenticate);
        btn_update = findViewById(R.id.btn_update);

        //--->ProgressBar
        authentication_progressBar = findViewById(R.id.authentication_progressBar);

        //--->LinearLayout
        LL_authentication = findViewById(R.id.LL_authentication);
        LL_newEmail = findViewById(R.id.LL_newEmail);

        btn_update.setEnabled(false); //Make button disabled in the beginning until the user is authenticated
        editTxt_newEmail.setEnabled(false);
    }
}