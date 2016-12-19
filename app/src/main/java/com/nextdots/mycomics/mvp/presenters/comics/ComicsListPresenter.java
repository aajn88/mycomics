package com.nextdots.mycomics.mvp.presenters.comics;

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
  private void requestsNextPage() {
    mComicsInteractor.getComics(mCurrentPage++, this);
  }

  @Override
  public void stop() {

  }

  @Override
  public void onComicsSuccess(@NonNull List<Comic> comics, @NonNull PageInfo pageInfo) {
    if (pageInfo.getCurrentPage() == STARTING_PAGE) {
      mComicsListView.showComicsList(comics);
    } else {
      mComicsListView.addComicsList(comics);
    }
  }

  @Override
  public void onComicsFailure(MyComicsException e) {
    mComicsListView.errorLoadingComics(e);
  }
}