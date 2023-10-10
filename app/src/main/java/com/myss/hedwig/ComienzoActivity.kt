package com.myss.hedwig

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContentProviderCompat.requireContext

class ComienzoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comienzo)

        val btnEstudiantes = findViewById<Button>(R.id.btn_estudiantes)
        btnEstudiantes.setOnClickListener {
            val intent = Intent(this, AlumnosActivity::class.java)
            startActivity(intent)
        }

        val btnProfesor = findViewById<Button>(R.id.btn_profesores)
        btnProfesor.setOnClickListener {
            //onBackPressed()
            val intent = Intent(this, ProfesorActivity::class.java)
            startActivity(intent)
        }


    }
}
