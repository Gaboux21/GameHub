package com.bytescolaborativos.gamehub.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public abstract class UserDetailsService {
    @Transactional
    public abstract UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
