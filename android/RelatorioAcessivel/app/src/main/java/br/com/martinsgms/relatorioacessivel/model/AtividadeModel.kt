package br.com.martinsgms.relatorioacessivel.model

import java.time.LocalTime

data class AtividadeModel (

    val hora : LocalTime,
    val atividade : String,
    val sintomas : String,
    val medicamentos : String
)