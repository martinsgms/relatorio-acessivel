package br.com.martinsgms.relatorioacessivel.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.martinsgms.relatorioacessivel.R
import br.com.martinsgms.relatorioacessivel.ui.adapter.RelatorioAdapter
import br.com.martinsgms.relatorioacessivel.ui.dao.RelatorioDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RelatorioActivity : AppCompatActivity(R.layout.activity_relatorio) {

    private val relatorioDAO = RelatorioDAO()
    private val adapter = RelatorioAdapter(context = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configuraRecyclerView()
        configuraBotaoReturn()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(relatorioDAO.findAll())
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
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}