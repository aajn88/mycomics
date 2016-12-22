package com.nextdots.mycomics.mvp.presenters.home;

import com.nextdots.mycomics.business.exceptions.MyComicsException;
import com.nextdots.mycomics.business.interactors.sign_in.SessionInteractor;
import com.nextdots.mycomics.common.model.session.User;
import com.nextdots.mycomics.common.utils.StringUtils;
import com.nextdots.mycomics.mvp.presenters.common.AbstractPresenter;

/**
 * Home presenter
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public class HomePresenter extends AbstractPresenter implements SessionInteractor.SignOutCallback {

  /** Name format **/
  private static final String NAME_FORMAT = "%s %s";

  /** Session interactor **/
  private final SessionInteractor mSessionInteractor;

  /** Home view **/
  private HomeView mHomeView;

  /**
   * Home presenter constructor
   *
   * @param homeView
   *         Home view
   */
  public HomePresenter(SessionInteractor sessionInteractor, HomeView homeView) {
    this.mHomeView = homeView;
    this.mSessionInteractor = sessionInteractor;
  }

  @Override
  public void start() {
    User user = mSessionInteractor.getCurrentSession();
    String displayName = StringUtils.format(NAME_FORMAT, user.getFirstName(), user.getLastName());
    mHomeView.loadUserInfo(displayName, user.getEmail(), user.getPicture());
    onMyComicsSelected();
  }

  @Override
  public void stop() {

  }

  /**
   * Called when my comics option is selected
   */
  public void onMyComicsSelected() {
    mHomeView.showComicsScreen();
  }

  /**
   * Called when my favorite comics option is selected
   */
  public void onMyFavoritesSelected() {
    mHomeView.showFavoritesComicsScreen();
  }

  /**
   * Called when users needs to log out
   */
  public void onLogOutSelected() {
    mSessionInteractor.signOutUser(this);
  }

  @Override
  public void onSignOutSuccess() {
    mHomeView.redirectToSplash();
  }

  @Override
  public void onSignOutFailure(MyComicsException e) {
    mHomeView.handleException(e);
  }

  /**
   * Called when back button is pressed
   */
  public void onBackPressed() {
    mHomeView.closeApp();
  }
}
