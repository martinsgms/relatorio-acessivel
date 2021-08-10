package br.com.martinsgms.relatorioacessivel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DetalheExameActivity : AppCompatActivity(R.layout.activity_detalhe_exame) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val btnRelatorio = findViewById<Button>(R.id.activity_detalhe_atividade_relatorio)
        btnRelatorio.setOnClickListener() {
            val intent = Intent(this, RelatorioActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}