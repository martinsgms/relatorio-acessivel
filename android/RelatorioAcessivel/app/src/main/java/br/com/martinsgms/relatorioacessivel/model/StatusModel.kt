package br.com.martinsgms.relatorioacessivel.model

import java.io.Serializable

data class StatusModel(
    val codigo: String,
    val descricao: String,
    val permiteEscrita: Boolean
) : Serializable {

}
