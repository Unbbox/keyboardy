package com.unbox.Keyboardy.dto.OAuth;

import java.util.Map;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GoogleMemberDetails implements OAuth2MemberInfo {

    private Map<String, Object> attributes;

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getPhone() {
        return (String) attributes.get("phone");
    }
    
    
}
