package com.novikovkirill.test.accountingsystem.models.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enum описывающий роли пользователей
 */

public enum Role implements GrantedAuthority {
    USER,
    ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }
}
