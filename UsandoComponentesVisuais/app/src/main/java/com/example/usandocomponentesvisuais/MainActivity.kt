package com.example.usandocomponentesvisuais

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.Spinner
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var cbFem : CheckBox
    private lateinit var cbMasc : CheckBox
    private lateinit var dpNasc : DatePicker
    private lateinit var tpNasc : TimePicker
    private lateinit var acCidade : AutoCompleteTextView
    private lateinit var spCidade : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cbFem = findViewById(R.id.cbFem)
        cbMasc = findViewById(R.id.cbMasc)
        //dpNasc = findViewById(R.id.dpNasc)
        //tpNasc = findViewById(R.id.tpNasc)
        acCidade = findViewById(R.id.acCidade)
        spCidade = findViewById(R.id.spCidade)

        val cidade = listOf<String>("Pato Branco", "São Paulo", "Rio de Janeiro","Mariópolis", "Francisco Beltrão")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cidade)
        acCidade.threshold = 2 //numero de caracteres até ele mostar as opções para auto preenchimento
        acCidade.setAdapter(adapter)

        spCidade.setAdapter(adapter) // tem que estar associado a um adapter para funcionar
    }

    fun btTestarComponenteOnClik(view: View) {
        //Snackbar.make(this, view, "Menssagem", Snackbar.LENGTH_LONG).show() //toast 2.0, aquela mensagem que aparece ao arquivar um e-mail

        /*val snak = Snackbar.make(this, view, "Carregando", Snackbar.LENGTH_LONG) //declaramos pois queremos personalizar ele

        snak.setBackgroundTint(Color.RED)
        snak.setAction("Desfazer") {
            snak.dismiss()
        }
        snak.show()

        if(cbFem.isChecked){
            Snackbar.make(this, view, "Feminino", Snackbar.LENGTH_LONG).show()
        }else{
            Snackbar.make(this, view, "Masculino", Snackbar.LENGTH_LONG).show()
        }*/

       /* val msg = "${dpNasc.dayOfMonth}/${dpNasc.month + 1}/${dpNasc.year}"

        Snackbar.make(this, view, msg, Snackbar.LENGTH_LONG).show()*/





    }
}