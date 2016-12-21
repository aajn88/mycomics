package com.nextdots.mycomics.common.model.comics;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.nextdots.mycomics.common.persistence_types.CollectionType;

import java.io.Serializable;
import java.util.List;

/**
 * Comic data
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public class Comic implements Serializable {

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

  /** List of dates **/
  @DatabaseField(persisterClass = CollectionType.class)
  private List<ComicDate> dates;

  /** List of available prices **/
  @DatabaseField(persisterClass = CollectionType.class)
  private List<Price> prices;

  /** Page count **/
  @DatabaseField(canBeNull = false)
  private Integer pageCount;

  /** Comic creators **/
  @DatabaseField(dataType = DataType.SERIALIZABLE)
  private CreatorsList creatorsList;

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
   * @return the dates
   */
  public List<ComicDate> getDates() {
    return dates;
  }

  /**
   * @param dates
   *         the dates to set
   */
  public void setDates(List<ComicDate> dates) {
    this.dates = dates;
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
   * @return the pageCount
   */
  public Integer getPageCount() {
    return pageCount;
  }

  /**
   * @param pageCount
   *         the pageCount to set
   */
  public void setPageCount(Integer pageCount) {
    this.pageCount = pageCount;
  }

  /**
   * @return the creatorsList
   */
  public CreatorsList getCreatorsList() {
    return creatorsList;
  }

  /**
   * @param creatorsList
   *         the creatorsList to set
   */
  public void setCreatorsList(CreatorsList creatorsList) {
    this.creatorsList = creatorsList;
  }

  @Override
  public String toString() {
    return "Comic{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", thumbnail=" + thumbnail +
            ", images=" + images +
            ", dates=" + dates +
            ", prices=" + prices +
            ", pageCount=" + pageCount +
            ", creatorsList=" + creatorsList +
            '}';
  }
}
