package org.example.userauthservice_june4.security;

import org.example.userauthservice_june4.models.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

public class CustomGrantedAuthority implements GrantedAuthority {
    private String authority;

    public CustomGrantedAuthority(Role role) {
        this.authority = role.getName();
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
