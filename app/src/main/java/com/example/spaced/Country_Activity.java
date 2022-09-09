package com.example.spaced;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spaced.CountryInventory.CountryData;
import com.example.spaced.CountryInventory.CountryModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class Country_Activity extends AppCompatActivity {

    private ImageView btn_back;

    private TextView txtView_Done;

    private CountryAdapter countryAdapter;

    private Button btn_update;

    private com.example.spaced.CustomSpinner spinner_currentCountry, spinner_newCountry;

    private String currentCountry, newCountry;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://spaced-768d2-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference databaseReference  = database.getReference().child("users");

    FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        initWidget();

        countrySpinner();

        pageDirectories();

    }

    private void pageDirectories() {
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCountry();
            }
        });

        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Account_Activity.class));
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void updateCountry() {
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
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(mPhoneNumber))
                            {
                                final String getCurrentCountry = snapshot.child(mPhoneNumber).child("User's Information").child("Country").getValue(String.class);

                                if (getCurrentCountry.equals(currentCountry))
                                {
                                    //--->Updating data in firebase realtime DB
                                    HashMap user = new HashMap();
                                    user.put("Country", newCountry);

                                    databaseReference.child(mPhoneNumber).child("User's Information")
                                            .updateChildren(user).addOnCompleteListener(new OnCompleteListener() {
                                                @Override
                                                public void onComplete(@NonNull Task task) {

                                                    if (task.isSuccessful())
                                                    {
                                                        //--->Updating data in firestore DB
                                                        firestoreDB.collection("user")
                                                                .whereEqualTo("Country", currentCountry)
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
                                                                                            Toast.makeText(Country_Activity.this, "Country has been successfully updated", Toast.LENGTH_SHORT).show();
                                                                                            //TODO: Update SQLite DB here later
                                                                                        }
                                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                                        @Override
                                                                                        public void onFailure(@NonNull Exception e) {
                                                                                            Toast.makeText(Country_Activity.this, "An error occured, please try again later", Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                        }
                                                                        else
                                                                        {
                                                                            Toast.makeText(Country_Activity.this, "An error occurred during the update, please try again later", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }
                                                                });
                                                    }
                                                    else
                                                        Toast.makeText(Country_Activity.this, "An error occurred, please try again later", Toast.LENGTH_SHORT).show();
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

    private void countrySpinner() {
        countryAdapter = new CountryAdapter(Country_Activity.this, CountryData.getCountryList());
        spinner_currentCountry.setAdapter(countryAdapter);
        spinner_currentCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                currentCountry = spinner_currentCountry.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        countryAdapter = new CountryAdapter(Country_Activity.this, CountryData.getCountryList());
        spinner_newCountry.setAdapter(countryAdapter);
        spinner_newCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                newCountry = spinner_newCountry.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initWidget() {
        //--->ImageView
        btn_back = findViewById(R.id.btn_back);

        //--->TextView
        txtView_Done = findViewById(R.id.txtView_Done);

        //--->Spinner
        spinner_currentCountry = findViewById(R.id.spinner_currentCountry);
        spinner_newCountry = findViewById(R.id.spinner_newCountry);

        //--->Button
        btn_update = findViewById(R.id.btn_update);
    }
}