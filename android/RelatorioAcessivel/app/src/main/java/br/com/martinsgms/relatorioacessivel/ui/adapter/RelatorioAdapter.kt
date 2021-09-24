package br.com.martinsgms.relatorioacessivel.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
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
            val horaView = itemView.findViewById<TextView>(R.id.hora)
            horaView.text = evento.horaFormatada

            val atividadeView = itemView.findViewById<TextView>(R.id.atividade)
            atividadeView.text = evento.descricao

            val sintomasView = itemView.findViewById<TextView>(R.id.sintomas)
            sintomasView.text = evento.sintoma

            if (!evento.medicamento.isNullOrBlank() && !evento.sintoma.isNullOrBlank())
                sintomasView.text =
                    sintomasView.text.toString().plus(" • ${evento.medicamento}")
            else if (evento.sintoma.isNullOrBlank())
                sintomasView.text = evento.medicamento
        }

        val cardView = itemView.findViewById<CardView>(R.id.card_view)
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