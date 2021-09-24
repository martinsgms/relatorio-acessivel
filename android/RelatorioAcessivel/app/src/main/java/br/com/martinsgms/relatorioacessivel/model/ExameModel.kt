package br.com.martinsgms.relatorioacessivel.model

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExameModel(
    val id: Long,
    val idExterno: String?,
    val servicoSaude: String?,
    val status: StatusModel?,
    val dataHoraExame: String?,
    val usuario: Long,
    val eventos: List<EventoModel>?,
) : Parcelable {

    companion object {
        fun deserialize(content: String): ExameModel =
            Gson().fromJson(content, ExameModel::class.java)
    }

}