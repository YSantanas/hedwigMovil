package com.myss.hedwig.Pnetwork

import com.myss.hedwig.model.datosEstudiantesItem
import retrofit2.http.GET
import retrofit2.Call

interface ApiProfesor {

    @GET("staff")
    fun getProfesor(): Call<List<datosEstudiantesItem>>
}
