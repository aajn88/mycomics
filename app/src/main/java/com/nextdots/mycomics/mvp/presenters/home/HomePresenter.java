package com.nextdots.mycomics.mvp.presenters.home;

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
public class HomePresenter extends AbstractPresenter {

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
    mHomeView.showComicsScreen();
  }

  @Override
  public void stop() {

  }
}
