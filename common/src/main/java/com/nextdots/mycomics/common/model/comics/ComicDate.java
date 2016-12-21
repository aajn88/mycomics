package com.nextdots.mycomics.common.model.comics;

import java.io.Serializable;

/**
 * Expected comic date
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 21/12/16
 */
public class ComicDate implements Serializable {

  /** Comic's date type **/
  private String type;

  /** Date in the following format: "2017-01-18T00:00:00-0500" **/
  private String date;

  /**
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * @param type
   *         the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return the date
   */
  public String getDate() {
    return date;
  }

  /**
   * @param date
   *         the date to set
   */
  public void setDate(String date) {
    this.date = date;
  }
}
