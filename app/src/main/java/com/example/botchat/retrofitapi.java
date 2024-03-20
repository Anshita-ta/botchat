package com.example.botchat;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface retrofitapi {
@GET
    Call<msg>getMessage(@Url String url);
}
