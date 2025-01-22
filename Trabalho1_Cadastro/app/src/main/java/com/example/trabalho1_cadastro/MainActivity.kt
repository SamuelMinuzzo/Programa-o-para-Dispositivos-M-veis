package com.example.trabalho1_cadastro

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabalho1_cadastro.database.DatabaseHandler
import com.example.trabalho1_cadastro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var banco : DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btCadastrarPessoa.setOnClickListener {
            btCadastrarPessoa()
        }
        binding.btCadastrarItem.setOnClickListener {
            btCadastrarItem()
        }
        binding.btRateio.setOnClickListener {
            btRateio()
        }
        binding.btClear.setOnClickListener {
            btClear()
        }

        banco = DatabaseHandler(this)
    }

    private fun btCadastrarPessoa() {
        val intent = Intent(this, PessoaActivity::class.java)
        startActivity(intent)
    }

    private fun btCadastrarItem() {
        val intent = Intent(this, ItemActivity::class.java)
        startActivity(intent)
    }

    private fun btRateio(){
        val intent = Intent(this, RateioActivity::class.java)
        startActivity(intent)
    }

    private fun btClear(){
        banco.delete()
        Toast.makeText( this, "Banco de dados limpo", Toast.LENGTH_LONG ).show()
    }

}