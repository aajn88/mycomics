package com.nextdots.mycomics.mvp.presenters.comics.detail;

import com.nextdots.mycomics.common.model.comics.Comic;
import com.nextdots.mycomics.mvp.presenters.common.BaseView;

/**
 * Comic detail view interface
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 21/12/16
 */
public interface ComicDetailView extends BaseView {

  /**
   * Renders the given comic
   *
   * @param comic
   *         Comic to be render
   */
  void renderComic(Comic comic);
}
