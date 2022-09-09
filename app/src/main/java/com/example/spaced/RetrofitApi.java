package com.example.spaced;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitApi {
    @GET
    Call<MsgModel> getMessages(@Url String url);
}
