package com.myss.hedwig

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myss.hedwig.R
import com.myss.hedwig.adapter.EstudianteAdapter
import com.myss.hedwig.model.datosEstudiantesItem
import com.myss.hedwig.network.ApiClient
import com.myss.hedwig.network.ApiEstudiantes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlumnosActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var estudianteAdapter: EstudianteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alumnos)

        recyclerView = findViewById(R.id.rv_alum)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        mostrarAlum()
    }

    private fun mostrarAlum() {
        val call: Call<List<datosEstudiantesItem>> = ApiClient().getClient().create(ApiEstudiantes::class.java).getEstudiantes()
        call.enqueue(object : Callback<List<datosEstudiantesItem>> {
            override fun onResponse(call: Call<List<datosEstudiantesItem>>, response: Response<List<datosEstudiantesItem>>) {
                if (response.isSuccessful) {
                    val estudiantesResponse: List<datosEstudiantesItem>? = response.body()
                    if (estudiantesResponse != null) {
                        estudianteAdapter = EstudianteAdapter(estudiantesResponse, this@AlumnosActivity)
                        recyclerView.adapter = estudianteAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<datosEstudiantesItem>>, t: Throwable) {
                Toast.makeText(this@AlumnosActivity, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
