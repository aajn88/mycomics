package com.nextdots.mycomics.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.nextdots.mycomics.R;


/**
 * This is a utility class for common and standard methods in view
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @version 3.0.0
 * @since 1/9/16
 */
public final class ViewUtils {

  /** Private constructor to avoid instances **/
  private ViewUtils() {
  }

  /**
   * This method gets the requested color based on the Device's OS level
   *
   * @param context
   *         App context
   * @param colorRes
   *         Color res
   *
   * @return Color int
   */
  @ColorInt
  public static int getColor(Context context, @ColorRes int colorRes) {
    int color;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      color = context.getColor(colorRes);
    } else {
      color = context.getResources().getColor(colorRes);
    }
    return color;
  }

  /**
   * This method gets the requested drawable based on the Device's OS level
   *
   * @param context
   *         App context
   * @param drawableRes
   *         Color res
   *
   * @return Drawable object
   */
  public static Drawable getDrawable(Context context, @DrawableRes int drawableRes) {
    Drawable drawable;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      drawable = context.getDrawable(drawableRes);
    } else {
      drawable = context.getResources().getDrawable(drawableRes);
    }
    return drawable;
  }

  /**
   * This method shows/hides the keyboard
   *
   * @param activity
   *         Current activity
   * @param show
   *         Show or hide
   */
  public static void showKeyboard(Activity activity, boolean show) {
    View view = activity.getCurrentFocus();
    InputMethodManager imm =
            (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    if (show) {
      imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    } else if (view != null) {
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
  }

  /**
   * Sets up the swipe and refresh layout with the standard colors for the application
   *
   * @param swipeRefreshLayout
   *         Swipe and refresh to be set up
   */
  public static void setUpSwipeAndRefresh(SwipeRefreshLayout swipeRefreshLayout) {
    swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
  }
}
