package br.com.martinsgms.relatorioacessivel.ui.activity.data.model

/**class that captures user information for logged in users retrieved from LoginR
 * Data epository
 */
data class LoggedInUser(
    val userId: String,
    val displayName: String,
)