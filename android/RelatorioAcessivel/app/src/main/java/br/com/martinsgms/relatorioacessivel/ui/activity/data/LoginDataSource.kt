package br.com.martinsgms.relatorioacessivel.ui.activity.data

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import br.com.martinsgms.relatorioacessivel.R
import br.com.martinsgms.relatorioacessivel.config.HttpConfig
import br.com.martinsgms.relatorioacessivel.dto.TokenDTO
import br.com.martinsgms.relatorioacessivel.model.UsuarioModel
import br.com.martinsgms.relatorioacessivel.service.LoginService
import kotlinx.coroutines.runBlocking

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    private var loginService = LoginService()

    fun login(username: String, password: String): Result<TokenDTO>? {
        try {

            val usuarioModel = UsuarioModel(email = username, senha = password, id = null, nome = null)

            var tokenJWT = TokenDTO(-1, "", "")

            runBlocking {
                tokenJWT = loginService.authenticate(usuarioModel)
            }

            Log.d("token", tokenJWT.token)


            HttpConfig.addTokenAndUserId(tokenJWT.token, tokenJWT.userId)

            return Result.Success(tokenJWT)

        } catch (e: Throwable) {
            return null
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}