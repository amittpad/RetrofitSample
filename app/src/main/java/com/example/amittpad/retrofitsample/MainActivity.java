package com.example.amittpad.retrofitsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private String mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadJsonFromServer();
    }




    private void loadJsonFromServer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Service.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);

        Call<ExampleResponse> call = service.getExampleResponse();
        call.enqueue(new Callback<ExampleResponse>() {
            @Override
            public void onResponse(Call<ExampleResponse> call, Response<ExampleResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getResult().equalsIgnoreCase("success")){
                        mResult = response.body().getName();
                        Log.d("name",mResult);
                        Toast.makeText(MainActivity.this, mResult, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ExampleResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Someting went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
