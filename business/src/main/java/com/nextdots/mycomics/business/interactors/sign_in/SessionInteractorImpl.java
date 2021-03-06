package com.nextdots.mycomics.business.interactors.sign_in;

import android.support.annotation.NonNull;
import android.util.Log;

import com.nextdots.mycomics.business.R;
import com.nextdots.mycomics.business.exceptions.MyComicsException;
import com.nextdots.mycomics.business.providers.sign_in.SessionProvider;
import com.nextdots.mycomics.business.providers.sign_in.facebook.FacebookProvider;
import com.nextdots.mycomics.business.providers.sign_in.google.GoogleProvider;
import com.nextdots.mycomics.common.model.session.User;
import com.nextdots.mycomics.common.providers.Provider;
import com.nextdots.mycomics.persistence.managers.session.UsersManager;

/**
 * Implementation of the {@link SessionInteractor} that performs the specific process for sign in
 * using providers
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
public class SessionInteractorImpl implements SessionInteractor, SessionProvider.SignInCallback,
        SessionProvider.SignOutCallback {

  /** Tag logs **/
  private static final String TAG = SessionInteractorImpl.class.getSimpleName();

  /** Facebook provider instance **/
  private final FacebookProvider mFacebookProvider;

  /** Google provider instance **/
  private final GoogleProvider mGoogleProvider;

  /** Users manager **/
  private final UsersManager mUsersManager;

  /** Sign in callback **/
  private SignInCallback mSignInCallback;

  /** Sign out callback **/
  private SignOutCallback mSignOutCallback;

  /** Current session **/
  private User mCurrentSession;

  /**
   * Session interactor constructor
   *
   * @param facebookProvider
   *         Facebook interactor instance
   */
  public SessionInteractorImpl(@NonNull FacebookProvider facebookProvider,
                               @NonNull GoogleProvider googleProvider,
                               @NonNull UsersManager usersManager) {
    this.mFacebookProvider = facebookProvider;
    this.mGoogleProvider = googleProvider;
    this.mUsersManager = usersManager;
  }

  @Override
  public void signInUsingProvider(@NonNull Provider provider,
                                  @NonNull SignInCallback callback) {
    this.mSignInCallback = callback;
    SessionProvider sessionProvider = getSessionProvider(provider);
    sessionProvider.signIn(this);
  }

  @Override
  public User getCurrentSession() {
    if (mCurrentSession == null) {
      mCurrentSession = mUsersManager.findFirstUser();
    }
    return mCurrentSession;
  }

  @Override
  public boolean isUserLogged() {
    return getCurrentSession() != null;
  }

  @Override
  public void signOutUser(@NonNull SignOutCallback callback) {
    this.mSignOutCallback = callback;
    signOutFromProvider();
  }

  /**
   * Logs out from {@link SessionProvider}
   */
  private void signOutFromProvider() {
    SessionProvider sessionProvider = getSessionProvider(mCurrentSession.getSessionProvider());
    sessionProvider.signOut(this);
  }

  /**
   * Returns the specific {@link SessionProvider} given the {@link Provider}
   *
   * @param provider
   *         Target provider
   *
   * @return {@link SessionProvider} instance
   */
  @NonNull
  private SessionProvider getSessionProvider(@NonNull Provider provider) {
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
    persistSession(user);
    mSignInCallback.onSignInSuccess();
  }

  /**
   * Persists the session of the given user
   *
   * @param user
   *         User to be persisted
   */
  private void persistSession(User user) {
    mUsersManager.deleteAll();
    mUsersManager.createOrUpdate(user);
    mCurrentSession = user;
  }

  @Override
  public void signInFailure(Throwable t) {
    mSignInCallback.onSignInFailure(
            new MyComicsException(t.getMessage(), R.string.error_signing_in));
  }

  @Override
  public void signOutSuccess() {
    mUsersManager.deleteAll();
    mCurrentSession = null;
    mSignOutCallback.onSignOutSuccess();
  }

  @Override
  public void signOutFailure(Throwable t) {
    mSignOutCallback.onSignOutFailure(
            new MyComicsException(t.getMessage(), R.string.error_signing_out));
  }
}
