package com.myss.hedwig.network

import com.myss.hedwig.model.datosEstudiantesItem
import retrofit2.http.GET
import retrofit2.Call

interface ApiEstudiantes {

    @GET("students")
    fun getEstudiantes(): Call<List<datosEstudiantesItem>>
}
