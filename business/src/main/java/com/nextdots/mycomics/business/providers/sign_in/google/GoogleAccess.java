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
package com.nextdots.mycomics.business.providers.sign_in.google;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * This class describe the methods that must implement any class that execute the Google login
 * (request an accessToken for use it)
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 16/12/16
 */
public interface GoogleAccess {

  /**
   * This method call the execution of request the Google sign in
   */
  void accessGoogle();

  /**
   * This interface describe the methods that communicate the result of request the accessToken to
   * Google
   */
  interface GoogleAccessCallback {
    /**
     * This method return the AccessToken when the request to google its success
     *
     * @param profile
     *         Google profile
     */
    void onGoogleAccessSuccess(GoogleSignInAccount profile);

    /**
     * This method communicate any failure in the sign in process with google
     *
     * @param t
     *         The exception that describe the error
     */
    void onGoogleAccessFailure(Throwable t);
  }
}
