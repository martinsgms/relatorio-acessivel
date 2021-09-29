package br.com.martinsgms.relatorioacessivel.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.martinsgms.relatorioacessivel.R
import br.com.martinsgms.relatorioacessivel.service.MeusExamesService
import br.com.martinsgms.relatorioacessivel.ui.adapter.MeusExamesAdapter
import kotlinx.coroutines.runBlocking

class MeusExamesActivity : AppCompatActivity(R.layout.activity_meus_exames) {

    private var idUsuario: Long = -1

    private val adapter = MeusExamesAdapter(
        context = this
    )

    val meusExamesService = MeusExamesService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configuraBotaoReturn()
        configuraRecyclerView()
        configuraSwipeRefresh()

        this.idUsuario = intent.getLongExtra("idUsuario", -1)


    }

    override fun onResume() {
        super.onResume()

        val self = this
        runBlocking {
            adapter.atualiza(meusExamesService.getExamesDoUsuario(self.idUsuario))
        }
    }

    private fun configuraRecyclerView() {
        val examesRecyclerView = findViewById<RecyclerView>(R.id.activity_meus_exames_rv)
        examesRecyclerView.adapter = adapter
        registerForContextMenu(examesRecyclerView)
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
        val swipeRefresh = findViewById<SwipeRefreshLayout>(R.id.activity_meus_exames_swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            this.onResume()
            swipeRefresh.isRefreshing = false
        }
    }
}
