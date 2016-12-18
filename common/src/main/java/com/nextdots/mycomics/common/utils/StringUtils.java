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
package com.nextdots.mycomics.common.utils;

/**
 * Utility class that offers standard and common methods for String management
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @version 3.0.0
 * @since 19/8/16
 */
public final class StringUtils {

  /** String null **/
  private static final String STRING_NULL = "null";

  /** Private constructor to avoid instances **/
  private StringUtils() {
  }

  /**
   * This method performs String.format method but every null value will be replaced to "null" *
   *
   * @param string
   *         Target String * @param params String parametters
   *
   * @return Formatted String
   */
  public static String format(String string, Object... params) {
    for (int i = 0; i < params.length; i++) {
      if (params[i] == null) {
        params[i] = STRING_NULL;
      }
    }
    return String.format(string, params);
  }
}
