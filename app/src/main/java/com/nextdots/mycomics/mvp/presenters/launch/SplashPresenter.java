package com.nextdots.mycomics.mvp.presenters.launch;

import android.os.Handler;

import com.nextdots.mycomics.business.interactors.sign_in.SessionInteractor;
import com.nextdots.mycomics.mvp.presenters.common.AbstractPresenter;

/**
 * Splash presenter that controls current status and redirects to the specific screen
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class SplashPresenter extends AbstractPresenter {

  /** The splash view instance **/
  private SplashView mView;

  /** Session interactor **/
  private final SessionInteractor mSessionInteractor;

  /** Constant for animation delay **/
  private static final int ANIM_DELAY = 500;

  /** Delay for the splash view **/
  private static final int SPLASH_DELAY = 2500;

  /**
   * Splash presenter constructor
   *
   * @param splashView
   *         Splash view instance
   */
  public SplashPresenter(SessionInteractor sessionInteractor, SplashView splashView) {
    this.mSessionInteractor = sessionInteractor;
    this.mView = splashView;
  }

  @Override
  public void start() {
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        mView.showSplash();
      }
    }, ANIM_DELAY);

    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        processNextScreen();
      }
    }, SPLASH_DELAY);
  }

  /**
   * Processes the next screen. That means, go to login or directly to home screen
   */
  private void processNextScreen() {
    if (mSessionInteractor.isUserLogged()) {
      mView.redirectToHome();
    } else {
      mView.redirectToLogin();
    }
  }

  @Override
  public void stop() {

  }
}
