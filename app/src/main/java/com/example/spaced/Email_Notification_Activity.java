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

public class Email_Notification_Activity extends AppCompatActivity {

    private ImageView btn_back;

    private TextView txtView_Done;

    private androidx.appcompat.widget.SwitchCompat emailNotification_switch, productsUpdates_switch, tips_switch, missed_switch,
            partnerProducts_switch, researchSurvey_switch;

    private String topics = "ON";

    private String partnerProduct = "ON";

    private String researchSurvey = "ON";

    private String thingsMissed = "ON";

    private String more = "ON";

    private String productUpdate = "ON";

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://spaced-768d2-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference databaseReference  = database.getReference().child("users");

    FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_notification);

        initWidget();

        pageDirectories();

    }

    private void pageDirectories() {
        //Discard changes and lead user to the previous page
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //Save changes and lead user to the previous page
        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //--->Updating data
                updateData();
            }
        });

        //onCheckChangeListener for emailNotification_switch
        emailNotification_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    topics = "ON";
                }
                else
                {
                    topics = "OFF";
                }
            }
        });


        //onCheckChangeListener for productsUpdates_switch
        productsUpdates_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    productUpdate = "ON";
                }
                else
                {
                    productUpdate = "OFF";
                }
            }
        });

        //onCheckChangeListener for tips_switch
        tips_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    more = "ON";
                }
                else
                {
                    more = "OFF";
                }
            }
        });


        //onCheckChangeListener for missed_switch
        missed_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    thingsMissed = "ON";
                }
                else
                {
                    thingsMissed = "OFF";
                }
            }
        });

        //onCheckChangeListener for partnerProducts_switch
        partnerProducts_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    partnerProduct = "ON";
                }
                else
                {
                    partnerProduct = "OFF";
                }
            }
        });

        //onCheckChangeListener for researchSurvey_switch
        researchSurvey_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    researchSurvey = "ON";
                }
                else
                {
                    researchSurvey = "OFF";
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
                    //--->Update data on firebase realtime DB
                    HashMap user = new HashMap();
                    user.put("Topics", topics);
                    user.put("Updates on SPACED Product", productUpdate);
                    user.put("Tips On Getting More Out Of SPACED", more);
                    user.put("Things You Missed", thingsMissed);
                    user.put("Partner Products and Third Party Services", partnerProduct);
                    user.put("Research Survey", researchSurvey);


                    databaseReference.child(mPhoneNumber)
                            .child("User's Information")
                            .child("User's Settings")
                            .child("Notification")
                            .child("Preference")
                            .child("Email Notifications")
                            .updateChildren(user).addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {

                                    if (task.isSuccessful())
                                    {
                                        //TODO: Update data in firestore here
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
                                                                            Toast.makeText(Email_Notification_Activity.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {
                                                                            Toast.makeText(Email_Notification_Activity.this, "An error occurred, please try again later", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    });
                                                        }
                                                    }
                                                });
                                    }
                                    else
                                        Toast.makeText(Email_Notification_Activity.this, "An error occurred during update, please try again later", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
        builder.setView(view).create().show();
    }

    private void initWidget() {

        btn_back = findViewById(R.id.btn_back);

        txtView_Done = findViewById(R.id.txtView_Done);

        emailNotification_switch = findViewById(R.id.emailNotification_switch);

        productsUpdates_switch = findViewById(R.id.productsUpdates_switch);

        tips_switch = findViewById(R.id.tips_switch);

        missed_switch = findViewById(R.id.missed_switch);

        partnerProducts_switch = findViewById(R.id.partnerProducts_switch);

        researchSurvey_switch = findViewById(R.id.researchSurvey_switch);


    }
}