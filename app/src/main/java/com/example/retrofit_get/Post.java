package com.example.retrofit_get;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

public class Post {
     private JsonElement userID;
     private JsonElement jobID;
     private JsonElement rating;

    @SerializedName("userID")

    public JsonElement getuserId() {
        return userID;
    }

    public JsonElement getjobId() {
        return jobID;
    }

    public JsonElement getRating() {
        return rating;
    }
}