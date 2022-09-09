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

public class Additional_Resources_Activity extends AppCompatActivity {

    private ImageView btn_back, btn_helpCenter, btn_termsOfService, btn_privacyPolicy, btn_cookiesUsed, btn_legalNotices;

    private TextView txtView_Done, txtView_version, txtView_crashReport, txtView_helpCenter, txtView_termsOfService, txtView_privacyPolicy,
            txtView_cookiesUsed, txtView_legalNotices;

    private LinearLayout LL_crashReport, LL_helpCenter;

    private RelativeLayout rel_crash_report, rel_helpCenter, rel_termsOfService, rel_privacyPolicy, rel_cookiesUsed, rel_legalNotices;

    private androidx.appcompat.widget.SwitchCompat crashReport_switch;

    private String crashReport = "ON";

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://spaced-768d2-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference databaseReference  = database.getReference().child("users");

    FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_resources);

        intiWidget();

        pageDirectories();
    }

    private void pageDirectories() {
        //Lead user to the previous page
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //Save changes and lead user to the main settings page
        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //--->Updating data
                updateData();
            }
        });

        //--->onClickListener for send report crash
        crashReport_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    crashReport = "ON";
                else
                    crashReport = "OFF";
            }
        });

        //--->onClickListener for Help center
        // These will lead you to the Help center page
        //TODO: Lead you to a external website
        btn_helpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lead you to a external website that needs to be created
            }
        });

        txtView_helpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        LL_helpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        rel_helpCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //--->onClickListener for Terms of service
        // These will lead you to the Terms of service page
        btn_termsOfService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        txtView_termsOfService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        rel_termsOfService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //--->onClickListener for Privacy policy
        // These will lead you to the Privacy policy page
        btn_privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PrivacyPolicy_Activity.class));
            }
        });

        txtView_privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PrivacyPolicy_Activity.class));
            }
        });

        rel_privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PrivacyPolicy_Activity.class));
            }
        });

        //--->onClickListener for cookies use
        // These will lead you to the cookies use page
        //TODO: Lead you to a external website
        btn_cookiesUsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lead you to a external website that needs to be created
            }
        });

        txtView_cookiesUsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        rel_cookiesUsed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //--->onClickListener for legal notice
        // These will lead you to the legal notice page
        btn_legalNotices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Legal_Notice_Activity.class));
            }
        });

        txtView_legalNotices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Legal_Notice_Activity.class));
            }
        });

        rel_legalNotices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Legal_Notice_Activity.class));
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
                    user.put("Crash Report", crashReport);

                    databaseReference.child(mPhoneNumber)
                            .child("User's Information")
                            .child("User's Settings")
                            .child("Additional Resources")
                            .updateChildren(user).addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {

                                    if (task.isSuccessful())
                                    {
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
                                                                            Toast.makeText(Additional_Resources_Activity.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {
                                                                            Toast.makeText(Additional_Resources_Activity.this, "An error occurred, please try again later", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    });
                                                        }
                                                    }
                                                });
                                    }
                                    else
                                        Toast.makeText(Additional_Resources_Activity.this, "An error occurred during update, please try again later", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
        builder.setView(view).create().show();
    }

    private void intiWidget() {
        //--->ImageView
        btn_back = findViewById(R.id.btn_back);
        btn_helpCenter = findViewById(R.id.btn_helpCenter);
        btn_termsOfService = findViewById(R.id.btn_termsOfService);
        btn_privacyPolicy = findViewById(R.id.btn_privacyPolicy);
        btn_cookiesUsed = findViewById(R.id.btn_cookiesUsed);
        btn_legalNotices = findViewById(R.id.btn_legalNotices);

        //--->TextView
        txtView_Done = findViewById(R.id.txtView_Done);
        txtView_version = findViewById(R.id.txtView_version);
        txtView_crashReport = findViewById(R.id.txtView_crashReport);
        txtView_helpCenter = findViewById(R.id.txtView_helpCenter);
        txtView_termsOfService = findViewById(R.id.txtView_termsOfService);
        txtView_privacyPolicy = findViewById(R.id.txtView_privacyPolicy);
        txtView_cookiesUsed = findViewById(R.id.txtView_cookiesUsed);
        txtView_legalNotices = findViewById(R.id.txtView_legalNotices);

        //--->LinearLayout
        LL_crashReport = findViewById(R.id.LL_crashReport);
        LL_helpCenter = findViewById(R.id.LL_helpCenter);

        //--->RelativeLayout
        rel_crash_report = findViewById(R.id.rel_crash_report);
        rel_helpCenter = findViewById(R.id.rel_helpCenter);
        rel_termsOfService = findViewById(R.id.rel_termsOfService);
        rel_privacyPolicy = findViewById(R.id.rel_privacyPolicy);
        rel_cookiesUsed = findViewById(R.id.rel_cookiesUsed);
        rel_legalNotices = findViewById(R.id.rel_legalNotices);

        //--->androidx.appcompat.widget.SwitchCompat
        crashReport_switch = findViewById(R.id.crashReport_switch);


    }
}