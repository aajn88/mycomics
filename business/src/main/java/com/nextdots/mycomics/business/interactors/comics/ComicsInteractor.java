package com.nextdots.mycomics.business.interactors.comics;

import android.support.annotation.NonNull;

import com.nextdots.mycomics.business.exceptions.MyComicsException;
import com.nextdots.mycomics.common.model.comics.Comic;
import com.nextdots.mycomics.common.model.common.PageInfo;

import java.util.List;

/**
 * Comics interactor
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public interface ComicsInteractor {

  /**
   * Requests the comics list of a given page
   *
   * @param page
   *         Requested page [1,..)
   * @param comicsCallback
   *         Comics callback to return the results
   */
  void getComics(int page, @NonNull ComicsCallback comicsCallback);

  /**
   * Requests the stored user's favorite comics
   *
   * @param comicsCallback
   *         Comics callback to return the results
   */
  void getFavoriteComics(@NonNull ComicsCallback comicsCallback);

  /**
   * Checks if the given comic ID owns a favorite comic
   *
   * @param comicId
   *         Target comic ID
   *
   * @return True if it's a favorite comic. Otherwise returns false
   */
  boolean isFavoriteComic(int comicId);

  /**
   * Saves favorite comic
   *
   * @param comic
   *         Comic to be saved as a favorite
   */
  void saveFavorite(Comic comic);

  /**
   * Removes a comic from favorites
   *
   * @param comicId
   *         Comic to be removed
   */
  void removeFavorite(int comicId);

  /**
   * Comics callback to return the results
   */
  interface ComicsCallback {

    /**
     * Called if requesting comics has succeeded. Comics list and page information is returned
     *
     * @param comics
     *         The resulting comics list
     * @param pageInfo
     *         The page information related to the returning comics
     */
    void onComicsSuccess(@NonNull List<Comic> comics, @NonNull PageInfo pageInfo);

    /**
     * Called if an error occurred while loading the comics
     *
     * @param e
     *         The thrown exception with a message to the user
     */
    void onComicsFailure(MyComicsException e);
  }

}
