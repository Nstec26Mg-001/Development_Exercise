package com.example.fullness.stationary.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.fullness.stationary.entity.EmployeeAccount;

public class EmployeeAccountDetails implements UserDetails {

    private final EmployeeAccount employeeAccount;

    private final Collection<GrantedAuthority> authorites;

    public EmployeeAccountDetails(EmployeeAccount account,
            Collection<GrantedAuthority> authorites) {
        this.employeeAccount = account;
        this.authorites = authorites;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorites;
    }

    @Override
    public String getPassword() {
        return employeeAccount.getPassword();
    }

    @Override
    public String getUsername() {
        return employeeAccount.getName();
    }

    public boolean isEnabled() {
        return true;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

}
