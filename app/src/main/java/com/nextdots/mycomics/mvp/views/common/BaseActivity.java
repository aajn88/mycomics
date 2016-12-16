/*************************************************************************
 * CLICK DELIVERY CONFIDENTIAL
 * __________________
 * <p/>
 * [2016] ClickDelivery
 * All Rights Reserved.
 * <p/>
 * NOTICE:  All information contained herein is, and remains
 * the property of Click Delivery and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to ClickDelivery
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from ClickDelivery.
 */
package com.nextdots.mycomics.mvp.views.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nextdots.mycomics.business.exceptions.MyComicsException;
import com.nextdots.mycomics.config.MyComicsApplication;
import com.nextdots.mycomics.config.di.DiComponent;
import com.nextdots.mycomics.mvp.presenters.common.Presenter;

import butterknife.ButterKnife;

/**
 * Base activity for all activities that are managed by a Presenter
 *
 * @param <T>
 *         Activity's presenter class
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public abstract class BaseActivity<T extends Presenter> extends AppCompatActivity
        implements Component<T> {

  /** Tag for logs **/
  private static final String TAG = BaseActivity.class.getSimpleName();

  /** Is being rotated? **/
  private boolean mIsBeingStateChanged = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injectComponent(injector());
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mIsBeingStateChanged = true;
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    if (savedInstanceState == null) {
      return;
    }
    getApplicationContext().onPresenterContextChanged(this);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (!mIsBeingStateChanged) {
      getApplicationContext().unbindPresenter(this);
    }
    mIsBeingStateChanged = false;
  }

  @Override
  public void setContentView(@LayoutRes int layoutResID) {
    super.setContentView(layoutResID);
    ButterKnife.bind(this);
  }

  /**
   * Instead of returning the common application context. The casted {@link MyComicsApplication}
   * is returned
   *
   * @return The {@link MyComicsApplication} context
   */
  @Override
  public MyComicsApplication getApplicationContext() {
    return (MyComicsApplication) super.getApplication();
  }

  /**
   * This method gets an instance of the respective presenter of the activity
   *
   * @return Activity's respective presenter instance
   */
  @Override
  public T getPresenter() {
    return (T) getApplicationContext().getPresenter(this);
  }

  /**
   * Injection component. This should be done if there are fields to be injected
   *
   * @param diComponent
   *         Dependency injection
   */
  protected abstract void injectComponent(DiComponent diComponent);

  /**
   * Class key
   *
   * @return Returns the class key
   */
  @Override
  public String key() {
    return getClass().getName();
  }

  /**
   * This method returns the current Activity, i.e. this Activity
   *
   * @return The current activity
   */
  protected Activity getActivity() {
    return this;
  }

  /**
   * This method returns the Dagger2 injector
   *
   * @return The Dagger2 injector component
   */
  public DiComponent injector() {
    return getApplicationContext().getInjector();
  }

  /**
   * This method manages a {@link MyComicsException} to be shown to the user
   *
   * @param view
   *         A of the hierarchy view to show the message
   * @param e
   *         The exception
   */
  protected void handleExeception(@NonNull View view, @NonNull MyComicsException e) {
    Snackbar.make(view, e.getCustomMessage(), Snackbar.LENGTH_SHORT).show();
  }

}
