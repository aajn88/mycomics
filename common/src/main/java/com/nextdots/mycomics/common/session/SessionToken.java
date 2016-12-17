package com.nextdots.mycomics.common.session;

import java.io.Serializable;
import java.util.Date;

/**
 * Session token information
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 17/12/16
 */
public class SessionToken implements Serializable {

  /** User's ID **/
  private String userId;

  /** Session token **/
  private String token;

  /** Token expires **/
  private Date expires;

  /**
   * Default constructor
   */
  public SessionToken() {
  }

  /**
   * Session token constructor
   *
   * @param userId
   *         The provider's user ID
   * @param token
   *         Session token
   * @param expires
   *         Expiration date
   */
  public SessionToken(String userId, String token, Date expires) {
    this.userId = userId;
    this.token = token;
    this.expires = expires;
  }

  /**
   * @return the userId
   */
  public String getUserId() {
    return userId;
  }

  /**
   * @param userId
   *         the userId to set
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * @return the token
   */
  public String getToken() {
    return token;
  }

  /**
   * @param token
   *         the token to set
   */
  public void setToken(String token) {
    this.token = token;
  }

  /**
   * @return the expires
   */
  public Date getExpires() {
    return expires;
  }

  /**
   * @param expires
   *         the expires to set
   */
  public void setExpires(Date expires) {
    this.expires = expires;
  }

  @Override
  public String toString() {
    return "SessionToken{" +
            "userId='" + userId + '\'' +
            ", token='" + token + '\'' +
            ", expires=" + expires +
            '}';
  }
}
