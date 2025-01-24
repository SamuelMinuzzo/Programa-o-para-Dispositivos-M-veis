package com.example.usandosqlite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SimpleCursorAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.usandosqlite.adapter.ElementoListaAdapter
import com.example.usandosqlite.database.DatabaseHandler
import com.example.usandosqlite.databinding.ActivityListarBinding


class ListarActivity : AppCompatActivity() {

    private lateinit var binding : ActivityListarBinding
    private lateinit var banco : DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListarBinding.inflate( layoutInflater )
        setContentView(binding.root)

        banco = DatabaseHandler( this )

        /*val paises = listOf("Brasil","Argentina","Paraguai","Uruguai")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, paises)

         */

        /*val adapter = SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_2,//onde os dados serão apresentados
            cursor, //fonte de dados
            arrayOf("nome","telefone"), //colunas a serem exibidas
            intArrayOf(android.R.id.text1, android.R.id.text2), //view que serão exibidas
            0 //tag para atualizar automaticamente
        )

         */

    }

    override fun onStart(){
        super.onStart()
        val cursor = banco.list()
        val adapter = ElementoListaAdapter(this, cursor)

        binding.lvPrincipal.adapter = adapter
    }

    fun btIncluirOnClick(view: View) {
        val intent = Intent( this, MainActivity::class.java )
        startActivity(intent)

    }
}