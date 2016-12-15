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

import com.nextdots.mycomics.mvp.views.common.IComponent;

import java.util.HashMap;
import java.util.Map;

/**
 * This class help to keep "alive" the presenter of all the views any moment when the app its
 * running
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class PresenterHolder {

  /** The data structure that keeps the presenters associated to the views for access */
  private Map<String, IPresenter> mPresenters = new HashMap<>();

  /**
   * This method return the presenter associated to a View
   *
   * @param component
   *         the component that requires to access to his own presenter
   *
   * @return the presenter associated to that view (Component)
   */
  public IPresenter get(IComponent component) {
    if (!has(component)) {
      hold(component);
    }
    IPresenter presenter = getPresenter(component);
    presenter.onContextChanged(component);
    return presenter;
  }

  /**
   * This method gets the respective presenter for the given component
   *
   * @param component
   *         Component to be searched
   *
   * @return IPresenter instance. Null if there is no a match
   */
  private IPresenter getPresenter(IComponent component) {
    return mPresenters.get(component.key());
  }

  /**
   * Bound the presenter to the respective component in UI
   *
   * @param component
   *         the component that going to be bounded to the presenter
   */
  public void hold(IComponent component) {
    mPresenters.put(component.key(), component.buildPresenter());
  }

  /**
   * This method response if an component has already a presenter associated to itself
   *
   * @param component
   *         the component that ask if has any presenter associated
   *
   * @return true if exists a presenter associated, otherwise return false
   */
  public boolean has(IComponent component) {
    return component != null && mPresenters.containsKey(component.key());
  }

  /**
   * This method change the presenter context given the new context
   *
   * @param newContext
   *         The new context
   */
  public void onContextChanged(IComponent newContext) {
    if (!has(newContext)) {
      return;
    }
    getPresenter(newContext).onContextChanged(newContext);
  }

  /**
   * Remove the specific presenter of a given component
   *
   * @param component
   *         the component that going to unbound the presenter associated
   */
  public void unhold(IComponent component) {
    mPresenters.remove(component.key());
  }
}
