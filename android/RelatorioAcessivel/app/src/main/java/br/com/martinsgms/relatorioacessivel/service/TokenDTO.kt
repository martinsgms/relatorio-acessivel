package br.com.martinsgms.relatorioacessivel.service

import android.os.Parcelable
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
class TokenDTO(
    val token: String,
    val tipo: String,
) : Parcelable {

    class Deserializer : ResponseDeserializable<TokenDTO> {
        override fun deserialize(content: String): TokenDTO =
            Gson().fromJson(content, TokenDTO::class.java)
    }
}
