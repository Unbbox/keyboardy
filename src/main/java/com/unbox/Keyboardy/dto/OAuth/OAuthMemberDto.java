package com.unbox.Keyboardy.dto.OAuth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.unbox.Keyboardy.entity.Member;

import lombok.Data;

@Data
public class OAuthMemberDto implements UserDetails, OAuth2User {
    
    private Member member;
    private Map<String, Object> attr;

    private String provider;
    private String providerId;

    public OAuthMemberDto(Member member, Map<String, Object> attr) {
        this.member = member;
        this.attr = attr;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attr;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            public String getAuthority() {
                return member.getRole().name();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUserId();
    }
}
