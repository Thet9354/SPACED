package com.example.spaced;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.annotations.NotNull;
import com.paypal.checkout.PayPalCheckout;
import com.paypal.checkout.approve.Approval;
import com.paypal.checkout.approve.OnApprove;
import com.paypal.checkout.cancel.OnCancel;
import com.paypal.checkout.config.CheckoutConfig;
import com.paypal.checkout.config.Environment;
import com.paypal.checkout.config.SettingsConfig;
import com.paypal.checkout.createorder.CreateOrder;
import com.paypal.checkout.createorder.CreateOrderActions;
import com.paypal.checkout.createorder.CurrencyCode;
import com.paypal.checkout.createorder.OrderIntent;
import com.paypal.checkout.createorder.UserAction;
import com.paypal.checkout.error.ErrorInfo;
import com.paypal.checkout.error.OnError;
import com.paypal.checkout.order.Amount;
import com.paypal.checkout.order.AppContext;
import com.paypal.checkout.order.CaptureOrderResult;
import com.paypal.checkout.order.OnCaptureComplete;
import com.paypal.checkout.order.Order;
import com.paypal.checkout.order.PurchaseUnit;
import com.paypal.checkout.paymentbutton.PayPalButton;
import com.paypal.checkout.paymentbutton.PaymentButton;
import com.paypal.pyplcheckout.pojo.Checkout;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;
 */

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

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.LongStream;

public class Payment_Activity extends AppCompatActivity {

    //--->TextView
    private TextView txtView_cancel, txtView_itemCost, txtView_moreOptions, txtView_shipping_address, txtView_paymentType,
            txtView_bagSubtotal, txtView_shippingFee, txtView_shipping, txtView_orderTotal, txtView_purchasedItem, txtView_shipping_address_hint;

    //--->Button
    private Button btn_placeOrder, btn_faqs, btn_call;

    /**private PaymentButton payPalButton;*/

    //--->ImageView
    private ImageView btn_shippingAddress, btn_payment, img_purchaseItem, img_message, img_call;

    //--->RadioButton
    private RadioButton rb_delivery, rb_pickUp;

    private String purchaseItemDetail;

    int purchaseHistoryID;

    //--->Variable to store data in database
    private String mCustomer, mCustomerName, mAGe, mEmail, mPhoneNumber, mDOE, mCVC, mPlanet, mLaunchPad;

    //--->Variables to store inputs entered
    private String paymentType, shippingAddress;

    int totalPrice;

    String childLabel;

    //--->Transfer Intent data
    private Intent intent;

    static int PERMISSION_CODE = 100;

    FirebaseFirestore fireStoreDB = FirebaseFirestore.getInstance();

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://spaced-768d2-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference databaseReference  = database.getReference().child("users");

    /**

    //--->Stripe API
    String SECRET_KEY="sk_test_51JmUgEGeOPIj2PNT5PktfO92FY0VzYKipx9TQo7o9YZYdYlENeOEqKc9F4P2QkvY5xvMWWMsNFzTAhCF1I3iBBxY00Ig0oUYNL";
    String PUBLISH_KEY="pk_test_51JmUgEGeOPIj2PNTiUkCj6BGHp7F2ezEncacHgHcCM2qBbCIeipIILXjs5wdh1ElmcQATyJ8qvg02Zo31veWi9Gf00nfnhWvAV";
    PaymentSheet paymentSheet;

    String customerID;
    String EphericalKey;
    String ClientSecret;

    private static final String PAYPAL_CLIENT_ID ="ARvpTwjev2Fx6fZ_zwGEgOjk61Rnut9RIdydClXw158eMNnWkXDPMRANKmLSbOSAh5z2aTNk3qtuUTH5";

     **/

    //--->Variables for intent stored data
    private String mainPassenger_fullname, mainPassenger_email, mainPassenger_phoneNumber, mainPassengerDOB,
            NOP, planet,  launchpad, planetPrice, arrivalPlanet, departurePlanet, arrivalTime, destinationDuration, LaunchShipID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        intent = getIntent();

        transferIntentData();

        initWidget();

        setPaymentDetails();

        summaryCalculation();

