package br.com.oliveiraemanoel.urestaurant.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private final static String BASE_URL = "https://my-json-server.typicode.com/delivery-direto/sample-api/";

    public static Retrofit getRetrofitInstance(){
        if(retrofit==null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }
}
