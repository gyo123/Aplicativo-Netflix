package com.example.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.netflixclone.databinding.ActivityFormLoginBinding
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class FormLogin : AppCompatActivity() {
    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar!!.hide()
        VerificarUsuarioCadastrado()

        binding.txtTelCadastro.setOnClickListener {
            val intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
        }
        binding.btEntrar.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()
            val mensagem = binding.mensagemErro

            if(email.isEmpty() || senha.isEmpty()){
                mensagem.setText("Preencha todos os campos")

            }else{
                AutenticarUsuario()

            }
        }
    }

    private fun AutenticarUsuario(){
        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()
        val mensagem = binding.mensagemErro
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this, "Login efetuado com sucesso!", Toast.LENGTH_SHORT).show()
                IrParaTeladeFilmes()

            }
        }.addOnFailureListener {

            var error = it
            when{
                error is FirebaseAuthInvalidCredentialsException -> mensagem.setText("Email ou senha estão incorretos")
                error is FirebaseNetworkException -> mensagem.setText("Sem conexão com a internet!")
                else -> mensagem.setText("Erro ao logar usuário")
            }
        }

    }
    private fun VerificarUsuarioCadastrado(){
        val usuarioLogado = FirebaseAuth.getInstance().currentUser

        if(usuarioLogado != null){
            IrParaTeladeFilmes()

        }
    }
    private fun IrParaTeladeFilmes(){
        val intent = Intent(this, ListaFilmes::class.java)
        startActivity(intent)
        finish()
    }
}