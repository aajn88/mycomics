package com.nextdots.mycomics.config.di;

import com.nextdots.mycomics.mvp.views.launch.MainActivity;
import com.nextdots.mycomics.mvp.views.login.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * The Dependency Injection component used by dagger to inject specific objects
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@Singleton
@Component(modules = {})
public interface DiComponent {

  /** The movies activity **/
  void inject(MainActivity moviesActivity);

  /** Login activity **/
  void inject(LoginActivity loginActivity);
}
