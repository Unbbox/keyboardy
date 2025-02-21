package com.unbox.Keyboardy.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.unbox.Keyboardy.constant.MemberRole;
import com.unbox.Keyboardy.dto.MemberDto;
import com.unbox.Keyboardy.dto.OAuth.GoogleMemberDetails;
import com.unbox.Keyboardy.dto.OAuth.KaKaoMemberDetails;
import com.unbox.Keyboardy.dto.OAuth.NaverMemberDetails;
import com.unbox.Keyboardy.dto.OAuth.OAuth2MemberInfo;
import com.unbox.Keyboardy.dto.OAuth.OAuthMemberDto;
import com.unbox.Keyboardy.entity.Member;
import com.unbox.Keyboardy.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService{

    private final MemberRepository memberRepository;
    
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 소셜로그인 공통인증 방식으로 로그인 하면 뜨는 정보들
        String clientName = userRequest.getClientRegistration().getClientName();
        log.info("========== OAuth Loagin ==========");
        log.info("userRequest: {}", userRequest);
        log.info("clientName: {}", clientName);
        log.info("Token: {}", userRequest.getAccessToken());
        log.info("Client: {}", userRequest.getClientRegistration());
        log.info("==================================");

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("getAttributes: {}", oAuth2User.getAttributes());
        
        String provider = userRequest.getClientRegistration().getRegistrationId();
        
        OAuth2MemberInfo oAuth2MemberInfo = null;

        // 소셜 로그인 정보 저장
        if(provider.equals("google")) {
            log.info("구글 로그인");

            oAuth2MemberInfo = new GoogleMemberDetails(oAuth2User.getAttributes());
        } else if(provider.equals("naver")) {
            log.info("네이버 로그인");

            oAuth2MemberInfo = new NaverMemberDetails(oAuth2User.getAttributes());
            log.info("oAuth2MemberInfo: " + oAuth2MemberInfo);
        } else if(provider.equals("kakao")) {
            log.info("카카오 로그인");

            oAuth2MemberInfo = new KaKaoMemberDetails(oAuth2User.getAttributes());
            log.info("oAuth2MemberInfo: " + oAuth2MemberInfo);
        }

        // OAuth 정보
        String providerId = oAuth2MemberInfo.getProviderId();
        String email = oAuth2MemberInfo.getEmail();
        String loginId =  provider + "_" + providerId;
        String nickname = provider + "_" + oAuth2MemberInfo.getName();
        String phone = oAuth2MemberInfo.getPhone();
        
        // 전화번호가 없을경우(ex: 구글 로그인, 사용자가 입력 X 등)
        if (phone == null) {
            phone = "";
        }
        

        Member findMember = memberRepository.findByOAuthUserId(loginId);
        Member member;

        if(findMember == null) {
            member = saveSocialMember(loginId, email, nickname, phone, provider, providerId);
        } else {
            member = findMember;
        }
        
        return new OAuthMemberDto(member, oAuth2User.getAttributes());
    }

    private Member saveSocialMember(String userId, String email, String nickname, String phone, String provider, String providerId) {

        Member member = Member.builder()
        .userId(userId)
        .email(email)
        .nickname(nickname)
        .phone(phone)
        .role(MemberRole.MEMBER)
        .provider(provider)
        .providerId(providerId)
        .build();

        memberRepository.save(member);

        return member;
    }

    private MemberDto entityToDto(Member member) {
        return MemberDto.builder()
        .idx(member.getIdx())
        .userId(member.getUserId())
        .password(member.getPassword())
        .nickname(member.getNickname())
        .email(member.getEmail())
        .phone(member.getPhone())
        .build();
    }
}
