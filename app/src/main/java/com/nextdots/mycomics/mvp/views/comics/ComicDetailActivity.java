package com.nextdots.mycomics.mvp.views.comics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.nextdots.mycomics.R;
import com.nextdots.mycomics.business.exceptions.MyComicsException;
import com.nextdots.mycomics.business.interactors.comics.ComicsInteractor;
import com.nextdots.mycomics.common.model.comics.Comic;
import com.nextdots.mycomics.config.di.DiComponent;
import com.nextdots.mycomics.mvp.presenters.comics.detail.ComicDetailPresenter;
import com.nextdots.mycomics.mvp.presenters.comics.detail.ComicDetailView;
import com.nextdots.mycomics.mvp.views.common.BaseActivity;
import com.nextdots.mycomics.mvp.views.renderers.ComicDetailRenderer;

import javax.inject.Inject;

import butterknife.BindView;

public class ComicDetailActivity extends BaseActivity<ComicDetailPresenter>
        implements ComicDetailView {

  /** Comic extra **/
  private static final String COMIC_EXTRA = "COMIC_EXTRA";

  /** Comics interactor **/
  @Inject
  ComicsInteractor mComicsInteractor;

  /** Toolbar **/
  @BindView(R.id.toolbar)
  Toolbar mToolbar;

  /** Main comics container **/
  @BindView(R.id.comics_container)
  ViewGroup mMainComicsContainer;

  /** Comic container **/
  @BindView(R.id.comic_container)
  ViewGroup mComicContainer;

  /** Progress bar **/
  @BindView(R.id.progress_bar)
  ProgressBar mProgressBar;

  /**
   * Start comic detail activity given a context
   *
   * @param activity
   *         Origin activity
   * @param comic
   *         Comic to be shown
   * @param containerTransition
   *         View for transition container
   * @param imageTransition
   *         View for transition image
   * @param titleTransition
   *         View for transition title
   */
  public static void start(Activity activity, Comic comic, View containerTransition,
                           View imageTransition,
                           View titleTransition) {
    Intent comicDetailIntent = new Intent(activity, ComicDetailActivity.class);
    comicDetailIntent.putExtra(COMIC_EXTRA, comic);

    Pair<View, String>[] pairs = new Pair[3];
    pairs[0] = new Pair<>(containerTransition, activity.getString(R.string.container_transition));
    pairs[1] = new Pair<>(imageTransition, activity.getString(R.string.image_transition));
    pairs[2] = new Pair<>(titleTransition, activity.getString(R.string.title_transition));
    ActivityOptionsCompat options =
            ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);

    activity.startActivity(comicDetailIntent, options.toBundle());
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_comic_detail);
    Comic comic = (Comic) getIntent().getSerializableExtra(COMIC_EXTRA);

    setSupportActionBar(mToolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onBackPressed();
      }
    });

    getPresenter().start(comic);
  }

  @Override
  protected void injectComponent(DiComponent diComponent) {
    diComponent.inject(this);
  }

  @Override
  public ComicDetailPresenter buildPresenter() {
    return new ComicDetailPresenter(mComicsInteractor, this);
  }

  @Override
  public void showLoading(boolean show) {

  }

  @Override
  public void handleException(MyComicsException e) {

  }

  @Override
  public void renderComic(Comic comic) {
    ComicDetailRenderer comicDetailRenderer =
            new ComicDetailRenderer(comic, getLayoutInflater(), mComicContainer,
                    mMainComicsContainer);
    comicDetailRenderer.render();
  }
}
