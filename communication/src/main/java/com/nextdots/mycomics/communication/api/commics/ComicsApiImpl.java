package com.nextdots.mycomics.communication.api.commics;

import com.nextdots.mycomics.common.model.dto.ComicsListResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Implementation of the {@link ComicsApi}
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public class ComicsApiImpl implements ComicsApi {

  /** Comics services **/
  private final ComicsServices mComicsServices;

  /**
   * Comics API constructor
   *
   * @param retrofit
   *         Retrofit client instance
   */
  public ComicsApiImpl(Retrofit retrofit) {
    this.mComicsServices = retrofit.create(ComicsServices.class);
  }

  @Override
  public void requestComics(final int limit, final int offset,
                            final ComicsRequestCallback comicsRequestCallback) {
    mComicsServices.requestComics(limit, offset).enqueue(new Callback<ComicsListResponse>() {
      @Override
      public void onResponse(Call<ComicsListResponse> call, Response<ComicsListResponse> response) {
        if (response.isSuccessful()) {
          comicsRequestCallback.onComicsRequestSuccess(limit, offset, response.body());
        } else {
          onFailure(call, new Throwable("Unexpected request code"));
        }
      }

      @Override
      public void onFailure(Call<ComicsListResponse> call, Throwable t) {
        comicsRequestCallback.onComicsRequestFailure(limit, offset, t);
      }
    });
  }
}
