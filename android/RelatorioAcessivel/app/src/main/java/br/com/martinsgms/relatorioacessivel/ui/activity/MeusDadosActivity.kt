package br.com.martinsgms.relatorioacessivel.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.martinsgms.relatorioacessivel.R
import br.com.martinsgms.relatorioacessivel.model.UsuarioModel

class MeusDadosActivity : AppCompatActivity(R.layout.activity_meus_dados) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configuraBotaoReturn()
        preencheDadosDoUsuario()
    }

    private fun preencheDadosDoUsuario() {
        val usuarioModel = intent.getParcelableExtra<UsuarioModel>("usuario")

        Log.d("usu", "${usuarioModel?.nome}")

        val nomeTextView = findViewById<TextView>(R.id.activity_meus_dados_nome)
        nomeTextView.text = usuarioModel!!.nome

        val emailTextView = findViewById<TextView>(R.id.activity_meus_dados_email)
        emailTextView.text = usuarioModel.email
    }

    private fun configuraBotaoReturn() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
