package br.com.martinsgms.relatorioacessivel.model

import android.os.Parcelable
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class ServicoSaudeModel(
    var id: String?,
    var nomeCompleto: String?,
    var nome: String?,
    var cnpj: String?,
    var cep: String?,
    var logradouro: String?,
    var bairro: String?,
    var cidade: String?,
    var estado: String?,
    var lote: String?,
    var linkMaps: String?,
    var linkWhats: String?,
) : Parcelable {

    class Deserializer : ResponseDeserializable<Array<ServicoSaudeModel>> {
        override fun deserialize(content: String): Array<ServicoSaudeModel> =
            Gson().fromJson(content, Array<ServicoSaudeModel>::class.java)
    }

}
