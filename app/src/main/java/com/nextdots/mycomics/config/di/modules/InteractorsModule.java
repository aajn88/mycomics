package com.nextdots.mycomics.config.di.modules;

import com.nextdots.mycomics.business.interactors.sign_in.SessionInteractor;
import com.nextdots.mycomics.business.interactors.sign_in.SessionInteractorImpl;
import com.nextdots.mycomics.business.providers.sign_in.facebook.FacebookProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger's dependencies for interactors
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
@Module
public class InteractorsModule {

  /**
   * Provides a {@link SessionInteractor} instance
   *
   * @param facebookProvider
   *         Facebook provider
   *
   * @return Instance of the {@link SessionInteractor}
   */
  @Provides
  @Singleton
  public SessionInteractor provideSessionInteractor(FacebookProvider facebookProvider) {
    return new SessionInteractorImpl(facebookProvider);
  }

}