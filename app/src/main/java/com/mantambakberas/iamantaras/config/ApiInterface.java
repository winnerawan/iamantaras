package com.mantambakberas.iamantaras.config;

import com.mantambakberas.iamantaras.response.ApiResponse;
import com.mantambakberas.iamantaras.response.LoginResponse;
import com.mantambakberas.iamantaras.response.MyInfoResponse;
import com.mantambakberas.iamantaras.response.UsersResponse;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by winnerawan on 10/16/16.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("/api/v1/register")
    void register(@Field("name") String name, @Field("email") String email, @Field("password")
            String password, Callback<ApiResponse> cb);

    @FormUrlEncoded
    @POST("/api/v1/login")
    void login(@Field("email") String email, @Field("password") String password, Callback<LoginResponse> cb);

    @GET("/api/v1/listUsers")
    void getlistusers(Callback<UsersResponse> cb);

    @GET("/api/v1/myInformation")
    void getmyinfo(Callback<MyInfoResponse> cb);
}
