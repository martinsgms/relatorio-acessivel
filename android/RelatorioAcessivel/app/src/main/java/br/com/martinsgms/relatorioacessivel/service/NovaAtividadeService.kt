package br.com.martinsgms.relatorioacessivel.service

import android.util.Log
import br.com.martinsgms.relatorioacessivel.config.HttpConfig
import br.com.martinsgms.relatorioacessivel.model.EventoModel
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.coroutines.awaitStringResponse
import com.github.kittinunf.fuel.httpDelete
import com.google.gson.Gson

class NovaAtividadeService {

    var gson = Gson()

    init {
        HttpConfig.config()
    }

    suspend fun salvaEvento(method: Method, model: EventoModel) {
        if (model.id != null && model.id == -1L)
            model.id = null

        if (model.idExame!! == -1L)
            model.idExame = null

        Log.d("gms-post", HttpConfig.token)

        val (request, response, result) = Fuel.request(method, "/exame/evento")
            .body(gson.toJson(model))
            .awaitStringResponse()

        Log.d("gms-post", result)
    }
}