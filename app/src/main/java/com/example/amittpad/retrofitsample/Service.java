package com.example.amittpad.retrofitsample;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    String BASE_URL = "https://api.myjson.com/bins/";
    @GET("hpuku")
    Call<ExampleResponse> getExampleResponse();

}
