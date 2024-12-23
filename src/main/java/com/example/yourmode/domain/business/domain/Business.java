package com.example.yourmode.domain.business.domain;

import com.example.yourmode.domain.customer.domain.Customer;
import com.example.yourmode.domain.member.domain.Member;
import com.example.yourmode.domain.review.domain.Review;
import com.example.yourmode.global.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Business extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BusinessType businessType;

    private String name;

    private String address;

    private String page;

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Customer> customers = new ArrayList<>();

    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Review> reviews = new ArrayList<>();

    @Setter
    @ManyToOne
    @JoinColumn(name = "owner_id") // 일단 널 허용 todo: 널 막아야 함
    private Member member;

    @Builder
    public Business(BusinessType businessType, String name, String address, String page) {
        this.businessType = businessType;
        this.name = name;
        this.address = address;
        this.page = page;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        customer.setBusiness(this); // 연관 관계 설정
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
        customer.setBusiness(null); // 연관 관계 해제
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setBusiness(this); // 연관 관계 설정
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setBusiness(null); // 연관 관계 해제
    }

}
