package com.nextdots.mycomics.mvp.views.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.nextdots.mycomics.R;
import com.nextdots.mycomics.business.interactors.sign_in.SessionInteractor;
import com.nextdots.mycomics.config.di.DiComponent;
import com.nextdots.mycomics.mvp.presenters.login.SignInPresenter;
import com.nextdots.mycomics.mvp.presenters.login.SignInView;
import com.nextdots.mycomics.mvp.views.common.BaseActivity;

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

  /** Progress bar **/
  @BindView(R.id.progress_bar)
  ProgressBar mProgressBar;

  /**
   * Start Login activity given a context
   *
   * @param context
   *         Current context
   */
  public static void start(Context context) {
    context.startActivity(new Intent(context, SignInActivity.class));
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    getPresenter().start();
  }

  @OnClick(R.id.facebook_sign_in_btn)
  public void onFacebookSignInClick() {
    getPresenter().facebookSignIn();
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
  public void redirectToHome() {
    // TODO: redirect to home
  }
}
