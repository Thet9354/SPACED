package com.example.spaced;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatBot_Activity extends AppCompatActivity {

    private RecyclerView recyclerView_chatBot;
    private EditText editTxt_userMsgBox;
    private ImageView img_sent, btn_back;

    ChatAdapter chatAdapter;

    ArrayList<Chatsmodel> chatsmodelArrayList;

    private final String USER_KEY = "user";
    private final String BOT_KEY = "bot";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {
        img_sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTxt_userMsgBox.getText().toString().isEmpty())
                {
                    editTxt_userMsgBox.setError("Field cannot be empty");
                    return;
                }
                getResponse(editTxt_userMsgBox.getText().toString());
                editTxt_userMsgBox.setText(" ");
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Payment_Activity.class));
            }
        });
    }

    private void getResponse(String message) {
        System.out.println(message);
        chatsmodelArrayList.add(new Chatsmodel(message, USER_KEY));
        chatAdapter.notifyDataSetChanged();
        String url = "http://api.brainshop.ai/get?bid=168736&key=pnepV4EDKa7UdUN0&uid=[uid]&msg=" + message;
        String BASE_URL = "http://api.brainshop.ai/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<MsgModel> call = retrofitApi.getMessages(url);
        call.enqueue(new Callback<MsgModel>() {
            @Override
            public void onResponse(Call<MsgModel> call, Response<MsgModel> response) {
                if (response.isSuccessful())
                {
                    MsgModel msgModel = response.body();
                    chatsmodelArrayList.add(new Chatsmodel(msgModel.getCnt(), BOT_KEY));
                    chatAdapter.notifyDataSetChanged();
                    recyclerView_chatBot.scrollToPosition(chatsmodelArrayList.size()-1);
                }
            }

            @Override
            public void onFailure(Call<MsgModel> call, Throwable t) {
                chatsmodelArrayList.add(new Chatsmodel("no response", BOT_KEY));
                chatAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initWidget() {

        recyclerView_chatBot = findViewById(R.id.recyclerView_chatBot);
        editTxt_userMsgBox = findViewById(R.id.editTxt_userMsgBox);
        img_sent = findViewById(R.id.img_sent);
        btn_back = findViewById(R.id.btn_back);
        chatsmodelArrayList = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatsmodelArrayList, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView_chatBot.setLayoutManager(manager);
        recyclerView_chatBot.setAdapter(chatAdapter);

    }
}