package br.edu.utfpr.usandoservicos

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.net.Socket

class MainActivity : AppCompatActivity() {

    private lateinit var tvHoras: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tvHoras = findViewById(R.id.tvHoras)

        val service = Intent(this, MyService::class.java)
        startService(service)


        /*Thread {
            buscarHora()
        }.start()*/


    }

    fun buscarHora(){
        val server = Socket("10.0.2.2",12345)

        val input = server.getInputStream().bufferedReader()
        val output = server.getOutputStream().bufferedWriter()

        while(true){
            output.write("hora\n")
            output.flush()

            val resposta = input.readLine()

            runOnUiThread {
                tvHoras.text = resposta // a thread paralela não tem autorização para mudar a interface gráfica por isso mandamos a thread principal rodar esse código
            }


            Thread.sleep(1000) // para o procedssamemto a cada um segundo
        }
    }

}