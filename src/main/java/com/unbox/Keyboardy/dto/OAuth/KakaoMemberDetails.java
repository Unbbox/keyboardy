package com.unbox.Keyboardy.dto.OAuth;

import java.util.Map;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KakaoMemberDetails implements OAuth2MemberInfo{

    private Map<String, Object> attributes;

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getEmail() {
        return (String) ((Map) attributes.get("kakao_account")).get("email");
    }

    @Override
    public String getName() {
        return (String) ((Map) attributes.get("properties")).get("nickname");
    }

    @Override
    public String getPhone() {
        return (String) ((Map) attributes.get("properties")).get("phone");
    }
    
}
