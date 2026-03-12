package br.edu.utfpr.usandoapigoogle

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var tvLatitude: TextView
    private lateinit var tvLongitude: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvLatitude = findViewById(R.id.tvLatitude)
        tvLongitude = findViewById(R.id.tvLongitude)

    }

    fun btVerEndereco(view: View) {
        Thread( Runnable {
            val endereco = "https://maps.googleapis.com/maps/api/geocode/xml?latlng=${tvLatitude.text},${tvLongitude.text}&key=AIzaSyDsy454kAkXofX828BEMieAQ7EbtpjohZY"
            var url = URL(endereco)
            var con = url.openConnection() //como se fosse um enter na barra de pesquisa do browser

            val inputStreamReader = con.getInputStream()

            val entrada = BufferedReader(InputStreamReader(inputStreamReader))

            val msgSaida = StringBuilder()

            var linha  = entrada.readLine()

            while(linha != null){
                msgSaida.append(linha)
                linha = entrada.readLine()
            }

            val formatedAdress = msgSaida.substring(msgSaida.indexOf("<formatted_address>") + 19, msgSaida.indexOf("</formatted_address>"))

            runOnUiThread {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Endereço: ")
                dialog.setMessage(formatedAdress)
                dialog.setCancelable(false)
                dialog.setNeutralButton("OK", null)
                dialog.show() // apenas a thread principal pode chamar o show a não ser que digamos o oposto que é o que estamos fazendo com o runOnUiThread

            }


        }).start()
    }


    fun btVerMapa(view: View) {
        Thread{
            val endereco =
                "https://maps.googleapis.com/maps/api/staticmap?center=UTFPR+PB&zoom=18&size=400x400&key=AIzaSyDsy454kAkXofX828BEMieAQ7EbtpjohZY"

            val url = URL(endereco)
            val urlConnection = url.openConnection()
            val inputStream = urlConnection.getInputStream()

            val imagem = BitmapFactory.decodeStream(inputStream)


            runOnUiThread {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Mapa: ")

                val imageView = ImageView(this)
                imageView.setImageBitmap(imagem)
                dialog.setView(imageView)

                dialog.setCancelable(false)
                dialog.setNeutralButton("OK", null)
                dialog.show() // apenas a thread principal pode chamar o show a não ser que digamos o oposto que é o que estamos fazendo com o runOnUiThread

            }
        }.start()
    }


}