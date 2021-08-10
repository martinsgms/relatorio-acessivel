package br.com.martinsgms.relatorioacessivel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Switch
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import br.com.martinsgms.relatorioacessivel.databinding.ActivityNovaAtividadeBinding
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class NovaAtividadeActivity : AppCompatActivity(R.layout.activity_nova_atividade) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.tp_aqui, TimePickerFragment(),"a")
            }
        }

        val switch = findViewById<SwitchMaterial>(R.id.switch_horario)
        switch.setOnClickListener {
            changeTimePickerVisibility(switch.isChecked)
        }

        changeTimePickerVisibility(switch.isChecked)
    }

    private fun changeTimePickerVisibility(isChecked : Boolean) {
        val visible = if(isChecked) View.GONE else View.VISIBLE
        findViewById<FragmentContainerView>(R.id.tp_aqui).visibility = visible
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}