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

public class Accessibility_Activity extends AppCompatActivity {

    private ImageView btn_back;

    private TextView txtView_Done;

    private androidx.appcompat.widget.SwitchCompat pronounce_switch, includeUsername_switch, shortenedURL_switch, colorContrast_switch,
            reduceMotion_switch, readerView_switch;

    private String username = "OFF";

    private String colorContrast = "ON";

    private String motion = "ON";

    private String readerView = "ON";

    private String hashtag = "OFF";

    private String shortenedURL = "OFF";

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://spaced-768d2-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference databaseReference  = database.getReference().child("users");

    FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessibility);

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
                //--->Updating Data
                updateData();
            }
        });

        //onCheckChangeListener for pronounce_switch
        pronounce_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    hashtag = "ON";
                }
                else
                {
                    hashtag = "OFF";
                }
            }
        });

        //onCheckChangeListener for includeUsername_switch
        includeUsername_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    username = "ON";
                else
                    username = "OFF";
            }
        });

        //onCheckChangeListener for shortenedURL_switch
        shortenedURL_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    shortenedURL = "ON";
                else
                    shortenedURL = "OFF";
            }
        });

        //onCheckChangeListener for colorContrast_switch
        colorContrast_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    colorContrast = "ON";
                else
                    colorContrast = "OFF";
            }
        });

        //onCheckChangeListener for reduceMotion_switch
        reduceMotion_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    motion = "ON";
                else
                    motion = "OFF";
            }
        });

        //onCheckChangeListener for readerView_switch
        readerView_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    readerView = "ON";
                else
                    readerView = "OFF";
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
                    user.put("Pronounce Hashtag", hashtag);
                    user.put("Include Username Timeline", username);
                    user.put("Read Shortened URLs", shortenedURL);
                    user.put("Increase Color Contrast", colorContrast);
                    user.put("Motion", motion);
                    user.put("Open Links in Reader View", readerView);

                    databaseReference.child(mPhoneNumber)
                            .child("User's Information")
                            .child("User's Settings")
                            .child("Accessibility Display and Languages")
                            .child("Accessibility")
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
                                                                            Toast.makeText(Accessibility_Activity.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {
                                                                            Toast.makeText(Accessibility_Activity.this, "An error occurred, please try again later", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    });
                                                        }
                                                    }
                                                });
                                    }
                                    else
                                        Toast.makeText(Accessibility_Activity.this, "An error occurred during the update, please try again later", Toast.LENGTH_SHORT).show();
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
        pronounce_switch = findViewById(R.id.pronounce_switch);
        includeUsername_switch = findViewById(R.id.includeUsername_switch);
        shortenedURL_switch = findViewById(R.id.shortenedURL_switch);
        colorContrast_switch = findViewById(R.id.colorContrast_switch);
        reduceMotion_switch = findViewById(R.id.reduceMotion_switch);
        readerView_switch = findViewById(R.id.readerView_switch);

    }
}