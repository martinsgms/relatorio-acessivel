package br.com.martinsgms.relatorioacessivel.ui.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.martinsgms.relatorioacessivel.R
import br.com.martinsgms.relatorioacessivel.model.UsuarioModel
import br.com.martinsgms.relatorioacessivel.service.AtualizarSenhaService
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.runBlocking

class AtualizarSenhaActivity : AppCompatActivity(R.layout.activity_atualizar_senha) {

    private var token: String?=null
    private val atualizarSenhaService = AtualizarSenhaService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configuraBotaoReturn()

        val sharedPref = getSharedPreferences("token_sp", Context.MODE_PRIVATE)
        val token = sharedPref.getString("JWT_TOKEN", null)
        this.token = token


        val usuarioModel = intent.getParcelableExtra<UsuarioModel>("usuario")

        val senhaAtualEditText = findViewById<TextInputLayout>(R.id.activity_atualizar_senha_atual)
        val repitaSenhaEditText = findViewById<TextInputLayout>(R.id.activity_atualizar_senha_repita)
        val novaSenhaEditText = findViewById<TextInputLayout>(R.id.activity_atualizar_senha_nova)

        val erroMsgTextView = findViewById<TextView>(R.id.activity_atualizar_senha_error)

        val btnSalvar = findViewById<Button>(R.id.activity_atualizar_senha_salvar)
        btnSalvar.setOnClickListener {

            val novaSenha = novaSenhaEditText.editText!!.text.toString()
            val repitaSenha = repitaSenhaEditText.editText!!.text.toString()

            Log.d("atts", "$novaSenha --- $repitaSenha")

            if (novaSenha != repitaSenha) {
                erroMsgTextView.text = "As senhas não são iguais"
                return@setOnClickListener
            }

            usuarioModel!!.novaSenha = novaSenha
            usuarioModel.senha = senhaAtualEditText.editText!!.text.toString()

            Log.d("atts", "2222222222")

            val self = this
            runBlocking {

                Log.d("atts", "${usuarioModel.senha}")
                Log.d("atts", "${usuarioModel.novaSenha}")

                val response = atualizarSenhaService.atualizaSenha(usuarioModel, self.token!!)

                val builder = AlertDialog.Builder(self)
                builder.setTitle("Aviso")
                builder.setMessage(response)
                builder.setPositiveButton("Ok") {_,_ ->
                    finish()
                }
                builder.setOnDismissListener{
                    finish()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }
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