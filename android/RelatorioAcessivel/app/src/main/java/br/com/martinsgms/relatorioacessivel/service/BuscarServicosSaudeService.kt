package br.com.martinsgms.relatorioacessivel.service

import br.com.martinsgms.relatorioacessivel.config.HttpConfig
import br.com.martinsgms.relatorioacessivel.model.ServicoSaudeModel
import com.github.kittinunf.fuel.coroutines.awaitObjectResponse
import com.github.kittinunf.fuel.httpGet

class BuscarServicosSaudeService {

    init {
        HttpConfig.config()
    }

    suspend fun getServicosSaude(): List<ServicoSaudeModel> {

        val (request, response, result) = "/servico-saude".httpGet()
            .awaitObjectResponse(ServicoSaudeModel.Deserializer())

        return result.asList()
    }
}
