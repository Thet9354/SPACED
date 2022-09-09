package com.example.spaced;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Update_PhoneNumber_Activity extends AppCompatActivity {

    private ImageView btn_back;

    private TextView txtView_Done;

    private EditText editTxt_newPhone, editTxt_currentPhone;

    private Button btn_update;

    private Intent intent;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://spaced-768d2-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference databaseReference  = database.getReference().child("users");

    FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();

    private String currentPhoneNumber, newPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_phone_number);

        intent = getIntent();

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
        //Ask confirmation if user wishes to update his/her phone number
        //If yes, save all changes and lead the user to the previous page
        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Account_Activity.class));
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validate_currentPhoneNumber();

                validate_newPhoneNumber();

                updatePhoneNumber();
            }
        });
    }

    private void updatePhoneNumber() {
        if (!validate_newPhoneNumber() | !validate_currentPhoneNumber())
        {
            return;
        }
        else
        {
            HashMap user = new HashMap();
            user.put("Phone Number", newPhoneNumber);

            databaseReference.child(currentPhoneNumber).child("User's Information").updateChildren(user).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {

                    if (task.isSuccessful())
                    {
                        //--->Updating data in firestore DB
                        firestoreDB.collection("user")
                                .whereEqualTo("Phone Number", currentPhoneNumber)
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
                                                            Toast.makeText(Update_PhoneNumber_Activity.this, "Phone Number has been successfully updated", Toast.LENGTH_SHORT).show();
                                                            //TODO: Update SQLite DB later
                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(Update_PhoneNumber_Activity.this, "An error has occurred during update, please try again later", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                        }
                                        else
                                        {
                                            Toast.makeText(Update_PhoneNumber_Activity.this, "An error has occurred during update, please try again later", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                    else
                        Toast.makeText(Update_PhoneNumber_Activity.this, "An error occurred, please try again later", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private boolean validate_newPhoneNumber() {

        newPhoneNumber = editTxt_newPhone.getText().toString();

        if (newPhoneNumber.isEmpty())
        {
            editTxt_newPhone.setError("Field cannot be empty");
            return false;
        }
        // Matching the input email to a predefined email pattern
        else if (!Patterns.PHONE.matcher(currentPhoneNumber).matches())
        {
            editTxt_newPhone.setError("Please enter a valid mobile number");
            return false;
        }
        else
        {
            editTxt_newPhone.setError(null);
            return true;
        }
    }

    private boolean validate_currentPhoneNumber() {

        currentPhoneNumber = editTxt_currentPhone.getText().toString();

        if (currentPhoneNumber.isEmpty())
        {
         editTxt_currentPhone.setError("Field cannot be empty");
         return false;
        }
        else if (!Patterns.PHONE.matcher(currentPhoneNumber).matches())
        {
            editTxt_currentPhone.setError("Please enter a valid mobile number");
            return false;
        }
        else
        {
            editTxt_currentPhone.setError(null);
            return true;
        }
    }

    private void initWidget() {
        //--->ImageView
        btn_back = findViewById(R.id.btn_back);

        //--->TextView
        txtView_Done = findViewById(R.id.txtView_Done);
        editTxt_currentPhone = findViewById(R.id.editTxt_currentPhone);

        //--->EditText
        editTxt_newPhone = findViewById(R.id.editTxt_newPhone);

        //--->Button
        btn_update = findViewById(R.id.btn_update);

    }
}