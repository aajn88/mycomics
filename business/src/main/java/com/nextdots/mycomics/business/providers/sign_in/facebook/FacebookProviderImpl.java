package com.nextdots.mycomics.business.providers.sign_in.facebook;

import android.content.Context;
import android.net.Uri;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.nextdots.mycomics.common.providers.Provider;
import com.nextdots.mycomics.common.model.session.SessionToken;
import com.nextdots.mycomics.common.model.session.User;

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
