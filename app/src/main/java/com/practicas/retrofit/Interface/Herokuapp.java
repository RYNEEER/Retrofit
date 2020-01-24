package com.practicas.retrofit.Interface;

import com.practicas.retrofit.Modelo.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Herokuapp {
    @GET ("posts")
    Call<List<Post>> getPosts();
}

