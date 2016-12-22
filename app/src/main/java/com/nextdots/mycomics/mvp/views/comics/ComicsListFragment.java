package com.nextdots.mycomics.mvp.views.comics;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nextdots.mycomics.R;
import com.nextdots.mycomics.business.exceptions.MyComicsException;
import com.nextdots.mycomics.business.interactors.comics.ComicsInteractor;
import com.nextdots.mycomics.common.model.comics.Comic;
import com.nextdots.mycomics.config.di.DiComponent;
import com.nextdots.mycomics.mvp.presenters.comics.list.ComicsListPresenter;
import com.nextdots.mycomics.mvp.presenters.comics.list.ComicsListView;
import com.nextdots.mycomics.mvp.views.common.BaseFragment;
import com.nextdots.mycomics.mvp.views.home.ActivityFragmentCallback;
import com.nextdots.mycomics.mvp.views.renderers.ComicRenderer;
import com.pedrogomez.renderers.ListAdapteeCollection;
import com.pedrogomez.renderers.RVRendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Comics list fragment
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public class ComicsListFragment extends BaseFragment<ComicsListPresenter>
        implements ComicsListView {

  /** Tag logs **/
  private static final String TAG = ComicsListFragment.class.getSimpleName();

  /** Indicates if the needed instance is a favorites list **/
  private static final String IS_FAVORITES_LIST = "IS_FAVORITES_LIST";

  /** Toolbar **/
  @BindView(R.id.toolbar)
  Toolbar mToolbar;

  /** Comics container **/
  @BindView(R.id.comics_container)
  ViewGroup mComicsContainer;

  /** Comics Recycler view **/
  @BindView(R.id.comics_rv)
  RecyclerView mComicsRv;

  /** Progress bar **/
  @BindView(R.id.progress_bar)
  ProgressBar mProgressBar;

  /** No items message TextView **/
  @BindView(R.id.no_items_message_tv)
  TextView mNoItemsMessageTv;

  /** Menu **/
  private Menu mMenu;

  /** Comics interactor **/
  @Inject
  ComicsInteractor mComicsInteractor;

  /** Comics renderer adapter **/
  private RVRendererAdapter<Comic> mAdapter;

  /** Action bar loaded callback **/
  private ActivityFragmentCallback mActivityFragmentCallback;

  /** Is favorites list **/
  private boolean mIsFavoritesList;

  /** UUID for this fragment **/
  private final String mUuid = UUID.randomUUID().toString();

  public ComicsListFragment() {
    // Required empty public constructor
  }

  /**
   * Creates a new instance of the comics list fragments.
   *
   * @param isFavoritesList
   *         Means if the current list is a favorites list
   *
   * @return A new instance of fragment ComicsListFragment.
   */
  public static ComicsListFragment newInstance(boolean isFavoritesList) {
    ComicsListFragment fragment = new ComicsListFragment();
    Bundle args = new Bundle();
    args.putBoolean(IS_FAVORITES_LIST, isFavoritesList);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      this.mIsFavoritesList = getArguments().getBoolean(IS_FAVORITES_LIST, false);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_comics_list, container, false);
    setHasOptionsMenu(!mIsFavoritesList);
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    if (mActivityFragmentCallback != null) {
      mActivityFragmentCallback.setActionBar(mToolbar);
    }
    mComicsRv.setLayoutManager(new LinearLayoutManager(getActivity()));
    setTitle();
    getPresenter().start();
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    mMenu = menu;
    if (mIsFavoritesList) {
      return;
    }
    inflater.inflate(R.menu.menu_comics_list, mMenu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.add_item:
        getPresenter().requestsNextPage();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  /**
   * Sets the corresponding title
   */
  private void setTitle() {
    int titleRes = mIsFavoritesList ? R.string.my_favorites : R.string.my_comics;
    mActivityFragmentCallback.setTitle(titleRes);
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof ActivityFragmentCallback) {
      mActivityFragmentCallback = (ActivityFragmentCallback) context;
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mActivityFragmentCallback = null;
  }

  @Override
  public ComicsListPresenter buildPresenter() {
    return new ComicsListPresenter(mComicsInteractor, this, mIsFavoritesList);
  }

  @Override
  protected void injectComponent(@NonNull DiComponent diComponent) {
    diComponent.inject(this);
  }

  @Override
  public void showLoading(boolean show) {
    enableOptions(!show);
    mProgressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
  }

  /**
   * Enables/disables toolbar options
   *
   * @param enable
   *         Enable/disable
   */
  private void enableOptions(boolean enable) {
    if (mMenu == null) {
      return;
    }
    int size = mMenu.size();
    for (int i = 0; i < size; i++) {
      mMenu.getItem(i).setEnabled(enable);
    }
  }

  @Override
  public void handleException(MyComicsException e) {
    Snackbar.make(mComicsContainer, e.getCustomMessage(), Snackbar.LENGTH_LONG).show();
  }

  @Override
  public String key() {
    return mUuid;
  }

  @Override
  public void showComicsList(@NonNull List<Comic> comics) {
    Log.d(TAG, "Comics to be displayed: " + comics);

    showEmptyMessage(false);
    ComicRenderer renderer = new ComicRenderer(getPresenter());
    RendererBuilder<Comic> builder = new RendererBuilder<>(renderer);
    mAdapter = new RVRendererAdapter<>(builder, new ListAdapteeCollection<>(comics));
    mComicsRv.setAdapter(mAdapter);
  }

  @Override
  public void addComicsList(@NonNull List<Comic> comics) {
    int currentSize = mAdapter.getItemCount();
    mAdapter.addAll(comics);
    int addedComicsSize = comics.size();
    mAdapter.notifyItemRangeInserted(currentSize, addedComicsSize);
    mComicsRv.smoothScrollToPosition(currentSize);
  }

  @Override
  public void showEmptyComicsListMessage() {
    showEmptyMessage(true);
    mNoItemsMessageTv.setText(mIsFavoritesList ? R.string.no_favorite_comics : R.string.no_comics);
  }

  /**
   * Shows/hides empty message and related views (comics Recycler View)
   *
   * @param show
   *         Shows/hides
   */
  private void showEmptyMessage(boolean show) {
    mNoItemsMessageTv.setVisibility(show ? View.VISIBLE : View.GONE);
    mComicsRv.setVisibility(!show ? View.VISIBLE : View.GONE);
  }

}
