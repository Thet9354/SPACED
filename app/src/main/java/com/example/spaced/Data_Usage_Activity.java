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

public class Data_Usage_Activity extends AppCompatActivity {

    private ImageView btn_back, btn_highQualityImages, btn_qualityVideo;

    private TextView txtView_Done, txtView_highQualityImage, txtView_highQualityImage_status, txtView_qualityVideo, txtView_qualityVideo_status;

    private LinearLayout LL_highQualityImage, LL_qualityVideo;

    private RelativeLayout rel_highQualityImages, rel_qualityVideo;

    private androidx.appcompat.widget.SwitchCompat dataSaver_switch;

    private String dataSaver = "OFF";

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://spaced-768d2-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference databaseReference  = database.getReference().child("users");

    FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_usage);

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

        //onCheckChangeListener for dataSaver_switch
        dataSaver_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    dataSaver = "ON";
                else
                    dataSaver = "OFF";
            }
        });

        //--->onClickListener for High quality image section
        // These will lead you to the High quality image page
        LL_highQualityImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), High_QualityImage_Activity.class));
            }
        });

        rel_highQualityImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), High_QualityImage_Activity.class));
            }
        });

        txtView_highQualityImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), High_QualityImage_Activity.class));
            }
        });

        txtView_highQualityImage_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), High_QualityImage_Activity.class));
            }
        });

        btn_highQualityImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), High_QualityImage_Activity.class));
            }
        });

        //--->onClickListener for High quality video section
        // These will lead you to the High quality video page
        LL_qualityVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), High_quality_videos_Activity.class));
            }
        });

        rel_qualityVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), High_quality_videos_Activity.class));
            }
        });

        txtView_qualityVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), High_quality_videos_Activity.class));
            }
        });

        txtView_qualityVideo_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), High_quality_videos_Activity.class));
            }
        });

        btn_qualityVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), High_quality_videos_Activity.class));
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
                    user.put("Data Saver", dataSaver);

                    databaseReference.child(mPhoneNumber)
                            .child("User's Information")
                            .child("User's Settings")
                            .child("Accessibility Display and Languages")
                            .child("Data Usage")
                            .updateChildren(user).addOnCompleteListener(new OnCompleteListener() {
                                @Override
                                public void onComplete(@NonNull Task task) {

                                    if (task.isSuccessful())
                                    {
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
                                                                            Toast.makeText(Data_Usage_Activity.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }).addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {
                                                                            Toast.makeText(Data_Usage_Activity.this, "An error occurred, please try again later", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    });
                                                        }
                                                    }
                                                });
                                    }
                                    else
                                        Toast.makeText(Data_Usage_Activity.this, "An error occurred during update, please try again later", Toast.LENGTH_SHORT).show();
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
        btn_highQualityImages = findViewById(R.id.btn_highQualityImages);
        btn_qualityVideo = findViewById(R.id.btn_qualityVideo);

        //--->TextView
        txtView_Done = findViewById(R.id.txtView_Done);
        txtView_highQualityImage = findViewById(R.id.txtView_highQualityImage);
        txtView_highQualityImage_status = findViewById(R.id.txtView_highQualityImage_status);
        txtView_qualityVideo = findViewById(R.id.txtView_qualityVideo);
        txtView_qualityVideo_status = findViewById(R.id.txtView_qualityVideo_status);

        //--->LinearLayout
        LL_highQualityImage = findViewById(R.id.LL_highQualityImage);
        LL_qualityVideo = findViewById(R.id.LL_qualityVideo);

        //--->RelativeLayout
        rel_highQualityImages = findViewById(R.id.rel_highQualityImages);
        rel_qualityVideo = findViewById(R.id.rel_qualityVideo);

        //--->androidx.appcompat.widget.SwitchCompat
        dataSaver_switch = findViewById(R.id.dataSaver_switch);

    }
}