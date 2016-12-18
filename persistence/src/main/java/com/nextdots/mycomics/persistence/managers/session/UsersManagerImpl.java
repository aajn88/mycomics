package com.nextdots.mycomics.persistence.managers.session;

import android.util.Log;

import com.nextdots.mycomics.common.model.session.User;
import com.nextdots.mycomics.persistence.DatabaseHelper;
import com.nextdots.mycomics.persistence.managers.common.CrudManagerImpl;

import java.sql.SQLException;

/**
 * Implementation of the {@link UsersManager} for DB management
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 17/12/16
 */
public class UsersManagerImpl extends CrudManagerImpl<User, Integer> implements UsersManager {

  /** Tag for logs **/
  private static final String TAG = UsersManagerImpl.class.getSimpleName();

  /**
   * This is the main constructor of the CrudManager
   *
   * @param helper
   *         The DBHelper
   *
   * @throws SQLException
   *         If there's an error creating the Entity's DAO
   */
  public UsersManagerImpl(DatabaseHelper helper) throws SQLException {
    super(helper);
  }

  @Override
  public User findFirstUser() {
    User entity = null;

    try {
      entity = getDao().queryBuilder().queryForFirst();
    } catch (SQLException e) {
      Log.e(TAG, "Error occurs finding the first user", e);
    }

    return entity;
  }

  @Override
  protected Class<User> getEntityClass() {
    return User.class;
  }
}
