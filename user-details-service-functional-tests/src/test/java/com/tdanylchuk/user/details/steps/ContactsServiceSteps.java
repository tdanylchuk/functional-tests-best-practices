package com.tdanylchuk.user.details.steps;

import org.springframework.stereotype.Component;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Component
public class ContactsServiceSteps {

    public void expectGetUserContacts(long userId, String body) {
        stubFor(get(urlPathMatching("/contacts")).withQueryParam("userId", equalTo(String.valueOf(userId)))
                .willReturn(okJson(body)));
    }
}
