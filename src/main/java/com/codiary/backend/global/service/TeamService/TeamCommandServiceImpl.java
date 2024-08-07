package com.codiary.backend.global.service.TeamService;

import com.codiary.backend.global.apiPayload.ApiResponse;
import com.codiary.backend.global.apiPayload.code.status.SuccessStatus;
import com.codiary.backend.global.domain.entity.*;
import com.codiary.backend.global.repository.TeamBannerImageRepository;
import com.codiary.backend.global.repository.TeamProfileImageRepository;
import com.codiary.backend.global.repository.TeamRepository;
import com.codiary.backend.global.repository.UuidRepository;
import com.codiary.backend.global.s3.AmazonS3Manager;
import com.codiary.backend.global.web.dto.Member.MemberResponseDTO;
import com.codiary.backend.global.web.dto.Team.TeamRequestDTO;
import com.codiary.backend.global.web.dto.Team.TeamResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TeamCommandServiceImpl implements TeamCommandService {

  private final TeamRepository teamRepository;
  private final UuidRepository uuidRepository;
  private final AmazonS3Manager s3Manager;
  private final TeamBannerImageRepository bannerImageRepository;
  private final TeamProfileImageRepository profileImageRepository;

  @Override
  @Transactional
  public Team createTeam(TeamRequestDTO.CreateTeamRequestDTO request) {
    Team team = Team.builder()
        .name(request.getName())
        .intro(request.getIntro())
        .profilePhoto(request.getProfilePhoto())
        .github(request.getGithub())
        .email(request.getEmail())
        .linkedin(request.getLinkedIn())
        .instagram(request.getInstagram())
        .build();

    return teamRepository.save(team);
  }

  @Override
  @Transactional
  public Team updateTeam(Long teamId, TeamRequestDTO.UpdateTeamDTO request) {
    Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalArgumentException("Invalid team ID"));

    team.setName(request.getName());
    team.setIntro(request.getIntro());
    team.setGithub(request.getGithub());
    team.setLinkedin(request.getLinkedIn());
    team.setInstagram(request.getInstagram());

    return teamRepository.save(team);
  }

  @Override
  public ApiResponse<TeamResponseDTO.TeamImageDTO> updateTeamBannerImage(Long teamId, TeamRequestDTO.TeamImageRequestDTO request) {
    Team team = teamRepository.findById(teamId).orElseThrow(); // 예외 처리 필요

    String uuid = UUID.randomUUID().toString();
    Uuid savedUuid = uuidRepository.save(Uuid.builder().uuid(uuid).build());
    String fileUrl = s3Manager.uploadFile(s3Manager.generatePostName(savedUuid), request.getImage());

    TeamBannerImage bannerImage = TeamBannerImage.builder()
            .imageUrl(fileUrl)
            .team(team)
            .build();

    TeamBannerImage savedImage = bannerImageRepository.save(bannerImage);
    TeamResponseDTO.TeamImageDTO response = new TeamResponseDTO.TeamImageDTO(savedImage.getImageUrl());
    return ApiResponse.onSuccess(SuccessStatus.TEAM_OK, response);
  }

  @Override
  public ApiResponse<TeamResponseDTO.TeamImageDTO> updateTeamProfileImage(Long teamId, TeamRequestDTO.TeamImageRequestDTO request) {
    Team team = teamRepository.findById(teamId).orElseThrow(); // 예외 처리 필요

    String uuid = UUID.randomUUID().toString();
    Uuid savedUuid = uuidRepository.save(Uuid.builder().uuid(uuid).build());
    String fileUrl = s3Manager.uploadFile(s3Manager.generatePostName(savedUuid), request.getImage());

    TeamProfileImage profileImage = TeamProfileImage.builder()
            .imageUrl(fileUrl)
            .team(team)
            .build();

    TeamProfileImage savedImage = profileImageRepository.save(profileImage);
    TeamResponseDTO.TeamImageDTO response = new TeamResponseDTO.TeamImageDTO(savedImage.getImageUrl());
    return ApiResponse.onSuccess(SuccessStatus.TEAM_OK, response);
  }


}
