package com.nextdots.mycomics.mvp.views.common;

import com.nextdots.mycomics.mvp.presenters.common.Presenter;

/**
 * This interface identifies the Delivery UI Components such as Activities and Fragments
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public interface Component<T extends Presenter> {

  /**
   * This method gets an instance of the respective presenter of the component
   *
   * @return Component's respective presenter instance
   */
  T getPresenter();

  /**
   * This method should return an instance of the Presenter
   *
   * @return Instance of the Presenter
   */
  T buildPresenter();

  /**
   * Class key
   *
   * @return Returns the class key
   */
  String key();

}
