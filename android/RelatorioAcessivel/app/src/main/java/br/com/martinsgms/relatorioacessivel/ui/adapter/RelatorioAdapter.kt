package br.com.martinsgms.relatorioacessivel.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.com.martinsgms.relatorioacessivel.R
import br.com.martinsgms.relatorioacessivel.model.AtividadeModel

class RelatorioAdapter(
    val context: Context,
    atividades: List<AtividadeModel> = emptyList(),
    var onClickAtividadeListener: OnClickAtividadeListener,
    var onLongClickAtividadeListener: OnLongClickAtividadeListener
) : RecyclerView.Adapter<RelatorioAdapter.ViewHolder>() {

    private val atividades = atividades.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(atividade: AtividadeModel) {
            val horaView = itemView.findViewById<TextView>(R.id.hora)
            horaView.text = atividade.dataHora.toString()

            val atividadeView = itemView.findViewById<TextView>(R.id.atividade)
            atividadeView.text = atividade.atividade

            val sintomasView = itemView.findViewById<TextView>(R.id.sintomas)
            sintomasView.text = atividade.sintoma

            if (!atividade.medicamento.isNullOrBlank() && !atividade.sintoma.isNullOrBlank())
                sintomasView.text =
                    sintomasView.text.toString().plus(" • ${atividade.medicamento}")
            else if (atividade.sintoma.isNullOrBlank())
                sintomasView.text = atividade.medicamento
        }

        val cardView = itemView.findViewById<CardView>(R.id.card_view)
    }

    interface OnClickAtividadeListener {
        fun OnClickAtividadeListener(atividade: AtividadeModel)
    }

    interface OnLongClickAtividadeListener {
        fun OnLongClickAtividadeListener(atividade: AtividadeModel) : Boolean
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.relatorio_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val atividade = atividades[position]

        holder.cardView.setOnClickListener {
            onClickAtividadeListener.OnClickAtividadeListener(atividade)
        }

        holder.cardView.setOnLongClickListener {
            onLongClickAtividadeListener.OnLongClickAtividadeListener(atividade)
        }

        holder.bind(atividade)
    }

    override fun getItemCount(): Int = atividades.size

    fun atualiza(atividades: List<AtividadeModel>) {
        this.atividades.clear()
        this.atividades.addAll(atividades)
        notifyDataSetChanged()
    }
}