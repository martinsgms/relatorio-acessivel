package br.com.martinsgms.relatorioacessivel.service

import br.com.martinsgms.relatorioacessivel.config.HttpConfig
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson

class RelatorioService {

    var gson: Gson = Gson()

    init {
        HttpConfig.config()
    }

    fun getDiarioDeAtividades() {

        "/exame/usuario/1?apenasMaisRecente=true".httpGet()
            .responseString { request, response, result ->

            }
    }
}