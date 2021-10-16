package br.com.martinsgms.relatorioacessivel.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.martinsgms.relatorioacessivel.R
import br.com.martinsgms.relatorioacessivel.model.UsuarioModel
import br.com.martinsgms.relatorioacessivel.service.HomeService
import kotlinx.coroutines.runBlocking

class HomeActivity : AppCompatActivity(R.layout.activity_home) {

    var homeService = HomeService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configuraSwipeRefresh()
        configuraBtnMeusExames()
        configuraBtnMeusDados()

        val btnBuscarServicosSaude = findViewById<Button>(R.id.activity_home_buscar_servicos)
        val context = this
        btnBuscarServicosSaude.setOnClickListener {
            val i = Intent(context, BuscarServicosSaudeActivity::class.java)
            startActivity(i)
        }
    }

    private fun configuraBtnMeusDados() {
        val usuario = UsuarioModel("Gabriel", "gmartins@gmail.com")

        val btnMeusDados = findViewById<Button>(R.id.activity_home_meus_dados)
        val context = this
        btnMeusDados.setOnClickListener {
            val intent = Intent(context, MeusDadosActivity::class.java)
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        }
    }

    private fun configuraBtnMeusExames() {
        val btnMeusExames = findViewById<Button>(R.id.activity_home_meus_exames)
        val context = this
        btnMeusExames.setOnClickListener {
            val intent = Intent(context, MeusExamesActivity::class.java)
            intent.putExtra("idUsuario", 1L)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        val cardExame = findViewById<CardView>(R.id.activity_home_card_exame_mais_recente)
        val servicoSaudeTextView = findViewById<TextView>(R.id.activity_home_servico_saude)
        val dataTextView = findViewById<TextView>(R.id.activity_home_data_exame)
        val statusTextView = findViewById<TextView>(R.id.activity_home_status_exame)

        val self = this
        runBlocking {

            val exameMaisRecenteModel = homeService.getExameMaisRecente()

            servicoSaudeTextView.text = exameMaisRecenteModel.servicoSaude.nome
            dataTextView.text = exameMaisRecenteModel.formatosDataHora.semanaDiaMesAnoExtenso
            statusTextView.text = exameMaisRecenteModel.status.descricao

            cardExame.setOnClickListener {
                val intent = Intent(self, DetalheExameActivity::class.java)
                intent.putExtra("exameModel", exameMaisRecenteModel)
                startActivity(intent)
            }
        }
    }

    private fun configuraSwipeRefresh() {
        val swipeRefresh = findViewById<SwipeRefreshLayout>(R.id.activity_home_swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            this.onResume()
            swipeRefresh.isRefreshing = false
        }
    }
}