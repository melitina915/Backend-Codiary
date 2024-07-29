package com.codiary.backend.global.repository;

import com.codiary.backend.global.domain.entity.Member;
import com.codiary.backend.global.domain.entity.Post;
import com.codiary.backend.global.domain.entity.Project;
import com.codiary.backend.global.domain.entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByPostTitleContainingIgnoreCaseOrderByCreatedAtDesc(String postTitle, Pageable pageable);
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
    //List<Post> findAllByMember(Member member);
    Page<Post> findByMemberOrderByCreatedAtDescPostIdDesc(Member member, Pageable pageable);
    Page<Post> findByTeamOrderByCreatedAtDescPostIdDesc(Team team, Pageable pageable);
    Page<Post> findByProjectAndMemberOrderByCreatedAtDescPostIdDesc(Project project, Member member, Pageable pageable);
    Page<Post> findByProjectAndTeamOrderByCreatedAtDescPostIdDesc(Project project, Team team, Pageable pageable);
    Page<Post> findByTeamAndMemberOrderByCreatedAtDescPostIdDesc(Team team, Member member, Pageable pageable);
    boolean existsByTeam(Team team);
    boolean existsByProject(Project project);
    boolean existsByMember(Member member);

    Optional<Post> findTopByPostIdLessThanOrderByCreatedAtDescPostIdDesc(Long postId);
    Optional<Post> findTopByPostIdGreaterThanOrderByCreatedAtAscPostIdAsc(Long postId);

    List<Post> findByMemberAndCreatedAtBetweenOrderByCreatedAtAsc(Member member, LocalDateTime startDate, LocalDateTime endDate);

    @EntityGraph(attributePaths = {"project"})
    List<Post> findByMemberAndCreatedAtBetweenOrderByCreatedAtAsc(@Param("member") Member member, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}

