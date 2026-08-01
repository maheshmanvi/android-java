package com.example.apisample.data.repository;

import com.example.apisample.data.model.User;
import com.example.apisample.data.remote.ApiClient;

import java.util.List;

import retrofit2.Call;

public class UserRepository {

    // This repository doesn't know about LiveDate, ViewModel, Activity, UI.
    // It only knows about data.

    public Call<List<User>> getUsers(){
        return ApiClient.getApiService().getUsers();
    }
}
