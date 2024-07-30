package com.codiary.backend.global.converter;

import com.codiary.backend.global.domain.entity.Member;
import com.codiary.backend.global.domain.entity.Post;
import com.codiary.backend.global.domain.entity.mapping.Categories;
import com.codiary.backend.global.web.dto.Post.PostRequestDTO;
import com.codiary.backend.global.web.dto.Post.PostResponseDTO;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PostConverter {

//    private static List<String> convertCategoriesToCategoryNames(List<String> categories) {
//        return categories.stream()
//                .map(Categories::getName)
//                .collect(Collectors.toList());
//    }
//
//    private static Set<Categories> convertCategoryNamesToCategories(Set<String> categoryNames) {
//        return categoryNames.stream()
//                .map(Categories::new) // String을 받는 생성자를 사용
//                .collect(Collectors.toSet());
//    }

    public static Post toPost(PostRequestDTO.CreatePostRequestDTO request) {
        //List<String> postCategories = new ArrayList<>(request.getPostCategory());
        return Post.builder()
                .postTitle(request.getPostTitle())
                .postBody(request.getPostBody())
                .postStatus(request.getPostStatus())
                .postCategory(new ArrayList<>(request.getPostCategory()))
                .postAccess(request.getPostAccess())
                .build();
    }

    public static PostResponseDTO.CreatePostResultDTO toCreateResultDTO(Post post) {
        //List<String> postCategories = convertCategoriesToCategoryNames(post.getPostCategory());
        return PostResponseDTO.CreatePostResultDTO.builder()
                .postId(post.getPostId())
                .memberId(post.getMember().getMemberId())
                .teamId(post.getTeam() != null ? post.getTeam().getTeamId() : null)
                .projectId(post.getProject().getProjectId())
                .postTitle(post.getPostTitle())
                .postBody(post.getPostBody())
                .postStatus(post.getPostStatus())
                .postCategory(String.join(", ", post.getPostCategory()))
                .coauthorIds(post.getAuthorsList().stream()
                        .map(author -> author.getMember().getMemberId())
                        .collect(Collectors.toSet()))
                .postAccess(post.getPostAccess())
                .postFileList(PostFileConverter.toPostFileListDTO(post.getPostFileList()))
                .build();
    }

    public static PostResponseDTO.UpdatePostResultDTO toUpdatePostResultDTO(Post post) {
        //List<String> postCategories = convertCategoriesToCategoryNames(post.getPostCategory());
        return PostResponseDTO.UpdatePostResultDTO.builder()
                .postId(post.getPostId())
                .memberId(post.getMember().getMemberId())
                .teamId(post.getTeam() != null ? post.getTeam().getTeamId() : null)
                .projectId(post.getProject().getProjectId())
                .postTitle(post.getPostTitle())
                .postBody(post.getPostBody())
                .postStatus(post.getPostStatus())
                .postCategory(String.join(", ", post.getPostCategory()))
                .coauthorIds(post.getAuthorsList().stream()
                        .map(author -> author.getMember().getMemberId())
                        .collect(Collectors.toSet()))
                .postAccess(post.getPostAccess())
                .build();
    }

    // Post 조회
    public static PostResponseDTO.PostPreviewDTO toPostPreviewDTO(Post post) {
        //List<String> postCategories = convertCategoriesToCategoryNames(post.getPostCategory());
        return PostResponseDTO.PostPreviewDTO.builder()
                .postId(post.getPostId())
                .memberId(post.getMember().getMemberId())
                .teamId(post.getTeam() != null ? post.getTeam().getTeamId() : null)
                .projectId(post.getProject().getProjectId())
                .postTitle(post.getPostTitle())
                .postBody(post.getPostBody())
                .postStatus(post.getPostStatus())
                .postCategory(String.join(", ", post.getPostCategory()))
                .coauthorIds(post.getAuthorsList().stream()
                        .map(author -> author.getMember().getMemberId())
                        .collect(Collectors.toSet()))
                .postAccess(post.getPostAccess())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    // Post 전체 리스트 조회
    public static PostResponseDTO.PostPreviewListDTO toPostPreviewListDTO(Page<Post> posts) {
        List<PostResponseDTO.PostPreviewDTO> postPreviewDTOList = posts.getContent().stream()
                .map(PostConverter::toPostPreviewDTO)
                .collect(Collectors.toList());

        return PostResponseDTO.PostPreviewListDTO.builder()
                .posts(postPreviewDTOList)
                .listSize(posts.getNumberOfElements())
                .totalPage(posts.getTotalPages())
                .totalElements(posts.getTotalElements())
                .isFirst(posts.isFirst())
                .isLast(posts.isLast())
                .build();
    }

    // 저자별 Post 조회
    public static PostResponseDTO.MemberPostPreviewDTO toMemberPostPreviewDTO(Post post) {
        //List<String> postCategories = convertCategoriesToCategoryNames(post.getPostCategory());
        return PostResponseDTO.MemberPostPreviewDTO.builder()
                .memberId(post.getMember().getMemberId())
                .postId(post.getPostId())
                .teamId(post.getTeam() != null ? post.getTeam().getTeamId() : null)
                .projectId(post.getProject().getProjectId())
                .postTitle(post.getPostTitle())
                .postBody(post.getPostBody())
                .postStatus(post.getPostStatus())
                .postCategory(String.join(", ", post.getPostCategory()))
                .coauthorIds(post.getAuthorsList().stream()
                        .map(author -> author.getMember().getMemberId())
                        .collect(Collectors.toSet()))
                .postAccess(post.getPostAccess())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
    // 저자별 Post 페이징 조회
    public static PostResponseDTO.MemberPostPreviewListDTO toMemberPostPreviewListDTO(Page<Post> posts) {
        List<PostResponseDTO.MemberPostPreviewDTO> memberPostPreviewDTOList = posts.getContent().stream()
                .map(PostConverter::toMemberPostPreviewDTO)
                .collect(Collectors.toList());

        return PostResponseDTO.MemberPostPreviewListDTO.builder()
                .posts(memberPostPreviewDTOList)
                .listSize(posts.getNumberOfElements())
                .totalPage(posts.getTotalPages())
                .totalElements(posts.getTotalElements())
                .isFirst(posts.isFirst())
                .isLast(posts.isLast())
                .build();
    }

    // 팀별 Post 조회
    public static PostResponseDTO.TeamPostPreviewDTO toTeamPostPreviewDTO(Post post) {
        //List<String> postCategories = convertCategoriesToCategoryNames(post.getPostCategory());
        return PostResponseDTO.TeamPostPreviewDTO.builder()
                .teamId(post.getTeam() != null ? post.getTeam().getTeamId() : null)
                .postId(post.getPostId())
                .memberId(post.getMember().getMemberId())
                .projectId(post.getProject().getProjectId())
                .postTitle(post.getPostTitle())
                .postBody(post.getPostBody())
                .postStatus(post.getPostStatus())
                .postCategory(String.join(", ", post.getPostCategory()))
                .coauthorIds(post.getAuthorsList().stream()
                        .map(author -> author.getMember().getMemberId())
                        .collect(Collectors.toSet()))
                .postAccess(post.getPostAccess())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    // 팀별 Post 페이징 조회
    public static PostResponseDTO.TeamPostPreviewListDTO toTeamPostPreviewListDTO(Page<Post> posts) {
        List<PostResponseDTO.TeamPostPreviewDTO> teamPostPreviewDTOList = posts.getContent().stream()
                .map(PostConverter::toTeamPostPreviewDTO)
                .collect(Collectors.toList());

        return PostResponseDTO.TeamPostPreviewListDTO.builder()
                .posts(teamPostPreviewDTOList)
                .listSize(posts.getNumberOfElements())
                .totalPage(posts.getTotalPages())
                .totalElements(posts.getTotalElements())
                .isFirst(posts.isFirst())
                .isLast(posts.isLast())
                .build();
    }


    // 프로젝트별 저자의 Post 조회
    public static PostResponseDTO.MemberPostInProjectPreviewDTO toMemberPostInProjectPreviewDTO(Post post) {
        //List<String> postCategories = convertCategoriesToCategoryNames(post.getPostCategory());
        return PostResponseDTO.MemberPostInProjectPreviewDTO.builder()
                .projectId(post.getProject().getProjectId())
                .memberId(post.getMember().getMemberId())
                .postId(post.getPostId())
                .teamId(post.getTeam() != null ? post.getTeam().getTeamId() : null)
                .postTitle(post.getPostTitle())
                .postBody(post.getPostBody())
                .postStatus(post.getPostStatus())
                .postCategory(String.join(", ", post.getPostCategory()))
                .coauthorIds(post.getAuthorsList().stream()
                        .map(author -> author.getMember().getMemberId())
                        .collect(Collectors.toSet()))
                .postAccess(post.getPostAccess())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    // 프로젝트별 저자의 Post 페이징 조회
    public static PostResponseDTO.MemberPostInProjectPreviewListDTO toMemberPostInProjectPreviewListDTO(Page<Post> posts) {
        List<PostResponseDTO.MemberPostInProjectPreviewDTO> memberPostInProjectPreviewListDTO = posts.getContent().stream()
                .map(PostConverter::toMemberPostInProjectPreviewDTO)
                .collect(Collectors.toList());

        return PostResponseDTO.MemberPostInProjectPreviewListDTO.builder()
                .posts(memberPostInProjectPreviewListDTO)
                .listSize(posts.getNumberOfElements())
                .totalPage(posts.getTotalPages())
                .totalElements(posts.getTotalElements())
                .isFirst(posts.isFirst())
                .isLast(posts.isLast())
                .build();
    }

    // 프로젝트별 팀의 Post 조회
    public static PostResponseDTO.TeamPostInProjectPreviewDTO toTeamPostInProjectPreviewDTO(Post post) {
        //List<String> postCategories = convertCategoriesToCategoryNames(post.getPostCategory());
        return PostResponseDTO.TeamPostInProjectPreviewDTO.builder()
                .projectId(post.getProject().getProjectId())
                .teamId(post.getTeam().getTeamId())
                .memberId(post.getMember().getMemberId())
                .postId(post.getPostId())
                .postTitle(post.getPostTitle())
                .postBody(post.getPostBody())
                .postStatus(post.getPostStatus())
                .postCategory(String.join(", ", post.getPostCategory()))
                .coauthorIds(post.getAuthorsList().stream()
                        .map(author -> author.getMember().getMemberId())
                        .collect(Collectors.toSet()))
                .postAccess(post.getPostAccess())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    // 프로젝트별 팀의 Post 페이징 조회
    public static PostResponseDTO.TeamPostInProjectPreviewListDTO toTeamPostInProjectPreviewListDTO(Page<Post> posts) {
        List<PostResponseDTO.TeamPostInProjectPreviewDTO> teamPostInProjectPreviewListDTO = posts.getContent().stream()
                .map(PostConverter::toTeamPostInProjectPreviewDTO)
                .collect(Collectors.toList());

        return PostResponseDTO.TeamPostInProjectPreviewListDTO.builder()
                .posts(teamPostInProjectPreviewListDTO)
                .listSize(posts.getNumberOfElements())
                .totalPage(posts.getTotalPages())
                .totalElements(posts.getTotalElements())
                .isFirst(posts.isFirst())
                .isLast(posts.isLast())
                .build();
    }

    // 팀별 멤버의 Post 조회
    public static PostResponseDTO.MemberPostInTeamPreviewDTO toMemberPostInTeamPreviewDTO(Post post) {
        //List<String> postCategories = convertCategoriesToCategoryNames(post.getPostCategory());
        return PostResponseDTO.MemberPostInTeamPreviewDTO.builder()
                .teamId(post.getTeam().getTeamId())
                .memberId(post.getMember().getMemberId())
                .postId(post.getPostId())
                .projectId(post.getProject().getProjectId())
                .postTitle(post.getPostTitle())
                .postBody(post.getPostBody())
                .postStatus(post.getPostStatus())
                .postCategory(String.join(", ", post.getPostCategory()))
                .coauthorIds(post.getAuthorsList().stream()
                        .map(author -> author.getMember().getMemberId())
                        .collect(Collectors.toSet()))
                .postAccess(post.getPostAccess())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    // 팀별 멤버의 Post 페이징 조회
    public static PostResponseDTO.MemberPostInTeamPreviewListDTO toMemberPostInTeamPreviewListDTO(Page<Post> posts) {
        List<PostResponseDTO.MemberPostInTeamPreviewDTO> memberPostInTeamPreviewListDTO = posts.getContent().stream()
                .map(PostConverter::toMemberPostInTeamPreviewDTO)
                .collect(Collectors.toList());

        return PostResponseDTO.MemberPostInTeamPreviewListDTO.builder()
                .posts(memberPostInTeamPreviewListDTO)
                .listSize(posts.getNumberOfElements())
                .totalPage(posts.getTotalPages())
                .totalElements(posts.getTotalElements())
                .isFirst(posts.isFirst())
                .isLast(posts.isLast())
                .build();
    }

    // 특정 Post의 인접한 Post 조회 (이전, 다음 Post 조회)
    public static PostResponseDTO.PostAdjacentDTO.PostAdjacentPreviewDTO toPostAdjacentPreviewDTO(Post post) {
        if (post == null) return null;

        //List<String> postCategories = convertCategoriesToCategoryNames(post.getPostCategory());
        return PostResponseDTO.PostAdjacentDTO.PostAdjacentPreviewDTO.builder()
                .postId(post.getPostId())
                .memberId(post.getMember().getMemberId())
                .teamId(post.getTeam() != null ? post.getTeam().getTeamId() : null)
                .projectId(post.getProject().getProjectId())
                .postTitle(post.getPostTitle())
                .postBody(post.getPostBody())
                .postStatus(post.getPostStatus())
                .postCategory(String.join(", ", post.getPostCategory()))
                .coauthorIds(post.getAuthorsList().stream()
                        .map(author -> author.getMember().getMemberId())
                        .collect(Collectors.toSet()))
                .postAccess(post.getPostAccess())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    public static PostResponseDTO.PostAdjacentDTO toPostAdjacentDTO(Post.PostAdjacent adjacent) {
        return PostResponseDTO.PostAdjacentDTO.builder()
                .hadOlder(adjacent.getOlderPost() != null)
                .hasLater(adjacent.getLaterPost() != null)
                .olderPost(toPostAdjacentPreviewDTO(adjacent.getOlderPost()))
                .laterPost(toPostAdjacentPreviewDTO(adjacent.getLaterPost()))
                .build();
    }

}
