package com.mantambakberas.iamantaras.config;

import com.mantambakberas.iamantaras.response.ApiResponse;
import com.mantambakberas.iamantaras.response.LoginResponse;
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
    @POST("/task_manager/v1/index.php/register")
    void register(@Field("name") String name, @Field("email") String email, @Field("password") String password, Callback<ApiResponse> cb);

    @FormUrlEncoded
    @POST("/task_manager/v1/index.php/login")
    void login(@Field("email") String email, @Field("password") String password, Callback<LoginResponse> cb);

    @GET("/task_manager/v1/index.php/listUsers")
    void getlistusers(Callback<UsersResponse> cb);
}
