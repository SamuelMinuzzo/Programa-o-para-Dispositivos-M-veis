package com.example.trabalho1_cadastro.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.trabalho1_cadastro.entity.Item
import com.example.trabalho1_cadastro.entity.Pessoa

class DatabaseHandler (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL( "CREATE TABLE IF NOT EXISTS ${TABLE_NAME1} (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL)")

        db?.execSQL( "CREATE TABLE IF NOT EXISTS ${TABLE_NAME2} (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "descricao TEXT NOT NULL,valor REAL NOT NULL, pagadores TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL( "DROP TABLE IF EXISTS ${TABLE_NAME1} " )
        onCreate( db )
        db?.execSQL( "DROP TABLE IF EXISTS ${TABLE_NAME2} " )
        onCreate( db )
    }

    fun insertPessoa(pessoa : Pessoa){
        val registro = ContentValues()
        registro.put("nome", pessoa.nome)

        val banco = this.writableDatabase

        banco.insert(TABLE_NAME1, null, registro)
    }

    fun insertItem(item : Item){
        val registro = ContentValues()
        registro.put("descricao", item.descricao)
        registro.put("valor", item.valor)
        registro.put("pagadores", item.pagadores)

        val banco = this.writableDatabase

        banco.insert(TABLE_NAME2, null, registro)
    }

    fun findPagador(nome : String) : Item? {
        val banco = this.writableDatabase

        val registro = banco.query(
            TABLE_NAME2,
            null,
            "pagadores = ?",
            arrayOf(nome),
            null,
            null,
            null
        )

        if (registro.moveToFirst()) {
            val item = Item(
                registro.getInt(registro.getColumnIndexOrThrow("_id")),
                registro.getString(registro.getColumnIndexOrThrow("descricao")),
                registro.getDouble(registro.getColumnIndexOrThrow("valor")),
                registro.getString(registro.getColumnIndexOrThrow("pagadores"))
            )

            return item
        } else {
            return null
        }

    }

    fun contarPessoas(): Int {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM ${TABLE_NAME1}", null)
        cursor.moveToFirst()
        return cursor.getInt(0)
    }

    fun verificarPagadorExistente(nome: String): Boolean {
        val cursor = readableDatabase.query(
            TABLE_NAME2,
            arrayOf("_id"),
            "pagadores = ?",
            arrayOf(nome),
            null,
            null,
            null
        )

        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    fun verificarPessoaExistente(nome: String): Boolean {
        val cursor = readableDatabase.query(
            TABLE_NAME1,
            arrayOf("_id"),
            "nome = ?",
            arrayOf(nome),
            null,
            null,
            null
        )

        val exists = cursor.count > 0
        cursor.close()
        return exists
    }



    fun atualizarValor(item: Item, x : Double) {
        val registro = ContentValues()
        var valor_novo = item.valor + x
        registro.put("valor", valor_novo)

        val banco = this.writableDatabase

        banco.update(TABLE_NAME2, registro, "_id=${item._id}", null)
    }


    fun listPessoas() : Cursor {
        val banco = this.writableDatabase

        val registros = banco.query(
            TABLE_NAME1,
            arrayOf("_id","nome"),
            null,
            null,
            null,
            null,
            null
        )
        return registros
    }

    fun listItens() : Cursor {
        val banco = this.writableDatabase

        val registros = banco.query(
            TABLE_NAME2,
            null,
            null,
            null,
            null,
            null,
            null
            )

        return registros
    }

    fun listPagadores() : Cursor {
        val banco = this.writableDatabase


        val registros = banco.query(
            TABLE_NAME2,
            arrayOf("pagadores"),
            null,
            null,
            null,
            null,
            null
        )

        return registros
    }

    fun delete(){
        val banco = this.writableDatabase
        banco.delete(TABLE_NAME1, null, null)
        banco.delete(TABLE_NAME2, null, null)
    }


    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "dbfile.sqlite"
        private const val TABLE_NAME1 = "pessoa"
        private const val TABLE_NAME2 = "item"
        private const val _ID = 0
    }
}