package eu.phaf.song.wiremock;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import eu.phaf.song.api.wiremock.DefaultApiMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.havingExactly;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.noValues;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;

public class SetupWiremock {

  public static void main(String[] args) {
    WireMock.configureFor(8080);
    stubFor(DefaultApiMockServer.stubDatesGet200(DefaultApiMockServer.datesGet200ResponseSample1()).withHeader("Accept", matching(".*")));
    stubFor(DefaultApiMockServer.stubSongsGet200(DefaultApiMockServer.songsGet200ResponseSample1()).withHeader("Accept", matching(".*")));
    stubFor(stubSubmitSongsPost200().withHeader("Accept", matching(".*")));
    stubFor(eu.phaf.song.api.wiremock.admin.DefaultApiMockServer.stubAuthLoginPost200("""
      {
        "username":"admin",
        "password":"admin"
      }
      """, eu.phaf.song.api.wiremock.admin.DefaultApiMockServer.authLoginPost200ResponseSample1()).withHeader("Accept", matching(".*")).withHeader("Authorization", noValues()));
    stubFor(stubAdminDatesPost201().withHeader("Accept", matching(".*")));
    stubFor(eu.phaf.song.api.wiremock.admin.DefaultApiMockServer.stubAdminSongsForDateGet200("2024-10-03", eu.phaf.song.api.wiremock.admin.DefaultApiMockServer.adminSongsForDateGet200ResponseSample1()).withHeader("Accept", matching(".*")));
  }

  public static MappingBuilder stubSubmitSongsPost200() {
    MappingBuilder stub = post(urlPathEqualTo("/submit-songs"))
      .withHeader("Content-Type", havingExactly("application/json"))
      .willReturn(aResponse()
        .withStatus(200)
      );


    return stub;
  }

  public static MappingBuilder stubAdminDatesPost201() {
    MappingBuilder stub = post(urlPathEqualTo("/admin/dates"))
      .withHeader("Content-Type", havingExactly("application/json"))
      .withHeader("Authorization", matching(".*"))
      .willReturn(aResponse()
        .withStatus(201)
      );


    return stub;
  }
}
