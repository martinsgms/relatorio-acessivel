package br.com.martinsgms.relatorioacessivel.service

import android.util.Log
import br.com.martinsgms.relatorioacessivel.config.HttpConfig
import br.com.martinsgms.relatorioacessivel.model.EventoModel
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson

class NovaAtividadeService {

    var gson: Gson = Gson()

    init {
        HttpConfig.config()
    }

    fun salvaAtividade(model: EventoModel) {
        Log.d("gms-post", model.toString())
        "/exame/evento".httpPost()
            .body(gson.toJson(model))
            .responseString { request, response, result ->
                Log.d("gms-post", result.get())
            }
    }
}