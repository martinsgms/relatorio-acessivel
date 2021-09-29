package br.com.martinsgms.relatorioacessivel.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
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

        val clinicaDisplay = findViewById<TextView>(R.id.activity_detalhe_exame_servico_saude)
        clinicaDisplay.text = exameModel!!.servicoSaude.nome

        val idExternoView = findViewById<TextView>(R.id.activity_detalhe_exame_id_externo)
        idExternoView.text = exameModel.idExterno

        val dataExameView = findViewById<TextView>(R.id.activity_detalhe_exame_data)
        dataExameView.text = exameModel.formatosDataHora.semanaDiaMesAnoHoraExtenso

        val statusExameView = findViewById<TextView>(R.id.activity_detalhe_exame_status)
        statusExameView.text = exameModel.status.descricao

        val btnRelatorio = findViewById<Button>(R.id.activity_detalhe_exame_btn_diario)

        if (!exameModel.status.permiteEscrita) {

            btnRelatorio.isEnabled = false
            btnRelatorio.isClickable = false

        } else {

            btnRelatorio.setOnClickListener {
                findViewById<RelativeLayout>(R.id.activity_detalhe_exame_loadingPanel).visibility = View.VISIBLE
                val intent = Intent(this, RelatorioActivity::class.java)
                intent.putExtra("exameModel", exameModel)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        findViewById<RelativeLayout>(R.id.activity_detalhe_exame_loadingPanel).visibility = View.INVISIBLE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}