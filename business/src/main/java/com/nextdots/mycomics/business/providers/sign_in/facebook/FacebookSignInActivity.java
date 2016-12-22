package com.nextdots.mycomics.business.providers.sign_in.facebook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

/**
 * Facebook sign in Activity to access into Facebook account
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
public class FacebookSignInActivity extends AppCompatActivity implements FacebookAccess,
        FacebookCallback<LoginResult>, GraphRequest.GraphJSONObjectCallback {

  /** Permission to access email **/
  private static final String FB_EMAIL_PERMISSION = "email";

  /** Permission to access public profile **/
  private static final String FB_PUBLIC_PROFILE = "public_profile";

  /** Facebook fields **/
  private static final String FB_FIELDS = "fields";

  /** Facebook access callback **/
  private static FacebookAccessCallback sFacebookAccessCallback;

  /** The callback of the 3rd API of Facebook used inside the activity for communicate with FB **/
  private CallbackManager mCallbackManager;

  /** Login button **/
  private LoginButton mLoginButton;

  /** Access token **/
  private AccessToken mAccessToken;

  /**
   * Starts and tries to sign in a user
   *
   * @param context
   *         Application context
   * @param callback
   *         Callback for facebook access result
   */
  public static void startAndSignIn(@NonNull Context context,
                                    @NonNull FacebookAccessCallback callback) {
    Intent facebookSignInIntent = new Intent(context, FacebookSignInActivity.class);
    facebookSignInIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(facebookSignInIntent);
    sFacebookAccessCallback = callback;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mLoginButton = new LoginButton(this);
    mCallbackManager = CallbackManager.Factory.create();
    mLoginButton.setReadPermissions(FB_PUBLIC_PROFILE, FB_EMAIL_PERMISSION);
    mLoginButton.registerCallback(mCallbackManager, this);
    accessFacebook();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    mCallbackManager.onActivityResult(requestCode, resultCode, data);
  }

  @Override
  public void accessFacebook() {
    mLoginButton.performClick();
  }

  @Override
  public void onSuccess(LoginResult loginResult) {
    mAccessToken = loginResult.getAccessToken();
    GraphRequest request = GraphRequest.newMeRequest(mAccessToken, this);
    Bundle parameters = new Bundle();
    parameters.putString(FB_FIELDS, FB_EMAIL_PERMISSION);
    request.setParameters(parameters);
    request.executeAsync();
  }

  @Override
  public void onCancel() {
    sFacebookAccessCallback.onFacebookAccessCancelled();
    finish();
  }

  @Override
  public void onError(FacebookException error) {
    sFacebookAccessCallback.onFacebookAccessFailure(error);
    finish();
  }

  @Override
  public void onCompleted(JSONObject object, GraphResponse response) {
    String email = object.optString(FB_EMAIL_PERMISSION);
    Profile profile = Profile.getCurrentProfile();
    sFacebookAccessCallback.onFacebookAccessSuccess(profile, email, mAccessToken);
    finish();
  }
}