        shipping_Toggle();

        /**

        //--->Start of Stripe integration
        PaymentConfiguration.init(getApplicationContext(), PUBLISH_KEY);

        paymentSheet = new PaymentSheet(this, paymentSheetResult -> {

            onPaymentResult(paymentSheetResult);

        });

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://api.stripe.com/v1/customers",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);
                            customerID = object.getString("id");
                            Toast.makeText(Payment_Activity.this, customerID, Toast.LENGTH_SHORT).show();

                            getEphericalKey(customerID);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header=new HashMap<>();
                header.put("Authorization","Bearer " + SECRET_KEY);
                return super.getHeaders();
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        setPaymentDetails();
         */

        pageDirectories();

        if (ContextCompat.checkSelfPermission(Payment_Activity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(Payment_Activity.this,new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CODE);

        }

        /**

        //--->Start of Paypal integration
        CheckoutConfig config = new CheckoutConfig(
                getApplication(),
                "YOUR_CLIENT_ID",
                Environment.SANDBOX,
                String.format("%s://paypalpay", "com.learn.paypal"),
                CurrencyCode.SGD,
                UserAction.PAY_NOW,
                new SettingsConfig(
                        true,false
                )
        );
        PayPalCheckout.setConfig(config);

         */
    }

    private void shipping_Toggle() {

        String Address = txtView_shipping_address_hint.getText().toString();

        if (Address != "Enter shipping address")
        {
            txtView_shipping_address_hint.setVisibility(View.GONE);
            txtView_shipping_address.setVisibility(View.VISIBLE);
            txtView_shipping_address.setText(Address);
        }
        else
        {
            txtView_shipping_address_hint.setVisibility(View.VISIBLE);
            txtView_shipping_address.setVisibility(View.GONE);
        }
    }

    /**

    private void onPaymentResult(PaymentSheetResult paymentSheetResult) {

        if (paymentSheetResult instanceof PaymentSheetResult.Completed)
        {
            Toast.makeText(this, "payment success", Toast.LENGTH_SHORT).show();
        }

    }

    private void getEphericalKey(String customerID) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://api.stripe.com/v1/ephemeral_keys",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);
                            EphericalKey = object.getString("id");
                            Toast.makeText(Payment_Activity.this, EphericalKey, Toast.LENGTH_SHORT).show();

                            getClientSecret(customerID, EphericalKey);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header=new HashMap<>();
                header.put("Authorization","Bearer " + SECRET_KEY);
                header.put("Stripe-Version","2020-08-27");
                return super.getHeaders();
            }


            @NonNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("customer", customerID);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void getClientSecret(String customerID, String ephericalKey) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "https://api.stripe.com/v1/payment_intents",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);
                            ClientSecret = object.getString("client_secret");
                            Toast.makeText(Payment_Activity.this, ClientSecret, Toast.LENGTH_SHORT).show();

                            PaymentFlow();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header=new HashMap<>();
                header.put("Authorization","Bearer " + SECRET_KEY);
                return super.getHeaders();
            }

            @NonNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("customer", customerID);
                params.put("amount", "1000"+"00");
                params.put("currency", "usd");
                params.put("automatic_payment_methods[enabled]", "true");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void PaymentFlow() {

        try {
            paymentSheet.presentWithPaymentIntent(
                    ClientSecret, new PaymentSheet.Configuration("ABC Company"
                            ,new PaymentSheet.CustomerConfiguration(
                            customerID,
                            EphericalKey
                    ))
            );
        }catch (Exception e)
        {
            System.out.println(customerID);
            System.out.println(EphericalKey);
            System.out.println("There seems to be an error here");
        }

    }

     **/

    //--->Set all the details for payment right for the user to checkout
    private void setPaymentDetails() {

        planetPriceStatement();

        shippingAddressStatement();

        //PaymentType();

        //summaryStatement();
    }

    private void shippingAddressStatement() {

    }

