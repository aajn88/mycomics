package com.nextdots.mycomics.mvp.presenters.comics;

import android.support.annotation.NonNull;

import com.nextdots.mycomics.common.model.comics.Comic;
import com.nextdots.mycomics.mvp.presenters.common.BaseView;

import java.util.List;

/**
 * Comics list view interface
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public interface ComicsListView extends BaseView {

  /**
   * Shows comics list and replaces the existing one
   *
   * @param comics
   *         Comics to be shown
   */
  void showComicsList(@NonNull List<Comic> comics);

  /**
   * Adds the given comics list to the existing list and updates the view
   *
   * @param comics
   *         Comics to be added to the existing list
   */
  void addComicsList(@NonNull List<Comic> comics);

}
