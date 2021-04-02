package com.example.netflixclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.netflixclone.databinding.ActivityFormCadastroBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class FormCadastro : AppCompatActivity() {
    private lateinit var binding: ActivityFormCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.btCadastrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val mensagem = binding.mensagemErro

            if(email.isEmpty() || senha.isEmpty()){
                mensagem.setText("Preencha todos os campos")

            }else{
                CadastrarUsuario()

            }
        }



        
    }
    private fun CadastrarUsuario(){
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val mensagem = binding.mensagemErro

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                binding.editEmail.setText("")
                binding.editSenha.setText("")
                mensagem.setText("")
            }
        }.addOnFailureListener {

            var erro = it

            when{
                erro is FirebaseAuthWeakPasswordException -> mensagem.setText("Digite uma senha com no mínimo 6 caracteres")
                erro is FirebaseAuthUserCollisionException -> mensagem.setText("Esta conta já existe")
                erro is FirebaseNetworkException -> mensagem.setText("Sem conexão com a internet")
                else -> mensagem.setText("Erro ao cadastrar usuário")

            }

            mensagem.setText("Erro ao cadastrar o usuário")
        }




    }

    private fun toolbar(){
        val toolbar = binding.toolbarCadastro
        toolbar.setBackgroundColor(getColor(R.color.white))
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_logo1))
    }
}