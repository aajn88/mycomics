package com.nextdots.mycomics.mvp.views.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.nextdots.mycomics.R;
import com.nextdots.mycomics.business.interactors.comics.ComicsInteractor;
import com.nextdots.mycomics.config.di.DiComponent;
import com.nextdots.mycomics.mvp.presenters.home.HomePresenter;
import com.nextdots.mycomics.mvp.presenters.home.HomeView;
import com.nextdots.mycomics.mvp.views.comics.ComicsListFragment;
import com.nextdots.mycomics.mvp.views.common.BaseActivity;

import javax.inject.Inject;

/**
 * Home activity in which the navigation views are shown
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
public class HomeActivity extends BaseActivity<HomePresenter>
        implements HomeView, NavigationView.OnNavigationItemSelectedListener,
        ActionBarLoadedCallback {

  /** Comics interactor **/
  @Inject
  ComicsInteractor mComicsInteractor;

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
    return new HomePresenter(this);
  }

  @Override
  public void showLoading(boolean show) {
    // No view to show loading
  }

  @Override
  public void showComicsScreen() {
    replaceComicsScreen(false);
  }

  @Override
  public void showFavoritesComicsScreen() {
    replaceComicsScreen(true);
  }

  /**
   * Shows comics screen depending if is a favorite screen
   *
   * @param isFavoritesScreen
   *         Is a favorites screen?
   */
  private void replaceComicsScreen(boolean isFavoritesScreen) {
    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_main_container,
            ComicsListFragment.newInstance(isFavoritesScreen)).commit();
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.home, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      // Handle the camera action
    } else if (id == R.id.nav_gallery) {

    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

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

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

}
