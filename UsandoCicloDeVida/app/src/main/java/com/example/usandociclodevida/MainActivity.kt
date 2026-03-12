package com.example.usandociclodevida

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        Log.i("ciclo de vida", "onCreate executado")

    }


    override fun onStart() {
        super.onStart()
        Log.i("ciclo de vida", "onStart executado")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo de vida", "onResume executado")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo de vida", "onPause executado")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo de vida", "onStop executado")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo de vida", "onDestroy executado")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo de vida", "onRestart executado")
    }

}