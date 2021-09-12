package org.homework.config;

import okhttp3.OkHttpClient;

public class HttpConnect {

  private static OkHttpClient OK_HTTP_CLIENT;

  public static OkHttpClient getInstance() {
    if (OK_HTTP_CLIENT == null) {
      synchronized (HttpConnect.class) {
        if (OK_HTTP_CLIENT == null) {
          OK_HTTP_CLIENT = new OkHttpClient();
        }
      }
    }
    return OK_HTTP_CLIENT;
  }
}
