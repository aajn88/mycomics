package com.nextdots.mycomics.mvp.views.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nextdots.mycomics.R;
import com.nextdots.mycomics.business.exceptions.MyComicsException;
import com.nextdots.mycomics.business.interactors.sign_in.SessionInteractor;
import com.nextdots.mycomics.config.di.DiComponent;
import com.nextdots.mycomics.mvp.presenters.home.HomePresenter;
import com.nextdots.mycomics.mvp.presenters.home.HomeView;
import com.nextdots.mycomics.mvp.views.comics.ComicsListFragment;
import com.nextdots.mycomics.mvp.views.common.BaseActivity;
import com.nextdots.mycomics.mvp.views.launch.MainActivity;
import com.nextdots.mycomics.utils.ImageUtils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Home activity in which the navigation views are shown
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
public class HomeActivity extends BaseActivity<HomePresenter>
        implements HomeView, NavigationView.OnNavigationItemSelectedListener,
        ActivityFragmentCallback {

  /** Tag for logs **/
  private static final String TAG = HomeActivity.class.getSimpleName();

  /** Drawer layout **/
  @BindView(R.id.drawer_layout)
  DrawerLayout mDrawerLayout;

  /** Navigation view **/
  @BindView(R.id.nav_view)
  NavigationView mNavigationView;

  /** Session interactor **/
  @Inject
  SessionInteractor mSessionInteractor;

  /**
   * Starts the {@link HomeActivity}
   *
   * @param context
   *         Origin context
   */
  public static void start(Context context) {
    context.startActivity(new Intent(context, HomeActivity.class));
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    getPresenter().start();
  }

  @Override
  protected void injectComponent(DiComponent diComponent) {
    diComponent.inject(this);
  }

  @Override
  public HomePresenter buildPresenter() {
    return new HomePresenter(mSessionInteractor, this);
  }

  @Override
  public void showLoading(boolean show) {
    // No view to show loading
  }

  @Override
  public void handleException(MyComicsException e) {

  }

  @Override
  public void loadUserInfo(String name, String email, String pictureUrl) {
    View headerView = mNavigationView.getHeaderView(0);
    TextView mUserNameTv = (TextView) headerView.findViewById(R.id.user_name_tv);
    TextView mUserEmailTv = (TextView) headerView.findViewById(R.id.user_email_tv);
    ImageView mUserAvatarIv = (ImageView) headerView.findViewById(R.id.user_avatar_iv);
    mUserNameTv.setText(name);
    mUserEmailTv.setText(email);
    ImageUtils.displayImage(mUserAvatarIv, pictureUrl, null);
  }

  @Override
  public void showComicsScreen() {
    replaceComicsScreen(false);
    selectMenuItem(R.id.nav_my_comics);
  }

  @Override
  public void showFavoritesComicsScreen() {
    replaceComicsScreen(true);
    selectMenuItem(R.id.nav_my_favorites);
  }

  @Override
  public void redirectToSplash() {
    MainActivity.start(this);
    finishAffinity();
  }

  @Override
  public void closeApp() {
    finishAffinity();
  }

  /**
   * Selects a menu item from navigation view
   *
   * @param itemId
   *         Item ID to be selected
   */
  private void selectMenuItem(@IdRes int itemId) {
    cleanSelection();
    mNavigationView.getMenu().findItem(itemId).setChecked(true);
  }

  /**
   * Cleans navigation view selections
   */
  private void cleanSelection() {
    Menu menu = mNavigationView.getMenu();
    for (int i = 0; i < menu.size(); i++) {
      MenuItem menuItem = menu.getItem(i);
      menuItem.setChecked(false);
    }
  }

  /**
   * Shows comics screen depending if is a favorite screen
   *
   * @param isFavoritesScreen
   *         Is a favorites screen?
   */
  private void replaceComicsScreen(boolean isFavoritesScreen) {
    try {
      getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main_container,
              ComicsListFragment.newInstance(isFavoritesScreen)).commit();
    } catch (IllegalStateException e) {
      Log.e(TAG, "Error instantiating fragment", e);
    }
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      getPresenter().onBackPressed();
    }
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    switch (id) {
      case R.id.nav_my_comics:
        getPresenter().onMyComicsSelected();
        break;
      case R.id.nav_my_favorites:
        getPresenter().onMyFavoritesSelected();
        break;
      case R.id.nav_log_out:
        getPresenter().onLogOutSelected();
        break;
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override
  public void setActionBar(@NonNull Toolbar toolbar) {
    setSupportActionBar(toolbar);
  }

  @Override
  public void setSupportActionBar(@Nullable Toolbar toolbar) {
    super.setSupportActionBar(toolbar);

    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, mDrawerLayout, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    mDrawerLayout.addDrawerListener(toggle);
    toggle.syncState();

    mNavigationView.setNavigationItemSelectedListener(this);
  }

}
