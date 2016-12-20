package com.nextdots.mycomics.persistence.managers.comics;

import com.nextdots.mycomics.common.model.comics.Comic;
import com.nextdots.mycomics.persistence.DatabaseHelper;
import com.nextdots.mycomics.persistence.managers.common.CrudManagerImpl;

import java.sql.SQLException;

/**
 * Comics manager implementation
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 19/12/16
 */
public class ComicsManagerImpl extends CrudManagerImpl<Comic, Integer> implements ComicsManager {

  /**
   * This is the main constructor of the CrudManager
   *
   * @param helper
   *         The DBHelper
   *
   * @throws SQLException
   *         If there's an error creating the Entity's DAO
   */
  public ComicsManagerImpl(DatabaseHelper helper)
          throws SQLException {
    super(helper);
  }

  @Override
  protected Class<Comic> getEntityClass() {
    return Comic.class;
  }
}
