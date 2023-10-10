package com.myss.hedwig

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    BASIC
}

class InicioActivity : AppCompatActivity() {
    private var email: String? = null
    private var provider: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        obtenerExtras()
        setup(email ?: "", provider ?: "")
    }

    private fun obtenerExtras() {
        email = intent.getStringExtra("email")
        provider = intent.getStringExtra("provider")
    }

    private fun setup(email: String, provider: String) {
        title = "Inicio"
        val btnIni = findViewById<Button>(R.id.btn_iniciar)
        val editC = findViewById<EditText>(R.id.edit_correo)
        val editP = findViewById<EditText>(R.id.editPass)

        editC.setText(email)
        editP.setText(provider)

        btnIni.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
            /*
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, OtraActivity::class.java)
            startActivity(intent)
            finish()
            */
        }

    }
}
