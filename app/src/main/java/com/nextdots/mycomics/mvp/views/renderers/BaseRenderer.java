/**
 * CONFIDENTIAL
 * <p/>
 * [2016] All Rights Reserved.
 * <p/>
 * NOTICE:  All information contained herein is, and remains
 * the property of Click Delivery and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Click Delivery
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Click Delivery.
 */
package com.nextdots.mycomics.mvp.views.renderers;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedrogomez.renderers.Renderer;

import butterknife.ButterKnife;

/**
 * Base renderer that includes ButterKnife injection for inflated views. Also implements unused
 * methods
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @version 3.0.0
 * @since 10/11/16
 */
public abstract class BaseRenderer<T> extends Renderer<T> {

  @Override
  protected View inflate(LayoutInflater inflater, ViewGroup parent) {
    View view = inflateView(inflater, parent);
    ButterKnife.bind(this, view);
    return view;
  }

  /**
   * This method will be called to inflate the view. ButterKnife injection is not needed as it is
   * performed in a later stage
   *
   * @param inflater
   *         Layout inflater
   * @param parent
   *         Parent view group
   *
   * @return Inflated view. Cannot be null
   */
  @NonNull
  protected abstract View inflateView(LayoutInflater inflater, ViewGroup parent);

  @Override
  protected void setUpView(View rootView) {
    // No needed as ButterKnife is being used
  }

  @Override
  protected void hookListeners(View rootView) {
    // No needed as ButterKnife is being used
  }
}
