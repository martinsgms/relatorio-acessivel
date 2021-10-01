package br.com.martinsgms.relatorioacessivel.ui.activity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_buscar_servicos_saude_menu, menu)

        val menuItem = menu!!.findItem(R.id.activity_buscar_servicos_saude_search)
        val searchView = menuItem.actionView as SearchView

        searchView.queryHint = "Pesquise por cidade..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}
