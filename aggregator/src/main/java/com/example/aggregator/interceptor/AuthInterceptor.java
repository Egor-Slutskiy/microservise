package com.example.aggregator.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements RequestInterceptor {
  private final HttpServletRequest request;

  @Override
  public void apply(RequestTemplate template) {
    // or RequestContextHolder
    final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (authorization != null) {
      template.header(HttpHeaders.AUTHORIZATION, authorization);
    }
  }
}
