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
import br.com.martinsgms.relatorioacessivel.R
import br.com.martinsgms.relatorioacessivel.model.AtividadeModel
import br.com.martinsgms.relatorioacessivel.ui.adapter.RelatorioAdapter
import br.com.martinsgms.relatorioacessivel.ui.dao.RelatorioDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalTime

class RelatorioActivity : AppCompatActivity(R.layout.activity_relatorio),
    RelatorioAdapter.OnClickAtividadeListener, RelatorioAdapter.OnLongClickAtividadeListener {

    private var atividadePressionada: AtividadeModel ?= null
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

        relatorioDAO.save(AtividadeModel(LocalTime.now(), "café", "dor de cabeça", "dipirona"))
        relatorioDAO.save(AtividadeModel(LocalTime.now(), "almoco", "enjoo", "paracetamol"))
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(relatorioDAO.findAll())
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

    override fun OnClickAtividadeListener(atividade: AtividadeModel) {
        Log.d("gmds", "${atividade.toString()}")

        val intent = Intent(this, NovaAtividadeActivity::class.java)
        intent.putExtra("atividade", atividade)

        startActivity(intent)
    }

    override fun OnLongClickAtividadeListener(atividade: AtividadeModel): Boolean {
        Log.d("long-click", "lg click - ${atividade.toString()}")
        this.atividadePressionada = atividade

        return false
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        Log.d("remover", "rm - $atividadePressionada")

        return super.onContextItemSelected(item)
    }
}