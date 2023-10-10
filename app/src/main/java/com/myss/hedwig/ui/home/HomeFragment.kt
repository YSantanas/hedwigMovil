package com.myss.hedwig.ui.home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.myss.hedwig.ComienzoActivity
import com.myss.hedwig.R
import com.myss.hedwig.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //TOMAR LOS BOTONES
        val editCorreo: EditText = binding.editCorreo
        val editPass: EditText = binding.editPass
        val btnEnvia: Button = binding.btnEnviar
        val btnRegistro: Button = binding.btnRegistro

        // Para logearnos llamamos a signInWithEmailAndPassword
        btnEnvia.setOnClickListener {
            val correo = editCorreo.text.toString().trim()
            val pass = editPass.text.toString().trim()

            if (correo.isEmpty() || pass.isEmpty()) {
                mostrarAlerta(getString(R.string.txt_campos_vacios))
            } else if (!esEmailValido(correo)) {
                mostrarAlerta(getString(R.string.txt_email_invalido))
            } else if (pass.length < 8 || !tieneNumerosYLetras(pass)) {
                mostrarAlerta(getString(R.string.txt_pass_invalida))
            } else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(correo, pass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            mostrarExito(getString(R.string.txt_exitoi), getString(R.string.txt_exito_messagei))
                            Handler().postDelayed({
                                mostrarSiguiente()
                            }, 3000)
                        } else {
                            mostrarAlerta(getString(R.string.txt_error_login))
                        }
                    }
            }
        }

        // Para crear un usuario usamos createUserWithEmailAndPassword
        btnRegistro.setOnClickListener {
            val correo = editCorreo.text.toString().trim()
            val pass = editPass.text.toString().trim()

            if (correo.isEmpty() || pass.isEmpty()) {
                mostrarAlerta(getString(R.string.txt_campos_vacios))
            } else if (!esEmailValido(correo)) {
                mostrarAlerta(getString(R.string.txt_email_invalido))
            } else if (pass.length < 8 || !tieneNumerosYLetras(pass)) {
                mostrarAlerta(getString(R.string.txt_pass_invalida))
            } else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo, pass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            mostrarExito(getString(R.string.txt_exito), getString(R.string.txt_exito_message))
                            Handler().postDelayed({
                                mostrarSiguiente()
                            }, 3000)
                        } else {
                            mostrarAlerta(getString(R.string.txt_error_registro))
                        }
                    }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun mostrarSiguiente() {
        val intent = Intent(requireContext(), ComienzoActivity::class.java)
        startActivity(intent)
    }

    private fun mostrarAlerta(mensaje: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.txt_error))
        builder.setMessage(mensaje)
        builder.setPositiveButton(getString(R.string.txt_aceptar), null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun mostrarExito(titulo: String, mensaje: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(titulo)
        builder.setMessage(mensaje)
        builder.setPositiveButton(getString(R.string.txt_aceptare), null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun esEmailValido(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun tieneNumerosYLetras(texto: String): Boolean {
        val tieneNumeros = texto.any { it.isDigit() }
        val tieneLetras = texto.any { it.isLetter() }
        return tieneNumeros && tieneLetras
    }
}
