package br.com.martinsgms.relatorioacessivel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class HomeActivity : AppCompatActivity(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cardExame = findViewById<CardView>(R.id.card_exame_mais_recente)
        cardExame.setOnClickListener() {
            val intent = Intent(this, DetalheExameActivity::class.java)
            startActivity(intent)
        }
    }
}