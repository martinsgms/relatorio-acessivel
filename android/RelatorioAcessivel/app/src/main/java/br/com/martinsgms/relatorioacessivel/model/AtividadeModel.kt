package br.com.martinsgms.relatorioacessivel.model

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import java.time.LocalTime

data class AtividadeModel(

    val hora: LocalTime,
    val atividade: String?,
    val sintomas: String?,
    val medicamentos: String?

) : Parcelable {

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(parcel: Parcel) : this(
        parcel.readSerializable() as LocalTime,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeSerializable(hora)
        parcel.writeString(atividade)
        parcel.writeString(sintomas)
        parcel.writeString(medicamentos)
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