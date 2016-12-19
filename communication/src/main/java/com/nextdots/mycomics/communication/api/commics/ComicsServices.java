package com.nextdots.mycomics.communication.api.commics;

import com.nextdots.mycomics.common.model.dto.ComicsListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * List of available services to consume from comics service
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public interface ComicsServices {

  /**
   * Requests a list of comics given some filters such as limit and offset items
   *
   * @param limit
   *         Limit of items
   * @param offset
   *         Items offset
   *
   * @return Call to comics request
   */
  @GET("comics")
  Call<ComicsListResponse> requestComics(@Query("limit") int limit, @Query("offset") int offset);

}
