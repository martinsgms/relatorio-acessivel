package br.com.martinsgms.relatorioacessivel.service

import android.util.Log
import br.com.martinsgms.relatorioacessivel.config.HttpConfig
import br.com.martinsgms.relatorioacessivel.model.ServicoSaudeModel
import com.github.kittinunf.fuel.coroutines.awaitObjectResponse
import com.github.kittinunf.fuel.httpGet

class BuscarServicosSaudeService {

    init {
        HttpConfig.config()
    }

    suspend fun getServicosSaude(): Array<ServicoSaudeModel> {

        val (request, response, result) = "/servico-saude".httpGet()
            .awaitObjectResponse(ServicoSaudeModel.Deserializer())

        result.forEach { Log.d("msg", "$it" ) }
        return result
    }
}
