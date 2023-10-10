package com.myss.hedwig

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myss.hedwig.Padapter.ProfesorAdapter
import com.myss.hedwig.model.datosEstudiantesItem
import com.myss.hedwig.Pnetwork.ApiClient2
import com.myss.hedwig.Pnetwork.ApiProfesor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfesorActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var estudianteAdapter: ProfesorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profesor)

        recyclerView = findViewById(R.id.rv_profesor)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        mostrarPro()
    }

    private fun mostrarPro() {
        val call: Call<List<datosEstudiantesItem>> =
            ApiClient2().getClient().create(ApiProfesor::class.java).getProfesor()
        call.enqueue(object : Callback<List<datosEstudiantesItem>> {
            override fun onResponse(
                call: Call<List<datosEstudiantesItem>>,
                response: Response<List<datosEstudiantesItem>>
            ) {
                if (response.isSuccessful) {
                    val estudiantesResponse: List<datosEstudiantesItem>? = response.body()
                    if (estudiantesResponse != null) {
                        estudianteAdapter = ProfesorAdapter(estudiantesResponse, this@ProfesorActivity)
                        recyclerView.adapter = estudianteAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<datosEstudiantesItem>>, t: Throwable) {
                Toast.makeText(this@ProfesorActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
