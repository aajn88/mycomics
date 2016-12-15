package com.nextdots.mycomics.exceptions;

import android.support.annotation.StringRes;

import com.nextdots.mycomics.R;

/**
 * Generic exception for UI handle
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class MyComicsException extends Exception {

  /** Custom message **/
  @StringRes
  private Integer mCustomMessage;

  /**
   * TimeApp default constructor
   */
  public MyComicsException() {
    super();
  }

  /**
   * MyComics exception + log message
   *
   * @param message
   *         Message to be logged
   */
  public MyComicsException(String message) {
    this(message, R.string.no_internet_connection);
  }

  /**
   * MyComics exception + log message
   *
   * @param message
   *         Message to be logged
   */
  public MyComicsException(String message, @StringRes int stringRes) {
    super(message);
    mCustomMessage = stringRes;
  }

  /**
   * @return the mCustomMessage
   */
  public Integer getCustomMessage() {
    return mCustomMessage;
  }

}
