package com.example.botchat;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatRVAdapter extends RecyclerView.Adapter {
private ArrayList<ChatsModel>chatsModelArrayList;

    public ChatRVAdapter(ArrayList<ChatsModel> chatsModelArrayList, Context context) {
        this.chatsModelArrayList = chatsModelArrayList;
        this.context = context;
    }

    private Context context;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case 0:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg_rv_item,parent,false);
                return new UserViewHolder(view);
            case 1:
                view =LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg_rv_item,parent,false);
                return new BotViewHolder(view);
         }
return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       ChatsModel chatsModel=chatsModelArrayList.get(position);
       switch (chatsModel.getSender()){
           case "user":
               ((UserViewHolder)holder).userTV.setText(chatsModel.getMessage());
               break;
           case "bot":
               ((BotViewHolder)holder).botMsgTV.setText(chatsModel.getMessage());
               break;
       }
    }

    @Override
    public int getItemCount() {
        return chatsModelArrayList.size();
    }
    public static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView userTV;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userTV=itemView.findViewById(R.id.idTVUser);
        }
    }
    public  static class BotViewHolder extends RecyclerView.ViewHolder{
        TextView botMsgTV;
        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            botMsgTV=itemView.findViewById(R.id.idTVBot);
        }
    }
}
