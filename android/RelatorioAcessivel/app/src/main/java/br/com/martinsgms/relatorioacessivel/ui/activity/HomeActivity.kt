package br.com.martinsgms.relatorioacessivel.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import br.com.martinsgms.relatorioacessivel.R
import br.com.martinsgms.relatorioacessivel.model.ExameModel
import br.com.martinsgms.relatorioacessivel.service.HomeService
import kotlinx.coroutines.runBlocking

class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    var homeService = HomeService()
    var exameMaisRecenteModel = ExameModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cardExame = findViewById<CardView>(R.id.card_exame_mais_recente)
        val clinicaDisplay = findViewById<TextView>(R.id.activity_home_clinica)
        val dataTextView = findViewById<TextView>(R.id.activity_home_data_exame)
        val statusTextView = findViewById<TextView>(R.id.activity_home_status_exame)

        val context = this

        runBlocking {

            val exameMaisRecenteModel = homeService.getExameMaisRecente()

            //clinicaDisplay.text = exameMaisRecente!!.servicoSaude
            dataTextView.text = exameMaisRecenteModel.dataHoraFormatada
            statusTextView.text = exameMaisRecenteModel.status!!.descricao

            cardExame.setOnClickListener {
                val intent = Intent(context, DetalheExameActivity::class.java)
                intent.putExtra("exameModel", exameMaisRecenteModel)
                startActivity(intent)
            }

        }

    }
}