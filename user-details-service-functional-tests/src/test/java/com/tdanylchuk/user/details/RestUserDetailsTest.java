package com.tdanylchuk.user.details;

import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class RestUserDetailsTest extends BaseFunctionalTest {

    private static final long USER_ID = 32343L;

    private final String userContactsResponse = readFile("json/user-contacts.json");
    private final String userDetails = readFile("json/user-details.json");
    private final String expectedUserResponse = readFile("json/user.json");

    @Test
    public void shouldSaveAndRetrieveUserDetails() throws Exception {
        //when
        userDetailsServiceSteps.saveUserDetails(USER_ID, userDetails);
        //then
        String userDetails = userDetailsServiceSteps.getUserDetails(USER_ID);
        //expect
        JSONAssert.assertEquals(userDetails, userDetails, false);
    }

    @Test
    public void shouldSaveUserDetailsAndRetrieveUser() throws Exception {
        //when
        userDetailsServiceSteps.saveUserDetails(USER_ID, userDetails);
        //and
        contactsServiceSteps.expectGetUserContacts(USER_ID, userContactsResponse);
        //then
        String actualUserResponse = userDetailsServiceSteps.getUser(USER_ID);
        //expect
        JSONAssert.assertEquals(expectedUserResponse, actualUserResponse, false);
    }
}
