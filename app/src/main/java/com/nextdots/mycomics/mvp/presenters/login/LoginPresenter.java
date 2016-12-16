package com.nextdots.mycomics.mvp.presenters.login;

import com.nextdots.mycomics.business.interactors.sign_in.SessionInteractor;
import com.nextdots.mycomics.mvp.presenters.common.AbstractPresenter;

/**
 * Login presenter that manages Facebook and Google login
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 15/12/16
 */
public class LoginPresenter extends AbstractPresenter {

  private final SessionInteractor mSessionInteractor;

  /**
   * Login presenter constructor
   *
   * @param sessionInteractor
   *         Session interactor needed to manage sign in processes
   */
  public LoginPresenter(SessionInteractor sessionInteractor) {
    this.mSessionInteractor = sessionInteractor;
  }

  @Override
  public void start() {

  }

  @Override
  public void stop() {

  }
}
