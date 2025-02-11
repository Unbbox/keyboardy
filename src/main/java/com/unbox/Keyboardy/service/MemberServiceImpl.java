package com.unbox.Keyboardy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.unbox.Keyboardy.constant.MemberRole;
import com.unbox.Keyboardy.dto.MemberDto;
import com.unbox.Keyboardy.entity.Member;
import com.unbox.Keyboardy.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService{

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Long createUser(MemberDto memberDto) throws IllegalStateException {

        // 이메일 중복 체크
        validateDuplicateEmail(memberDto.getEmail());
        // 닉네임 중복 체크
        validateDuplicateNickname(memberDto.getNickname());

        // 비밀번호 암호화
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        // 권한 부여
        memberDto.setRole(MemberRole.MEMBER);
        
        return memberRepository.save(dtoToEntity(memberDto)).getIdx();
    }

    // 로그인
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("로그인 요청");
        Optional<Member> result = memberRepository.findByUserId(username);
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        Member member = result.get();

        List<GrantedAuthority> authorites = new ArrayList<>();
        authorites.add(new SimpleGrantedAuthority(MemberRole.MEMBER.name()));

        return new User(member.getUserId(), member.getPassword(), authorites);
    }

    // 중복 아이디 검사
    @Override
    public Boolean checkUserId(String userId) {
        Optional<Member> idResult = memberRepository.findOptionalByUserId(userId);

        // 이미 아이디가 존재하는 경우
        if (idResult.isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    // 중복 닉네임 검사
    public void validateDuplicateNickname(String nickname) throws IllegalStateException {
        Optional<Member> result = memberRepository.findOptionalByNickname(nickname);

        if(result.isPresent()) {
            throw new IllegalStateException("이미 사용중인 닉네임입니다.");
        }
    }

    // 중복 이메일 검사
    public void validateDuplicateEmail(String email) throws IllegalStateException {
        Optional<Member> result = memberRepository.findByEmail(email);

        if(result.isPresent()) {
            throw new IllegalStateException("이미 가입된 이메일 입니다.");
        }
    }
}
