package com.med.todo.security;

import com.med.todo.entity.User;
import com.med.todo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       // logger.debug("Attempting to load user by username or email: {}", usernameOrEmail);
        User user= userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User"+username+"doesn't exist"));
        logger.debug("User found: {}", user);
        Set<GrantedAuthority> authorities=user.getRoles()
                .stream()
                .map((role -> new SimpleGrantedAuthority(role.getName())))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(
                //usernameOrEmail,
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
