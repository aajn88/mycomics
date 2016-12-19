package com.nextdots.mycomics.config.di.modules;

import com.google.gson.GsonBuilder;
import com.nextdots.mycomics.common.constants.MyComicsConstants;
import com.nextdots.mycomics.communication.api.commics.ComicsApi;
import com.nextdots.mycomics.communication.api.commics.ComicsApiImpl;
import com.nextdots.mycomics.communication.api.interceptors.ParamsInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Communication module for dagger injection
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
@Module
public class CommunicationModule {

  /**
   * Provides a retrofit client instance
   *
   * @return Retrofit instance
   */
  @Provides
  @Singleton
  public Retrofit provideRetrofit() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
    Retrofit.Builder builder = new Retrofit.Builder().baseUrl(MyComicsConstants.sComicsServerUrl)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .client(addInterceptors(clientBuilder).build());
    return builder.build();
  }

  /**
   * This method adds the interceptors to the Retrofit Client Builder
   *
   * @param builder
   *         Target retrofit client builder
   *
   * @return The modified builder including the attached interceptors
   */
  private OkHttpClient.Builder addInterceptors(OkHttpClient.Builder builder) {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    builder.addInterceptor(new ParamsInterceptor());
    builder.addInterceptor(logging);
    builder.readTimeout(60, TimeUnit.SECONDS);
    return builder;
  }

  /**
   * Comics API instance
   *
   * @param retrofit
   *         Retrofit client instance
   *
   * @return {@link ComicsApi} instance
   */
  @Provides
  @Singleton
  public ComicsApi provideComicsApi(Retrofit retrofit) {
    return new ComicsApiImpl(retrofit);
  }

}
