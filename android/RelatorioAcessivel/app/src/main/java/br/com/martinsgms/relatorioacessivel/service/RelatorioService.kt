package br.com.martinsgms.relatorioacessivel.service

import br.com.martinsgms.relatorioacessivel.config.HttpConfig
import br.com.martinsgms.relatorioacessivel.model.EventoModel
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.coroutines.awaitObjectResponse
import com.github.kittinunf.fuel.coroutines.awaitStringResponse
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson

class RelatorioService {

    var gson: Gson = Gson()

    init {
        HttpConfig.config()
    }

    suspend fun getEventos(idExame : Long) : Array<EventoModel> {

        val (request, response, result) = "/exame/${idExame}/eventos".httpGet().awaitObjectResponse(EventoModel.Deserializer())

        return result
    }
}