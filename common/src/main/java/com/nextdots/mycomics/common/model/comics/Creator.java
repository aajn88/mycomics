package com.nextdots.mycomics.common.model.comics;

import java.io.Serializable;

/**
 * Model for comic creator
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 21/12/16
 */
public class Creator implements Serializable {

  /** Creator's name **/
  private String name;

  /** Creator's role **/
  private String role;

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *         the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the role
   */
  public String getRole() {
    return role;
  }

  /**
   * @param role
   *         the role to set
   */
  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "Creator{" +
            "name='" + name + '\'' +
            ", role='" + role + '\'' +
            '}';
  }
}
