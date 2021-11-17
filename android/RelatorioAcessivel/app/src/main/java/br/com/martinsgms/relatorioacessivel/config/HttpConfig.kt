package br.com.martinsgms.relatorioacessivel.config

import android.util.Log
import com.github.kittinunf.fuel.core.FuelManager

class HttpConfig {

    companion object Factory {
        var token = ""
        var userId = 0L

        // local
        //var BASE_PATH = "http://172.18.41.49"

        // aws
        var BASE_PATH = "http://34.236.191.183:31255"

        var BASE_HEADERS = mapOf("Content-Type" to "application/json")

        fun config() {

            BASE_HEADERS = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer $token")
            Log.d("headddd", userId.toString())
            FuelManager.instance.basePath = BASE_PATH
            FuelManager.instance.baseHeaders = BASE_HEADERS
        }

        fun addTokenAndUserId(token: String, userId: Long) {

            this.token = token
            this.userId = userId
        }
    }
}