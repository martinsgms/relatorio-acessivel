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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configuraBotaoReturn()

        configuraTimePicker(savedInstanceState)

        configuraSwitch()

        val relatorioDAO = RelatorioDAO()
        val btnRegistrar = findViewById<Button>(R.id.activity_nova_atividade_registrar)
        btnRegistrar.setOnClickListener {
            relatorioDAO.save(criaAtividade())
            finish()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun criaAtividade() : AtividadeModel {
        val switch = findViewById<SwitchMaterial>(R.id.switch_horario)

        val atividadeEditText = findViewById<TextInputLayout>(R.id.atividade)
        val atividade = atividadeEditText.editText?.text.toString()

        val sintomaEditText = findViewById<TextInputLayout>(R.id.sintoma)
        val sintoma = sintomaEditText.editText?.text.toString()

        val medicamentosEditText = findViewById<TextInputLayout>(R.id.medicamento)
        val medicamento = medicamentosEditText.editText?.text.toString()

        var hora = ""
        if (switch.isChecked) {
            val date = Calendar.getInstance().time
            val formatter = SimpleDateFormat("HH:mm") //or use getDateInstance()
            hora = formatter.format(date)

        } else {
            val horaEditText = findViewById<EditText>(R.id.hora)
            hora = horaEditText.text.toString()

            if (hora.split(":")[1].length == 1)
                hora = hora.replace(":", ":0")

            if (hora.split(":")[0].length == 1)
                hora = "0".plus(hora)
        }

        Log.d("AA", "${LocalTime.parse(hora)} $atividade $sintoma $medicamento")
        return AtividadeModel(LocalTime.parse(hora), atividade, sintoma, medicamento)
    }

    private fun configuraSwitch() {
        val switch = findViewById<SwitchMaterial>(R.id.switch_horario)
        switch.setOnClickListener {
            changeTimePickerVisibility(switch.isChecked)
        }

        changeTimePickerVisibility(switch.isChecked)
    }

    private fun configuraTimePicker(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.tp_aqui, TimePickerFragment(), "a")
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
}