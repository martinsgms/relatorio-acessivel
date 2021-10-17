package br.com.martinsgms.relatorioacessivel.ui.activity.data

import br.com.martinsgms.relatorioacessivel.dto.TokenDTO


/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var token: TokenDTO? = null
        private set

    val isLoggedIn: Boolean
        get() = token != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        token = null
    }

    fun logout() {
        token = null
        dataSource.logout()
    }

    fun login(username: String, password: String): Result<TokenDTO> {
        // handle login
        val result = dataSource.login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result!!
    }

    private fun setLoggedInUser(loggedInUser: TokenDTO) {
        this.token = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}