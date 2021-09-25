package br.com.martinsgms.relatorioacessivel.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.martinsgms.relatorioacessivel.R
import br.com.martinsgms.relatorioacessivel.model.ExameModel

class DetalheExameActivity : AppCompatActivity(R.layout.activity_detalhe_exame) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val exameModel = intent.getParcelableExtra<ExameModel>("exameModel")

        //val clinicaDisplay = findViewById<TextView>(R.id.activity_detalhe_exame_clinica)
        //clinicaDisplay.text = exameModel?.servicoSaude

        val idExternoView = findViewById<TextView>(R.id.activity_detalhe_exame_id_exame)
        idExternoView.text = exameModel?.idExterno

        val dataExameView = findViewById<TextView>(R.id.activity_detalhe_exame_data)
        dataExameView.text = exameModel?.dataHoraFormatada

        val statusExameView = findViewById<TextView>(R.id.activity_detalhe_exame_status)
        statusExameView.text = exameModel?.status?.descricao

        val btnRelatorio = findViewById<Button>(R.id.activity_detalhe_atividade_relatorio)

        if (!exameModel?.status?.permiteEscrita!!) {

            btnRelatorio.isEnabled = false
            btnRelatorio.isClickable = false

        } else {

            btnRelatorio.setOnClickListener() {
                val intent = Intent(this, RelatorioActivity::class.java)
                intent.putExtra("exameModel", exameModel)

                startActivity(intent)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}