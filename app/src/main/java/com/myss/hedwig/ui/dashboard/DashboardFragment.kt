package com.myss.hedwig.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.myss.hedwig.R
import com.myss.hedwig.databinding.FragmentDashboardBinding
import java.util.*

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val imagenSuerte: ImageView = binding.recibeSuerte
        val btnA: ImageButton = binding.btnA

        imagenSuerte.setOnClickListener {
            val seed = System.currentTimeMillis()
            val randomWithSeed = Random(seed)

            val randomNumber = randomWithSeed.nextInt(4) + 1 // Generar número aleatorio entre 1 y 4

            val drawableResId = when (randomNumber) {
                1 -> R.drawable.imagen_g
                2 -> R.drawable.imagen_h
                3 -> R.drawable.imagen_s
                4 -> R.drawable.imagen_r
                else -> R.drawable.defectoescudos // Imagen por defecto si no coincide ningún caso
            }

            imagenSuerte.setImageResource(drawableResId)
        }

        btnA.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
