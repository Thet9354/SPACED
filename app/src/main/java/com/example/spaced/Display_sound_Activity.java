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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.slider.Slider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class Display_sound_Activity extends AppCompatActivity {

    private ImageView btn_back;

    private TextView txtView_Done;

    private com.google.android.material.slider.Slider slider_pushUp;

    private androidx.appcompat.widget.SwitchCompat mediaPreview_switch, darkMode_switch, soundEffects_switch, readerView_switch;

    private String mediaPreview = "ON";

    private String darkMode = "ON";

    private String mediaPreviews = "ON";

    private String readerView = "ON";

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://spaced-768d2-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference databaseReference  = database.getReference().child("users");

    FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sound);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {
        //Discard changes and lead the user back to the previous page
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //Save changes and lead the user to the previous page
        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //--->Updating data
                updateData();
            }
        });

        //addOnSliderTouchListener for slider_pushUp
        slider_pushUp.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {

            }
        });

        //onCheckChangeListener for personalizedIdentity_switch
        mediaPreview_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    mediaPreview = "ON";
                }
                else
                {
                    mediaPreview = "OFF";
                }
            }
        });

        //onCheckChangeListener for darkMode_switch
        darkMode_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    darkMode = "ON";
                else
                    darkMode = "OFF";
            }
        });

        //onCheckChangeListener for soundEffects_switch
        soundEffects_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    mediaPreviews = "ON";
                else
                    mediaPreviews = "OFF";
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
                    user.put("Media Previews", mediaPreviews);
                    user.put("Dark Mode", darkMode);
                    user.put("Media Preview", mediaPreview);
                    user.put("Sound Reader View", readerView);

                    databaseReference.child(mPhoneNumber)
                            .child("User's Information")
                            .child("User's Settings")
                            .child("Accessibility Display and Languages")
                            .child("Display and Sound")
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
                                                                            Toast.makeText(Display_sound_Activity.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {
                                                                            Toast.makeText(Display_sound_Activity.this, "An error occurred, please try again later", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    });
                                                        }
                                                    }
                                                });
                                    }
                                    else
                                        Toast.makeText(Display_sound_Activity.this, "An error occurred during the update, please try again later", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
        builder.setView(view).create().show();
    }

    private void initWidget() {
        //--->Imageview
        btn_back = findViewById(R.id.btn_back);

        //--->TextView
        txtView_Done = findViewById(R.id.txtView_Done);

        //--->com.google.android.material.slider.Slider
        slider_pushUp = findViewById(R.id.slider_pushUp);

        //--->androidx.appcompat.widget.SwitchCompat
        mediaPreview_switch = findViewById(R.id.mediaPreview_switch);
        darkMode_switch = findViewById(R.id.darkMode_switch);
        soundEffects_switch = findViewById(R.id.soundEffects_switch);
        readerView_switch = findViewById(R.id.readerView_switch);

    }
}