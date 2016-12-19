package com.nextdots.mycomics.common.model.dto;

import com.google.gson.annotations.SerializedName;
import com.nextdots.mycomics.common.model.comics.Comic;

import java.util.List;

/**
 * Expected response from server for comics list
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public class ComicsList {

  /** Results offset **/
  private Integer offset;

  /** Total results **/
  private Integer total;

  /** Amount of results **/
  private Integer count;

  /** List of comics **/
  @SerializedName("results")
  private List<Comic> comics;

  /**
   * @return the offset
   */
  public Integer getOffset() {
    return offset;
  }

  /**
   * @param offset
   *         the offset to set
   */
  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  /**
   * @return the total
   */
  public Integer getTotal() {
    return total;
  }

  /**
   * @param total
   *         the total to set
   */
  public void setTotal(Integer total) {
    this.total = total;
  }

  /**
   * @return the count
   */
  public Integer getCount() {
    return count;
  }

  /**
   * @param count
   *         the count to set
   */
  public void setCount(Integer count) {
    this.count = count;
  }

  /**
   * @return the comics
   */
  public List<Comic> getComics() {
    return comics;
  }

  /**
   * @param comics
   *         the comics to set
   */
  public void setComics(List<Comic> comics) {
    this.comics = comics;
  }
}
