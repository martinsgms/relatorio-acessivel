package br.com.martinsgms.relatorioacessivel.model

import android.os.Parcelable
import br.com.martinsgms.relatorioacessivel.dto.FormatosDataHoraDTO
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExameModel(
    val id: Long,
    val usuario: Long,
    val idExterno: String?,
    val status: StatusModel,
    val servicoSaude: ServicoSaudeModel,
    val formatosDataHora : FormatosDataHoraDTO
) : Parcelable {

    class Deserializer : ResponseDeserializable<Array<ExameModel>> {
        override fun deserialize(content: String): Array<ExameModel>? =
            Gson().fromJson(content, Array<ExameModel>::class.java)
    }

    companion object {
        fun deserialize(content: String): ExameModel =
            Gson().fromJson(content, ExameModel::class.java)
    }

}