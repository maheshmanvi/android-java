package com.example.apisample.ui.list;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apisample.data.model.User;
import com.example.apisample.data.repository.UserRepository;
import com.example.apisample.di.ServiceLocator;
import com.example.apisample.utils.Resource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewModel extends ViewModel {

    // This ViewModel owns the UI state.
    private final UserRepository repository;

    private final MutableLiveData<Resource<List<User>>> users = new MutableLiveData<>();

    public ListViewModel(){
        repository = ServiceLocator.provideUserRepository();
    }

    public LiveData<Resource<List<User>>> getUsers(){
        return users;
    }

    public void loadUsers(){
        users.setValue(Resource.loading());
        repository.getUsers().enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                if(response.isSuccessful() && response.body() != null){
                    users.setValue(Resource.success(response.body()));
                } else {
                    users.setValue(Resource.error("Unable to load users."));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                users.setValue(Resource.error(t.getLocalizedMessage() != null ? t.getLocalizedMessage() : "Something went wrong."));
            }
        });
    }


/*    // ViewModel contains almost no code.
    // That is intentional
    // Business logic belongs inside the Repository.
    // The ViewModel simply exposes UI state.
    public LiveData<Resource<List<User>>> getUsers(){
        return repository.getUsers();
    }*/
}
