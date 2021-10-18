package br.com.martinsgms.relatorioacessivel.service

import br.com.martinsgms.relatorioacessivel.config.HttpConfig
import br.com.martinsgms.relatorioacessivel.model.UsuarioModel
import com.github.kittinunf.fuel.coroutines.awaitStringResponse
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson

class CadastroService {

    var gson = Gson()

    init {
        HttpConfig.config()
    }

    suspend fun cadastrar(usuarioModel: UsuarioModel): String {

        val (request, response, result) = "/usuario".httpPost().body(gson.toJson(usuarioModel))
            .awaitStringResponse()

        return result
    }
}