package br.com.martinsgms.relatorioacessivel.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.martinsgms.relatorioacessivel.R
import br.com.martinsgms.relatorioacessivel.model.AtividadeModel

class RelatorioAdapter(
    val context: Context,
    atividades: List<AtividadeModel> = emptyList()
) : RecyclerView.Adapter<RelatorioAdapter.ViewHolder>() {

    private val atividades = atividades.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(atividade: AtividadeModel) {
            val horaView = itemView.findViewById<TextView>(R.id.hora)
            horaView.text = atividade.hora.toString()

            val atividadeView = itemView.findViewById<TextView>(R.id.atividade)
            atividadeView.text = atividade.atividade

            val sintomasView = itemView.findViewById<TextView>(R.id.sintomas)
            sintomasView.text = atividade.sintomas

            if (atividade.medicamentos.isNotBlank() && atividade.sintomas.isNotBlank())
                sintomasView.text = sintomasView.text.toString().plus(" • ${atividade.medicamentos}")

            else if (atividade.sintomas.isBlank())
                sintomasView.text = atividade.medicamentos
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.relatorio_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val atividade = atividades[position]
        holder.bind(atividade)
    }

    override fun getItemCount(): Int = atividades.size

    fun atualiza(atividades: List<AtividadeModel>) {
        this.atividades.clear()
        this.atividades.addAll(atividades)
        notifyDataSetChanged()
    }
}