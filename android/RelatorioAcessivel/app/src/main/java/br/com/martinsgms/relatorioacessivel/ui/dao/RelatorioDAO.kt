package br.com.martinsgms.relatorioacessivel.ui.dao

import br.com.martinsgms.relatorioacessivel.model.EventoModel

class RelatorioDAO {

    fun save(evento : EventoModel) {
        atividades.add(evento)
    }

    fun findAll() : List<EventoModel> {
        return atividades.toList()
    }

    companion object {
        private val atividades = mutableListOf<EventoModel>()
    }
}