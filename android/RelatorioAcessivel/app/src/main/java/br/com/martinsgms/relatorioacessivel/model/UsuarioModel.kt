package br.com.martinsgms.relatorioacessivel.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class UsuarioModel(
    val id: Long,
    val nome: String,
    val email: String,
) : Parcelable {

}
