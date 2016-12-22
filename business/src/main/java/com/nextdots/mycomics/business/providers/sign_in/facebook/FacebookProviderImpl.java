package com.nextdots.mycomics.business.providers.sign_in.facebook;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.nextdots.mycomics.common.model.session.SessionToken;
import com.nextdots.mycomics.common.model.session.User;
import com.nextdots.mycomics.common.providers.Provider;

/**
 * Implementation of the {@link FacebookProvider} to perform specific actions such as sign in
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
public class FacebookProviderImpl implements FacebookProvider,
        FacebookAccess.FacebookAccessCallback {

  /** Tag for logs **/
  private static final String TAG = FacebookProviderImpl.class.getSimpleName();

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
  public void signIn(@NonNull SignInCallback callback) {
    Log.i(TAG, "Facebook sign in provider called");
    this.mSignInCallback = callback;
    FacebookSignInActivity.startAndSignIn(mContext, this);
  }

  @Override
  public void signOut(@NonNull SignOutCallback callback) {
    Log.i(TAG, "Facebook sign out provider called");
    LoginManager.getInstance().logOut();
    callback.signOutSuccess();
  }

  @Override
  public void onFacebookAccessSuccess(Profile profile, String email, AccessToken accessToken) {
    int dimensionPixelSize = mContext.getResources()
            .getDimensionPixelSize(
                    com.facebook.R.dimen.com_facebook_profilepictureview_preset_size_large);
    Uri pictureUri = profile.getProfilePictureUri(dimensionPixelSize, dimensionPixelSize);
    User user = new User().
            setFirstName(profile.getFirstName())
            .setLastName(profile.getLastName())
            .setEmail(email)
            .setPicture(pictureUri.toString())
            .setSessionProvider(Provider.FACEBOOK)
            .setSessionToken(new SessionToken(profile.getId(), accessToken.getToken(),
                    accessToken.getExpires()));
    mSignInCallback.signInSuccess(user);
  }

  @Override
  public void onFacebookAccessCancelled() {
    mSignInCallback.signInFailure(new Throwable("Facebook sign in was cancelled"));
  }

  @Override
  public void onFacebookAccessFailure(Throwable t) {
    mSignInCallback.signInFailure(t);
  }
}
