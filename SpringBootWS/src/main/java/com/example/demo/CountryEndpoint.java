package com.example.demo;

import gs_producing_web_service.Country;
import gs_producing_web_service.Currency;
import gs_producing_web_service.GetCountryRequest;
import gs_producing_web_service.GetCountryResponse;
import gs_producing_web_service.ObjectFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {

  private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
  @ResponsePayload
  public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
    GetCountryResponse response = new GetCountryResponse();

    Country country = new ObjectFactory().createCountry();

    country.setName("Portugal");
    country.setCapital("Lisboa");
    country.setCurrency(Currency.EUR);

    response.setCountry(country);
    return response;
  }

}