    private void planetPriceStatement() {

        switch (planet)
        {
            case "Moon":
                img_purchaseItem.setImageResource(R.drawable.moon_transparent);
                purchaseItemDetail = "Space Ticket-"+ planet + "-" + LaunchShipID + " " + launchpad +" X" + NOP;
                txtView_purchasedItem.setText(purchaseItemDetail);
                break;

            case "Mars":
                img_purchaseItem.setImageResource(R.drawable.mars_transparent);
                purchaseItemDetail = "Space Ticket-"+ planet + "-" + LaunchShipID + " " + launchpad +" X" + NOP;
                txtView_purchasedItem.setText(purchaseItemDetail);
                break;

            case "Uranus":
                img_purchaseItem.setImageResource(R.drawable.uranus_transparent);
                purchaseItemDetail = "Space Ticket-"+ planet + "-" + LaunchShipID + " " + launchpad +" X" + NOP;
                txtView_purchasedItem.setText(purchaseItemDetail);
                break;

            case "Mercury":
                img_purchaseItem.setImageResource(R.drawable.mercury_transparent);
                purchaseItemDetail = "Space Ticket-"+ planet + "-" + LaunchShipID + " " + launchpad +" X" + NOP;
                txtView_purchasedItem.setText(purchaseItemDetail);
                break;

            case "Venus":
                img_purchaseItem.setImageResource(R.drawable.venus_transparent);
                purchaseItemDetail = "Space Ticket-"+ planet + "-" + LaunchShipID + " " + launchpad +" X" + NOP;
                txtView_purchasedItem.setText(purchaseItemDetail);
                break;

            case "Jupiter":
                img_purchaseItem.setImageResource(R.drawable.jupiter_transparent);
                purchaseItemDetail = "Space Ticket-"+ planet + "-" + LaunchShipID + " " + launchpad +" X" + NOP;
                txtView_purchasedItem.setText(purchaseItemDetail);
                break;

            case "Saturn":
                img_purchaseItem.setImageResource(R.drawable.saturn_transparent);
                purchaseItemDetail = "Space Ticket-"+ planet + "-" + LaunchShipID + " " + launchpad +" X" + NOP;
                txtView_purchasedItem.setText(purchaseItemDetail);
                break;

            case "Neptune":
                img_purchaseItem.setImageResource(R.drawable.neptune_transparent);
                purchaseItemDetail = "Space Ticket-"+ planet + "-" + LaunchShipID + " " + launchpad +" X" + NOP;
                txtView_purchasedItem.setText(purchaseItemDetail);
                break;

            case "Pluto":
                img_purchaseItem.setImageResource(R.drawable.pluto_transparent);
                purchaseItemDetail = "Space Ticket-"+ planet + "-" + LaunchShipID + " " + launchpad +" X" + NOP;
                txtView_purchasedItem.setText(purchaseItemDetail);
                break;

            case "Makemake":
                img_purchaseItem.setImageResource(R.drawable.makemake_transparent);
                purchaseItemDetail = "Space Ticket-"+ planet + "-" + LaunchShipID + " " + launchpad +" X" + NOP;
                txtView_purchasedItem.setText(purchaseItemDetail);
                break;

            case "Ceres":
                img_purchaseItem.setImageResource(R.drawable.ceres_transparent);
                purchaseItemDetail = "Space Ticket-"+ planet + "-" + LaunchShipID + " " + launchpad +" X" + NOP;
                txtView_purchasedItem.setText(purchaseItemDetail);
                break;
        }
    }

    //--->Transfer data from the previous activity to here
    private void transferIntentData() {

        mainPassenger_fullname = intent.getStringExtra("editTxt_passenger1_fullname");
        mainPassenger_email = intent.getStringExtra("editTxt_passenger1_email");
        mainPassenger_phoneNumber = intent.getStringExtra("editTxt_passenger1_phoneNumber");
        mainPassengerDOB = intent.getStringExtra("datePicker_passenger1");

        NOP = intent.getStringExtra("NOP");
        planet = intent.getStringExtra("Planet");
        launchpad = intent.getStringExtra("LaunchPad");

        planetPrice = intent.getStringExtra("planetPrice");
        arrivalPlanet = intent.getStringExtra("arrivalPlanet");
        departurePlanet = intent.getStringExtra("departurePlanet");
        arrivalTime = intent.getStringExtra("arrivalTime");
        destinationDuration = intent.getStringExtra("destinationDuration");
        LaunchShipID = intent.getStringExtra("LaunchShipID");

        System.out.println(mainPassenger_fullname);
        System.out.println(mainPassenger_email);
        System.out.println(mainPassenger_phoneNumber);
        System.out.println(mainPassengerDOB);

        System.out.println(NOP);
        System.out.println(planet);
        System.out.println(launchpad);

        System.out.println(planetPrice);
        System.out.println(arrivalPlanet);
        System.out.println(departurePlanet);
        System.out.println(arrivalTime);
        System.out.println(destinationDuration);
        System.out.println(LaunchShipID);

    }

