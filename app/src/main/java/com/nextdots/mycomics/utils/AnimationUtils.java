package com.nextdots.mycomics.utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;

/**
 * Utility class to manage standard animations in TimeApp
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio A. Jimenez N.</a>
 */
public final class AnimationUtils {

  /** Private constructor to avoid instances **/
  private AnimationUtils() {
  }

  /**
   * This method creates an Object Animator based on the targeted view, the property to be
   * animated and the initial value and final value
   *
   * @param view
   *         Target view
   * @param property
   *         Property to be animated
   * @param init
   *         Initial value
   * @param end
   *         Final value
   * @param duration
   *         Animation duration
   *
   * @return ObjectAnimator with the given animated property
   */
  @NonNull
  public static ObjectAnimator createObjectAnimator(View view, String property, float init,
                                                    float end, long duration) {
    ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(view, property, init, end);
    scaleXAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
    scaleXAnimation.setDuration(duration);
    return scaleXAnimation;
  }

  /**
   * This method shows a view using circular reveal animation
   *
   * @param viewToReveal
   *         View to be revealed
   * @param cx
   *         X center of the circle to reveal the view
   * @param cy
   *         Y center of the circle to reveal the view
   * @param duration
   *         Animation duration
   * @param inverse
   *         If true, then the animation starts from the edge of the circle to the center of it
   *
   * @return The circular reveal animation
   */
  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public static Animator showUsingCircularReveal(final View viewToReveal, int cx, int cy,
                                                 int duration,
                                                 final boolean inverse) {
    int initialRadius = 0;
    int finalRadius = Math.max(viewToReveal.getWidth(), viewToReveal.getHeight());
    if (inverse) {
      initialRadius = finalRadius;
      finalRadius = 0;
    }

    Animator anim = ViewAnimationUtils
            .createCircularReveal(viewToReveal, cx, cy, initialRadius, finalRadius);
    if (!inverse) {
      viewToReveal.setVisibility(View.VISIBLE);
    }
    anim.addListener(new Animator.AnimatorListener() {
      @Override
      public void onAnimationStart(Animator animation) {
      }

      @Override
      public void onAnimationEnd(Animator animation) {
        if (inverse) {
          viewToReveal.setVisibility(View.GONE);
        }
      }

      @Override
      public void onAnimationCancel(Animator animation) {
      }

      @Override
      public void onAnimationRepeat(Animator animation) {
      }
    });
    anim.setDuration(duration);
    anim.setInterpolator(new AccelerateInterpolator());
    return anim;
  }

}
