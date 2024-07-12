package com.med.todo.service;

import com.med.todo.dto.LoginDTO;
import com.med.todo.dto.RegisterDTO;

public interface AuthService {

    String register(RegisterDTO registerDTO);
    String login(LoginDTO loginDTO);
}
