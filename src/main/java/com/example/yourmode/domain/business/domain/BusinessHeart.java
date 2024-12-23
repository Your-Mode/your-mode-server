package com.example.yourmode.domain.business.domain;

import com.example.yourmode.domain.member.domain.Member;
import com.example.yourmode.global.common.base.BaseEntity;
import com.example.yourmode.global.common.enums.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BusinessHeart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;

    @Builder
    public BusinessHeart(Member member, Business business) {
        this.member = member;
        this.business = business;
        this.status = Status.ACTIVE; // 기본값 활성화
    }
}
