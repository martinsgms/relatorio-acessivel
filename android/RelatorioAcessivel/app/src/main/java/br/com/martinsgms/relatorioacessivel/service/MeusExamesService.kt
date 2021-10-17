package br.com.martinsgms.relatorioacessivel.service

import br.com.martinsgms.relatorioacessivel.config.HttpConfig
import br.com.martinsgms.relatorioacessivel.model.ExameModel
import com.github.kittinunf.fuel.coroutines.awaitObjectResponse
import com.github.kittinunf.fuel.httpGet

class MeusExamesService {

    init {
        HttpConfig.config()
    }

    suspend fun getExamesDoUsuario(idUsuario: Long): Array<ExameModel> {

        val (request, response, result) = "/exame/usuario/${HttpConfig.userId}".httpGet()
            .awaitObjectResponse(ExameModel.Deserializer())

        return result
    }
}
