package br.com.martinsgms.relatorioacessivel.service

import android.util.Log
import br.com.martinsgms.relatorioacessivel.config.HttpConfig
import br.com.martinsgms.relatorioacessivel.model.UsuarioModel
import com.github.kittinunf.fuel.coroutines.awaitObjectResponse
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson

class LoginService {

    var gson = Gson()

    init {
        HttpConfig.config()
    }

    suspend fun authenticate(authRequest: UsuarioModel): TokenDTO {

        Log.d("login-req", authRequest.toString())

        val (request, response, result) = "/login".httpPost().body(gson.toJson(authRequest))
            .awaitObjectResponse(TokenDTO.Deserializer())

        Log.d("login-res", result.toString())

        return result
    }
}