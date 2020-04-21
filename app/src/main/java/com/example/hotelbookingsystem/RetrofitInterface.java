package com.example.hotelbookingsystem;

import java.util.HashMap;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/but2")
    Call<Void> executeregister (@Body HashMap<String, String> map);

    @POST("/but4")
    Call<Void> executelogin (@Body HashMap<String, String> map);
}

