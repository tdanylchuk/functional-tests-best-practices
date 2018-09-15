package com.tdanylchuk.user.details.contacts;

import com.tdanylchuk.user.details.model.UserContacts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class ContactsServiceClient {

    private final RestTemplate restTemplate;
    private final String contactsServiceUrl;

    public ContactsServiceClient(final RestTemplateBuilder restTemplateBuilder,
                                 @Value("${contacts.service.url}") final String contactsServiceUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.contactsServiceUrl = contactsServiceUrl;
    }

    public UserContacts getUserContacts(long userId) {
        URI uri = UriComponentsBuilder.fromHttpUrl(contactsServiceUrl + "/contacts")
                .queryParam("userId", userId).build().toUri();
        return restTemplate.getForObject(uri, UserContacts.class);
    }

}
