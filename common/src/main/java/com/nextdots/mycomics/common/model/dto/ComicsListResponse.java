package com.nextdots.mycomics.common.model.dto;

/**
 * Expected response from server for comics list
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public class ComicsListResponse {

  /** Comics list **/
  private ComicsList data;

  /**
   * @return the data
   */
  public ComicsList getData() {
    return data;
  }

  /**
   * @param data
   *         the data to set
   */
  public void setData(ComicsList data) {
    this.data = data;
  }
}
