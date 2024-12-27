package com.example.yourmode.domain.business.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BusinessType {
    PERSONAL_COLOR("퍼스널컬러"),
    SKELETON_DIAGNOSIS("골격 진단"),
    MAKEUP_CLASS("메이크업 클래스"),
    ETC("기타");

    public final String toContent;
}

