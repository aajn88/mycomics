package com.nextdots.mycomics.business.providers.sign_in;

import android.support.annotation.NonNull;

import com.nextdots.mycomics.common.model.session.User;

/**
 * Contract for sign in providers. This interface should be implemented in order implement a sign
 * in provider
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
public interface SessionProvider {

  /**
   * Starts sign in process given by the provider
   *
   * @param callback
   *         Callback to return the resulting process
   */
  void signIn(@NonNull SignInCallback callback);

  /**
   * Signs out the current session
   *
   * @param callback
   *         Callback used to return the result
   */
  void signOut(@NonNull SignOutCallback callback);

  /**
   * Callback to return the sign in process result or to ask credentials (if necessary) such as
   * username and password
   */
  interface SignInCallback {

    /**
     * Called when sign in process succeeded. It sends the resulting user which contains his basic
     * information and the session token information
     *
     * @param user
     *         Resulting user, containing all user's basic information and the session token
     */
    void signInSuccess(User user);

    /**
     * Called when sign in process has failed
     *
     * @param t
     *         Throwable thrown by the provider
     */
    void signInFailure(Throwable t);

  }

  /**
   * Callback used to return the sign out process result
   */
  interface SignOutCallback {

    /**
     * Called when sign out process has succeeded
     */
    void signOutSuccess();

    /**
     * Called when sign out process has failed
     *
     * @param t
     *         Throwable thrown by the provider
     */
    void signOutFailure(Throwable t);

  }

}
