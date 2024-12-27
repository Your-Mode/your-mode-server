package com.example.yourmode.domain.file.entity;

import com.example.yourmode.global.common.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String fileName;

    private String fileOriginName;

    private String filePath;

    private String fileType;
}
