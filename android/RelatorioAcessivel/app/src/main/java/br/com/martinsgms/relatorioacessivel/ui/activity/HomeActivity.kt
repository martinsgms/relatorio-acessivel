package br.com.martinsgms.relatorioacessivel.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

        val cardExame = findViewById<CardView>(R.id.activity_home_card_exame_mais_recente)
        val servicoSaudeTextView = findViewById<TextView>(R.id.activity_home_servico_saude)
        val dataTextView = findViewById<TextView>(R.id.activity_home_data_exame)
        val statusTextView = findViewById<TextView>(R.id.activity_home_status_exame)
        val btnMeusExames = findViewById<Button>(R.id.activity_home_meus_exames)

        val context = this
        btnMeusExames.setOnClickListener {
            val intent = Intent(context, MeusExamesActivity::class.java)
            intent.putExtra("idUsuario", 1L)
            startActivity(intent)
        }

        runBlocking {

            val exameMaisRecenteModel = homeService.getExameMaisRecente()

            servicoSaudeTextView.text = exameMaisRecenteModel.servicoSaude.nome
            dataTextView.text = exameMaisRecenteModel.formatosDataHora.semanaDiaMesAnoExtenso
            statusTextView.text = exameMaisRecenteModel.status.descricao

            cardExame.setOnClickListener {
                val intent = Intent(context, DetalheExameActivity::class.java)
                intent.putExtra("exameModel", exameMaisRecenteModel)
                startActivity(intent)
            }

        }

    }
}