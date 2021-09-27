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
import br.com.martinsgms.relatorioacessivel.model.ExameModel

class MeusExamesAdapter(val context: Context, exames: Array<ExameModel> = emptyArray()) :
    RecyclerView.Adapter<MeusExamesAdapter.ViewHolder>() {

    private val exames = exames.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(exame: ExameModel) {

            val dataTextView = itemView.findViewById<TextView>(R.id.exame_item_data)
            dataTextView.text = exame.dataHoraFormatada

            //val servicoSaudeTextView = itemView.findViewById<TextView>(R.id.exame_item_servico_saude)
            //servicoSaudeTextView.text = exame.servicoSaude

            val statusTextView = itemView.findViewById<TextView>(R.id.exame_item_status)
            statusTextView.text = exame.status!!.descricao
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.exame_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(exames[position])

    override fun getItemCount() = exames.count()

    fun atualiza(exames: Array<ExameModel>?) {
        this.exames.clear()
        this.exames.addAll(exames!!)
        notifyDataSetChanged()
    }
}
