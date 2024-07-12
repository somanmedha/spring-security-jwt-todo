package com.med.todo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// To get the encoded password to save in database for each user
public class PasswordEncoderImpl {
    public static void main(String[] args) {

        PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        // System.out.println(passwordEncoder.encode("m123"));
        // System.out.println(passwordEncoder.encode("r123"));
        System.out.println(passwordEncoder.encode("admin123"));
    }
}
