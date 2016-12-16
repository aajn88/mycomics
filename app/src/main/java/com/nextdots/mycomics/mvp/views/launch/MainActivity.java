package com.nextdots.mycomics.mvp.views.launch;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;

import com.nextdots.mycomics.R;
import com.nextdots.mycomics.config.di.DiComponent;
import com.nextdots.mycomics.mvp.presenters.launch.SplashView;
import com.nextdots.mycomics.mvp.presenters.launch.SplashPresenter;
import com.nextdots.mycomics.mvp.views.common.BaseActivity;
import com.nextdots.mycomics.mvp.views.login.LoginActivity;
import com.nextdots.mycomics.utils.AnimationUtils;

import butterknife.BindView;

/**
 * Main activity where splash screen is shown
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class MainActivity extends BaseActivity<SplashPresenter> implements SplashView {

  /** Constant for animations duration **/
  private static final int ANIM_DURATION = 500;

  /** Logo Image View **/
  @BindView(R.id.logo_iv)
  ImageView mLogoIv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override
  protected void onStart() {
    super.onStart();
    getPresenter().start();
  }

  @Override
  protected void injectComponent(DiComponent diComponent) {
    diComponent.inject(this);
  }

  @Override
  public SplashPresenter buildPresenter() {
    return new SplashPresenter(this);
  }

  @Override
  public void showSplash() {
    ObjectAnimator scaleXAnimation = AnimationUtils
            .createObjectAnimator(mLogoIv, "scaleX", 0.0F, 1.0F, ANIM_DURATION);
    ObjectAnimator scaleYAnimation = AnimationUtils
            .createObjectAnimator(mLogoIv, "scaleY", 0.0F, 1.0F, ANIM_DURATION);
    ObjectAnimator alphaAnimation = AnimationUtils
            .createObjectAnimator(mLogoIv, "alpha", 0.0F, 1.0F, ANIM_DURATION);
    AnimatorSet animatorSet = new AnimatorSet();
    animatorSet.play(scaleXAnimation).with(scaleYAnimation).with(alphaAnimation);
    animatorSet.start();
  }

  @Override
  public void redirectToLogin() {
    LoginActivity.start(this);
  }
}
