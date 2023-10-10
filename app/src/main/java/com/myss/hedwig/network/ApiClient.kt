package com.myss.hedwig.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    //convierte lo del json

    private lateinit var retrofit: Retrofit

    fun getClient(): Retrofit {
        retrofit = Retrofit.Builder()
            .baseUrl("https://hp-api.onrender.com/api/characters/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        return retrofit;
    }

}