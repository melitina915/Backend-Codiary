package com.codiary.backend.global.service.TeamService;

import com.codiary.backend.global.apiPayload.ApiResponse;
import com.codiary.backend.global.domain.entity.Member;
import com.codiary.backend.global.web.dto.Team.TeamResponseDTO;
import org.springframework.stereotype.Service;
import com.codiary.backend.global.web.dto.Team.TeamResponseDTO;

import java.util.List;

@Service
public interface TeamQueryService {

    ApiResponse<TeamResponseDTO.TeamImageDTO> getBannerImage(Long teamId);

    ApiResponse<TeamResponseDTO.TeamImageDTO> getProfileImage(Long teamId);

    TeamResponseDTO.TeamCheckResponseDTO getTeamById(Long teamId);

    Boolean isFollowingTeam(Long teamId, Member fromMember);

    List<Member> getTeamFollowers(Long teamId);
}