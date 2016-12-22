package com.nextdots.mycomics.business.providers.sign_in.google.session_activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/**
 * Google sign out Activity to disconnect Google account
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 19/12/16
 */
public class GoogleSignOutActivity extends AppCompatActivity implements GoogleSignOut,
        GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks,
        ResultCallback<Status> {

  /** Google API client **/
  private GoogleApiClient mGoogleApiClient;

  /** Static sign out callback **/
  private static GoogleSignOutCallback sGoogleSignOutCallback;

  /** Sign out callback **/
  private GoogleSignOutCallback mGoogleSignOutCallback;

  /** Has sign out? **/
  private boolean mHasSignOut;

  /** Has disconnected? **/
  private boolean mHasDisconnected;

  /**
   * Starts and tries to sign out a user
   *
   * @param context
   *         Application context
   * @param callback
   *         Callback for google sign out result
   */
  public static void startAndSignOut(@NonNull Context context,
                                     @NonNull GoogleSignOutCallback callback) {
    Intent googleSignInIntent = new Intent(context, GoogleSignOutActivity.class);
    googleSignInIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(googleSignInIntent);
    sGoogleSignOutCallback = callback;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mGoogleSignOutCallback = sGoogleSignOutCallback;
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build();
    mGoogleApiClient = new GoogleApiClient.Builder(this)
            .enableAutoManage(this, this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .addConnectionCallbacks(this)
            .build();
    mGoogleApiClient.connect();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mGoogleSignOutCallback = null;
  }

  @Override
  public void signOutGoogle() {
    mHasSignOut = true;
    Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(this);
  }

  /**
   * Disconnects google account
   */
  private void disconnectAccount() {
    mHasDisconnected = true;
    Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(this);
  }

  @Override
  public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    onSignOutFailure();
  }

  /**
   * Returns the failure to the callback and finishes the activity
   */
  private void onSignOutFailure() {
    mGoogleSignOutCallback.onGoogleSignOutFailure(new Throwable("Google connection has failed"));
    finish();
  }

  @Override
  public void onConnected(@Nullable Bundle bundle) {
    signOutGoogle();
  }

  @Override
  public void onConnectionSuspended(int i) {
    onSignOutFailure();
  }

  @Override
  public void onResult(@NonNull Status status) {
    if (status.isSuccess()) {
      processSuccessResult();
    } else {
      onSignOutFailure();
    }
  }

  private void processSuccessResult() {
    if (mHasSignOut && mHasDisconnected) {
      mGoogleSignOutCallback.onGoogleSignOutSuccess();
      finish();
      return;
    }
    if (mHasSignOut) {
      disconnectAccount();
    }
    if (mHasDisconnected) {
      signOutGoogle();
    }
  }

}
