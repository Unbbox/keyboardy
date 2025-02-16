// package com.unbox.Keyboardy.service;

// import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
// import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
// import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
// import org.springframework.security.oauth2.core.user.OAuth2User;
// import org.springframework.stereotype.Service;

// import com.unbox.Keyboardy.dto.MemberDto;
// import com.unbox.Keyboardy.entity.Member;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.log4j.Log4j2;

// @Log4j2
// @Service
// @RequiredArgsConstructor
// public class Oauth2UserService extends DefaultOAuth2UserService{
    
//     @Override
//     public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//         // 소셜로그인 공통인증 방식으로 로그인 하면 뜨는 정보들
//         String clientName = userRequest.getClientRegistration().getClientName();
//         log.info("========== OAuth Loagin ==========");
//         log.info("userRequest: {}", userRequest);
//         log.info("clientName: {}", clientName);
//         log.info("Token: {}", userRequest.getAccessToken());
//         log.info("Client: {}", userRequest.getClientRegistration());
//         log.info("==================================");

//         OAuth2User oAuth2User = super.loadUser(userRequest);
//         log.info("getAttributes: {}", oAuth2User.getAttributes());

//         String oauthProvider = userRequest.getClientRegistration().getRegistrationId();


//         // 소셜 로그인 정보 저장
//         // OAuth2UserInfo
//         // if(oauthProvider.equals("google")) {
//         //     log.info("구글 로그인");

//         //     OAuth2UserInfo
//         // }
        
//         return new AuthMemberDto(entityToDto(member), oauthProvider);
//     }

//     private Member saveSocialMember() {

//         Member member = Member.builder()
//         .userId(null)
//         .password(null)
//         .email(null)
//         .nickname(null)
//         .role(null)
//         .provider(null)
//         .build();

//         return member;
//     }

//     private MemberDto entityToDto(Member member) {
//         return MemberDto.builder()
//         .idx(member.getIdx())
//         .userId(member.getUserId())
//         .password(member.getPassword())
//         .nickname(member.getNickname())
//         .email(member.getEmail())
//         .phone(member.getPhone())
//         .build();
//     }
// }
