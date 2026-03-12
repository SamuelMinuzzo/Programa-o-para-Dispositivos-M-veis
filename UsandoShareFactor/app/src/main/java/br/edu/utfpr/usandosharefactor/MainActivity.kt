package br.edu.utfpr.usandosharefactor

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var imageview: ImageView

    private var ligado = false
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

        imageview = findViewById(R.id.imageview)

        ligado = sharedPreferences.getBoolean("ligado", false)

        when(ligado){
            true -> imageview.setImageResource(android.R.drawable.btn_star_big_on)
            false -> imageview.setImageResource(android.R.drawable.btn_star_big_off)
        }

    }

    fun btOnOffOnClick(view: View?) {
        when(ligado){
            true ->{
                imageview.setImageResource(android.R.drawable.btn_star_big_off)
                ligado = false
            }
            false ->{
                imageview.setImageResource(android.R.drawable.btn_star_big_on)
                ligado = true
            }
        }

        var editor = sharedPreferences.edit()
        editor.putBoolean("ligado", ligado)
        editor.commit()
    }

    fun btTelaConfiguracaoOnClick(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }
}