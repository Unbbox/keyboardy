// package com.unbox.Keyboardy.dto;

// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.Map;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.oauth2.core.user.OAuth2User;

// import lombok.Data;

// @Data
// public class OAuthMemberDto implements UserDetails, OAuth2User {
    
//     private MemberDto memberDto;

//     private Map<String, Object> attr;

//     // 일반 로그인
//     public PrincipalDetails(MemberDto memberDto, String provider) {
//         this.memberDto = memberDto;
//     }

//     // oauth 로그인
//     public PrincipalDetails(Map<String, Object> attr) {
//         this.attr = attr;
//     }

//     @Override
//     public Map<String, Object> getAttributes() {
//         return this.attr;
//     }

//     @Override
//     public String getName() {
//         return null;
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         Collection<GrantedAuthority> collection = new ArrayList<>();
//         collection.add(new GrantedAuthority() {
//             public String getAuthority() {
//                 return memberDto.getRole().name();
//             }
//         });

//         return collection;
//     }

//     @Override
//     public String getPassword() {
//         return memberDto.getPassword();
//     }

//     @Override
//     public String getUsername() {
//         return memberDto.getUserId();
//     }
// }
