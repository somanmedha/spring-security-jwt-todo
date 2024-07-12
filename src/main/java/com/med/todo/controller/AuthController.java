package com.med.todo.controller;

import com.med.todo.dto.JwtAuthResponse;
import com.med.todo.dto.LoginDTO;
import com.med.todo.dto.RegisterDTO;
import com.med.todo.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(
        name = "Authentication REST APIs",
        description = "CRUD REST APIs for Authentication - Register User, Login User, and related operations"
)
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    //Register Rest API
    @Operation(
            summary = "Register User REST API",
            description = "Register User REST API to save user into the system"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS 201 CREATED"
    )
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
       String response=  authService.register(registerDTO);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }


    //Build Login RestAPI
    @Operation(
            summary = "Login User REST API",
            description = "Login User REST API to authenticate user and return JWT token"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )

@PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login (@RequestBody LoginDTO loginDTO){
       String token= authService.login(loginDTO);
       JwtAuthResponse jwtAuthResponse=new JwtAuthResponse();
       jwtAuthResponse.setAccessToken(token);
        return new ResponseEntity<>(jwtAuthResponse,HttpStatus.OK);

    }

}
