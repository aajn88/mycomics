package com.nextdots.mycomics.mvp.views.home;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;

/**
 * Intended to communicate fragments and activities when action bar has been loaded from fragment
 * side
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
public interface ActivityFragmentCallback {

  /**
   * This method sets the action bar
   *
   * @param toolbar
   *         Toolbar to be set
   */
  void setActionBar(@NonNull Toolbar toolbar);

  /**
   * Sets toolbar title
   *
   * @param stringRes
   *         String res
   */
  void setTitle(@StringRes int stringRes);

}
