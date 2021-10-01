package br.com.martinsgms.relatorioacessivel.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.martinsgms.relatorioacessivel.R
import br.com.martinsgms.relatorioacessivel.model.ServicoSaudeModel
import br.com.martinsgms.relatorioacessivel.ui.activity.BuscarServicosSaudeActivity

class BuscarServicosSaudeAdapter(
    val context: Context,
    servicos: Array<ServicoSaudeModel> = emptyArray(),
) : RecyclerView.Adapter<BuscarServicosSaudeAdapter.ViewHolder>() {

    private val servicos = servicos.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(servico: ServicoSaudeModel, context: Context) {

            val nomeTextView = itemView.findViewById<TextView>(R.id.servico_saude_item_nome)
            nomeTextView.text = servico.nome

            val logradouroNumeroBairroTextView =
                itemView.findViewById<TextView>(R.id.servico_saude_item_logradouro_numero_bairro)
            "${servico.logradouro}, nº ${servico.lote}. ${servico.bairro}".also {
                logradouroNumeroBairroTextView.text = it
            }

            val cepCidadeEstadoTextView =
                itemView.findViewById<TextView>(R.id.servico_saude_item_cep_cidade_estado)
            "${servico.cep}. ${servico.cidade} - ${servico.estado}".also {
                cepCidadeEstadoTextView.text = it
            }


            val verNoMapaTextView =
                itemView.findViewById<TextView>(R.id.servico_saude_item_ver_no_mapa)
            verNoMapaTextView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(servico.linkMaps))
                startActivity(context, intent, Bundle())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.servico_saude_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(servicos[position], context)

    override fun getItemCount() = servicos.count()

    fun atualiza(servicos: Array<ServicoSaudeModel>) {
        this.servicos.clear()
        this.servicos.addAll(servicos)
        notifyDataSetChanged()
    }
}
