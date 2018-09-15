package com.tdanylchuk.user.details.repository;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsEntity {

    @Id
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;

}
