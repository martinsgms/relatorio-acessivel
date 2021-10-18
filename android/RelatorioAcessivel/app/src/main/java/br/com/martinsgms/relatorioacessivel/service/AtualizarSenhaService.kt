package br.com.martinsgms.relatorioacessivel.service

import android.util.Log
import br.com.martinsgms.relatorioacessivel.config.HttpConfig
import br.com.martinsgms.relatorioacessivel.model.UsuarioModel
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.coroutines.awaitStringResponse
import com.github.kittinunf.fuel.httpPatch
import com.google.gson.Gson

class AtualizarSenhaService {

    var gson = Gson()

    init {
        HttpConfig.config()
    }

    suspend fun atualizaSenha(usuarioModel: UsuarioModel, token : String): String {

        Log.d("toks", "too->> $token")
        Log.d("json", "too->> ${gson.toJson(usuarioModel)}")

        val (request, response, result) = Fuel.request(Method.PUT, "/usuario/senha")
            .header("Content-Type" to "application/json", "Authorization" to "Bearer $token")
            .body(gson.toJson(usuarioModel))
            .awaitStringResponse()

        return result
    }

}
