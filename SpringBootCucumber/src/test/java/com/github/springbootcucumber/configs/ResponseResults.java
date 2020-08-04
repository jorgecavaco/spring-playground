package com.github.springbootcucumber.configs;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;

public class ResponseResults {
  private final ClientHttpResponse theResponse;
  private final String body;

  ResponseResults(final ClientHttpResponse response) throws IOException {
    this.theResponse = response;

    final InputStream bodyInputStream = response.getBody();
    final StringWriter stringWriter = new StringWriter();

    IOUtils.copy(bodyInputStream, stringWriter, StandardCharsets.UTF_8);

    this.body = stringWriter.toString();
  }

  ClientHttpResponse getTheResponse() {
    return theResponse;
  }

  String getBody() {
    return body;
  }
}
