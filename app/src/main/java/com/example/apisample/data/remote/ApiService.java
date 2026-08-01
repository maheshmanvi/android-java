package com.example.apisample.data.remote;


import com.example.apisample.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    // This interface contains ONLY API definitions.
    // NO Logic.
    @GET("users")
    Call<List<User>> getUsers();
}
