package br.com.martinsgms.relatorioacessivel.config

import com.github.kittinunf.fuel.core.FuelManager

class HttpConfig {

    companion object Factory {
        var BASE_PATH_LOCAL = "http://192.168.102.129"
        var BASE_PATH_CLOUD = "http://ec2-44-195-80-58.compute-1.amazonaws.com:32168"

        var BASE_HEADERS = mapOf("Content-Type" to "application/json")

        fun config() {
            FuelManager.instance.basePath = BASE_PATH_LOCAL
            FuelManager.instance.baseHeaders = BASE_HEADERS
        }
    }
}