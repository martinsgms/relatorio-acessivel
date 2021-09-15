package br.com.martinsgms.relatorioacessivel.model

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

data class AtividadeModel(

    val dataHora: String?,
    val atividade: String?,
    val sintoma: String?,
    val medicamento: String?

) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeSerializable(dataHora)
        parcel.writeString(atividade)
        parcel.writeString(sintoma)
        parcel.writeString(medicamento)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AtividadeModel> {
        @RequiresApi(Build.VERSION_CODES.O)
        override fun createFromParcel(parcel: Parcel): AtividadeModel {
            return AtividadeModel(parcel)
        }

        override fun newArray(size: Int): Array<AtividadeModel?> {
            return arrayOfNulls(size)
        }
    }

}