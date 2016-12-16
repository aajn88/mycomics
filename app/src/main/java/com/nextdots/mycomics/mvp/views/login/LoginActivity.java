package com.nextdots.mycomics.mvp.views.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.nextdots.mycomics.R;
import com.nextdots.mycomics.business.interactors.sign_in.SessionInteractor;
import com.nextdots.mycomics.config.di.DiComponent;
import com.nextdots.mycomics.mvp.presenters.login.LoginPresenter;
import com.nextdots.mycomics.mvp.views.common.BaseActivity;

import javax.inject.Inject;

/**
 * Login activity that shows Facebook and Google login
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class LoginActivity extends BaseActivity<LoginPresenter> {

  @Inject
  SessionInteractor mSessionInteractor;

  /**
   * Start Login activity given a context
   *
   * @param context
   *         Current context
   */
  public static void start(Context context) {
    context.startActivity(new Intent(context, LoginActivity.class));
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
  }

  @Override
  protected void injectComponent(DiComponent diComponent) {
    diComponent.inject(this);
  }

  @Override
  public LoginPresenter buildPresenter() {
    return new LoginPresenter(mSessionInteractor);
  }
}
