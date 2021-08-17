package br.com.martinsgms.relatorioacessivel.ui.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import br.com.martinsgms.relatorioacessivel.R
import br.com.martinsgms.relatorioacessivel.model.AtividadeModel
import br.com.martinsgms.relatorioacessivel.ui.dao.RelatorioDAO
import br.com.martinsgms.relatorioacessivel.ui.fragment.TimePickerFragment
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*


class NovaAtividadeActivity : AppCompatActivity(R.layout.activity_nova_atividade) {


    private var atividadeEditText: TextInputLayout? = null
    private var sintomaEditText: TextInputLayout? = null
    private var medicamentosEditText: TextInputLayout? = null
    private var horaEditText: EditText? = null
    private var btnRegistrar: Button? = null

    private val timePicker = TimePickerFragment()
    private val relatorioDAO = RelatorioDAO()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        atividadeEditText = findViewById(R.id.atividade)
        sintomaEditText = findViewById(R.id.sintoma)
        medicamentosEditText = findViewById(R.id.medicamento)
        horaEditText = findViewById(R.id.hora)
        btnRegistrar = findViewById(R.id.activity_nova_atividade_registrar)

        configuraBotaoReturn()
        configuraTimePicker(savedInstanceState)

        val editAtividade = intent.getParcelableExtra<AtividadeModel>("atividade")
        configuraSwitch(editAtividade)

        btnRegistrar?.setOnClickListener {
            relatorioDAO.save(criaAtividade())
            finish()
        }

        if (editAtividade != null) {
            atividadeEditText?.editText?.setText(editAtividade.atividade)
            sintomaEditText?.editText?.setText(editAtividade.sintomas)
            medicamentosEditText?.editText?.setText(editAtividade.medicamentos)

            supportActionBar?.title = "Editar atividade"
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun criaAtividade() : AtividadeModel {
        val switch = findViewById<SwitchMaterial>(R.id.switch_horario)

        val atividade = atividadeEditText?.editText?.text.toString()
        val sintoma = sintomaEditText?.editText?.text.toString()
        val medicamento = medicamentosEditText?.editText?.text.toString()

        var hora = ""
        if (switch.isChecked) {
            val date = Calendar.getInstance().time
            val formatter = SimpleDateFormat("HH:mm") //or use getDateInstance()
            hora = formatter.format(date)

        } else {
            hora = horaEditText?.text.toString()

            //tratar hora em ponto ex 8:00
            if (hora.split(":")[1].length == 1)
                hora = hora.replace(":", ":0")

            if (hora.split(":")[0].length == 1)
                hora = "0".plus(hora)
        }

        Log.d("AA", "${LocalTime.parse(hora)} $atividade $sintoma $medicamento")
        return AtividadeModel(LocalTime.parse(hora), atividade, sintoma, medicamento)
    }

    private fun configuraSwitch(editAtividade: AtividadeModel?) {
        val switch = findViewById<SwitchMaterial>(R.id.switch_horario)

        switch.setOnClickListener {
            changeTimePickerVisibility(switch.isChecked)
        }

        if (editAtividade != null) {
            switch.isChecked = false
            return
        }

        changeTimePickerVisibility(switch.isChecked)
    }

    private fun configuraTimePicker(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.tp_aqui, timePicker, "a")
            }
        }
    }

    private fun configuraBotaoReturn() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun changeTimePickerVisibility(isChecked: Boolean) {
        val visible = if (isChecked) View.GONE else View.VISIBLE
        findViewById<FragmentContainerView>(R.id.tp_aqui).visibility = visible
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun onFragmentViewCreated(view: View) {
        val editAtividade = intent.getParcelableExtra<AtividadeModel>("atividade") ?: return

        val horaFrag = findViewById<EditText>(R.id.hora_fragment)
        horaFrag.setText(editAtividade.hora.toString())
    }
}