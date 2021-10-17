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

    suspend fun getExameMaisRecente(): ExameModel {

        val (request, response, result) = "/exame/usuario/${HttpConfig.userId}?apenasMaisRecente=true".httpGet()
            .awaitStringResponse()

        return ExameModel.deserialize(result)
    }

    suspend fun getDadosUsuario(): UsuarioModel {

        val (request, response, result) = "/usuario?identificador=${HttpConfig.userId}".httpGet()
            .awaitObjectResponse(UsuarioModel.Deserializer())

        return result
    }
}