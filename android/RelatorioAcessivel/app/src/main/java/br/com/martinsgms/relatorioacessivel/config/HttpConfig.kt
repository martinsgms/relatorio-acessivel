package br.com.martinsgms.relatorioacessivel.config

import android.content.Context
import android.util.Log
import com.github.kittinunf.fuel.core.FuelManager


class HttpConfig {

    companion object Factory {
        var token = ""
        var userId = 0L
        var BASE_PATH_LOCAL = "http://172.18.41.49"
        var BASE_PATH_CLOUD = "http://ec2-44-195-80-58.compute-1.amazonaws.com:32168"

        var BASE_HEADERS = mapOf("Content-Type" to "application/json")

        fun config() {

            BASE_HEADERS = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer $token")
            Log.d("headddd", userId.toString())
            FuelManager.instance.basePath = BASE_PATH_LOCAL
            FuelManager.instance.baseHeaders = BASE_HEADERS
        }

        fun addTokenAndUserId(token: String, userId: Long) {

            this.token = token
            this.userId = userId
        }
    }
}