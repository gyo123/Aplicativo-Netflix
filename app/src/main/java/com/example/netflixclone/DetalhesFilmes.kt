package com.example.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.netflixclone.Adapter.FilmesAdapter
import com.example.netflixclone.Model.addFilmes
import com.example.netflixclone.databinding.ActivityDetalhesFilmesBinding
import com.squareup.picasso.Picasso

class DetalhesFilmes : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesFilmesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesFilmesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        Toolbar()

        val recycler_outros_filmes = binding.recylerOutrosFilmes
        recycler_outros_filmes.adapter = FilmesAdapter(addFilmes())
        recycler_outros_filmes.layoutManager = GridLayoutManager(applicationContext, 3)

        val capaTheWitcher = "https://firebasestorage.googleapis.com/v0/b/netflix-clone-51e97.appspot.com/o/video.jpg?alt=media&token=6f33147b-5dfb-4b16-880d-60072fc34ceb"

        Picasso.get().load(capaTheWitcher).fit().into(binding.capa)

        binding.playVideo.setOnClickListener {
            val intent = Intent(this, Video::class.java)
            startActivity(intent)
        }
    }
    private fun Toolbar(){
        val toolbar_detalhes = binding.tbdetalhes
        toolbar_detalhes.setNavigationIcon(getDrawable(R.drawable.ic_voltar))
        toolbar_detalhes.setNavigationOnClickListener{
            val intent = Intent(this, ListaFilmes::class.java)
            startActivity(intent)
            finish()

        }
    }

}