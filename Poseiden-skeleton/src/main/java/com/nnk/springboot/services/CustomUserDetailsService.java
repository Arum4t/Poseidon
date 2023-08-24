package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**
 * Classic userDetailsService, help found user who try to connect to the app by their username
 * using a query findByUsername
 * @see UserRepository
 *
 * @author Quentin
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @return a UserDetails entity
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username+"not fount !");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getFullname())
                .password(user.getPassword())
                .authorities(user.getRole())
                .build();
    }
}
