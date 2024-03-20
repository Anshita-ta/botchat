package com.example.botchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
private RecyclerView chatsRV;
private EditText userMsgEdit;
private FloatingActionButton sendMsgFAB;
private final String BOT_KEY="bot";
private  final String USER_KEY="user";
private ArrayList<ChatsModel>chatsModelArrayList;
private ChatRVAdapter chatRVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatsRV=findViewById(R.id.personalChats);
        userMsgEdit=findViewById(R.id.edtMessage);
        sendMsgFAB=findViewById(R.id.idFABSend);
        chatsModelArrayList=new ArrayList<>();
        chatRVAdapter=new ChatRVAdapter(chatsModelArrayList,this);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        chatsRV.setLayoutManager(manager);
        chatsRV.setAdapter(chatRVAdapter);
        sendMsgFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userMsgEdit.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Your Message", Toast.LENGTH_SHORT).show();
                    return;
                }
                getResponse(userMsgEdit.getText().toString());
                userMsgEdit.setText("");
            }
        });
    }
    private void getResponse(String message) {
        chatsModelArrayList.add(new ChatsModel(message, USER_KEY));
        chatRVAdapter.notifyDataSetChanged();
        String url = "\n" +
                "http://api.brainshop.ai/get?bid=180527&key=nAjfczVj6ad189y1&uid=[uid]&msg=hello";
        String BASE_URL = "http://api.brainshop.ai/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConventryFactory.create())
                .build();
        retrofitapi retrofitApi = retrofit.create(retrofitapi.class);
        Call<MsgModal> call = retrofitApi.getMessage(url);
        call.enqueue(new Callback<MsgModal>() {

            @Override
            public void onResponse(Call<MsgModal> call, Response<MsgModal> response) {
                if (response.isSuccessful()) {
                    MsgModal modal = response.body();
                    chatsModelArrayList.add(new ChatsModel(modal.getCnt(), BOT_KEY));
                    chatRVAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MsgModal> call, Throwable t) {
                chatsModelArrayList.add(new ChatsModel("Please revert  your question ", BOT_KEY));
                chatRVAdapter.notifyDataSetChanged();
            }

        });
