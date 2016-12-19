package com.nextdots.mycomics.config.di.modules;

import com.nextdots.mycomics.business.interactors.comics.ComicsInteractor;
import com.nextdots.mycomics.business.interactors.comics.ComicsInteractorImpl;
import com.nextdots.mycomics.business.interactors.sign_in.SessionInteractor;
import com.nextdots.mycomics.business.interactors.sign_in.SessionInteractorImpl;
import com.nextdots.mycomics.business.providers.sign_in.facebook.FacebookProvider;
import com.nextdots.mycomics.business.providers.sign_in.google.GoogleProvider;
import com.nextdots.mycomics.communication.api.commics.ComicsApi;
import com.nextdots.mycomics.persistence.managers.session.UsersManager;

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
   * @param googleProvider
   *         Google provider
   * @param usersManager
   *         Users manager
   *
   * @return Instance of the {@link SessionInteractor}
   */
  @Provides
  @Singleton
  public SessionInteractor provideSessionInteractor(FacebookProvider facebookProvider,
                                                    GoogleProvider googleProvider,
                                                    UsersManager usersManager) {
    return new SessionInteractorImpl(facebookProvider, googleProvider, usersManager);
  }

  /**
   * Provides a {@link ComicsInteractor} instance
   *
   * @param comicsApi
   *         Comics API instance
   *
   * @return Instance of the {@link ComicsInteractor}
   */
  @Provides
  @Singleton
  public ComicsInteractor provideComicsInteractor(ComicsApi comicsApi) {
    return new ComicsInteractorImpl(comicsApi);
  }

}
