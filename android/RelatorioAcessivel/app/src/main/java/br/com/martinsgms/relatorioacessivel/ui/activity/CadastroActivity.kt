package br.com.martinsgms.relatorioacessivel.ui.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.martinsgms.relatorioacessivel.R
import br.com.martinsgms.relatorioacessivel.model.UsuarioModel
import br.com.martinsgms.relatorioacessivel.service.CadastroService
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.runBlocking
import java.util.stream.Collectors

@RequiresApi(Build.VERSION_CODES.N)
class CadastroActivity : AppCompatActivity(R.layout.activity_cadastro) {

    private val cadastroService = CadastroService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configuraBotaoReturn()

        findViewById<Button>(R.id.activity_cadastro_cadastrar).setOnClickListener {
            try {
                cadastrar()
            } catch (e: Exception) {
                Log.d("Erro ao cadastrar", e.message!!)
            }
        }
    }

    private fun cadastrar() {

        val nome = findViewById<TextInputLayout>(R.id.activity_cadastro_nome).editText!!.text.toString()
        val email = findViewById<TextInputLayout>(R.id.activity_cadastro_email).editText!!.text.toString()
        val senha = findViewById<TextInputLayout>(R.id.activity_cadastro_senha).editText!!.text.toString()
        val repitaSenha = findViewById<TextInputLayout>(R.id.activity_cadastro_senha_repita).editText!!.text.toString()

        validaCadastro(senha, repitaSenha, nome, email)

        runBlocking {
            val responseMessage = cadastroService.cadastrar(UsuarioModel(null, nome.trim(), email.trim(), senha))
            buildSuccessAlert(responseMessage).show()
        }
    }

    private fun buildSuccessAlert(responseMessage: String): AlertDialog {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Aviso")
        builder.setMessage(responseMessage)
        builder.setPositiveButton("Ok") { _, _ ->
            finish()
        }
        builder.setOnDismissListener {
            finish()
        }
        return builder.create()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun validaCadastro(senha: String, repitaSenha: String, nome: String, email: String) {
        val erroMsgTextView = findViewById<TextView>(R.id.activity_cadastro_error_msg)

        val errorList = ArrayList<String>()

        if (senha.isBlank()) {
            errorList.add("senha")
        }

        if (repitaSenha.isBlank()) {
            errorList.add("repita a senha")
        }

        if (senha != repitaSenha) {
            erroMsgTextView.text = "As senhas não são iguais"
            throw RuntimeException("senhas diferentes")
        }

        if (nome.isBlank()) {
            errorList.add("nome")
        }

        if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorList.add("email")
        }

        var camposComErro = errorList.stream().collect(Collectors.joining(", ")).toString()
        camposComErro = camposComErro.removeRange(camposComErro.lastIndexOf(","), camposComErro.lastIndexOf(","))

        if (errorList.isNotEmpty()) {
            "Dados inválidos: $camposComErro".also { erroMsgTextView.text = it }
            throw RuntimeException("campos inválidos ao cadastrar")
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun configuraBotaoReturn() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }
}