/**
 * CONFIDENTIAL
 * <p/>
 * [2016] All Rights Reserved.
 * <p/>
 * NOTICE:  All information contained herein is, and remains
 * the property of Click Delivery and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Click Delivery
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Click Delivery.
 */
package com.nextdots.mycomics.persistence.managers.common;

import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.nextdots.mycomics.common.utils.StringUtils;
import com.nextdots.mycomics.persistence.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

import static android.R.attr.id;

/**
 * This class implements the basic operations of a Database entity management (CRUD)
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 9/13/16
 */
public abstract class CrudManagerImpl<Entity, Id> implements CrudManager<Entity, Id> {

  /** Logs Tag **/
  private static final String TAG_LOG = CrudManager.class.getName();

  /** Entity's DAO **/
  private final Dao<Entity, Id> dao;

  /** Entity's Class **/
  private final Class<Entity> clazz;

  /**
   * This is the main constructor of the CrudManager
   *
   * @param helper
   *         The DBHelper
   *
   * @throws SQLException
   *         If there's an error creating the Entity's DAO
   */
  public CrudManagerImpl(DatabaseHelper helper) throws SQLException {
    this.clazz = getEntityClass();
    this.dao = helper.getDao(clazz);
  }

  /**
   * This method should return the entity's class
   *
   * @return The entity's class
   */
  protected abstract Class<Entity> getEntityClass();

  @Override
  public boolean createOrUpdate(Entity entity) {
    boolean created = false;

    try {
      dao.createOrUpdate(entity);
      created = true;
    } catch (SQLException e) {
      Log.e(TAG_LOG, String.format("An error occurs creating an element of the Entity {%s}", clazz),
              e);
    }

    return created;
  }

  @Override
  public Entity findById(Id id) {
    Entity entity = null;

    try {
      entity = dao.queryForId(id);
    } catch (SQLException e) {
      Log.e(TAG_LOG,
              StringUtils.format("Error occurs finding an element of the Entity {%s} with id {%s}",
                      clazz, id), e);
    }

    return entity;
  }

  @Override
  public List<Entity> all() {
    List<Entity> all = null;
    try {
      all = dao.queryForAll();
    } catch (SQLException e) {
      Log.e(TAG_LOG,
              StringUtils.format("An error occurs requesting all elements of the entity {%s}",
                      clazz),
              e);
    }
    return all;
  }

  @Override
  public Entity deleteById(Id id) {
    Entity entity = findById(id);
    try {
      if (entity != null) {
        dao.deleteById(id);
      }
    } catch (SQLException e) {
      Log.e(TAG_LOG,
              StringUtils.format("Error occurs deleting an element of the entity {%s} with Id {%s}",
                      clazz, id), e);
    }
    return entity;
  }

  @Override
  public void deleteAll() {
    try {
      dao.deleteBuilder().delete();
    } catch (SQLException e) {
      Log.e(TAG_LOG,
              StringUtils.format("Error occurs deleting all elements into the Database", clazz, id),
              e);
    }
  }

  @Override
  public Dao<Entity, Id> getDao() {
    return dao;
  }
}