    private void pageDirectories() {

        //--->OnCLickListener for rb_pickUp[Done]
        rb_pickUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtView_moreOptions.setTextColor(Color.GRAY);
                txtView_moreOptions.setEnabled(false);
            }
        });

        //--->OnCLickListener for rb_delivery[Done]
        rb_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtView_moreOptions.setTextColor(Color.BLUE);
                txtView_moreOptions.setEnabled(true);
            }
        });

        //--->OnCLickListener for btn_faqs[Done]
        btn_faqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Payment_Activity.this);
                builder.setTitle("External website");
                builder.setMessage("This leads to an external website, do you wish to continue?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Uri uriUrl = Uri.parse("https://github.com/Thet9354");
                        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                        startActivity(launchBrowser);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();

            }
        });

        //--->onClickListener for img_msg[Done]
        img_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //https://www.youtube.com/watch?v=7_Cc36c7EW0 for tutorial on how to create a chat bot
                startActivity(new Intent(getApplicationContext(), ChatBot_Activity.class));
            }
        });

        //--->onClickListener for img_call[Done]
        img_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String myPhoneNumber = "93542856";
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+myPhoneNumber));
                startActivity(i);
            }
        });

        //--->OnCLickListener for btn_call[Done]
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //--->OnCLickListener for btn_shippingAddress[Done]
        btn_shippingAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ShippingInfo_Activity.class));
            }
        });

        //--->OnCLickListener for btn_payment[Done]
        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CardPayment_info_Activity.class));
            }
        });

        //--->onClickListener for btn_placeOrder
        btn_placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validateShippingAddress();

                if (!validateShippingAddress())
                {
                    return;
                }
                else
                {
                    validatePayment();

                    if (validatePayment())
                    {
                        Toast.makeText(Payment_Activity.this, "Your order has been placed", Toast.LENGTH_SHORT).show();
                        placeOrder();
                    }
                }
