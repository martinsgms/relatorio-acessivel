package br.com.martinsgms.relatorioacessivel.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import br.com.martinsgms.relatorioacessivel.R
import br.com.martinsgms.relatorioacessivel.config.HttpConfig
import com.github.kittinunf.fuel.httpGet

class HomeActivity : AppCompatActivity(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        HttpConfig.config()

        /*
        "/exame/usuario/1?apenasMaisRecente=true".httpGet()
            .responseString { request, response, result ->
                Log.d("gms-data", result.get())
            }
        */

        val cardExame = findViewById<CardView>(R.id.card_exame_mais_recente)
        cardExame.setOnClickListener() {
            val intent = Intent(this, DetalheExameActivity::class.java)
            startActivity(intent)
        }
    }
}