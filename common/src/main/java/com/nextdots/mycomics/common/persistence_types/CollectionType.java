package com.nextdots.mycomics.common.persistence_types;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.BaseDataType;
import com.j256.ormlite.misc.IOUtils;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Collections type for DB managers
 *
 * @author Antonio Jimenez
 * @version 3.0.0
 * @since 7/12/16
 */
public class CollectionType extends BaseDataType {

  private static final CollectionType singleton = new CollectionType();

  public static CollectionType getSingleton() {
    return singleton;
  }

  /**
   * Here for others to subclass.
   */
  protected CollectionType() {
    super(SqlType.SERIALIZABLE, new Class<?>[]{List.class, Set.class});
  }

  @Override
  public Object parseDefaultString(FieldType fieldType, String defaultStr)
          throws SQLException {
    throw new SQLException("Default values for serializable types are not supported");
  }

  @Override
  public Object resultToSqlArg(FieldType fieldType, DatabaseResults results, int columnPos)
          throws SQLException {
    return results.getBytes(columnPos);
  }

  @Override
  public Object sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos)
          throws SQLException {
    byte[] bytes = (byte[]) sqlArg;
    ObjectInputStream objInStream = null;
    try {
      objInStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
      return objInStream.readObject();
    } catch (Exception e) {
      throw SqlExceptionUtil.create("Could not read serialized object from byte array: "
              + Arrays.toString(bytes)
              + "(len "
              + bytes.length
              + ")", e);
    } finally {
      // we do this to give GC a hand with ObjectInputStream reference maps
      IOUtils.closeQuietly(objInStream);
    }
  }

  @Override
  public Object javaToSqlArg(FieldType fieldType, Object obj) throws SQLException {
    ObjectOutputStream objOutStream = null;
    try {
      ByteArrayOutputStream outStream = new ByteArrayOutputStream();
      objOutStream = new ObjectOutputStream(outStream);
      objOutStream.writeObject(obj);
      objOutStream.close();
      objOutStream = null;
      return outStream.toByteArray();
    } catch (Exception e) {
      throw SqlExceptionUtil.create("Could not write serialized object to byte array: " + obj, e);
    } finally {
      // we do this to give GC a hand with ObjectOutputStream reference maps
      IOUtils.closeQuietly(objOutStream);
    }
  }

  @Override
  public boolean isValidForField(Field field) {
    return List.class.isAssignableFrom(field.getType());
  }

  @Override
  public boolean isStreamType() {
    // can't do a getObject call beforehand so we have to check for nulls
    return true;
  }

  @Override
  public boolean isComparable() {
    return false;
  }

  @Override
  public boolean isAppropriateId() {
    return false;
  }

  @Override
  public boolean isArgumentHolderRequired() {
    return true;
  }

  @Override
  public Object resultStringToJava(FieldType fieldType, String stringValue, int columnPos)
          throws SQLException {
    throw new SQLException("Serializable type cannot be converted from string to Java");
  }

  @Override
  public Class<?> getPrimaryClass() {
    return List.class;
  }
}
