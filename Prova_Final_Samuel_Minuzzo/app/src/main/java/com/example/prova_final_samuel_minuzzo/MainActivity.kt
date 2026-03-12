package com.example.prova_final_samuel_minuzzo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


val cidade = listOf<String> ( "Pato Branco", "Mariopolis", "Marmeleiro", "Vitorino", "Coronel Vivida", "Francisco Beltrão", "Mangueirinha" )


class MainActivity : AppCompatActivity() {

    private lateinit var spCidade : Spinner
    private lateinit var tela : View
    private lateinit var imCidade : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tela = findViewById( R.id.main )
        spCidade = findViewById(R.id.spCidade )
        imCidade = findViewById( R.id.imgCidade )


        val adapter = ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, cidade )

        val imageID = listOf(R.drawable.patobranco, R.drawable.mariopolis, R.drawable.marmeleiro, R.drawable.vitorino, R.drawable.coronelvivida, R.drawable.franciscobeltrao, R.drawable.mangueirinha)

        spCidade.setAdapter( adapter )

        spCidade.setSelection(0)


        spCidade.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                imCidade.setImageResource( imageID[position] )
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(this@MainActivity, "Cidade não selecionada", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun btListar(view: View) {
        val intent = Intent(this, Recycler_Activity::class.java)
        startActivity(intent)
    }
}