package com.codiary.backend.global.converter;

import com.codiary.backend.global.domain.entity.Bookmark;
import com.codiary.backend.global.domain.entity.Follow;
import com.codiary.backend.global.domain.entity.Member;
import com.codiary.backend.global.domain.entity.Team;
import com.codiary.backend.global.domain.entity.mapping.MemberCategory;
import com.codiary.backend.global.domain.entity.mapping.TeamMember;
import com.codiary.backend.global.domain.entity.mapping.TechStacks;
import com.codiary.backend.global.web.dto.Member.FollowResponseDto;
import com.codiary.backend.global.web.dto.Member.MemberResponseDTO;
import com.codiary.backend.global.web.dto.Member.MemberSumResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberConverter {

    public static FollowResponseDto toManageFollowDto(Follow follow) {
        return FollowResponseDto.builder()
                .followId(follow.getFollowId())
                .followerId(follow.getFromMember().getMemberId())
                .followerName(follow.getFromMember().getNickname())
                .followingId(follow.getToMember().getMemberId())
                .followingName(follow.getToMember().getNickname())
                .followStatus(follow.getFollowStatus())
                .build();
    }

    public MemberSumResponseDto toFollowResponseDto(Member member) {
        return MemberSumResponseDto.builder()
                .memberId(member.getMemberId())
                .nickname(member.getNickname())
                .photoUrl((member.getImage() != null)
                        ? member.getImage().getImageUrl()
                        : "")
                .build();
    }

    public static List<MemberSumResponseDto> toFollowingResponseDto(List<Member> members) {
        return members.stream()
                .map(member -> MemberSumResponseDto.builder()
                        .memberId(member.getMemberId())
                        .nickname(member.getNickname())
                        .photoUrl((member.getImage() != null)
                                ? member.getImage().getImageUrl()
                                : "")
                        .build())
                .collect(Collectors.toList());
    }

    public static List<MemberSumResponseDto> toFollowerResponseDto(List<Member> members) {
        return members.stream()
                .map(member -> MemberSumResponseDto.builder()
                        .memberId(member.getMemberId())
                        .nickname(member.getNickname())
                        .photoUrl((member.getImage() != null)
                                ? member.getImage().getImageUrl()
                                : "")
                        .build())
                .collect(Collectors.toList());
    }


    public MemberResponseDTO.TechStacksDTO toTechStacksResponseDto(Member member) {
        return MemberResponseDTO.TechStacksDTO.builder()
                .memberId(member.getMemberId())
                .techStackList(member.getTechStackList()
                        .stream()
                        .map(TechStacks::getName)
                        .collect(Collectors.toList()))
                .build();
    }

    public static MemberResponseDTO.UserInfoDTO toMemberInfoResponseDto(Member member) {
        return MemberResponseDTO.UserInfoDTO.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .birth(member.getBirth())
                .introduction(member.getIntroduction())
                .githubUrl(member.getGithub())
                .linkedinUrl(member.getLinkedin())
                .discordUrl(member.getDiscord())
                .build();
    }

    // 회원별 북마크 리스트 조회
    public static MemberResponseDTO.BookmarkDTO toBookmarkDTO(Bookmark bookmark) {
        return MemberResponseDTO.BookmarkDTO.builder()
                .memberId(bookmark.getMember().getMemberId())
                .bookmarkId(bookmark.getId())
                .postId(bookmark.getPost().getPostId())
                .thumbnailImageUrl((bookmark.getPost().getThumbnailImage() != null)
                        ? bookmark.getPost().getThumbnailImage().getFileUrl()
                        : "")
                .postTitle(bookmark.getPost().getPostTitle())
                .nickname(bookmark.getPost().getMember().getNickname())
                .postBody(bookmark.getPost().getPostBody())
                .createdAt(bookmark.getPost().getCreatedAt())
                .build();



//        final String DEFAULT_PHOTO_URL = "default_photo_url"; // 기본값 설정
//        // Optional을 사용하여 photoUrl을 안전하게 추출
//        String photoUrl = Optional.ofNullable(bookmark.getPost().getPostFileList())
//                .filter(list -> !list.isEmpty())
//                .map(list -> list.get(0).getFileUrl())
//                .orElse("default_photo_url"); // 기본값을 설정합니다.
    }

    public static MemberResponseDTO.BookmarkListDTO toBookmarkListDTO(Page<Bookmark> bookmarkList) {
        List<MemberResponseDTO.BookmarkDTO> bookmarkDTOList = bookmarkList.stream()
                .map(MemberConverter::toBookmarkDTO).collect(Collectors.toList());

        return MemberResponseDTO.BookmarkListDTO.builder()
                .isLast(bookmarkList.isLast())
                .isFirst(bookmarkList.isFirst())
                .totalPage(bookmarkList.getTotalPages())
                .totalElements(bookmarkList.getTotalElements())
                .listSize(bookmarkDTOList.size())
                .bookmarkList(bookmarkDTOList)
                .build();
    }

    // 회원별 관심 카테고리탭 리스트 조회
    public static MemberResponseDTO.MemberCategoryDTO toMemberCategoryDTO(MemberCategory memberCategory) {
        return MemberResponseDTO.MemberCategoryDTO.builder()
                .memberId(memberCategory.getMember().getMemberId())
                .memberCategoryId(memberCategory.getMemberCategoryId())
                .categoryId(memberCategory.getCategories().getCategoryId())
                .categoryName(memberCategory.getCategories().getName())
                .createdAt(memberCategory.getCreatedAt())
                .build();
    }

    public static MemberResponseDTO.MemberCategoryListDTO toMemberCategoryListDTO(List<MemberCategory> memberCategoryList) {
        List<MemberResponseDTO.MemberCategoryDTO> memberCategoryDTOList = memberCategoryList.stream()
                .map(MemberConverter::toMemberCategoryDTO).collect(Collectors.toList());

        return MemberResponseDTO.MemberCategoryListDTO.builder()
                .listSize(memberCategoryDTOList.size())
                .memberCategoryList(memberCategoryDTOList)
                .build();
    }

    public static MemberResponseDTO.UserProfileDTO toProfileResponseDto(Member member, Member user) {
        return MemberResponseDTO.UserProfileDTO.builder()
                .currentMemberId(member.getMemberId())
                .userId(user.getMemberId())
                .userName(user.getNickname())
                .photoUrl((member.getImage() != null)
                        ? member.getImage().getImageUrl()
                        : "")
                .githubUrl(user.getGithub())
                .linkedinUrl(user.getLinkedin())
                .discordUrl(user.getDiscord())
                .introduction(user.getIntroduction())
                .techStacksList(user.getTechStackList().stream()
                        .map(TechStacks::getName)
                        .collect(Collectors.toList()))
                .teamList(user.getTeamMemberList().stream()
                        .map(TeamMember::getTeam)
                        .map(Team::getName)
                        .collect(Collectors.toList()))
                .myPage(user.getMemberId().equals(member.getMemberId()))
                .build();
    }

}
