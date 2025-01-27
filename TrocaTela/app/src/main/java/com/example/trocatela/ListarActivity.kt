package com.example.trocatela

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)

        val lvPrincipal : ListView = findViewById(R.id.lvPrincipal)

        lvPrincipal.setOnItemClickListener { parent, view, position, id ->
            val cod = position + 1
            intent.putExtra("codRetornado", cod)
            setResult(RESULT_OK, intent)
            finish()
        }

    }
}