package com.nextdots.mycomics.mvp.presenters.login;

import android.support.annotation.NonNull;

import com.nextdots.mycomics.business.exceptions.MyComicsException;
import com.nextdots.mycomics.business.interactors.sign_in.SessionInteractor;
import com.nextdots.mycomics.common.providers.Provider;
import com.nextdots.mycomics.mvp.presenters.common.AbstractPresenter;

/**
 * Sign in presenter that manages Facebook and Google login
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 15/12/16
 */
public class SignInPresenter extends AbstractPresenter implements SessionInteractor.SignInCallback {

  /** Session interactor **/
  private final SessionInteractor mSessionInteractor;

  /** Sign ing view **/
  private SignInView mSignInView;

  /**
   * Login presenter constructor
   *
   * @param sessionInteractor
   *         Session interactor needed to manage sign in processes
   */
  public SignInPresenter(SessionInteractor sessionInteractor, SignInView signInView) {
    this.mSessionInteractor = sessionInteractor;
    this.mSignInView = signInView;
  }

  @Override
  public void start() {

  }

  @Override
  public void stop() {

  }

  /**
   * Start facebook signs in
   */
  public void facebookSignIn() {
    signIn(Provider.FACEBOOK);
  }

  /**
   * Signs in using the given provider
   *
   * @param provider
   *         Sign in provider
   */
  private void signIn(@NonNull Provider provider) {
    mSignInView.showLoading(true);
    mSessionInteractor.signInUsingProvider(provider, this);
  }

  @Override
  public void onSignInSuccess() {
    mSignInView.showLoading(false);
    mSignInView.redirectToHome();
  }

  @Override
  public void onSignInFailure(MyComicsException e) {
    mSignInView.showLoading(false);
  }
}
