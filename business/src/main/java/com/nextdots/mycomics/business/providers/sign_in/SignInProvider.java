package com.nextdots.mycomics.business.providers.sign_in;

import com.nextdots.mycomics.common.session.User;

/**
 * Contract for sign in providers. This interface should be implemented in order implement a sign
 * in provider
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
public interface SignInProvider {

  /**
   * Starts sign in process given by the provider
   *
   * @param callback
   *         Callback to return the resulting process
   */
  void signIn(SignInCallback callback);

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

}
