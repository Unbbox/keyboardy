package com.unbox.Keyboardy.entity;

import com.unbox.Keyboardy.constant.MemberRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {

    @GeneratedValue
    @Id
    private Long idx;

    @Column(unique = true, nullable = false)
    private String userId;

    private String password;

    @Column(unique=true, nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    private MemberRole role;
    
    // OAuth 소셜로그인 확인용
    private String provider; 

    // providerId : 소셜 로그인 한 유저의 고유 ID가 들어감
    private String providerId;
}
