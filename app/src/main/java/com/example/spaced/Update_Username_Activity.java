package com.example.spaced;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Update_Username_Activity extends AppCompatActivity {

    private ImageView btn_back;

    private TextView txtView_Done;

    private EditText editTxt_newUsername, editTxt_currentUsername;

    private Button btn_update;

    private String currentUsername, newUsername;

    private UserModel userModel;

    private UserDataBaseHelper dataBaseHelper;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://spaced-768d2-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference databaseReference  = database.getReference().child("users");

    FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_username);

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
        //Ask confirmation if user wishes to update his/her username
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

                currentUsername_validation();

                newUsername_validation();

                updateUsername();
            }
        });


    }

    private void updateUsername() {
        if (!currentUsername_validation() | !newUsername_validation())
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
                        editTxt_mobileNumber.setError("Field cannot be left empty");
                    }
                    else
                    {
                        //--->Updating data in firebase
                        HashMap user = new HashMap();
                        user.put("Username", newUsername);

                        databaseReference.child(mPhoneNumber)
                                .child("User's Information")
                                .updateChildren(user).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {

                                        if (task.isSuccessful())
                                        {
                                            //--->Updating data in firestore
                                            firestoreDB.collection("user")
                                                    .whereEqualTo("Username", currentUsername)
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
                                                                                Toast.makeText(Update_Username_Activity.this, "Successfully updated ", Toast.LENGTH_SHORT).show();
                                                                                //TODO: Update SQLite DB later


                                                                            }
                                                                        }).addOnFailureListener(new OnFailureListener() {
                                                                            @Override
                                                                            public void onFailure(@NonNull Exception e) {
                                                                                Toast.makeText(Update_Username_Activity.this, "An error occurred, please try again later", Toast.LENGTH_SHORT).show();
                                                                            }
                                                                        });
                                                            }
                                                            else {
                                                                Toast.makeText(Update_Username_Activity.this, "An error occurred , please try again later", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });
                                        }
                                        else
                                        {
                                            Toast.makeText(Update_Username_Activity.this, "An error occurred, please try again later", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            });

            builder.setView(view).create().show();


        }
    }

    private boolean newUsername_validation() {

        newUsername = editTxt_newUsername.getText().toString();

        // defining our own username pattern
        final Pattern USERNAME_PATTERN =
                Pattern.compile("^" +
                        "(?=.*[@#$%^&+=])" +     // at least 1 special character
                        "(?=\\S+$)" +            // no white spaces
                        ".{5,}" +                // at least 4 characters
                        "$");

        if (newUsername.isEmpty())
        {
            editTxt_newUsername.setError("Field cannot be empty to update your username");
            return false;
        }
        // Matching the input email to a predefined email pattern
        else if (!USERNAME_PATTERN.matcher(newUsername).matches())
        {
            editTxt_newUsername.setError("Please enter a valid username");
            return false;
        }
        else
        {
            editTxt_newUsername.setError(null);
            return true;
        }

    }

    private boolean currentUsername_validation() {
        //Access firebase database to retrieve the user's username
        currentUsername = editTxt_currentUsername.getText().toString();

         Pattern USERNAME_PATTERN =
                Pattern.compile("^" +
                        "(?=.*[@#$%^&+=])" +     // at least 1 special character
                        "(?=\\S+$)" +            // no white spaces
                        ".{5,}" +                // at least 4 characters
                        "$");

        if (currentUsername.isEmpty())
        {
            editTxt_currentUsername.setError("Field cannot be empty to update your username");
            return false;
        }
        else if (!USERNAME_PATTERN.matcher(currentUsername).matches())
        {
            editTxt_currentUsername.setError("Please enter a valid username");
            return false;
        }
        else
        {
            editTxt_newUsername.setError(null);
            return true;
        }

    }

    private void initWidget() {
        //--->ImageView
        btn_back = findViewById(R.id.btn_back);

        //--->TextView
        txtView_Done = findViewById(R.id.txtView_Done);
        editTxt_currentUsername = findViewById(R.id.editTxt_currentUsername);

        //--->EditText
        editTxt_newUsername = findViewById(R.id.editTxt_newUsername);

        //--->Button
        btn_update = findViewById(R.id.btn_update);
    }
}