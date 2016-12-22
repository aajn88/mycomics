package com.nextdots.mycomics.mvp.presenters.common;

import android.app.Activity;

import com.nextdots.mycomics.mvp.views.common.Component;

/**
 * Delivery Presenter that is implemented by all Presenters that want to interact with the
 * Activities and Interactors
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public interface Presenter {

  /**
   * This method should be called in {@link Activity#onStart()} method. This initializes the
   * presenter life cycle
   */
  void start();

  /**
   * This method should be called in {@link Activity#onStop()} method. This finishes the presenter
   * life cycle
   */
  void stop();

  /**
   * This method is called when the presenter context has changed
   *
   * @param activity
   *         New context of the presenter
   */
  void onContextChanged(Component activity);
}
