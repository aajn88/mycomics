package com.nextdots.mycomics.mvp.presenters.home;

import com.nextdots.mycomics.mvp.presenters.common.BaseView;

/**
 * Home view interface
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public interface HomeView extends BaseView {

  /**
   * Shows the comics screen
   */
  void showComicsScreen();

  /**
   * Shows the favorites comics screen
   */
  void showFavoritesComicsScreen();

}
