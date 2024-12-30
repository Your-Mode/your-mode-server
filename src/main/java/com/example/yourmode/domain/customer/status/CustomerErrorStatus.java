package com.example.yourmode.domain.customer.status;

import com.example.yourmode.global.common.exception.code.BaseCodeDto;
import com.example.yourmode.global.common.exception.code.BaseCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomerErrorStatus implements BaseCodeInterface {

    EMPTY_CUSTOMER(HttpStatus.NOT_FOUND, "CUSTOMER404", "고객을 찾을 수 없습니다."),
    EXIST_CUSTOMER(HttpStatus.CONFLICT, "CUSTOMER409", "이미 존재하는 고객입니다."),
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

