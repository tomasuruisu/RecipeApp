package com.example.recipeapp;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface RecipeApiService {

    String BASE_URL = "https://www.food2fork.com/";
    String KEY = "c4242ace400a0beaf5024ed2b032e6d2";

    /**
     * Create a retrofit client.
     */

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("api/search?key="+KEY+"&sort=r&count=3")
    Call<RecipeList> getRecipes();

}