package br.com.martinsgms.relatorioacessivel.model

import android.os.Parcelable
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
class UsuarioModel(
    val id: Long?,
    val nome: String?,
    val email: String?,
    val senha: String?,
    ) : Parcelable {

    val novaSenha: String? = null

    class Deserializer : ResponseDeserializable<UsuarioModel> {
        override fun deserialize(content: String): UsuarioModel =
            Gson().fromJson(content, UsuarioModel::class.java)
    }
}
