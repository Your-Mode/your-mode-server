package com.example.yourmode.domain.customer.entity;

import com.example.yourmode.domain.business.entity.Business;
import com.example.yourmode.domain.member.entity.Member;
import com.example.yourmode.global.common.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50) //일단 닉네임 길이 제한 50자
    private String nikeName;

    // 전화 번호 형식으로만 들어가게
    @Pattern(regexp = "^01(?:0|1|[6-9])-(\\d{3}|\\d{4})-(\\d{4})$")
    private String phone;

    // 길이 제한 필요함
    private String memo;

    private String pdfFile;

    @ElementCollection // 컬렉션 타입 필드 처리
    @CollectionTable(name = "customer_image_files", joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "image_file", length = 255)
    private List<String> imageFiles;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_member_id")
    private Member member; //고객의 Member 엔티티

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", nullable = false)
    private Business business;


    @Builder
    public Customer(String nikeName, String phone, String memo, String pdfFile, List<String> imageFiles) {
        this.nikeName = nikeName;
        this.phone = phone;
        this.memo = memo;
        this.pdfFile = pdfFile;
        this.imageFiles = imageFiles;
    }
}
