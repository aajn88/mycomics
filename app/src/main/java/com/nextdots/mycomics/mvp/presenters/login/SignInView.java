package com.nextdots.mycomics.mvp.presenters.login;

import com.nextdots.mycomics.mvp.presenters.common.BaseView;

/**
 * Sign in view to interactor from the presenter
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 15/12/16
 */
public interface SignInView extends BaseView {

  /**
   * Requests a redirect to home screen
   */
  void redirectToHome();

  /**
   * Closes the app
   */
  void closeApp();
}
