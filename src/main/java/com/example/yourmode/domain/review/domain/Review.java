package com.example.yourmode.domain.review.domain;

import com.example.yourmode.domain.business.domain.Business;
import com.example.yourmode.domain.member.domain.Member;
import com.example.yourmode.global.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private int score;

    @ElementCollection // 컬렉션 타입 필드 처리
    @CollectionTable(name = "review_image_files", joinColumns = @JoinColumn(name = "review_id"))
    @Column(name = "image_file", length = 255)
    private List<String> imageFiles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Member member;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false) // 외래 키 설정
    private Business business;

    @Builder
    public Review(String content, int score, List<String> imageFiles, Member member, Business business) {
        this.content = content;
        this.score = score;
        this.imageFiles = imageFiles;
        this.member = member;
        this.business = business;
    }
}
