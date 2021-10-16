package br.com.martinsgms.relatorioacessivel.model

import android.os.Parcelable
import br.com.martinsgms.relatorioacessivel.dto.FormatosDataHoraDTO
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
class UsuarioModel(

    val email: String,
    val senha: String,

) : Parcelable {

    val id: Long? = null
    val nome: String? = null
    val novaSenha: String ?=null

    val formatosDataHora: FormatosDataHoraDTO? = null

    class Deserializer : ResponseDeserializable<UsuarioModel> {
        override fun deserialize(content: String): UsuarioModel =
            Gson().fromJson(content, UsuarioModel::class.java)
    }
}
