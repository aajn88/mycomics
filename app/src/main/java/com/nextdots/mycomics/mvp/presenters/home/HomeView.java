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
   * Loads the user info
   *
   * @param name
   *         User's name
   * @param email
   *         User's email
   * @param pictureUrl
   *         User's picture
   */
  void loadUserInfo(String name, String email, String pictureUrl);

  /**
   * Shows the comics screen
   */
  void showComicsScreen();

  /**
   * Shows the favorites comics screen
   */
  void showFavoritesComicsScreen();

}
