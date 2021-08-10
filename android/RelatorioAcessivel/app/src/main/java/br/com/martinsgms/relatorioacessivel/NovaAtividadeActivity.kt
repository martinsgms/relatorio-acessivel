package br.com.martinsgms.relatorioacessivel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class NovaAtividadeActivity : AppCompatActivity(R.layout.activity_nova_atividade) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}