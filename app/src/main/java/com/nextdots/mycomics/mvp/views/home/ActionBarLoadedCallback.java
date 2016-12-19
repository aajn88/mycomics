package com.nextdots.mycomics.mvp.views.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;

/**
 * Intended to communicate fragments and activities when action bar has been loaded from fragment
 * side
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
public interface ActionBarLoadedCallback {

  /**
   * This method sets the action bar
   *
   * @param toolbar
   *         Toolbar to be set
   */
  void setActionBar(@NonNull Toolbar toolbar);
}
