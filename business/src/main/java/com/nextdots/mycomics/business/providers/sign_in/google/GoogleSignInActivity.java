package com.nextdots.mycomics.business.providers.sign_in.google;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Google sign in Activity to access into Google account
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
public class GoogleSignInActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener, GoogleAccess {

  /** Tag for logs **/
  private static final String TAG = GoogleSignInActivity.class.getSimpleName();

  /** Google API client **/
  private GoogleApiClient mGoogleApiClient;

  /** Sign in request code **/
  private final int RC_SIGN_IN = 1;

  /** Google access callback **/
  private static GoogleAccess.GoogleAccessCallback sGoogleAccessCallback;

  /**
   * Starts and tries to sign in a user
   *
   * @param context
   *         Application context
   * @param callback
   *         Callback for google access result
   */
  public static void startAndSignIn(@NonNull Context context,
                                    @NonNull GoogleAccess.GoogleAccessCallback callback) {
    Intent googleSignInIntent = new Intent(context, GoogleSignInActivity.class);
    googleSignInIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(googleSignInIntent);
    sGoogleAccessCallback = callback;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build();
    mGoogleApiClient = new GoogleApiClient.Builder(this)
            .enableAutoManage(this, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build();
    accessGoogle();
  }

  @Override
  public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    sGoogleAccessCallback.onGoogleAccessFailure(new Throwable("Connection failed"));
    finish();
  }

  @Override
  public void accessGoogle() {
    Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
    startActivityForResult(signInIntent, RC_SIGN_IN);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == RC_SIGN_IN) {
      GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
      handleSignInResult(result);
    }

  }

  private void handleSignInResult(GoogleSignInResult result) {
    Log.d(TAG, "handleSignInResult:" + result.isSuccess());
    if (result.isSuccess()) {
      // Signed in successfully, show authenticated UI.
      GoogleSignInAccount acct = result.getSignInAccount();
      sGoogleAccessCallback.onGoogleAccessSuccess(acct);
    } else {
      sGoogleAccessCallback.onGoogleAccessFailure(new Throwable("Couldn't sign in"));
    }
    finish();
  }

}
