package com.example.retrofit_get;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderAPI {

    @GET("/")
    Call<Post> getPosts();

}
