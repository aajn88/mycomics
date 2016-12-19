package com.nextdots.mycomics.mvp.views.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.gms.common.SignInButton;
import com.nextdots.mycomics.R;
import com.nextdots.mycomics.business.exceptions.MyComicsException;
import com.nextdots.mycomics.business.interactors.sign_in.SessionInteractor;
import com.nextdots.mycomics.config.di.DiComponent;
import com.nextdots.mycomics.mvp.presenters.login.SignInPresenter;
import com.nextdots.mycomics.mvp.presenters.login.SignInView;
import com.nextdots.mycomics.mvp.views.common.BaseActivity;
import com.nextdots.mycomics.mvp.views.home.HomeActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Sign in activity that shows Facebook and Google login
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class SignInActivity extends BaseActivity<SignInPresenter> implements SignInView {

  /** Session interactor **/
  @Inject
  SessionInteractor mSessionInteractor;

  /** Progress bar **/
  @BindView(R.id.sign_in_buttons_container)
  ViewGroup mSignInButtonsContainer;

  /** Google's sign in button **/
  @BindView(R.id.google_sign_in_btn)
  SignInButton mGoogleSignInButton;

  /** Progress bar **/
  @BindView(R.id.progress_bar)
  ProgressBar mProgressBar;

  /**
   * Start Login activity given a context
   *
   * @param activity
   *         Origin activity
   * @param transitionLogo
   *         View for transition logo
   */
  public static void start(Activity activity, View transitionLogo) {
    Intent signInIntent = new Intent(activity, SignInActivity.class);
    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
            transitionLogo, activity.getString(R.string.logo_transition));
    activity.startActivity(signInIntent, options.toBundle());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    mGoogleSignInButton.setSize(SignInButton.SIZE_WIDE);
    getPresenter().start();
  }

  /**
   * On click to start facebook sign in
   */
  @OnClick(R.id.facebook_sign_in_btn)
  public void onFacebookSignInClick() {
    getPresenter().facebookSignIn();
  }

  /**
   * On click to start google sign in
   */
  @OnClick(R.id.google_sign_in_btn)
  public void onGoogleSignInClick() {
    getPresenter().googleSignIn();
  }

  @Override
  protected void injectComponent(DiComponent diComponent) {
    diComponent.inject(this);
  }

  @Override
  public SignInPresenter buildPresenter() {
    return new SignInPresenter(mSessionInteractor, this);
  }

  @Override
  public void showLoading(boolean show) {
    mSignInButtonsContainer.setVisibility(!show ? View.VISIBLE : View.GONE);
    mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
  }

  @Override
  public void handleException(MyComicsException e) {
    Snackbar.make(mProgressBar, e.getCustomMessage(), Snackbar.LENGTH_LONG).show();
  }

  @Override
  public void onBackPressed() {
    getPresenter().onBackPressed();
  }

  @Override
  public void redirectToHome() {
    HomeActivity.start(this);
  }

  @Override
  public void closeApp() {
    finishAffinity();
  }
}
