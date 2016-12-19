package com.nextdots.mycomics.mvp.presenters.home;

import com.nextdots.mycomics.mvp.presenters.common.AbstractPresenter;

/**
 * Home presenter
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public class HomePresenter extends AbstractPresenter {

  /** Home view **/
  private HomeView mHomeView;

  /**
   * Home presenter constructor
   *
   * @param homeView
   *         Home view
   */
  public HomePresenter(HomeView homeView) {
    this.mHomeView = homeView;
  }

  @Override
  public void start() {
    mHomeView.showComicsScreen();
  }

  @Override
  public void stop() {

  }
}
