package br.edu.utfpr.usandofotos

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var ivFoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivFoto = findViewById(R.id.ivFoto)

    }

    val register = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { imagem : Bitmap? ->
        imagem?.let{
            ivFoto.setImageBitmap(imagem)
        }
    }

    fun btCameraOnClick(view: View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) // abre a camera do celular
        startActivity(intent)
        register.launch(null)
    }
}