package com.example.spaced;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Two_Factor_authentication_Activity extends AppCompatActivity {

    private ImageView btn_back;

    private TextView txtView_Done;

    private androidx.appcompat.widget.SwitchCompat securityKey_switch, authenticationApp_switch;

    private String authenticationApp = "ON";

    private String securityKey = "ON";

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://spaced-768d2-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference databaseReference  = database.getReference().child("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_factor_authentication);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {
        //Discard changes and lead users to the previous page
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //Save changes and lead users to the previous page
        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //--->Update data
                updateData();
            }
        });

        //onCheckChangedListener for authenticationApp_switch
        authenticationApp_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    authenticationApp = "ON";
                }
                else
                {
                    authenticationApp = "OFF";
                }
            }
        });

        //onCheckedChangedListener for security key
        securityKey_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    securityKey = "ON";
                }
                else
                {
                    securityKey = "OFF";
                }
            }
        });
    }

    private void updateData() {

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
                    //--->Updating data in firebase realtime DB
                    HashMap user = new HashMap();
                    user.put("Authentication App", authenticationApp);
                    user.put("Security Key", securityKey);

                    databaseReference.child(mPhoneNumber)
                            .child("User's Information")
                            .child("User's Settings")
                            .child("Security and Account Access")
                            .child("Security")
                            .child("Two Factor Authentication")
                            .updateChildren(user).addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(Two_Factor_authentication_Activity.this, "Yah we did it", Toast.LENGTH_SHORT).show();
                                        //TODO Update the data to firestore
                                    }
                                    else
                                        Toast.makeText(Two_Factor_authentication_Activity.this, "An error occurred during update, please try again later", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
        builder.setView(view).create().show();
    }

    private void initWidget() {
        //--->ImageView
        btn_back = findViewById(R.id.btn_back);

        //--->TextView
        txtView_Done = findViewById(R.id.txtView_Done);

        //--->androidx.appcompat.widget.SwitchCompat
        authenticationApp_switch = findViewById(R.id.authenticationApp_switch);
        securityKey_switch = findViewById(R.id.securityKey_switch);
    }
}