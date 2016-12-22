package com.nextdots.mycomics.config.di.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Common dependencies
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
@Module
public class CommonModule {

  /** Context to be injected into dependencies **/
  private final Context mContext;

  /**
   * Common module constructor
   *
   * @param context
   *         Application context
   */
  public CommonModule(Context context) {
    this.mContext = context;
  }

  /**
   * Injection of the application context
   *
   * @return Application context
   */
  @Provides
  public Context context() {
    return mContext;
  }

}
