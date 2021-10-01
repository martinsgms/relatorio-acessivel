package br.com.martinsgms.relatorioacessivel.ui.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.martinsgms.relatorioacessivel.R
import br.com.martinsgms.relatorioacessivel.model.ServicoSaudeModel
import java.text.Normalizer

class BuscarServicosSaudeAdapter(
    val context: Context,
    servicos: List<ServicoSaudeModel> = emptyList(),
) : RecyclerView.Adapter<BuscarServicosSaudeAdapter.ViewHolder>(), Filterable {

    private val servicos = servicos.toMutableList()
    private val servicosAll = servicos.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(servico: ServicoSaudeModel, context: Context) {

            val nomeTextView = itemView.findViewById<TextView>(R.id.servico_saude_item_nome)
            nomeTextView.text = servico.nome

            val logradouroNumeroBairroTextView = itemView.findViewById<TextView>(R.id.servico_saude_item_logradouro_numero_bairro)
            "${servico.logradouro}, nº ${servico.lote}. ${servico.bairro}".also {
                logradouroNumeroBairroTextView.text = it
            }

            val cepCidadeEstadoTextView = itemView.findViewById<TextView>(R.id.servico_saude_item_cep_cidade_estado)
            "${servico.cep}. ${servico.cidade} - ${servico.estado}".also {
                cepCidadeEstadoTextView.text = it
            }

            val verNoMapaTextView = itemView.findViewById<TextView>(R.id.servico_saude_item_ver_no_mapa)
            verNoMapaTextView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(servico.linkMaps))
                startActivity(context, intent, Bundle())
            }

            val mensagemTextView = itemView.findViewById<TextView>(R.id.servico_saude_item_enviar_mensagem)
            mensagemTextView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(servico.linkWhats))
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

    fun atualiza(servicos: List<ServicoSaudeModel>) {
        this.servicos.clear()
        this.servicosAll.clear()

        this.servicos.addAll(servicos)
        this.servicosAll.addAll(servicos)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun performFiltering(charSequence: CharSequence?): FilterResults {

                val filtered = ArrayList<ServicoSaudeModel>()

                if (charSequence.toString().isBlank()) {
                    filtered.addAll(servicosAll)

                } else {
                    servicosAll.forEach {
                        if (it.cidade!!.lowercase().unaccent().contains(charSequence.toString().lowercase().unaccent())) {
                            filtered.add(it)
                        }
                    }
                }

                val results = FilterResults()
                results.values = filtered

                return results
            }

            override fun publishResults(charSequence: CharSequence?, results: FilterResults?) {
                val resultList = results!!.values as ArrayList<ServicoSaudeModel>

                servicos.clear()
                servicos.addAll(resultList)

                notifyDataSetChanged()
            }
        }
    }

    private val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()

    fun CharSequence.unaccent(): String {
        val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
        return REGEX_UNACCENT.replace(temp, "")
    }
}
