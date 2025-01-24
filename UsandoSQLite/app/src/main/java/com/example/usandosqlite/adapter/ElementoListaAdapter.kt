package com.example.usandosqlite.adapter

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.example.usandosqlite.MainActivity
import com.example.usandosqlite.R
import com.example.usandosqlite.entity.Cadastro
import kotlinx.coroutines.MainScope

class ElementoListaAdapter(val context: Context, val cursor : Cursor) : BaseAdapter() {
    override fun getCount(): Int {
        return cursor.count
    }

    override fun getItem(id: Int): Any {
        cursor.moveToPosition(id)
        val cadastro = Cadastro(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2)
        )

        return cadastro
    }

    override fun getItemId(id: Int): Long {
        cursor.moveToPosition(id)

        return cursor.getInt(0).toLong()
    }

    override fun getView(id: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.elemento_lista, null)

        val tvNomeElementoLista = v.findViewById<TextView>(R.id.tvNomeElementoLista)
        val tvTelefoneElementoLista = v.findViewById<TextView>(R.id.tvTelefoneElementoLista)
        val btEditarElementoLista = v.findViewById<ImageButton>(R.id.btEditarElementoLista)

        cursor.moveToPosition(id)

        tvNomeElementoLista.text = cursor.getString(1)
        tvTelefoneElementoLista.text = cursor.getString(2)

        btEditarElementoLista.setOnClickListener {

            cursor.moveToPosition(id)

            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("cod", cursor.getInt(0))
            intent.putExtra("nome", cursor.getString(1))
            intent.putExtra("telefone", cursor.getString(2))

            context.startActivity(intent)
        }
        return v
    }
}