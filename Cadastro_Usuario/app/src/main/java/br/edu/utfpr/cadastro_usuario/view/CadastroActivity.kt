package br.edu.utfpr.cadastro_usuario.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.utfpr.cadastro_usuario.databinding.ActivityCadastroBinding
import com.google.firebase.auth.FirebaseAuth

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btCadastrar.setOnClickListener {
            cadastrar()
        }


    }

    fun cadastrar(){
        val email = binding.etEmail.text.toString()
        val senha = binding.etSenha.text.toString()

        if(email.isNotEmpty() && senha.isNotEmpty()){
            auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener {cadastro ->
                if(cadastro.isSuccessful){
                    finish()
                }else{
                    binding.etEmail.error = "Email já cadastrado"
                    binding.etSenha.error = "Senha já cadastrada"
                 }
            }
        }else{
            binding.etEmail.error = "Preencha o email"
            binding.etSenha.error = "Preencha a senha"
        }
    }
}