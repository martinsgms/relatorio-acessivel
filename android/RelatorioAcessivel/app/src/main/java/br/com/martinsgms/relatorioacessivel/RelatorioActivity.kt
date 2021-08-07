package br.com.martinsgms.relatorioacessivel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RelatorioActivity : AppCompatActivity(R.layout.activity_relatorio) {

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