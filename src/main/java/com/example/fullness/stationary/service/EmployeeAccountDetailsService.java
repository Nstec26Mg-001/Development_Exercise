package com.example.fullness.stationary.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.mapper.EmployeeAccountMapper;

@Service
@Transactional(readOnly = true)
public class EmployeeAccountDetailsService implements UserDetailsService {
    @Autowired
    EmployeeAccountMapper employeeAccountMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeAccount employeeAccount = employeeAccountMapper.selectByEmployeeAccountName(username);

        if (employeeAccount == null) {
            // TODO: メッセージを定数化
            throw new UsernameNotFoundException("エラーメッセージ");
        }

        Collection<GrantedAuthority> authorites = getAuthorites(employeeAccount);
        return new EmployeeAccountDetails(employeeAccount, authorites);
    }

    private Collection<GrantedAuthority> getAuthorites(EmployeeAccount account) {
        return AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER", "ROLE_GUEST");
    }

}
