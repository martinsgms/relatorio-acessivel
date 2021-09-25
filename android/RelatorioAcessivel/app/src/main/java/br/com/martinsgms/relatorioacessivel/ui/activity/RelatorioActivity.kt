package br.com.martinsgms.relatorioacessivel.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.martinsgms.relatorioacessivel.R
import br.com.martinsgms.relatorioacessivel.model.EventoModel
import br.com.martinsgms.relatorioacessivel.model.ExameModel
import br.com.martinsgms.relatorioacessivel.service.RelatorioService
import br.com.martinsgms.relatorioacessivel.ui.adapter.RelatorioAdapter
import br.com.martinsgms.relatorioacessivel.ui.dao.RelatorioDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.runBlocking

class RelatorioActivity : AppCompatActivity(R.layout.activity_relatorio),
    RelatorioAdapter.OnClickAtividadeListener, RelatorioAdapter.OnLongClickAtividadeListener {

    private var relatorioService = RelatorioService()
    private var exameModel: ExameModel ?= null
    private var eventoPressionado: EventoModel ?= null
    private val relatorioDAO = RelatorioDAO()
    private val adapter = RelatorioAdapter(
        context = this,
        onClickAtividadeListener = this,
        onLongClickAtividadeListener = this
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configuraRecyclerView()
        configuraBotaoReturn()
        configuraFab()
        configuraSwipeRefresh()

        this.exameModel = intent.getParcelableExtra("exameModel")
        Log.d("ExameModel-->", exameModel.toString())
    }

    private fun configuraSwipeRefresh() {
        val swipeRefresh = findViewById<SwipeRefreshLayout>(R.id.activity_relatorio_swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            this.onResume()
            swipeRefresh.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("atualizou", "atualizou")
        runBlocking {
            adapter.atualiza(relatorioService.getEventos(exameModel!!.id))
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        menuInflater.inflate(R.menu.activity_relatorio_menu, menu)
    }

    private fun configuraFab() {
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        fab.setOnClickListener {
            val intent = Intent(this, NovaAtividadeActivity::class.java)

            intent.putExtra("idExame", this.exameModel!!.id)

            startActivity(intent)
        }
    }

    private fun configuraBotaoReturn() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun configuraRecyclerView() {
        val relatorioRecyclerView = findViewById<RecyclerView>(R.id.activity_relatorio_rv)
        relatorioRecyclerView.adapter = adapter
        registerForContextMenu(relatorioRecyclerView)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun OnClickAtividadeListener(evento: EventoModel) {
        Log.d("gmds", "${evento.toString()}")

        val intent = Intent(this, NovaAtividadeActivity::class.java)
        intent.putExtra("idExame", evento.idExame)
        intent.putExtra("evento", evento)

        startActivity(intent)
    }

    override fun OnLongClickAtividadeListener(evento: EventoModel): Boolean {
        Log.d("long-click", "lg click - ${evento.toString()}")
        this.eventoPressionado = evento

        return false
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        Log.d("remover", "rm - $eventoPressionado")

        runBlocking {
            relatorioService.removeEvento(eventoPressionado?.id!!)
        }

        this.onResume()

        return super.onContextItemSelected(item)
    }
}