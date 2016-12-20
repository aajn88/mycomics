package com.nextdots.mycomics.business.interactors.comics;

import android.support.annotation.NonNull;

import com.nextdots.mycomics.business.R;
import com.nextdots.mycomics.business.exceptions.MyComicsException;
import com.nextdots.mycomics.common.model.comics.Comic;
import com.nextdots.mycomics.common.model.common.PageInfo;
import com.nextdots.mycomics.common.model.dto.ComicsList;
import com.nextdots.mycomics.common.model.dto.ComicsListResponse;
import com.nextdots.mycomics.communication.api.commics.ComicsApi;
import com.nextdots.mycomics.persistence.managers.comics.ComicsManager;

import java.util.List;

/**
 * Implementation of the {@link ComicsInteractor}
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public class ComicsInteractorImpl implements ComicsInteractor, ComicsApi.ComicsRequestCallback {

  /** Items limit per request **/
  private static final int ITEMS_LIMIT = 30;

  /** Comics Manager **/
  private final ComicsManager mComicsManager;

  /** Comics API **/
  private final ComicsApi mComicsApi;

  /** Comics Callback **/
  private ComicsCallback mComicsCallback;

  /**
   * Comics interactor constructor
   *
   * @param comicsManager
   *         Comics manager
   * @param comicsApi
   *         Comics API to consume server services
   */
  public ComicsInteractorImpl(ComicsManager comicsManager, ComicsApi comicsApi) {
    this.mComicsManager = comicsManager;
    this.mComicsApi = comicsApi;
  }

  @Override
  public void getComics(int page, @NonNull ComicsCallback comicsCallback) {
    this.mComicsCallback = comicsCallback;
    int offset = calculateOffset(page);
    mComicsApi.requestComics(ITEMS_LIMIT, offset, this);
  }

  @Override
  public void getFavoriteComics(@NonNull ComicsCallback comicsCallback) {
    List<Comic> comics = mComicsManager.all();
    PageInfo pageInfo = new PageInfo().
            setTotalItems(comics.size())
            .setPagesLimit(1)
            .setItemsPerPage(comics.size())
            .setCurrentPage(1);
    comicsCallback.onComicsSuccess(comics, pageInfo);
  }

  @Override
  public boolean isFavoriteComic(int comicId) {
    return mComicsManager.findById(comicId) != null;
  }

  @Override
  public void saveFavorite(Comic comic) {
    mComicsManager.createOrUpdate(comic);
  }

  @Override
  public void removeFavorite(int comicId) {
    mComicsManager.deleteById(comicId);
  }

  /**
   * Calculates the items offset given the page number
   *
   * @param page
   *         Page number
   *
   * @return Items offset
   */
  private int calculateOffset(int page) {
    page = Math.max(page, 1) - 1;
    return page * ITEMS_LIMIT;
  }

  /**
   * Calculates the current page given the offset
   *
   * @param offset
   *         Item offset
   *
   * @return Items offset
   */
  private int calculatePage(int offset) {
    return offset / ITEMS_LIMIT + 1;
  }

  @Override
  public void onComicsRequestSuccess(int limit, int offset, ComicsListResponse comicsListResponse) {
    ComicsList comicsList = comicsListResponse.getData();
    PageInfo pageInfo = new PageInfo().
            setCurrentPage(calculatePage(offset))
            .setItemsPerPage(ITEMS_LIMIT)
            .setPagesLimit(comicsList.getTotal() / ITEMS_LIMIT + 1)
            .setTotalItems(comicsList.getTotal());
    mComicsCallback.onComicsSuccess(comicsList.getComics(), pageInfo);
  }

  @Override
  public void onComicsRequestFailure(int limit, int offset, Throwable t) {
    mComicsCallback.onComicsFailure(
            new MyComicsException(t.getMessage(), R.string.error_loading_comics));
  }
}
