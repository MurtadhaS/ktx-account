package co.windly.ktxaccount.runtime.scheme

import android.accounts.Account
import android.content.Context

/**
 * This scheme assumes MULTIPLE accounts for given authenticator can exist.
 *
 * Client code should take all necessary effort that is required to switch between
 * accounts to establish the proper flow of all reactive subjects.
 */
abstract class MultipleAccountScheme(context: Context) : BaseAccountScheme(context) {

  //region Account

  /**
   * Retrieves an account associated with provided authenticator and identified by
   * given name.
   *
   * @exception IllegalArgumentException when account identified by given name does
   * not exist.
   */
  protected fun retrieveAccount(name: String): Account {

    // Retrieve account or an exception if account does not exist.
    return manager
      .getAccountsByType(provideAuthenticator())
      .firstOrNull { it.name == name }
      ?: throw IllegalArgumentException("Account does not exist.")
  }

  /**
   * Retrieves an account associated with provided authenticator and identified by
   * given name or null if the before mentioned account does not exist.
   */
  protected fun retrieveNullableAccount(name: String): Account? {

    // Retrieve account or an exception if account does not exist.
    return manager
      .getAccountsByType(provideAuthenticator())
      .firstOrNull { it.name == name }
  }

  //endregion
}