package com.nextdots.mycomics.common.model.comics;

import java.io.Serializable;

/**
 * Comic images model
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public class Image implements Serializable {

  /** Image path **/
  private String path;

  /** Extension **/
  private String extension;

  /**
   * @return the path
   */
  public String getPath() {
    return path;
  }

  /**
   * @param path
   *         the path to set
   */
  public void setPath(String path) {
    this.path = path;
  }

  /**
   * @return the extension
   */
  public String getExtension() {
    return extension;
  }

  /**
   * @param extension
   *         the extension to set
   */
  public void setExtension(String extension) {
    this.extension = extension;
  }
}
