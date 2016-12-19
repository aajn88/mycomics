package com.nextdots.mycomics.communication.api.commics;

import com.nextdots.mycomics.common.model.dto.ComicsListResponse;

/**
 * Available comics API
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public interface ComicsApi {

  /**
   * Requests a list of comics given a limit of items and an offset
   *
   * @param limit
   *         Limit of items
   * @param offset
   *         Items offset
   * @param comicsRequestCallback
   *         Comics request callback
   */
  void requestComics(int limit, int offset, ComicsRequestCallback comicsRequestCallback);

  /**
   * Comics request callback to be used to return the results
   */
  interface ComicsRequestCallback {

    /**
     * Called if comics request has succeeded
     *
     * @param limit
     *         Limit of requested items
     * @param offset
     *         Offset item
     * @param comicsListResponse
     *         Comics response
     */
    void onComicsRequestSuccess(int limit, int offset, ComicsListResponse comicsListResponse);

    /**
     * Called if comics request has failed
     *
     * @param limit
     *         Limit of requested items
     * @param offset
     *         Offset item
     * @param t
     *         The thrown exception
     */
    void onComicsRequestFailure(int limit, int offset, Throwable t);

  }

}
