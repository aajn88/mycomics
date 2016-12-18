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
package com.nextdots.mycomics.mvp.presenters.common;

import com.nextdots.mycomics.mvp.views.common.Component;

/**
 * The abstract presenter that implements common and standard methods for reuse if needed
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public abstract class AbstractPresenter implements Presenter {

  @Override
  public void onContextChanged(Component activity) {
    // Here comes what presenter should do in case of a context change
  }

}
