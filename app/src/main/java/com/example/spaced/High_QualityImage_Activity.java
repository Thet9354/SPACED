package com.example.spaced;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class High_QualityImage_Activity extends AppCompatActivity {

    private ImageView btn_back;

    private TextView txtView_Done;

    private RadioButton rb_onCellular, rb_onlyWifi, rb_never;

    private String highQualityImage = "On Cellular Of Wifi";

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://spaced-768d2-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference databaseReference  = database.getReference().child("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_quality_image);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        txtView_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //--->Updating Data
                updateData();
            }
        });

        rb_never.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highQualityImage = "Never";
            }
        });

        rb_onCellular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highQualityImage = "On Cellular Of Wifi";
            }
        });

        rb_onlyWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highQualityImage = "Only Wifi";
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
                    user.put("High Quality Images", highQualityImage);

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
                                        Toast.makeText(High_QualityImage_Activity.this, "Yessir we did it", Toast.LENGTH_SHORT).show();

                                        //TODO: Update data in firestore here
                                    }
                                    else
                                        Toast.makeText(High_QualityImage_Activity.this, "An error occurred during update, please try again later", Toast.LENGTH_SHORT).show();
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

        //--->RadioButton
        rb_onCellular = findViewById(R.id.rb_onCellular);
        rb_onlyWifi = findViewById(R.id.rb_onlyWifi);
        rb_never = findViewById(R.id.rb_never);
    }
}