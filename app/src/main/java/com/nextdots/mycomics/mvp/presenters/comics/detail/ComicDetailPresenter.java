package com.nextdots.mycomics.mvp.presenters.comics.detail;

import com.nextdots.mycomics.business.interactors.comics.ComicsInteractor;
import com.nextdots.mycomics.common.model.comics.Comic;
import com.nextdots.mycomics.mvp.presenters.common.AbstractPresenter;

/**
 * Comic detail presenter
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 21/12/16
 */
public class ComicDetailPresenter extends AbstractPresenter {

  /** Comics interactor **/
  private final ComicsInteractor mComicsInteractor;

  /** Comic detail view **/
  private ComicDetailView mComicDetailView;

  /** Comic to show **/
  private Comic mComic;

  /**
   * Comic detail presenter constructor
   *
   * @param comicsInteractor
   *         The comics interactor
   * @param comicDetailView
   *         The comic detail view
   */
  public ComicDetailPresenter(ComicsInteractor comicsInteractor, ComicDetailView comicDetailView) {
    mComicsInteractor = comicsInteractor;
    mComicDetailView = comicDetailView;
  }

  @Override
  public void start() {

  }

  @Override
  public void stop() {

  }

  /**
   * Starts the presenter given the related comic
   *
   * @param comic
   *         Comic to be shown
   */
  public void start(Comic comic) {
    this.mComic = comic;
    mComicDetailView.renderComic(mComic);
  }
}
