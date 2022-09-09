package com.example.spaced;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class MyFlightFragment extends Fragment {

    androidx.viewpager.widget.ViewPager ticket_slideViewpager;
    LinearLayout parent;

    TextView[] dots;
    Ticket_ViewPageAdapter ticket_viewPageAdapter;
    ArrayList<TicketSliderData> ticketSliderDataArrayList;


    private ImageView img_add;

    private TextView txtView_seeAll;

    int size;
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://spaced-768d2-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference databaseReference  = database.getReference().child("users");

    FirebaseFirestore firestoreDB = FirebaseFirestore.getInstance();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyFlightFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Schedule_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Schedule_Fragment newInstance(String param1, String param2) {
        Schedule_Fragment fragment = new Schedule_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_my_flight, container, false);

        ticket_slideViewpager = view.findViewById(R.id.ticket_slideViewpager);
        parent = view.findViewById(R.id.parent);

        img_add = view.findViewById(R.id.img_add);
        txtView_seeAll = view.findViewById(R.id.txtView_seeAll);

        ticketSliderDataArrayList = new ArrayList<>();

        loadDataFromFirebase();

        pageDirectories();

        return view;
    }

    private void loadDataFromFirebase() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
                    firestoreDB.collection("user")
                            .whereEqualTo("Phone Number", mPhoneNumber)
                            .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    // after getting the data we are calling on success method
                                    // and inside this method we are checking if the received
                                    // query snapshot is empty or not.

                                    if (!queryDocumentSnapshots.isEmpty())
                                    {
                                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                        for (DocumentSnapshot d : list)
                                        {
                                            TicketSliderData ticketSliderData = d.toObject(TicketSliderData.class);

                                            ticketSliderDataArrayList.add(ticketSliderData);
                                        }

                                        ticket_viewPageAdapter = new Ticket_ViewPageAdapter(getContext(), ticketSliderDataArrayList);

                                        ticket_slideViewpager.setAdapter(ticket_viewPageAdapter);

                                        size = ticketSliderDataArrayList.size();
                                    }
                                    else
                                    {
                                        System.out.println("No data found in Database");
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    System.out.println("Fail to load data...");
                                }
                            });
                }
            }
        });

    }

    private void pageDirectories() {
        ticket_slideViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

//    public void setupIndicator(int position)
//    {
//
//    }


}