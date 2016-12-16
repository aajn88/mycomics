package com.nextdots.mycomics.business.interactors.sign_in;

import android.support.annotation.NonNull;

import com.nextdots.mycomics.business.providers.Provider;
import com.nextdots.mycomics.business.providers.sign_in.facebook.FacebookProvider;

/**
 * Implementation of the {@link SessionInteractor} that performs the specific process for sign in
 * using providers
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
public class SessionInteractorImpl implements SessionInteractor {

  /** Facebook provider instance **/
  private final FacebookProvider mFacebookProvider;

  /** Sign in callback **/
  private SignInCallback mSignInCallback;

  /**
   * Session interactor constructor
   *
   * @param facebookProvider
   *         Facebook interactor instance
   */
  public SessionInteractorImpl(@NonNull FacebookProvider facebookProvider) {
    this.mFacebookProvider = facebookProvider;
  }

  @Override
  public void signInUsingProvider(@NonNull Provider provider,
                                  @NonNull SignInCallback callback) {
    this.mSignInCallback = callback;
  }
}
