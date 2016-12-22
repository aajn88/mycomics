package com.nextdots.mycomics.mvp.views.launch;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.nextdots.mycomics.R;
import com.nextdots.mycomics.business.interactors.sign_in.SessionInteractor;
import com.nextdots.mycomics.config.di.DiComponent;
import com.nextdots.mycomics.mvp.presenters.launch.SplashPresenter;
import com.nextdots.mycomics.mvp.presenters.launch.SplashView;
import com.nextdots.mycomics.mvp.views.common.BaseActivity;
import com.nextdots.mycomics.mvp.views.home.HomeActivity;
import com.nextdots.mycomics.mvp.views.login.SignInActivity;
import com.nextdots.mycomics.utils.AnimationUtils;

import javax.inject.Inject;

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

  /** Session interactor **/
  @Inject
  SessionInteractor mSessionInteractor;

  /**
   * Starts the Main/Splash activity
   *
   * @param context
   *         Origin context
   */
  public static void start(Context context) {
    context.startActivity(new Intent(context, MainActivity.class));
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getPresenter().start();
  }

  @Override
  protected void injectComponent(DiComponent diComponent) {
    diComponent.inject(this);
  }

  @Override
  public SplashPresenter buildPresenter() {
    return new SplashPresenter(mSessionInteractor, this);
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
    SignInActivity.start(this, mLogoIv);
    getApplicationContext().unbindPresenter(this);
  }

  @Override
  public void redirectToHome() {
    HomeActivity.start(this);
    getApplicationContext().unbindPresenter(this);
  }

  @Override
  public void onBackPressed() {
    // Does nothing
  }
}
