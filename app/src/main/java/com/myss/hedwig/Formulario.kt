package com.myss.hedwig

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.ImageView
import com.bumptech.glide.Glide

class Formulario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        // Obtener los datos recibidos
        val name = intent.getStringExtra("name")
        val actor = intent.getStringExtra("actor")
        val house = intent.getStringExtra("house")
        val image = intent.getStringExtra("image")
        val patronus = intent.getStringExtra("patronus")
        val alive = intent.getBooleanExtra("alive", false)
        val ancestry = intent.getStringExtra("ancestry")
        val yearOfBirth = intent.getIntExtra("yearOfBirth", -1)

        // Obtener referencias a los elementos del diseño XML
        val nombreTextView = findViewById<TextView>(R.id.recibenombreEs)
        val casaTextView = findViewById<TextView>(R.id.recibecasaE)
        val imagenImageView = findViewById<ImageView>(R.id.recibeimagenE)
        val patronusTextView = findViewById<TextView>(R.id.recibepatroE)
        val aliveTextView = findViewById<TextView>(R.id.recibealiveE)
        val ancestryTextView = findViewById<TextView>(R.id.recibeAsc)
        val actorTextView = findViewById<TextView>(R.id.recibeactorE)
        val yearOfBirthTextView = findViewById<TextView>(R.id.recibeAnio) // Cambio de nombre

        // Asignar los datos recibidos a los elementos del diseño
        nombreTextView.text = name
        casaTextView.text = house
        patronusTextView.text = patronus
        aliveTextView.text = if (alive) "Vivo" else "Muerto" // Mostrar "Vivo" o "Muerto" según el valor booleano
        ancestryTextView.text = ancestry
        actorTextView.text = actor

        val yearOfBirthText = if (yearOfBirth != 0) yearOfBirth.toString() else getString(R.string.txt_des)
        yearOfBirthTextView.text = yearOfBirthText

        val imagenCasa = findViewById<ImageView>(R.id.recibeimagenCasa)

        when (house) {
            "Gryffindor" -> imagenCasa.setImageResource(R.drawable.imagen_g)
            "Hufflepuff" -> imagenCasa.setImageResource(R.drawable.imagen_h)
            "Slytherin" -> imagenCasa.setImageResource(R.drawable.imagen_s)
            "Ravenclaw" -> imagenCasa.setImageResource(R.drawable.imagen_r)
            else -> imagenCasa.setImageResource(R.drawable.defectoescudos) // Imagen por defecto si no coincide ningún caso
        }


        if (!image.isNullOrEmpty()) {
            Glide.with(this)
                .load(image)
                .placeholder(R.drawable.imagen_defecto)
                .into(imagenImageView)
        } else {
            // Si la imagen del estudiante no está disponible en el intent, mostrar la imagen por defecto
            Glide.with(this)
                .load(R.drawable.imagen_defecto)
                .into(imagenImageView)
        }

        val btnStras = findViewById<ImageButton>(R.id.btn_stras)
        btnStras.setOnClickListener {
            onBackPressed()
        }

        // Resto del código...
    }
}
