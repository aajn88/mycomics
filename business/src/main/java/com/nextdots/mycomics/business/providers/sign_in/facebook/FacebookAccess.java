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
package com.nextdots.mycomics.business.providers.sign_in.facebook;

import com.facebook.AccessToken;
import com.facebook.Profile;

/**
 * This class describe the methods that must implement any class that execute le login of facebook
 * (request an accessToken for use it)
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
public interface FacebookAccess {

  /**
   * This method call the excecution of request an accessToken to FB for use it in queries related
   * to access data to the user
   */
  void accessFacebook();

  /**
   * This interface describe the methods that communicate the result of request the accessToken to
   * FB
   */
  interface FacebookAccessCallback {
    /**
     * This method return the AccessToken when the request to facebook its success
     *
     * @param profile
     *         Facebook profile
     * @param email
     *         User's email
     * @param accessToken
     *         with the data for execute queries to facebook about the user
     */
    void onFacebookAccessSuccess(Profile profile, String email, AccessToken accessToken);

    /**
     * This method communicate if the facebook process was cancelled
     */
    void onFacebookAccessCancelled();

    /**
     * This method communicate any failure in the accessToken process with Facebook
     *
     * @param t
     *         The exception that describe the error
     */
    void onFacebookAccessFailure(Throwable t);
  }
}
