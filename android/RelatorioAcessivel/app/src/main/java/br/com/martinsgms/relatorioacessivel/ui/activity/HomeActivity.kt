package br.com.martinsgms.relatorioacessivel.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.cardview.widget.CardView
import br.com.martinsgms.relatorioacessivel.R
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result;

class HomeActivity : AppCompatActivity(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        "http://192.168.15.157:8080/exame/usuario/1?apenasMaisRecente=true".httpGet()
            .responseString { request, response, result ->
                Log.d("gms-data", result.get())
            }

        Log.d("gms-data", "fez")


        val cardExame = findViewById<CardView>(R.id.card_exame_mais_recente)
        cardExame.setOnClickListener() {
            val intent = Intent(this, DetalheExameActivity::class.java)
            startActivity(intent)
        }
    }
}