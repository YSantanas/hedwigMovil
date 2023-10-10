package com.myss.hedwig.Pnetwork

import com.myss.hedwig.Hmodel.datosHechizosItem
import com.myss.hedwig.model.datosEstudiantesItem
import retrofit2.http.GET
import retrofit2.Call

interface ApiHechizos {

    @GET("spells")
    fun getSpells(): Call<List<datosHechizosItem>>
}
