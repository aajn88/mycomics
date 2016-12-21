package com.nextdots.mycomics.config.di;

import com.nextdots.mycomics.config.di.modules.CommonModule;
import com.nextdots.mycomics.config.di.modules.CommunicationModule;
import com.nextdots.mycomics.config.di.modules.InteractorsModule;
import com.nextdots.mycomics.config.di.modules.PersistenceModule;
import com.nextdots.mycomics.config.di.modules.ProvidersModule;
import com.nextdots.mycomics.mvp.views.comics.ComicDetailActivity;
import com.nextdots.mycomics.mvp.views.comics.ComicsListFragment;
import com.nextdots.mycomics.mvp.views.home.HomeActivity;
import com.nextdots.mycomics.mvp.views.launch.MainActivity;
import com.nextdots.mycomics.mvp.views.login.SignInActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * The Dependency Injection component used by dagger to inject specific objects
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@Singleton
@Component(modules = {CommonModule.class, InteractorsModule.class, ProvidersModule.class,
        PersistenceModule.class, CommunicationModule.class})
public interface DiComponent {

  /** The movies activity **/
  void inject(MainActivity moviesActivity);

  /** Login activity **/
  void inject(SignInActivity signInActivity);

  /** Home activity **/
  void inject(HomeActivity homeActivity);

  /** Comics list fragment **/
  void inject(ComicsListFragment comicsListFragment);

  /** Comic detail activity **/
  void inject(ComicDetailActivity comicDetailActivity);
}
