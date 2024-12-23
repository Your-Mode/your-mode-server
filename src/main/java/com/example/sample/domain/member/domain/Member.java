package com.example.sample.domain.member.domain;

import com.example.sample.global.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    private String clientId;

    // 편의상 DB에 저장, 실제로는 저장하지 않게 해야 함
    @Setter
    private String refreshToken;


    @Builder
    public Member(String name, String email, LoginType loginType, String clientId) {
        this.name = name;
        this.email = email;
        this.role = Role.MEMBER;
        this.loginType = loginType;
        this.clientId = clientId;
    }

    public void changeRole(Role role) {
        this.role = role;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}