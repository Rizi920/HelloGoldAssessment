package com.example.hellogold.network;

import com.example.hellogold.models.rates.RatesApiResponse;
import com.example.hellogold.models.signUp.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiServices {

    @FormUrlEncoded
    @POST("/api/v3/users/register.json")
    Call<SignUpResponse> insertUser(
            @Field("email") String email,
            @Field("uuid") String uuid,
            @Field("data") String data,
            @Field("tnc") Boolean tnc);


    @GET("/api/v2/spot_price.json")
    Call<RatesApiResponse> getRates();
}

