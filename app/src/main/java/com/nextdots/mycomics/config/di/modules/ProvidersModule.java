package com.nextdots.mycomics.config.di.modules;

import android.content.Context;

import com.nextdots.mycomics.business.providers.sign_in.facebook.FacebookProvider;
import com.nextdots.mycomics.business.providers.sign_in.facebook.FacebookProviderImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger's dependencies for providers
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
@Module
public class ProvidersModule {

  /**
   * Provides a {@link FacebookProvider} instance
   *
   * @param context
   *         Application context
   *
   * @return {@link FacebookProvider} instance
   */
  @Provides
  @Singleton
  public FacebookProvider provideFacebookProvider(Context context) {
    return new FacebookProviderImpl(context);
  }

}
