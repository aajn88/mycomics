package com.nextdots.mycomics.mvp.views.renderers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nextdots.mycomics.common.model.comics.Comic;

/**
 * Basic comic renderer
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public class ComicDetailRenderer extends ComicRenderer {

  /**
   * Comic detail renderer constructor for an existing view
   *
   * @param comicView
   *         Created comic view
   */
  public ComicDetailRenderer(Comic comic, LayoutInflater layoutInflater, View comicView,
                             ViewGroup parent) {
    super(comicView);
    onCreate(comic, layoutInflater, parent);
  }

  @Override
  public void render() {
    super.render();
    // TODO: Add other render items such as description, dates, creators, etc.
  }

}
