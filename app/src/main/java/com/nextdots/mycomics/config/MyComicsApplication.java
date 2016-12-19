package com.nextdots.mycomics.config;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.nextdots.mycomics.BuildConfig;
import com.nextdots.mycomics.common.constants.MyComicsConstants;
import com.nextdots.mycomics.config.di.DaggerDiComponent;
import com.nextdots.mycomics.config.di.DiComponent;
import com.nextdots.mycomics.config.di.modules.CommonModule;
import com.nextdots.mycomics.config.di.modules.InteractorsModule;
import com.nextdots.mycomics.config.di.modules.PersistenceModule;
import com.nextdots.mycomics.config.di.modules.ProvidersModule;
import com.nextdots.mycomics.mvp.presenters.common.Presenter;
import com.nextdots.mycomics.mvp.presenters.common.PresenterHolder;
import com.nextdots.mycomics.mvp.views.common.Component;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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
    enableFacebook();
    mPresenterHolder = new PresenterHolder();
    mDiComponent = DaggerDiComponent.builder().
            commonModule(new CommonModule(this))
            .interactorsModule(new InteractorsModule())
            .providersModule(new ProvidersModule())
            .persistenceModule(new PersistenceModule())
            .build();
    enableImageLoader();
    initConstants();
  }

  /**
   * This method inits the environment constants
   */
  private void initConstants() {
    MyComicsConstants.sComicsServerUrl = BuildConfig.COMICS_URL;
    MyComicsConstants.sComicsServerApiKey = BuildConfig.COMICS_API_KEY;
    MyComicsConstants.sComicsServerPrivateKey = BuildConfig.COMICS_PRIVATE_KEY;
  }

  /**
   * Enables image loader for the whole application
   */
  private void enableImageLoader() {
    ImageLoader imageLoader = ImageLoader.getInstance();
    if (!imageLoader.isInited()) {
      imageLoader.init(ImageLoaderConfiguration.createDefault(this));
    }
  }

  /**
   * Enables facebook features
   */
  private void enableFacebook() {
    FacebookSdk.sdkInitialize(getApplicationContext());
    AppEventsLogger.activateApp(this);
  }

  /**
   * This method returns a presenter associated to every Component in the app
   *
   * @param component
   *         the component that requires the presenter associated
   *
   * @return the presenter used for the view
   */
  public Presenter getPresenter(Component component) {
    return mPresenterHolder.get(component);
  }

  /**
   * This method unbinds the current component to the presenter
   *
   * @param component
   *         Activity to be unbound
   */
  public void unbindPresenter(Component component) {
    mPresenterHolder.unhold(component);
  }

  /**
   * This method unbinds the current component to the presenter
   *
   * @param component
   *         Component to be unbound
   */
  public void onPresenterContextChanged(Component component) {
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
