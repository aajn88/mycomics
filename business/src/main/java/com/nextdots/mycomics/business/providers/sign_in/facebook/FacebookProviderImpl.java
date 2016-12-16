package com.nextdots.mycomics.business.providers.sign_in.facebook;

import android.content.Context;

import com.facebook.AccessToken;
import com.facebook.Profile;

/**
 * Implementation of the {@link FacebookProvider} to perform specific actions such as sign in
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
public class FacebookProviderImpl implements FacebookProvider,
        FacebookAccess.FacebookAccessCallback {

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
  public FacebookProviderImpl(Context context) {
    this.mContext = context;
  }

  @Override
  public void signIn(SignInCallback callback) {
    this.mSignInCallback = callback;
    FacebookSignInActivity.startAndSignIn(mContext, this);
  }

  @Override
  public void onFacebookAccessSuccess(Profile profile, AccessToken accessToken) {
    // TODO: Parse profile and access token into User
  }

  @Override
  public void onFacebookAccessCanceled() {

  }

  @Override
  public void onFacebookAccessFailure(Throwable e) {

  }
}
