package com.example.yourmode.domain.business.status;

import com.example.yourmode.global.common.exception.code.BaseCodeDto;
import com.example.yourmode.global.common.exception.code.BaseCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BusinessErrorStatus implements BaseCodeInterface {

    EMPTY_BUSINESS(HttpStatus.NOT_FOUND, "BUSINESS404", "사업체를 찾을 수 없습니다."),
    UNABLE_TO_CREATE_BUSINESS(HttpStatus.FORBIDDEN, "BUSINESS403", "사업체를 생성할 수 없는 역할입니다."),
    UNABLE_TO_HANDLE_BUSINESS(HttpStatus.FORBIDDEN, "BUSINESS403", "자신의 사업체가 아닌 것은 수정, 삭제 할 수 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final boolean isSuccess = false;
    private final String code;
    private final String message;

    @Override
    public BaseCodeDto getCode() {
        return BaseCodeDto.builder()
                .httpStatus(httpStatus)
                .isSuccess(isSuccess)
                .code(code)
                .message(message)
                .build();
    }
}

