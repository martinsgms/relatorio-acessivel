package br.com.martinsgms.relatorioacessivel.model



import android.os.Parcelable
import br.com.martinsgms.relatorioacessivel.dto.FormatosDataHoraDTO
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventoModel(
    var id: Long?,
    var idExame: Long?,
    val timestampEvento: String?,
    val descricao: String?,
    val sintoma: String?,
    val medicamento: String?,
) : Parcelable {

    val formatosDataHora: FormatosDataHoraDTO? = null

    class Deserializer : ResponseDeserializable<Array<EventoModel>> {
        override fun deserialize(content: String): Array<EventoModel>? = Gson().fromJson(content, Array<EventoModel>::class.java)
    }
}