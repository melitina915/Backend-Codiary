package com.codiary.backend.global.apiPayload.code.status;

import com.codiary.backend.global.apiPayload.code.BaseCode;
import com.codiary.backend.global.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 유저 관련 응답
    MEMBER_OK(HttpStatus.OK, "MEMBER_1000", "성공입니다."),
    // 팀 관련 응답
    TEAM_OK(HttpStatus.OK, "TEAM_2000", "성공입니다."),
    // 포스트 관련 응답
    POST_OK(HttpStatus.OK, "POST_3000", "성공입니다."),
    // 코멘트 관련 응답
    COMMENT_OK(HttpStatus.OK, "COMMENT_4000", "성공입니다."),
    // 포스트사진 관련 응답
    POSTPHOTO_OK(HttpStatus.OK, "POSTPHOTO_5000", "성공입니다."),
    // 북마크 관련 응답
    BOOKMARK_OK(HttpStatus.OK, "BOOKMARK_6000", "성공입니다."),
    // 카테고리 관련 응답
    CATEGORY_OK(HttpStatus.OK, "CATEGORY_7000", "성공입니다."),
    // 회원별 관심 카테고리 관련 응답
    MEMBERCATEGORY_OK(HttpStatus.OK, "MEMBERCATEGORY_8000", "성공입니다."),
    // 프로젝트 관련 응답
    PROJECT_OK(HttpStatus.OK, "PROJECT_9000", "성공입니다.")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}