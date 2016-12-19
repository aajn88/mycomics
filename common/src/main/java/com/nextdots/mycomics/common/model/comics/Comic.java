package com.nextdots.mycomics.common.model.comics;

import java.util.List;

/**
 * Comic data
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public class Comic {

  /** Comic's ID **/
  private Integer id;

  /** Comic's title **/
  private String title;

  /** Description **/
  private String description;

  /** List of available prices **/
  private List<Price> prices;

  /**
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * @param id
   *         the id to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title
   *         the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description
   *         the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the prices
   */
  public List<Price> getPrices() {
    return prices;
  }

  /**
   * @param prices
   *         the prices to set
   */
  public void setPrices(List<Price> prices) {
    this.prices = prices;
  }

  @Override
  public String toString() {
    return "Comic{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", prices=" + prices +
            '}';
  }
}
