package br.com.martinsgms.relatorioacessivel.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FormatosDataHoraDTO(
    var timestamp: String,
    var dataHora: String,
    var data: String,
    var hora: String,
    var semanaDiaMesAnoExtenso: String,
    var semanaDiaMesAnoHoraExtenso: String
) : Parcelable {

}
