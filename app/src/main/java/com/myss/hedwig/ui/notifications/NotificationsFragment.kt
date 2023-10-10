package com.myss.hedwig.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myss.hedwig.Hmodel.datosHechizosItem
import com.myss.hedwig.databinding.FragmentNotificationsBinding

import com.myss.hedwig.Padapter.HechizosAdapter
import com.myss.hedwig.Pnetwork.ApiClient3
import com.myss.hedwig.Pnetwork.ApiHechizos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    private var estudiantesH: List<datosHechizosItem> = listOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var estudianteA: HechizosAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root



        recyclerView = binding.rvHechizos
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)

        mostrarPro()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun mostrarPro() {
        val call: Call<List<datosHechizosItem>> =
            ApiClient3().getClient().create(ApiHechizos::class.java).getSpells()
        call.enqueue(object : Callback<List<datosHechizosItem>> {
            override fun onResponse(
                call: Call<List<datosHechizosItem>>,
                response: Response<List<datosHechizosItem>>
            ) {
                if (response.isSuccessful) {
                    val estudiantesResponse: List<datosHechizosItem>? = response.body()
                    if (estudiantesResponse != null) {
                        estudiantesH = estudiantesResponse
                        estudianteA = HechizosAdapter(estudiantesH, requireContext())
                        recyclerView.adapter = estudianteA
                    }
                }
            }

            override fun onFailure(call: Call<List<datosHechizosItem>>, t: Throwable) {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
