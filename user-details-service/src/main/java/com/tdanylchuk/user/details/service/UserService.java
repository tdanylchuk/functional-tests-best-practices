package com.tdanylchuk.user.details.service;

import com.tdanylchuk.user.details.contacts.ContactsServiceClient;
import com.tdanylchuk.user.details.model.User;
import com.tdanylchuk.user.details.model.UserContacts;
import com.tdanylchuk.user.details.model.UserDetails;
import com.tdanylchuk.user.details.repository.UserDetailsEntity;
import com.tdanylchuk.user.details.repository.UserDetailsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDetailsRepository userDetailsRepository;
    private final ContactsServiceClient contactsServiceClient;

    public User getUser(long userId) {
        UserDetailsEntity userDetailsEntity = userDetailsRepository.getOne(userId);
        UserDetails userDetails = UserDetails.fromEntity(userDetailsEntity);
        UserContacts userContacts = contactsServiceClient.getUserContacts(userId);
        return User.of(userDetails, userContacts);
    }

    public void saveDetails(long userId, UserDetails userDetails) {
        UserDetailsEntity entity = userDetails.toEntity(userId);
        userDetailsRepository.save(entity);
    }

    public UserDetails getDetails(long userId) {
        UserDetailsEntity userDetailsEntity = userDetailsRepository.getOne(userId);
        return UserDetails.fromEntity(userDetailsEntity);
    }
}
