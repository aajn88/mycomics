package com.nextdots.mycomics.common.model.comics;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Price of a comic
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public class Price implements Serializable {

  /** Price type **/
  private String type;

  /** Price value **/
  @SerializedName("price")
  private Double value;

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
   * @return the value
   */
  public Double getValue() {
    return value;
  }

  /**
   * @param value
   *         the value to set
   */
  public void setValue(Double value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Price{" +
            "type='" + type + '\'' +
            ", value=" + value +
            '}';
  }
}
