package com.nextdots.mycomics.mvp.presenters.common;

/**
 * Base methods for each view, such as the show loading
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 17/12/16
 */
public interface BaseView {

  /**
   * Shows/hides the loading view
   *
   * @param show
   *         True to show. False to hide
   */
  void showLoading(boolean show);

}
