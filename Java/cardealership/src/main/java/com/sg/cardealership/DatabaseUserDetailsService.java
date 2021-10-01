/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership;

import com.sg.cardealership.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author kavin
 */
@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminService adminService;


    // constructor ...

    @Override
    public UserDetails loadUserByUsername(String usernameEmail)
            throws UsernameNotFoundException {
        com.sg.cardealership.models.User inDBuser = adminService.getUser(usernameEmail);
        if (inDBuser == null) {
            throw new UsernameNotFoundException(usernameEmail);
        }
        UserDetails user = User.withUsername(inDBuser.getEmail()).password(inDBuser.getPasswordHash()).authorities(inDBuser.getRole().getValue()).build();
        return user;
    }
}
