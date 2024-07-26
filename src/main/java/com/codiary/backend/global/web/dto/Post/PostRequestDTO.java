package com.codiary.backend.global.web.dto.Post;

import com.codiary.backend.global.domain.enums.PostAccess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

public class PostRequestDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreatePostRequestDTO {
        private Long teamId;
        private String postTitle;
        private String postBody;
        private Boolean postStatus;
        private String postCategory;
        private PostAccess postAccess;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdatePostDTO {
        private String postTitle;
        private String postBody;
        private Boolean postStatus;
        private String postCategory;
        private PostAccess postAccess;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateVisibilityRequestDTO {
        private Boolean postStatus;  // 공개(true) / 비공개(false) 상태
    }


    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateCoauthorRequestDTO {
        private List<Long> memberIds;
    }


}
