package com.unbox.Keyboardy.service;

import com.unbox.Keyboardy.dto.MemberDto;
import com.unbox.Keyboardy.entity.Member;

public interface MemberService {

    Long createUser(MemberDto userDto);

    Boolean checkUserId(String userId);

    public default MemberDto entityToDto(Member user) {
        return MemberDto.builder()
        .userId(user.getUserId())
        .password(user.getPassword())
        .nickname(user.getNickname())
        .email(user.getEmail())
        .phone(user.getPhone())
        .role(user.getRole())
        .build();
    }

    public default Member dtoToEntity(MemberDto dto) {
        return Member.builder()
        .userId(dto.getUserId())
        .password(dto.getPassword())
        .nickname(dto.getNickname())
        .email(dto.getEmail())
        .phone(dto.getPhone())
        .role(dto.getRole())
        .build();
    }
} 
