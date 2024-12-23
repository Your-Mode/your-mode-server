package com.example.yourmode.domain.member.domain;

import com.example.yourmode.domain.business.domain.Business;
import com.example.yourmode.domain.customer.domain.Customer;
import com.example.yourmode.domain.review.domain.Review;
import com.example.yourmode.global.common.base.BaseEntity;
import com.example.yourmode.global.common.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private Status status;

    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    private String clientId;

    // 편의상 DB에 저장, 실제로는 저장하지 않게 해야 함
    @Setter
    private String refreshToken;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Business> businesses = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Customer> customers = new ArrayList<>(); // 내가 고객인 경우

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Review> reviews = new ArrayList<>(); // 내가 리뷰 작성자인 경우

    @Builder
    public Member(String name, String email, LoginType loginType, String clientId) {
        this.name = name;
        this.email = email;
        this.role = Role.MEMBER;
        this.loginType = loginType;
        this.clientId = clientId;
        this.status = Status.ACTIVE;
    }

    public void changeRole(Role role) {
        this.role = role;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void addBusiness(Business business) {
        businesses.add(business);
        business.setMember(this); // 연관 관계 설정
    }

    public void removeBusiness(Business business) {
        businesses.remove(business);
        business.setMember(null); // 연관 관계 해제
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        customer.setMember(this); // 연관 관계 설정
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
        customer.setMember(null); // 연관 관계 해제
    }

}