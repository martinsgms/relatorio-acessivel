package br.com.martinsgms.relatorioacessivel.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FormatosDataHoraDTO(
    var dataHora: String,
    var data: String,
    var hora: String,
    var diaMes: String,
    var semanaDiaMesAnoExtenso: String,
    var semanaDiaMesAnoHoraExtenso: String
) : Parcelable {

}
