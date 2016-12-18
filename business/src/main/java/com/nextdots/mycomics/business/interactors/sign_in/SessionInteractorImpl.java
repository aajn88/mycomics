package com.nextdots.mycomics.business.interactors.sign_in;

import android.support.annotation.NonNull;
import android.util.Log;

import com.nextdots.mycomics.business.R;
import com.nextdots.mycomics.business.exceptions.MyComicsException;
import com.nextdots.mycomics.business.providers.sign_in.SignInProvider;
import com.nextdots.mycomics.business.providers.sign_in.facebook.FacebookProvider;
import com.nextdots.mycomics.business.providers.sign_in.google.GoogleProvider;
import com.nextdots.mycomics.common.providers.Provider;
import com.nextdots.mycomics.common.session.User;

/**
 * Implementation of the {@link SessionInteractor} that performs the specific process for sign in
 * using providers
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
public class SessionInteractorImpl implements SessionInteractor, SignInProvider.SignInCallback {

  /** Tag logs **/
  private static final String TAG = SessionInteractorImpl.class.getSimpleName();

  /** Facebook provider instance **/
  private final FacebookProvider mFacebookProvider;

  /** Google provider instance **/
  private final GoogleProvider mGoogleProvider;

  /** Sign in callback **/
  private SignInCallback mSignInCallback;

  /**
   * Session interactor constructor
   *
   * @param facebookProvider
   *         Facebook interactor instance
   */
  public SessionInteractorImpl(@NonNull FacebookProvider facebookProvider,
                               @NonNull GoogleProvider googleProvider) {
    this.mFacebookProvider = facebookProvider;
    this.mGoogleProvider = googleProvider;
  }

  @Override
  public void signInUsingProvider(@NonNull Provider provider,
                                  @NonNull SignInCallback callback) {
    this.mSignInCallback = callback;
    SignInProvider signInProvider = getSignInProvider(provider);
    signInProvider.signIn(this);
  }

  /**
   * Returns the specific {@link SignInProvider} given the {@link Provider}
   *
   * @param provider
   *         Target provider
   *
   * @return {@link SignInProvider} instance
   */
  @NonNull
  private SignInProvider getSignInProvider(@NonNull Provider provider) {
    switch (provider) {
      default:
      case FACEBOOK:
        return mFacebookProvider;
      case GOOGLE:
        return mGoogleProvider;
    }
  }

  @Override
  public void signInSuccess(User user) {
    Log.i(TAG, "Sign in success");
    Log.d(TAG, "User information: " + user);
    // TODO: Persist user info
    mSignInCallback.onSignInSuccess();
  }

  @Override
  public void signInFailure(Throwable t) {
    mSignInCallback.onSignInFailure(
            new MyComicsException(t.getMessage(), R.string.error_signing_in));
  }
}
