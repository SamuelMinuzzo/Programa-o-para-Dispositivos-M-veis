package com.example.trocatela

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfirmarActivity : AppCompatActivity() {
    private lateinit var tvCod : TextView
    private lateinit var tvQtd : TextView
    private lateinit var tvValor : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmar)

        val tvCod : TextView = findViewById(R.id.tvCod)
        val tvQtd : TextView = findViewById(R.id.tvQtd)
        val tvValor : TextView = findViewById(R.id.tvValor)

        val cod = intent.getIntExtra("cod", 0)
        val qtd = intent.getDoubleExtra("qtd", 0.0)
        val valor = intent.getDoubleExtra("valor", 0.0)



        tvCod.text = cod.toString()
        tvQtd.text = qtd.toString()
        tvValor.text = valor.toString()
    }

    fun btConfirmarOnClick(view: View) {
        val mesg = "Cod: ${tvCod.text} - Qtd: ${tvQtd.text} - Valor: ${tvValor.text}"
        val numero = "sms:+5546991124391"

        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(numero))
        intent.putExtra("sms_body", mesg)
        startActivity(intent)
    }
}