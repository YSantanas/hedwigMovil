package com.myss.hedwig.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myss.hedwig.R
import com.myss.hedwig.Formulario

import com.myss.hedwig.model.datosEstudiantesItem

class EstudianteAdapter(
    private val estudiantesGuardan: List<datosEstudiantesItem>,
    private val context: Context
) : RecyclerView.Adapter<EstudianteAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagenE: ImageView = itemView.findViewById(R.id.imagenE)
        val nombreEs: TextView = itemView.findViewById(R.id.nombreEs)
        val casaE: TextView = itemView.findViewById(R.id.casaE)
        val patroE: TextView = itemView.findViewById(R.id.patroE)
        val button: Button = itemView.findViewById(R.id.btn_perfil)
        val estadoE: TextView = itemView.findViewById(R.id.estadoE)
        val actorE: TextView = itemView.findViewById(R.id.actorE)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_estudiantes, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val estudiante = estudiantesGuardan[position]

        holder.nombreEs.text = estudiante.name
        holder.estadoE.text= estudiante.alive.toString()
        holder.casaE.text = estudiante.house
        holder.actorE.text = estudiante.actor



        if (estudiante.image.isNotEmpty()) {
            Glide.with(context)
                .load(estudiante.image)
                .placeholder(R.drawable.imagen_defecto)
                .into(holder.imagenE)
        } else {
            // Si la imagen del estudiante no está disponible en el JSON, mostrar la imagen por defecto
            Glide.with(context)
                .load(R.drawable.imagen_defecto)
                .into(holder.imagenE)
        }

        holder.patroE.text = if (estudiante.patronus.isNullOrEmpty()) {
            "Patronus -> NA"
        } else {
            estudiante.patronus
        }


        holder.estadoE.text = if (estudiante.alive) {
            "Vivo"
        } else {
            "Muerto"
        }
        holder.button.setOnClickListener {
            val intent = Intent(holder.itemView.context, Formulario::class.java)
            intent.putExtra("name", estudiante.name)
            intent.putExtra("actor", estudiante.actor)
            intent.putExtra("house", estudiante.house)
            intent.putExtra("image", estudiante.image)
            intent.putExtra("patronus", estudiante.patronus)
            intent.putExtra("alive", estudiante.alive)
            intent.putExtra("ancestry", if (estudiante.ancestry.isNullOrEmpty()) holder.itemView.context.getString(R.string.txt_des) else estudiante.ancestry)
            intent.putExtra("yearOfBirth", estudiante.yearOfBirth)//nuevo dato
            // Agrega aquí más putExtra para los otros datos que desees enviar
            holder.itemView.context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return estudiantesGuardan.size
    }
}
