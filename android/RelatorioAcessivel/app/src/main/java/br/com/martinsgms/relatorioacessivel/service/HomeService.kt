package br.com.martinsgms.relatorioacessivel.service

import android.util.Log
import br.com.martinsgms.relatorioacessivel.config.HttpConfig
import br.com.martinsgms.relatorioacessivel.model.ExameModel
import br.com.martinsgms.relatorioacessivel.model.UsuarioModel
import com.github.kittinunf.fuel.coroutines.awaitObjectResponse
import com.github.kittinunf.fuel.coroutines.awaitStringResponse
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost

class HomeService {

    init {
        HttpConfig.config()
    }

    suspend fun getExameMaisRecente(token : String): ExameModel {

        val (request, response, result) = "/exame/usuario/${HttpConfig.userId}?apenasMaisRecente=true".httpGet()
            .header("Authorization" to "Bearer $token")
            .awaitStringResponse()

        return ExameModel.deserialize(result)
    }

    suspend fun getDadosUsuario(token : String): UsuarioModel {

        val (request, response, result) = "/usuario?identificador=${HttpConfig.userId}".httpGet()
            .header("Authorization" to "Bearer $token")
            .awaitObjectResponse(UsuarioModel.Deserializer())

        return result
    }
}