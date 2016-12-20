package com.nextdots.mycomics.config.di.modules;

import android.content.Context;
import android.util.Log;

import com.nextdots.mycomics.persistence.DatabaseHelper;
import com.nextdots.mycomics.persistence.managers.comics.ComicsManager;
import com.nextdots.mycomics.persistence.managers.comics.ComicsManagerImpl;
import com.nextdots.mycomics.persistence.managers.session.UsersManager;
import com.nextdots.mycomics.persistence.managers.session.UsersManagerImpl;

import java.sql.SQLException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Persistence module to provide managers and DB manager helper instances
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 17/12/16
 */
@Module
public class PersistenceModule {

  /** Tag for logs **/
  private static final String TAG = PersistenceModule.class.getSimpleName();

  /**
   * Provides with an instance of {@link DatabaseHelper}
   *
   * @return {@link DatabaseHelper} instance
   */
  @Provides
  @Singleton
  public DatabaseHelper databaseHelper(Context context) {
    return new DatabaseHelper(context);
  }

  /**
   * Provides with an instance of {@link UsersManager}
   *
   * @param databaseHelper
   *         Database helper instance
   *
   * @return Instance of {@link UsersManager}
   */
  @Provides
  @Singleton
  public UsersManager provideUsersManager(DatabaseHelper databaseHelper) {
    try {
      return new UsersManagerImpl(databaseHelper);
    } catch (SQLException e) {
      Log.e(TAG, "Error instantiating the manager", e);
    }
    return null;
  }

  /**
   * Provides with an instance of {@link ComicsManager}
   *
   * @param databaseHelper
   *         Database helper instance
   *
   * @return Instance of {@link ComicsManager}
   */
  @Provides
  @Singleton
  public ComicsManager provideComicsManager(DatabaseHelper databaseHelper) {
    try {
      return new ComicsManagerImpl(databaseHelper);
    } catch (SQLException e) {
      Log.e(TAG, "Error instantiating the manager", e);
    }
    return null;
  }

}
