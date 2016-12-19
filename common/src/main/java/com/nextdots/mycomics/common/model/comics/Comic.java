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

  /** Comic's thumbnail **/
  private Image thumbnail;

  /** Comic's images **/
  private List<Image> images;

  /** List of available prices **/
  private List<Price> prices;

  /** Is favorite comic **/
  private boolean isFavorite;

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
   * @return the thumbnail
   */
  public Image getThumbnail() {
    return thumbnail;
  }

  /**
   * @param thumbnail
   *         the thumbnail to set
   */
  public void setThumbnail(Image thumbnail) {
    this.thumbnail = thumbnail;
  }

  /**
   * @return the images
   */
  public List<Image> getImages() {
    return images;
  }

  /**
   * @param images
   *         the images to set
   */
  public void setImages(List<Image> images) {
    this.images = images;
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

  /**
   * @return the isFavorite
   */
  public boolean isFavorite() {
    return isFavorite;
  }

  /**
   * @param isFavorite
   *         the isFavorite to set
   */
  public void setFavorite(boolean isFavorite) {
    isFavorite = isFavorite;
  }

  @Override
  public String toString() {
    return "Comic{" +
            "isFavorite=" + isFavorite +
            ", id=" + id +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", thumbnail=" + thumbnail +
            ", images=" + images +
            ", prices=" + prices +
            '}';
  }
}
