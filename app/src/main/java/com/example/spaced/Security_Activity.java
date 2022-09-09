package com.example.spaced;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class Security_Activity extends AppCompatActivity {

    private ImageView btn_back, btn_two_factor_authentication;

    private TextView txtView_Done, txtView_two_factor_authentication;

    private LinearLayout LL_authentication;

    private RelativeLayout rel_two_factor_authentication;

    private androidx.appcompat.widget.SwitchCompat passwordProtection_switch;

    private String passProtection = "OFF";

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://spaced-768d2-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference databaseReference  = database.getReference().child("users");

    FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {
        //Discard changes and lead user to the previous page
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Security_and_account_Activity.class));
            }
        });

        //Save changes and lead user to the previous page
        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //--->Update data on firebase realtime DB
                update();

            }
        });


        //--->onClickListener for the Two-factor authentication section
        // These will lead you to the Two-factor authentication page
        btn_two_factor_authentication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Two_Factor_authentication_Activity.class));
            }
        });

        txtView_two_factor_authentication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Two_Factor_authentication_Activity.class));
            }
        });

        LL_authentication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Two_Factor_authentication_Activity.class));
            }
        });

        rel_two_factor_authentication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Two_Factor_authentication_Activity.class));
            }
        });


        //--->onClickListener for the password reset protection switch
        passwordProtection_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    passProtection = "ON";
                }
                else
                    passProtection = "OFF";
            }
        });
    }

    private void update() {
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
                    user.put("Password Reset Protection", passProtection);

                    databaseReference.child(mPhoneNumber)
                            .child("User's Information")
                            .child("User's Settings")
                            .child("Security and Account Access")
                            .child("Security")
                            .updateChildren(user).addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {

                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(Security_Activity.this, "Yessir we did it", Toast.LENGTH_SHORT).show();

                                        //TODO: Update data in firestore here
                                        //--->Updating data in firestore

                                        firestoreDB.collection("user")
                                                .whereEqualTo("Phone Number", mPhoneNumber)
                                                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                                        if (task.isSuccessful() && !task.getResult().isEmpty())
                                                        {
                                                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                                                            String documentID = documentSnapshot.getId();
                                                            firestoreDB.collection("user")
                                                                    .document(documentID)
                                                                    .update(user).addOnSuccessListener(new OnSuccessListener() {
                                                                        @Override
                                                                        public void onSuccess(Object o) {
                                                                            Toast.makeText(Security_Activity.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {
                                                                            Toast.makeText(Security_Activity.this, "An error occurred, please try again later", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    });
                                                        }
                                                    }
                                                });
                                    }
                                    else
                                        Toast.makeText(Security_Activity.this, "An error occurred during update, please try again later", Toast.LENGTH_SHORT).show();
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
        btn_two_factor_authentication = findViewById(R.id.btn_two_factor_authentication);

        //--->TextView
        txtView_Done = findViewById(R.id.txtView_Done);
        txtView_two_factor_authentication = findViewById(R.id.txtView_two_factor_authentication);

        //--->LinearLayout
        LL_authentication = findViewById(R.id.LL_authentication);

        //--->RelativeLayout
        rel_two_factor_authentication = findViewById(R.id.rel_two_factor_authentication);

        //--->androidx.appcompat.widget.SwitchCompat
        passwordProtection_switch = findViewById(R.id.passwordProtection_switch);
    }
}