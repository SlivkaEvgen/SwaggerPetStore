package org.homework.repository;

import okhttp3.HttpUrl;
import okhttp3.Request;

public abstract class Parser<R,T,D> {

  abstract T getResponse(D d);

  abstract Request.Builder getRequestBuilder(String url);

  abstract HttpUrl getUrl(String urlUrl);

  abstract R gsonGet(String urls);

  abstract R gsonWithBody(T t);
}