//                validatePaymentType();
//
//                placeOrder_withStripe();
//
//                placeOrder_withPayPal();

                //addData();
            }
        });

        //--->onClickListener for txtView_moreOptions[Done]
        txtView_moreOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MoreOptions_Activity.class);
                intent.putExtra("purchaseItemDetail", purchaseItemDetail);
                System.out.println(purchaseItemDetail);
                startActivity(intent);
            }
        });

        //--->onClickListener for cancel btn[Done]
        txtView_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ask user for confirmation to cancel order
                AlertDialog.Builder builder = new AlertDialog.Builder(Payment_Activity.this);
                builder.setTitle("Terminate checkout");
                builder.setMessage("Are you sure you want to exit the checkout process?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getApplicationContext(), MainMenuPage_Activity.class));
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });
    }

    private void placeOrder() {
        //Update firebase real database
        //Update firebase firestore

        addToFireStore();

        addToRealTimeDB();

        addToSQLiteDB();

        startActivity(new Intent(getApplicationContext(), MainMenuPage_Activity.class));

    }

    private void addToSQLiteDB() {
        //TODO: Do this tmr

    }

    private void addToRealTimeDB() {

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(mainPassenger_phoneNumber))
                {
                    databaseReference.child(mainPassenger_phoneNumber).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.hasChild("Purchase History"))
                            {
                                databaseReference.child("Purchase History").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        purchaseHistoryID_generator();

                                        while (snapshot.hasChild(childLabel))
                                        {
                                            purchaseHistoryID_generator();
                                        }

                                        databaseReference.child(mainPassenger_phoneNumber).child("Purchase History").child(childLabel).child("Payment Type").setValue(paymentType);
                                        databaseReference.child(mainPassenger_phoneNumber).child("Purchase History").child(childLabel).child("Shipping address").setValue(shippingAddress);
                                        databaseReference.child(mainPassenger_phoneNumber).child("Purchase History").child(childLabel).child("Purchase Item").setValue(purchaseItemDetail);
                                        databaseReference.child(mainPassenger_phoneNumber).child("Purchase History").child(childLabel).child("Order Total").setValue(totalPrice);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }
                            else
                            {
                                purchaseHistoryID_generator();

                                databaseReference.child(mainPassenger_phoneNumber).child("Purchase History").child(childLabel).child("Payment Type").setValue(paymentType);
                                databaseReference.child(mainPassenger_phoneNumber).child("Purchase History").child(childLabel).child("Shipping address").setValue(shippingAddress);
                                databaseReference.child(mainPassenger_phoneNumber).child("Purchase History").child(childLabel).child("Purchase Item").setValue(purchaseItemDetail);
                                databaseReference.child(mainPassenger_phoneNumber).child("Purchase History").child(childLabel).child("Order Total").setValue(totalPrice);

                            }
                            System.out.println("User's data successfully added to firebase realtime database under: " + childLabel);
                            finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else
                {
                    System.out.println("Unsuccessful in adding user's data to firebase realtime database");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void purchaseHistoryID_generator() {
        
        Random random = new Random();
        purchaseHistoryID = random.nextInt();
        childLabel = "Purchase ID: " + purchaseHistoryID;
    }

    private void addToFireStore() {
        Map<String, Object> userDetail = new HashMap<>();
        userDetail.put("Shipping Address", shippingAddress);
        userDetail.put("Payment Type", paymentType);

        fireStoreDB.collection("user")
                .whereEqualTo("Full Name", mainPassenger_fullname)
                .whereEqualTo("Phone Number", mainPassenger_phoneNumber)
                .whereEqualTo("Email", mainPassenger_email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        System.out.println(task.getResult().toString());

                        if (task.isSuccessful() && !task.getResult().isEmpty())
                        {

                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            String documentID = documentSnapshot.getId();
                            fireStoreDB.collection("user")
                                    .document(documentID)
                                    .update(userDetail)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Payment_Activity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Payment_Activity.this, "Some Error Occurred", Toast.LENGTH_SHORT).show();
                                        }
                                    });


                        }
                        else
                        {
                            Toast.makeText(Payment_Activity.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean validatePayment() {

        if (txtView_paymentType.getText().toString().equals("Enter pament"))
        {
            //---> Inform user that he/she has not entered any payment method & ask if they would like to edit it
            AlertDialog.Builder builder = new AlertDialog.Builder(Payment_Activity.this);
            builder.setTitle("Missing Payment Type");
            builder.setMessage("We can't proceed due to missing payment type information");
            builder.setPositiveButton("Update payment type", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(getApplicationContext(), CardPayment_info_Activity.class));
                }
            });
            builder.setNegativeButton("Maybe next time", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.create().show();

            return false;
        }
        else
        {
            paymentType = txtView_paymentType.getText().toString();

            return true;
        }

    }

    /**

    //TODO: Continue with paypal integration
    private void placeOrder_withPayPal() {
        //--->Customer will pay using paypal
        payPalButton.setup(
                new CreateOrder() {
                    @Override
                    public void create(@NotNull CreateOrderActions createOrderActions) {
                        ArrayList<PurchaseUnit> purchaseUnits = new ArrayList<>();
                        purchaseUnits.add(
                                new PurchaseUnit.Builder()
                                        .amount(
                                                new Amount.Builder()
                                                        .currencyCode(CurrencyCode.USD)
                                                        .value("10.00")
                                                        .build()
                                        )
                                        .build()
                        );
                        Order order = new Order(
                                OrderIntent.CAPTURE,
                                new AppContext.Builder()
                                        .userAction(UserAction.PAY_NOW)
                                        .build(),
                                purchaseUnits
                        );
                        createOrderActions.create(order, (CreateOrderActions.OnOrderCreated) null);
                    }
                },
                new OnApprove() {
                    @Override
                    public void onApprove(@NotNull Approval approval) {
                        approval.getOrderActions().capture(new OnCaptureComplete() {
                            @Override
                            public void onCaptureComplete(@NotNull CaptureOrderResult result) {
                                Log.i("CaptureOrder", String.format("CaptureOrderResult: %s", result));
                            }
                        });
                    }
                }
        );
    }

    private void placeOrder_withStripe() {

        if (!validateShippingAddress() | !validatePaymentType())
        {
            Toast.makeText(this, "Please check if you ave filled up all the required details", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            PaymentFlow();
        }

    }

    private boolean validatePaymentType() {

        if (txtView_paymentType.getText().equals(" "))
        {
            Toast.makeText(this, "Please enter a payment type", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            return true;
        }
    }

    **/

    private boolean validateShippingAddress() {

        if (txtView_shipping_address.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please check your shipping address again", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (txtView_shipping_address.getText().toString().equals("Enter shipping addess"))
        {
            //---> Inform user that he/she has not entered any shipping address & ask if they would like to edit it
            AlertDialog.Builder builder = new AlertDialog.Builder(Payment_Activity.this);
            builder.setTitle("Missing Shipping Address");
            builder.setMessage("We can't proceed due to missing shipping address information");
            builder.setPositiveButton("Update shipping address", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(getApplicationContext(), ShippingInfo_Activity.class));
                }
            });
            builder.setNegativeButton("Maybe next time", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create().show();
            return false;
        }
        else
        {
            shippingAddress = txtView_shipping_address.getText().toString();
            System.out.println(shippingAddress);

            return true;
        }
    }

    private void initWidget() {

        //--->TextView
        txtView_cancel = findViewById(R.id.txtView_cancel);
        txtView_itemCost = findViewById(R.id.txtView_itemCost);
        txtView_moreOptions = findViewById(R.id.txtView_moreOptions);
        txtView_shipping_address = findViewById(R.id.txtView_shipping_address);
        txtView_paymentType = findViewById(R.id.txtView_paymentType);
        txtView_bagSubtotal = findViewById(R.id.txtView_bagSubtotal);
        txtView_shippingFee = findViewById(R.id.txtView_shippingFee);
        txtView_orderTotal = findViewById(R.id.txtView_orderTotal);
        txtView_purchasedItem = findViewById(R.id.txtView_purchasedItem);
        txtView_shipping_address_hint = findViewById(R.id.txtView_shipping_address_hint);
        txtView_shipping = findViewById(R.id.txtView_shipping);

        //--->Button
        btn_placeOrder = findViewById(R.id.btn_placeOrder);
        btn_faqs = findViewById(R.id.btn_faqs);
        btn_call = findViewById(R.id.btn_call);

        //--->ImageView
        btn_shippingAddress = findViewById(R.id.btn_shippingAddress);
        btn_payment = findViewById(R.id.btn_payment);
        img_purchaseItem = findViewById(R.id.img_purchaseItem);
        img_message = findViewById(R.id.img_message);
        img_call = findViewById(R.id.img_call);

        //--->RadioButton
        rb_delivery = findViewById(R.id.rb_delivery);
        rb_pickUp = findViewById(R.id.rb_pickUp);

        //--->Paypal Payment button
        /**payPalButton = findViewById(R.id.payPalButton);*/


    }

    private void summaryCalculation() {

        int ticket = Integer.parseInt(NOP);

        int bagSubTotal = Integer.parseInt(planetPrice);
        bagSubTotal = bagSubTotal * ticket;

        txtView_itemCost.setText("S$" + bagSubTotal);
        txtView_bagSubtotal.setText("S$" + bagSubTotal);

        int shippingPrice = 0;

        if (bagSubTotal < 8000)
        {
            shippingPrice = 50;
            txtView_shippingFee.setText("S$" + shippingPrice);
            txtView_shipping.setText("Shipping Fee");
        }

        totalPrice = bagSubTotal + shippingPrice;
        txtView_orderTotal.setText("S$" + totalPrice);

    }
}