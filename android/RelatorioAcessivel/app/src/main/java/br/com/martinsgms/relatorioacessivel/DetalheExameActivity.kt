package br.com.martinsgms.relatorioacessivel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetalheExameActivity : AppCompatActivity(R.layout.activity_detalhe_exame) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}