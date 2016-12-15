package com.nextdots.mycomics.config;

import android.app.Application;

import com.nextdots.mycomics.config.di.DaggerDiComponent;
import com.nextdots.mycomics.config.di.DiComponent;
import com.nextdots.mycomics.mvp.presenters.common.IPresenter;
import com.nextdots.mycomics.mvp.presenters.common.PresenterHolder;
import com.nextdots.mycomics.mvp.views.common.IComponent;

/**
 * This is the MyComics application where configuration and common process are made
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class MyComicsApplication extends Application {

  /**
   * The holder that allows to the presenters keep alive in any moment of the execution of the app
   */
  private PresenterHolder mPresenterHolder;

  /** The class that injects the dependencies inside the app **/
  private DiComponent mDiComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    mPresenterHolder = new PresenterHolder();
    mDiComponent = DaggerDiComponent.builder()
            .build();
  }

  /**
   * This method returns a presenter associated to every Component in the app
   *
   * @param component
   *         the component that requires the presenter associated
   *
   * @return the presenter used for the view
   */
  public IPresenter getPresenter(IComponent component) {
    return mPresenterHolder.get(component);
  }

  /**
   * This method unbinds the current component to the presenter
   *
   * @param component
   *         Activity to be unbound
   */
  public void unbindPresenter(IComponent component) {
    mPresenterHolder.unhold(component);
  }

  /**
   * This method unbinds the current component to the presenter
   *
   * @param component
   *         Component to be unbound
   */
  public void onPresenterContextChanged(IComponent component) {
    mPresenterHolder.onContextChanged(component);
  }

  /**
   * This method allows to access to the injector of classes of dagger inside the app
   *
   * @return the DiComponent that injects the classes in the app
   */
  public DiComponent getInjector() {
    return mDiComponent;
  }

}
