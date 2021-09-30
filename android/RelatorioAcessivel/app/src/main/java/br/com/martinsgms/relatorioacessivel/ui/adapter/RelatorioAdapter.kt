package br.com.martinsgms.relatorioacessivel.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import br.com.martinsgms.relatorioacessivel.R
import br.com.martinsgms.relatorioacessivel.model.EventoModel

class RelatorioAdapter(
    val context: Context,
    eventos: Array<EventoModel> = emptyArray(),
    var onClickAtividadeListener: OnClickAtividadeListener,
    var onLongClickAtividadeListener: OnLongClickAtividadeListener
) : RecyclerView.Adapter<RelatorioAdapter.ViewHolder>() {

    private val eventos = eventos.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(evento: EventoModel) {
            val horaView = itemView.findViewById<TextView>(R.id.exame_item_data)
            //horaView.text = "${evento.formatosDataHora!!.data}\n${evento.formatosDataHora.hora}"
            horaView.text = evento.formatosDataHora!!.hora

            val atividadeView = itemView.findViewById<TextView>(R.id.exame_item_servico_saude)
            atividadeView.text = evento.descricao

            val sintomasView = itemView.findViewById<TextView>(R.id.exame_item_status)
            sintomasView.text = evento.sintoma

            if (!evento.medicamento.isNullOrBlank() && !evento.sintoma.isNullOrBlank())
                sintomasView.text =
                    sintomasView.text.toString().plus(" • ${evento.medicamento}")
            else if (evento.sintoma.isNullOrBlank())
                sintomasView.text = evento.medicamento
        }

        val cardView = itemView.findViewById<ConstraintLayout>(R.id.relatorio_item_registro)
    }

    interface OnClickAtividadeListener {
        fun OnClickAtividadeListener(evento: EventoModel)
    }

    interface OnLongClickAtividadeListener {
        fun OnLongClickAtividadeListener(evento: EventoModel) : Boolean
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.relatorio_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val atividade = eventos[position]

        holder.cardView.setOnClickListener {
            onClickAtividadeListener.OnClickAtividadeListener(atividade)
        }

        holder.cardView.setOnLongClickListener {
            onLongClickAtividadeListener.OnLongClickAtividadeListener(atividade)
        }

        holder.bind(atividade)
    }

    override fun getItemCount(): Int = eventos.size

    fun atualiza(eventos: Array<EventoModel>?) {
        this.eventos.clear()
        this.eventos.addAll(eventos!!)
        notifyDataSetChanged()
    }
}