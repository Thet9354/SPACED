package com.example.spaced;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ChangePassword_Activity extends AppCompatActivity {

    private ImageView btn_back;

    private TextView txtView_Done, txtView_forgotPassword;

    private EditText editTxt_currentPassword, editTxt_newPassword, editTxt_confirmPassword;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://spaced-768d2-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference databaseReference = database.getReference().child("users");
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;


    private String mCurrentPassword, mNewPassword, mConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {
        //Cancel changes and lead users to the previous page
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), YourAccount_Activity.class));
            }
        });

        //Save changes and lead the user to the previous page
        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateCurrentPassword();
                validateNewPassword();
                validateConfirmPassword();
                updatePassword();
            }
        });
    }

    private void updatePassword() {
        if (!validateNewPassword() | !validateConfirmPassword() | !validateCurrentPassword())
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

                    String mPhoneNumber = editTxt_mobileNumber.getText().toString();

                    if (mPhoneNumber.isEmpty())
                    {
                        editTxt_mobileNumber.setError("Field cannot be empty");
                    }
                    else
                    {
                        //--->Update data on firebase realtime DB
                        HashMap user = new HashMap();
                        user.put("Password", mNewPassword);

                        databaseReference.child(mPhoneNumber)
                                .child("User's Information")
                                .updateChildren(user).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {

                                        if (task.isSuccessful())
                                        {
                                            Toast.makeText(ChangePassword_Activity.this, "Yessir we did it", Toast.LENGTH_SHORT).show();

                                            //TODO: Update data in firestore here
                                        }
                                        else
                                            Toast.makeText(ChangePassword_Activity.this, "An error occurred during update, please try again later", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }
            });
            builder.setView(view).create().show();
        }
    }

    private boolean validateConfirmPassword() {
        mConfirmPassword = editTxt_confirmPassword.getText().toString();

        if (mConfirmPassword.isEmpty())
        {
            editTxt_confirmPassword.setError("File cannot be empty.");
            return false;
        }
        else if (!mNewPassword.equals(mConfirmPassword))
        {
            editTxt_confirmPassword.setError("Ensure your new password and confirm password is the same.");
            return false;
        }
        else if ((mCurrentPassword.equals(mNewPassword)) | (mCurrentPassword.equals(mConfirmPassword)))
        {
            editTxt_confirmPassword.setError("Your new password cannot be the same as the current password.");
            editTxt_newPassword.setError("Your new password cannot be the same as the current password.");
            editTxt_currentPassword.setError("Your new password cannot be the same as the current password.");
            return false;
        }
        else
            return true;
    }

    private boolean validateNewPassword() {
        mNewPassword = editTxt_newPassword.getText().toString();

        if (mNewPassword.isEmpty())
        {
            editTxt_newPassword.setError(null);
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean validateCurrentPassword() {
        mCurrentPassword = editTxt_currentPassword.getText().toString();

        if (mCurrentPassword.isEmpty())
        {
            editTxt_currentPassword.setError("Field cannot be empty.");
            return false;
        }
        else
        {
            editTxt_currentPassword.setError(null);
            return true;
        }

    }

    private void initWidget() {
        //--->ImageView
        btn_back = findViewById(R.id.btn_back);

        //--->TextView
        txtView_Done = findViewById(R.id.txtView_Done);
        txtView_forgotPassword = findViewById(R.id.txtView_forgotPassword);

        //--->EditText
        editTxt_currentPassword = findViewById(R.id.editTxt_currentPassword);
        editTxt_newPassword = findViewById(R.id.editTxt_newPassword);
        editTxt_confirmPassword = findViewById(R.id.editTxt_confirmPassword);
    }
}