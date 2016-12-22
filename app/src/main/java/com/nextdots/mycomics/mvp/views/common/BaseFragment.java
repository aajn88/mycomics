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

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.nextdots.mycomics.config.MyComicsApplication;
import com.nextdots.mycomics.config.di.DiComponent;
import com.nextdots.mycomics.mvp.presenters.common.AbstractPresenter;

import butterknife.ButterKnife;

/**
 * Base fragment for all fragments that are managed by a Presenter
 *
 * @param <T>
 *         Fragment's presenter class
 *
 * @author Antonio Jiménez
 */
public abstract class BaseFragment<T extends AbstractPresenter> extends Fragment
        implements Component<T> {

  /** Is being rotated? **/
  private boolean mIsBeingStateChanged = false;

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    DiComponent diComponent = getInjector();
    if (diComponent != null) {
      injectComponent(diComponent);
    }
  }

  /**
   * This method obtains the applicaiton context.
   *
   * @return Returns the {@link MyComicsApplication}. Returns null if there is no activity attached
   */
  public MyComicsApplication getApplicationContext() {
    return isAdded() ? (MyComicsApplication) getActivity().getApplication() : null;
  }

  /**
   * This method gets the injector
   *
   * @return Dependency injector. Returns null if there is no application context available
   */
  public DiComponent getInjector() {
    MyComicsApplication myComicsApplication = getApplicationContext();
    return myComicsApplication != null ? myComicsApplication.getInjector() : null;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if (isAdded() && !mIsBeingStateChanged) {
      getApplicationContext().unbindPresenter(this);
    }
    mIsBeingStateChanged = false;
  }

  /**
   * This method gets an instance of the respective presenter of the activity
   *
   * @return Activity's respective presenter instance
   */
  @Override
  public T getPresenter() {
    MyComicsApplication deliveryApplication = getApplicationContext();
    return deliveryApplication != null ? (T) getApplicationContext().getPresenter(this) : null;
  }

  /**
   * This method should return an instance of the Presenter
   *
   * @return Instance of the Presenter
   */
  public abstract T buildPresenter();

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
   * Injection component. This should be done if there are fields to be injected
   *
   * @param diComponent
   *         Dependency injection
   */
  protected abstract void injectComponent(@NonNull DiComponent diComponent);
}
