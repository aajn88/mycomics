package com.nextdots.mycomics.business.providers.sign_in.google.session_activities;

/**
 * Interface to signs out and disconnect from Google account
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 19/12/16
 */
public interface GoogleSignOut {

  /**
   * Signs out and disconnect from google account
   */
  void signOutGoogle();

  /**
   * Callback called when google sign out process has finished
   */
  interface GoogleSignOutCallback {

    /**
     * Called when google sign out process has succeeded
     */
    void onGoogleSignOutSuccess();

    /**
     * Called when google sign out process has failed
     *
     * @param t
     *         The exception that describe the error
     */
    void onGoogleSignOutFailure(Throwable t);
  }

}
