package com.example.listingapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("posts/{id}")
    Call<List<PostsModel>> getPosts(@Path("id") int userId);

    @GET("/comments/{id}")
    Call<List<ModelComments>> getComments(@Path("id") int postId);
}
