package com.example.calculaimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var etPeso : EditText // não podemos fazer um lateinit val temos que usar o var
    private lateinit var etAltura : EditText // quadno usamos lateinit precisamos dizer o tipo
    private lateinit var tvResultado : TextView // declara
    private lateinit var btCalcular : Button
    private lateinit var btLimpar : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        //log.e("onCreate","menssagem de erro aqui") // mostra menssagens no log cat porém deixa a menssagem colorida sendo mais fácil de indentificar
        //log.w("onCreate","menssagem de warning aqui") //podemos procurar pela tag - nesse caso a onCreate - para filtrar as menssagens
        //log.i("onCreate","menssagem de info aqui")


        super.onCreate(savedInstanceState)
        //System.out.println("Passei aqui") //mostra menssagens no log cat
        setContentView(R.layout.activity_main)

        etPeso = findViewById<EditText>(R.id.etPeso) //variavel declarda uma unica vez (como se fosse uma constante)
        etAltura = findViewById<EditText>(R.id.etAltuta)
        tvResultado = findViewById<TextView>(R.id.tvResultado) // instancia
        btCalcular = findViewById<Button>(R.id.btCalcular)
        btLimpar = findViewById<Button>(R.id.btLimpar)

        btCalcular.setOnClickListener{//aqui ja colocamos o código de clique
            btCalculaOnClick()
        }

        btLimpar.setOnClickListener(){
            btLimparOnClick()
        }

        /* btCalcular.setOnLongClickListener(View.OnLongClickListener { //o professor não passou ainda
             retrun false
         })*/

    }

    private fun btCalculaOnClick() {
        if(etPeso.text.isEmpty()){
            etPeso.error = getString(R.string.erro_peso)
            //Toast.makeText(this,"msg de erro",Toast.LENGTH_LONG).show() //outra forma de mesnsaagem mas a aparição na tela é de outra forma, aparece em baixo na tela
            etPeso.requestFocus()
            return
        }

        if(etAltura.text.isEmpty()){
            etAltura.error = getString(R.string.erro_altura)
            etAltura.requestFocus()
            return
        }

        val peso = etPeso.text.toString().toDouble()
        val altura = etAltura.text.toString().toDouble()

        var imc = 0.0
        if(Locale.getDefault().language.equals("en")){
            imc = 703*(peso/altura.pow(2.0))
        }else{
            imc = peso / Math.pow(altura,2.0)
        }

        val nf = NumberFormat.getNumberInstance(Locale.US)
        val df = DecimalFormat("0.0")

        tvResultado.setText(df.format(imc))

        Toast.makeText(this,getString(R.string.sucesso),Toast.LENGTH_LONG).show() // obs: menssagens de erro não é interessante de se mostrar em um toast
    }

    fun btLimparOnClick( ) {
        etPeso.setText( "")
        etAltura.setText( "")
        tvResultado.text = getString(R.string.zeros)
        etPeso.requestFocus()//apos limpar volta  para o campo do peso
    }
}