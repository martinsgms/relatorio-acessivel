package br.com.martinsgms.relatorioacessivel.config

import android.util.Log
import br.com.martinsgms.relatorioacessivel.ui.activity.data.LoginRepository
import com.github.kittinunf.fuel.core.FuelManager

class HttpConfig {

    companion object Factory {
        var token = ""
        var BASE_PATH_LOCAL = "http://192.168.107.81"
        var BASE_PATH_CLOUD = "http://ec2-44-195-80-58.compute-1.amazonaws.com:32168"

        var BASE_HEADERS = mapOf("Content-Type" to "application/json")

        fun config() {
            BASE_HEADERS = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer $token")
            Log.d("headddd", token)
            FuelManager.instance.basePath = BASE_PATH_LOCAL
            FuelManager.instance.baseHeaders = BASE_HEADERS
        }

        fun addToken(token: String) {
            this.token = token
        }
    }
}