package com.nextdots.mycomics.business.interactors.sign_in;

import android.support.annotation.NonNull;

import com.nextdots.mycomics.business.exceptions.MyComicsException;
import com.nextdots.mycomics.common.providers.Provider;

/**
 * Session interactor that manages all session information and login with corresponding providers
 * such as Facebook and Google
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
public interface SessionInteractor {

  /**
   * Signs in a user using an specific provider. The result of this operation will be returned
   * using the passed callback
   *
   * @param provider
   *         Provider to be used for sign in process
   * @param callback
   *         Callback to be used for the returning result
   */
  void signInUsingProvider(@NonNull Provider provider, @NonNull SignInCallback callback);

  /**
   * Callback used to return the result of the sign in operation
   */
  interface SignInCallback {

    /**
     * Called if sign in process has succeeded
     */
    void onSignInSuccess();

    /**
     * Called if sign in process has failed
     *
     * @param e
     *         Exception with information about the failure and a message to the user
     */
    void onSignInFailure(MyComicsException e);
  }

}
