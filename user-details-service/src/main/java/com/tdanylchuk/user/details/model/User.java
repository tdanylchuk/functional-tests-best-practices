package com.tdanylchuk.user.details.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class User {
    UserDetails userDetails;
    UserContacts userContacts;
}
