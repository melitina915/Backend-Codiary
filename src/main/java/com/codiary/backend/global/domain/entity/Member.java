package com.codiary.backend.global.domain.entity;

import com.codiary.backend.global.domain.common.BaseEntity;
import com.codiary.backend.global.domain.entity.mapping.*;
import com.codiary.backend.global.domain.enums.MemberState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id", nullable = false, columnDefinition = "bigint")
  private Long memberId;

  @Column(name = "email", nullable = false, columnDefinition = "varchar(256)")
  private String email;

  @Column(name = "password", nullable = false, columnDefinition = "varchar(256)")
  private String password;

  @Column(name = "nickname", nullable = false, columnDefinition = "varchar(256)")
  private String nickname;

  @Column(name = "birth", columnDefinition = "varchar(256)")
  private String birth;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  public enum Gender {Male, Female}

  //계정상태
  @Column(name = "status", columnDefinition = "varchar(500)")
  @Enumerated(EnumType.STRING)
  private MemberState status;

  @Column(name = "inactiveDate", columnDefinition = "timestamp")
  //private Boolean inactiveDate;
  private LocalDateTime inacticeDate;

  @Column(name = "github", columnDefinition = "varchar(500)")
  private String github;

  @Column(name = "linkedin", columnDefinition = "varchar(500)")
  private String linkedin;

  @Column(name = "discord", columnDefinition = "varchar(500)")
  private String discord;

  @Column(name="introduction", columnDefinition = "varchar(500)")
  private String introduction;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TechStacks> techStackList = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Post> postList = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TeamMember> teamMemberList = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Authors> authorsList = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MemberProjectMap> memberProjectMapList = new ArrayList<>();

//  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
//  private List<Categories> catecoriesList = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> commentList = new ArrayList<>();

  @OneToMany(mappedBy = "fromMember", fetch = FetchType.LAZY)
  private List<Follow> followings = new ArrayList<>();

  @OneToMany(mappedBy = "toMember", fetch = FetchType.LAZY)
  private List<Follow> followers = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TeamFollow> followedTeams = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Bookmark> bookmarkList = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MemberCategory> memberCategoryList = new ArrayList<>();

  @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
  private MemberImage image;
}
