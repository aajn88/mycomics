package com.nextdots.mycomics.common.session;

import com.nextdots.mycomics.common.providers.Provider;

import java.util.Date;

/**
 * This class contains user's basic information and session data
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
public class User {

  /** Local user's ID **/
  private int id;

  /** User's first name **/
  private String firstName;

  /** User's last name **/
  private String lastName;

  /** User's email **/
  private String email;

  /** User's picture **/
  private String picture;

  /** Session provider **/
  private Provider sessionProvider;

  /** Session token **/
  private String sessionToken;

  /** Expiration date **/
  private Date expirationDate;

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
  public String getSessionToken() {
    return sessionToken;
  }

  /**
   * @param sessionToken
   *         the sessionToken to set
   */
  public User setSessionToken(String sessionToken) {
    this.sessionToken = sessionToken;
    return this;
  }

  /**
   * @return the expirationDate
   */
  public Date getExpirationDate() {
    return expirationDate;
  }

  /**
   * @param expirationDate
   *         the expirationDate to set
   */
  public User setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
    return this;
  }
}
