package br.edu.utfpr.usandocloudfirestore

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {

    private lateinit var etCod: EditText
    private lateinit var etNome: EditText
    private lateinit var etTelefone: EditText

    val db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        etCod = findViewById(R.id.etCod)
        etNome = findViewById(R.id.etNome)
        etTelefone = findViewById(R.id.etTelefone)
    }

    fun btIncluirOnClick(view: View) {
        val pessoa = hashMapOf(
            "cod" to etCod.text.toString(),
            "nome" to etNome.text.toString(),
            "telefone" to etTelefone.text.toString()
        )
        db.collection("pessoas") // é para criar outra tabela pois essa não existe, o nome la é Pessoa e não pessoa como no exemplo
            .document(etCod.text.toString())
            .set(pessoa)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Inclusão realizada com sucesso", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erro ao incluir", Toast.LENGTH_SHORT).show()
            }
    }
    fun btAlterarOnClick(view: View) {
        val pessoa = hashMapOf(
            "nome" to etNome.text.toString(),
            "telefone" to etTelefone.text.toString()
        )
        db.collection("pessoas")
            .document(etCod.text.toString())
            .set(pessoa)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Alteração realizada com sucesso", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erro ao alterar", Toast.LENGTH_SHORT).show()
            }
    }
    fun btExcluirOnClick(view: View) {
        db.collection("pessoas")
            .document(etCod.text.toString())
            .delete()
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Exclusão realizada com sucesso", Toast.LENGTH_SHORT).show()
                }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erro ao excluir", Toast.LENGTH_SHORT).show()
            }
    }
    fun btPesquisarOnClick(view: View) {
        var saida = StringBuilder()
        db.collection("pessoas").whereEqualTo("nome", etCod.text.toString()).get().addOnSuccessListener { result ->
            if (result.isEmpty) {
                saida.append("Nenhum registro encontrado")
            } else {
                val registro = result.elementAt(0).data
                saida.append("${registro.get("nome")}")
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(this, "Erro ao pesquisar", Toast.LENGTH_SHORT).show()
        }
    }
    fun btListarOnClick(view: View) {
        var saida = StringBuilder()

        db.collection("pessoas").get().addOnSuccessListener { result ->
            for (document in result) {
                saida.append("${document.data.get("nome")} \n") // o get() retorna tudo fo banco de dados
            }
            Toast.makeText(this, saida, Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { exception ->
            Toast.makeText(this, "Erro ao listar", Toast.LENGTH_SHORT).show()
        }
    }
}