package br.com.martinsgms.relatorioacessivel.model



import android.os.Parcelable
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize
import java.util.ArrayList

@Parcelize
data class EventoModel(

    val idExame : Long?,
    val dataHora: String?,
    val descricao: String?,
    val sintoma: String?,
    val medicamento: String?,

    ) : Parcelable {

    val horaFormatada: String? = null

    class Deserializer : ResponseDeserializable<Array<EventoModel>> {
        override fun deserialize(content: String): Array<EventoModel>? = Gson().fromJson(content, Array<EventoModel>::class.java)
    }
}