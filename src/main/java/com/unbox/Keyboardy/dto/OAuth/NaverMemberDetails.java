package com.unbox.Keyboardy.dto.OAuth;

import java.util.Map;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NaverMemberDetails implements OAuth2MemberInfo {

    private Map<String, Object> attributes;

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getProviderId() {
       return (String) ((Map) attributes.get("response")).get("id");
    }

    @Override
    public String getEmail() {
        return (String) ((Map) attributes.get("response")).get("email");
    }

    @Override
    public String getName() {
        return (String) ((Map) attributes.get("response")).get("name");
    }

    @Override
    public String getPhone() {
        return (String) ((Map) attributes.get("response")).get("mobile");
    }
}