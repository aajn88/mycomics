package com.nextdots.mycomics.common.model.common;

/**
 * Page info
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public class PageInfo {

  /** Current page **/
  private int currentPage;

  /** Pages limit **/
  private int pagesLimit;

  /** Items per page **/
  private int itemsPerPage;

  /** Total items **/
  private int totalItems;

  /**
   * @return the pagesLimit
   */
  public int getPagesLimit() {
    return pagesLimit;
  }

  /**
   * @param pagesLimit
   *         the pagesLimit to set
   */
  public PageInfo setPagesLimit(int pagesLimit) {
    this.pagesLimit = pagesLimit;
    return this;
  }

  /**
   * @return the currentPage
   */
  public int getCurrentPage() {
    return currentPage;
  }

  /**
   * @param currentPage
   *         the currentPage to set
   */
  public PageInfo setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
    return this;
  }

  /**
   * @return the itemsPerPage
   */
  public int getItemsPerPage() {
    return itemsPerPage;
  }

  /**
   * @param itemsPerPage
   *         the itemsPerPage to set
   */
  public PageInfo setItemsPerPage(int itemsPerPage) {
    this.itemsPerPage = itemsPerPage;
    return this;
  }

  /**
   * @return the totalItems
   */
  public int getTotalItems() {
    return totalItems;
  }

  /**
   * @param totalItems
   *         the totalItems to set
   */
  public PageInfo setTotalItems(int totalItems) {
    this.totalItems = totalItems;
    return this;
  }
}
