package com.nextdots.mycomics.common.model.comics;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.nextdots.mycomics.common.persistence_types.CollectionType;

import java.util.List;

/**
 * Comic data
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public class Comic {

  /** Comic's ID **/
  @DatabaseField(id = true)
  private Integer id;

  /** Comic's title **/
  @DatabaseField(canBeNull = false)
  private String title;

  /** Description **/
  @DatabaseField
  private String description;

  /** Comic's thumbnail **/
  @DatabaseField(dataType = DataType.SERIALIZABLE)
  private Image thumbnail;

  /** Comic's images **/
  @DatabaseField(persisterClass = CollectionType.class)
  private List<Image> images;

  /** List of available prices **/
  @DatabaseField(persisterClass = CollectionType.class)
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
   * @param isFavorite
   *         the isFavorite to set
   */
  public void setFavorite(boolean isFavorite) {
    isFavorite = isFavorite;
  }

  @Override
  public String toString() {
    return "Comic{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", thumbnail=" + thumbnail +
            ", images=" + images +
            ", prices=" + prices +
            '}';
  }
}
