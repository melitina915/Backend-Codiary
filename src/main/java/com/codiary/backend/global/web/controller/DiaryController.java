package com.codiary.backend.global.web.controller;

import com.codiary.backend.global.apiPayload.ApiResponse;
import com.codiary.backend.global.service.DiaryService.DiaryCommandService;
import com.codiary.backend.global.service.DiaryService.DiaryQueryService;
import com.codiary.backend.global.web.dto.Diary.DiaryResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/diaries")
@Slf4j
public class DiaryController {
    //private final DiaryCommandService diaryCommandService;
    //private final DiaryQueryService diaryQueryService;

    // 글 생성하기
    @PostMapping()
    @Operation(
            summary = "글 생성 API"
            , description = "글을 생성합니다."
            //, security = @SecurityRequirement(name = "accessToken")
    )
    public ApiResponse<DiaryResponseDTO> createDiary(
    ){
        return null;
    }

    // 글 상세 조회
    @GetMapping("/{diaryId}")
    @Operation(
            summary = "글 상세 조회 API"
            , description = "글을 상세 조회합니다. Param으로 diaryId를 입력하세요"
            //, security = @SecurityRequirement(name = "accessToken")
    )
    public ApiResponse<DiaryResponseDTO> findDiary(
    ){
        return null;
    }

    // 글 수정하기
    @PatchMapping("/{diaryId}")
    @Operation(
            summary = "글 수정 API"
            , description = "글을 수정합니다. Param으로 diaryId를 입력하세요"
            //, security = @SecurityRequirement(name = "accessToken")
    )
    public ApiResponse<DiaryResponseDTO> updateDiary(
    ){
        return null;
    }

    // 글 삭제하기
    @DeleteMapping("/{diaryId}")
    @Operation(
            summary = "글 삭제 API"
            , description = "글을 삭제합니다. Param으로 memberId를 입력하세요"
            //, security = @SecurityRequirement(name = "accessToken")
    )
    public ApiResponse<DiaryResponseDTO> deleteDiary(
    ){
        return null;
    }

}
