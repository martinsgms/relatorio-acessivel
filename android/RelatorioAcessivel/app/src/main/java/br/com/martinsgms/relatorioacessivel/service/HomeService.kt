package br.com.martinsgms.relatorioacessivel.service

import br.com.martinsgms.relatorioacessivel.config.HttpConfig
import br.com.martinsgms.relatorioacessivel.model.ExameModel
import com.github.kittinunf.fuel.coroutines.awaitStringResponse
import com.github.kittinunf.fuel.httpGet

class HomeService {

    init {
        HttpConfig.config()
    }

    suspend fun getExameMaisRecente(): ExameModel {

        val (request, response, result) = "/exame/usuario/1?apenasMaisRecente=true".httpGet()
            .awaitStringResponse()

        return ExameModel.deserialize(result)
    }
}