package com.nextdots.mycomics.mvp.presenters.launch;

/**
 * Splash view interface
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public interface SplashView {

  /**
   * Renders the splash animation and related views
   */
  void showSplash();

  /**
   * Redirects to login screen
   */
  void redirectToLogin();

  /**
   * Redirects to home screen
   */
  void redirectToHome();
}
