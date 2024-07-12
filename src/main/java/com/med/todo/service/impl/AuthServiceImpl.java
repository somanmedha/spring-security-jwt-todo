package com.med.todo.service.impl;

import com.med.todo.dto.LoginDTO;
import com.med.todo.dto.RegisterDTO;
import com.med.todo.entity.Role;
import com.med.todo.entity.User;
import com.med.todo.exception.TodoApiException;
import com.med.todo.repository.RoleRepository;
import com.med.todo.repository.UserRepository;
import com.med.todo.security.JwtTokenProvider;
import com.med.todo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public String register(RegisterDTO registerDTO) {
        // Check if username already exists in database
        if(userRepository.existsByUsername(registerDTO.getUsername())){
throw new TodoApiException(HttpStatus.BAD_REQUEST, "Username already exists");

        }
        if(userRepository.existsByEmail(registerDTO.getUsername())){
            throw new TodoApiException(HttpStatus.BAD_REQUEST, "Email already exists");

        }
        User user=new User();
        user.setName(registerDTO.getName());
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        Set<Role> roles= new HashSet<>();
       Role userRole= roleRepository.findByName("ROLE_USER");
       roles.add(userRole);

       user.setRoles(roles);
       userRepository.save(user);

        return "User Registered Successfully";
    }

    @Override
    public String login(LoginDTO loginDTO) {
      Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(),
                loginDTO.getPassword()


        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtTokenProvider.generateToken(authentication);

        return token;
    }
}
