package br.com.martinsgms.relatorioacessivel.ui.activity.data

import android.util.Log
import br.com.martinsgms.relatorioacessivel.config.HttpConfig
import br.com.martinsgms.relatorioacessivel.model.UsuarioModel
import br.com.martinsgms.relatorioacessivel.service.LoginService
import br.com.martinsgms.relatorioacessivel.service.TokenDTO
import kotlinx.coroutines.runBlocking
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    private var loginService = LoginService()

    fun login(username: String, password: String): Result<TokenDTO>? {
        try {

            val usuarioModel = UsuarioModel(email = username, senha = password)

            var tokenJWT =  TokenDTO("", "")

            runBlocking {
                tokenJWT = loginService.authenticate(usuarioModel)
            }

            Log.d("token", tokenJWT.token)
            HttpConfig.addToken(tokenJWT.token)

            return Result.Success(tokenJWT)

        } catch (e: Throwable) {
            return null
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}