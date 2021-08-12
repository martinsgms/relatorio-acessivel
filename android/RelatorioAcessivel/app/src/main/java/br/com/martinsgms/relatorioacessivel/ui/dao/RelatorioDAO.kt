package br.com.martinsgms.relatorioacessivel.ui.dao

import br.com.martinsgms.relatorioacessivel.model.AtividadeModel

class RelatorioDAO {

    fun save(atividade : AtividadeModel) {
        atividades.add(atividade)
    }

    fun findAll() : List<AtividadeModel> {
        return atividades.toList()
    }

    companion object {
        private val atividades = mutableListOf<AtividadeModel>()
    }
}