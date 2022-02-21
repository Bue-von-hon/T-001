package com.kim.account.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String nickname;

    private String password;

    private boolean emailVerified;

    private String emailCheckToken;

    private LocalDateTime emailCheckTokenGeneratedAt;

    private LocalDateTime joinedAt;

    private String bio;

    private String url;

    private String occupation;

    private String location;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String profileImage;

    private boolean dramaTalkCreatedByEmail;

    private boolean dramaTalkCreatedByWeb = true;

    private boolean dramaTalkEnrollmentResultByEmail;

    private boolean dramaTalkEnrollmentResultByWeb = true;

    private boolean dramaTalkUpdatedByEmail;

    private boolean dramaTalkUpdatedByWeb = true;

}
