package com.myss.hedwig.Padapter

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
import com.myss.hedwig.model.datosEstudiantesItem
import com.myss.hedwig.Formulario
import com.myss.hedwig.Hmodel.datosHechizosItem


class HechizosAdapter(
    private val estudiantesGuardan: List<datosHechizosItem>,
    private val context: Context
) : RecyclerView.Adapter<HechizosAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagenE: ImageView = itemView.findViewById(R.id.imagenH)
        val nombreEs: TextView = itemView.findViewById(R.id.nombreH)
        val descriptionH: TextView = itemView.findViewById(R.id.descriptionH)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hechizos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val estudiante = estudiantesGuardan[position]

        holder.nombreEs.text = estudiante.name
        holder.descriptionH.text = estudiante.description


            // Imagen del estudiante
            Glide.with(context)
                .load(R.drawable.varita)
                .into(holder.imagenE)

    }


    override fun getItemCount(): Int {
        //item a devolver
        return estudiantesGuardan.size
    }
}
