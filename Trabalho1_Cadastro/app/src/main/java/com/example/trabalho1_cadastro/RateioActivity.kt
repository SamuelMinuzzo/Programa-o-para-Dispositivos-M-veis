package com.example.trabalho1_cadastro

import android.os.Bundle
import android.widget.SimpleCursorAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.trabalho1_cadastro.database.DatabaseHandler
import com.example.trabalho1_cadastro.databinding.ActivityRateioBinding

class RateioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRateioBinding
    private lateinit var banco : DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRateioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        banco = DatabaseHandler(this)

    }

    override fun onStart(){
        super.onStart()
        val cursor = banco.listItens()

        val adapter = SimpleCursorAdapter(this,
            android.R.layout.simple_list_item_2,
            cursor,
            arrayOf("pagadores", "valor"),
            intArrayOf(android.R.id.text1,android.R.id.text2),
            0)

        adapter.setViewBinder { view, cursor, columnIndex ->
            when (view.id) {
                android.R.id.text2 -> {
                    val valor = cursor.getDouble(columnIndex)
                    val textView = view as TextView
                    textView.text = "R$ %.2f".format(valor)
                    true
                }
                else -> false
            }
        }

        binding.lvPrincipal.adapter = adapter
    }
}