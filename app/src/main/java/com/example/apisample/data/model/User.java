package com.example.apisample.data.model;
import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
public class User {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;

    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name != null ? name : "";
    }

    // Why return empty string? -> return name != null ? name : "";
    // This prevents accidental 'NullPointerExceptions' in the UI.
    // While keeping the model lightweight.

    @NonNull
    public String getEmail() {
        return email != null ? email : "";
    }
}
