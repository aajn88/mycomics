package com.nextdots.mycomics.common.model.comics;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Expected model for creators list
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 21/12/16
 */
public class CreatorsList implements Serializable {

  /** Creators list **/
  @SerializedName("items")
  private List<Creator> creators;

  /**
   * @return the creators
   */
  public List<Creator> getCreators() {
    return creators;
  }

  /**
   * @param creators
   *         the creators to set
   */
  public void setCreators(
          List<Creator> creators) {
    this.creators = creators;
  }

  @Override
  public String toString() {
    return "CreatorsList{" +
            "creators=" + creators +
            '}';
  }
}
