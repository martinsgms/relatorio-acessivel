package br.com.martinsgms.relatorioacessivel.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.martinsgms.relatorioacessivel.R
import br.com.martinsgms.relatorioacessivel.service.BuscarServicosSaudeService
import br.com.martinsgms.relatorioacessivel.ui.adapter.BuscarServicosSaudeAdapter
import kotlinx.coroutines.runBlocking

class BuscarServicosSaudeActivity : AppCompatActivity(R.layout.activity_buscar_servicos_saude) {

    private val adapter = BuscarServicosSaudeAdapter(
        context = this
    )

    val service = BuscarServicosSaudeService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configuraBotaoReturn()
        configuraRecyclerView()
        configuraSwipeRefresh()
    }

    override fun onResume() {
        super.onResume()

        runBlocking {
            adapter.atualiza(service.getServicosSaude())
        }
    }

    private fun configuraRecyclerView() {
        val servicosRecyclerView = findViewById<RecyclerView>(R.id.activity_buscar_servicos_saude_rv)
        servicosRecyclerView.adapter = adapter
        registerForContextMenu(servicosRecyclerView)
    }

    private fun configuraBotaoReturn() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun configuraSwipeRefresh() {
        val swipeRefresh =
            findViewById<SwipeRefreshLayout>(R.id.activity_buscar_servicos_saude_swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            this.onResume()
            swipeRefresh.isRefreshing = false
        }
    }
}
