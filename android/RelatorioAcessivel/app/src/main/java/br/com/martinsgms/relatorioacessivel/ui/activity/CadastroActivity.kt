package br.com.martinsgms.relatorioacessivel.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.martinsgms.relatorioacessivel.R
import br.com.martinsgms.relatorioacessivel.model.UsuarioModel
import br.com.martinsgms.relatorioacessivel.service.CadastroService
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.runBlocking

class CadastroActivity : AppCompatActivity(R.layout.activity_cadastro) {

    private val cadastroService = CadastroService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val btnCadastrar = findViewById<Button>(R.id.activity_cadastro_cadastrar)
        btnCadastrar.setOnClickListener {

            val nome = findViewById<TextInputLayout>(R.id.activity_cadastro_nome).editText!!.text.toString()
            val email = findViewById<TextInputLayout>(R.id.activity_cadastro_email).editText!!.text.toString()
            val senha = findViewById<TextInputLayout>(R.id.activity_cadastro_senha).editText!!.text.toString()
            val repitaSenha = findViewById<TextInputLayout>(R.id.activity_cadastro_senha_repita).editText!!.text.toString()
            val erroMsgTextView = findViewById<TextView>(R.id.activity_cadastro_error_msg)

            if (senha != repitaSenha) {
                erroMsgTextView.text = "As senhas não são iguais"
                return@setOnClickListener
            }

            val usuarioModel = UsuarioModel(null, nome, email, senha)

            val self = this
            runBlocking {
                val response = cadastroService.cadastrar(usuarioModel)

                val builder = AlertDialog.Builder(self)
                builder.setTitle("Aviso")
                builder.setMessage(response)
                builder.setPositiveButton("Ok") { _, _ ->
                    finish()
                }
                builder.setOnDismissListener {
                    finish()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}