package com.nextdots.mycomics.mvp.presenters.comics.list;

import android.support.annotation.NonNull;

import com.nextdots.mycomics.business.exceptions.MyComicsException;
import com.nextdots.mycomics.business.interactors.comics.ComicsInteractor;
import com.nextdots.mycomics.common.model.comics.Comic;
import com.nextdots.mycomics.common.model.common.PageInfo;
import com.nextdots.mycomics.mvp.presenters.common.AbstractPresenter;

import java.util.List;

/**
 * Comics list presenter
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public class ComicsListPresenter extends AbstractPresenter
        implements ComicsInteractor.ComicsCallback {

  /** The starting page **/
  private static final int STARTING_PAGE = 1;

  /** Comics interactor instance **/
  private final ComicsInteractor mComicsInteractor;

  /** Comics list view **/
  private ComicsListView mComicsListView;

  /** Is favorites list **/
  private final boolean mIsFavoritesList;

  /** Current page **/
  private int mCurrentPage;

  /**
   * Comics list presenter constructor
   *
   * @param comicsInteractor
   *         Comics interactor
   * @param comicsListView
   *         Comics list view
   * @param isFavoritesList
   *         Is favorites list screen?
   */
  public ComicsListPresenter(ComicsInteractor comicsInteractor, ComicsListView comicsListView,
                             boolean isFavoritesList) {
    this.mComicsInteractor = comicsInteractor;
    this.mComicsListView = comicsListView;
    this.mIsFavoritesList = isFavoritesList;
  }

  @Override
  public void start() {
    mCurrentPage = STARTING_PAGE;
    if (mIsFavoritesList) {
      mComicsInteractor.getFavoriteComics(this);
    } else {
      requestsNextPage();
    }
  }

  /**
   * Requests the next page of comics
   */
  public void requestsNextPage() {
    mComicsListView.showLoading(true);
    mComicsInteractor.getComics(mCurrentPage++, this);
  }

  @Override
  public void stop() {

  }

  @Override
  public void onComicsSuccess(@NonNull List<Comic> comics, @NonNull PageInfo pageInfo) {
    if (pageInfo.getCurrentPage() == STARTING_PAGE) {
      replaceComicsList(comics);
    } else {
      mComicsListView.addComicsList(comics);
    }
    mComicsListView.showLoading(false);
  }

  /**
   * Replaces the existing comics list
   *
   * @param comics
   *         Comics list to be shown
   */
  private void replaceComicsList(@NonNull List<Comic> comics) {
    if (comics.isEmpty()) {
      mComicsListView.showEmptyComicsListMessage();
    } else {
      mComicsListView.showComicsList(comics);
    }
  }

  @Override
  public void onComicsFailure(MyComicsException e) {
    mComicsListView.showLoading(false);
    mComicsListView.handleException(e);
  }

  /**
   * Checks if the given comic is favorite
   *
   * @param comic
   *         Comic to be checked
   *
   * @return True if is a favorite comic. False otherwise
   */
  public boolean isFavoriteComic(@NonNull Comic comic) {
    return mComicsInteractor.isFavoriteComic(comic.getId());
  }

  /**
   * Saves favorite comic
   *
   * @param comic
   *         Comic to be saved as a favorite
   */
  public void saveFavorite(@NonNull Comic comic) {
    mComicsInteractor.saveFavorite(comic);
  }

  /**
   * Removes a comic from favorites
   *
   * @param comic
   *         Comic to be removed
   */
  public void removeFavorite(@NonNull Comic comic) {
    mComicsInteractor.removeFavorite(comic.getId());
  }
}
