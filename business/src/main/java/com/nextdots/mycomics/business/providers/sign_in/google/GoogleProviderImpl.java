package com.nextdots.mycomics.business.providers.sign_in.google;

import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.nextdots.mycomics.common.providers.Provider;
import com.nextdots.mycomics.common.model.session.SessionToken;
import com.nextdots.mycomics.common.model.session.User;

/**
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 17/12/16
 */

public class GoogleProviderImpl implements GoogleProvider, GoogleAccess.GoogleAccessCallback {

  /** Application context **/
  private final Context mContext;

  /** Sign in callback **/
  private SignInCallback mSignInCallback;

  /**
   * Facebook provider constructor
   *
   * @param context
   *         Application context
   */
  public GoogleProviderImpl(Context context) {
    this.mContext = context;
  }

  @Override
  public void signIn(SignInCallback callback) {
    this.mSignInCallback = callback;
    GoogleSignInActivity.startAndSignIn(mContext, this);
  }

  @Override
  public void onGoogleAccessSuccess(GoogleSignInAccount profile) {
    mSignInCallback.signInSuccess(parseUser(profile));
  }

  /**
   * Parses the given profile into a user
   *
   * @param profile
   *         Target profile
   *
   * @return A user instance with all information
   */
  private User parseUser(GoogleSignInAccount profile) {
    String pictureUrl = profile.getPhotoUrl() == null ? null : profile.getPhotoUrl().toString();
    return new User().
            setFirstName(profile.getGivenName())
            .setLastName(profile.getFamilyName())
            .setEmail(profile.getEmail())
            .setPicture(pictureUrl)
            .setSessionProvider(Provider.GOOGLE)
            .setSessionToken(new SessionToken(profile.getId(), profile.getServerAuthCode(), null));
  }

  @Override
  public void onGoogleAccessFailure(Throwable t) {
    mSignInCallback.signInFailure(t);
  }
}
