package com.nextdots.mycomics.persistence.managers.session;

import com.nextdots.mycomics.common.model.session.User;
import com.nextdots.mycomics.persistence.managers.common.CrudManager;

/**
 * Users manager for DB management
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 17/12/16
 */
public interface UsersManager extends CrudManager<User, Integer> {

  /**
   * Finds the first stored user
   *
   * @return Stored user
   */
  User findFirstUser();

}
