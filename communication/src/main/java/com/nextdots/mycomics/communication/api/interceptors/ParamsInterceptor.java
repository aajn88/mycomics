package com.nextdots.mycomics.communication.api.interceptors;

import com.nextdots.mycomics.common.constants.MyComicsConstants;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Interceptor that adds params such as timestamp, api key and hash
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 * @since 18/12/16
 */
public class ParamsInterceptor implements Interceptor {

  /** Timestamp param **/
  private static final String TIMESTAMP_PARAM = "ts";

  /** API Key param **/
  private static final String API_KEY_PARAM = "apikey";

  /** Hash param **/
  private static final String HASH_PARAM = "hash";

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request request = chain.request();

    request = addParamsRequest(request);

    return chain.proceed(request);
  }

  /**
   * Adds params to the request
   *
   * @param request
   *         Request to be modified
   *
   * @return Modified request
   */
  private Request addParamsRequest(Request request) {
    HttpUrl url = request.url();
    HttpUrl.Builder urlBuilder = url.newBuilder();
    // Timestamp
    String timestamp = Long.toString(Calendar.getInstance().getTimeInMillis());
    urlBuilder.addQueryParameter(TIMESTAMP_PARAM, timestamp);

    // API key
    String apiKey = MyComicsConstants.sComicsServerApiKey;
    urlBuilder.addQueryParameter(API_KEY_PARAM, apiKey);

    // Hash
    String concatValue = timestamp + MyComicsConstants.sComicsServerPrivateKey + apiKey;
    String hash = new String(Hex.encodeHex(DigestUtils.md5(concatValue)));
    urlBuilder.addQueryParameter(HASH_PARAM, hash);

    return request.newBuilder().url(urlBuilder.build()).build();
  }

}
