package br.com.martinsgms.relatorioacessivel.ui.service

import android.util.Log
import br.com.martinsgms.relatorioacessivel.config.HttpConfig
import br.com.martinsgms.relatorioacessivel.model.AtividadeModel
import com.github.kittinunf.fuel.gson.jsonBody
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson

class NovaAtividadeService {
    var gson: Gson = Gson()

    init {
        HttpConfig.config()
    }

    fun salvaAtividade(model: AtividadeModel) {

        Log.d("gson" ,"${gson.toJson(model).toString()}")

        "/exame/5/evento".httpPost()
            .body(gson.toJson(model))
            .responseString { request, response, result ->
                Log.d("gms-post", result.get())
            }

    }
}