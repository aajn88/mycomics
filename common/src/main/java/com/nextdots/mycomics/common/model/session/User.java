package com.nextdots.mycomics.common.model.session;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.nextdots.mycomics.common.providers.Provider;

/**
 * This class contains user's basic information and session data
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
public class User {

  /** Local user's ID **/
  @DatabaseField(generatedId = true)
  private int id;

  /** User's first name **/
  @DatabaseField(canBeNull = false)
  private String firstName;

  /** User's last name **/
  @DatabaseField(canBeNull = false)
  private String lastName;

  /** User's email **/
  @DatabaseField(canBeNull = false)
  private String email;

  /** User's picture **/
  @DatabaseField
  private String picture;

  /** Session provider **/
  @DatabaseField(canBeNull = false, dataType = DataType.ENUM_STRING)
  private Provider sessionProvider;

  /** Session token information **/
  @DatabaseField(dataType = DataType.SERIALIZABLE)
  private SessionToken sessionToken;

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id
   *         the id to set
   */
  public User setId(int id) {
    this.id = id;
    return this;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName
   *         the firstName to set
   */
  public User setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName
   *         the lastName to set
   */
  public User setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email
   *         the email to set
   */
  public User setEmail(String email) {
    this.email = email;
    return this;
  }

  /**
   * @return the picture
   */
  public String getPicture() {
    return picture;
  }

  /**
   * @param picture
   *         the picture to set
   */
  public User setPicture(String picture) {
    this.picture = picture;
    return this;
  }

  /**
   * @return the sessionProvider
   */
  public Provider getSessionProvider() {
    return sessionProvider;
  }

  /**
   * @param sessionProvider
   *         the sessionProvider to set
   */
  public User setSessionProvider(Provider sessionProvider) {
    this.sessionProvider = sessionProvider;
    return this;
  }

  /**
   * @return the sessionToken
   */
  public SessionToken getSessionToken() {
    return sessionToken;
  }

  /**
   * @param sessionToken
   *         the sessionToken to set
   */
  public User setSessionToken(SessionToken sessionToken) {
    this.sessionToken = sessionToken;
    return this;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", picture='" + picture + '\'' +
            ", sessionProvider=" + sessionProvider +
            ", sessionToken=" + sessionToken +
            '}';
  }
}
