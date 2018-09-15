package com.tdanylchuk.user.details.model;

import com.tdanylchuk.user.details.repository.UserDetailsEntity;
import lombok.Value;

@Value(staticConstructor = "of")
public class UserDetails {
    String firstName;
    String lastName;

    public static UserDetails fromEntity(UserDetailsEntity entity) {
        return UserDetails.of(entity.getFirstName(), entity.getLastName());
    }

    public UserDetailsEntity toEntity(long userId) {
        return new UserDetailsEntity(userId, firstName, lastName);
    }
}
