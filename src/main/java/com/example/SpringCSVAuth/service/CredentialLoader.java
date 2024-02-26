package com.example.SpringCSVAuth.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;



public class CredentialLoader {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static Map<String, String> loadCredentials(String filePath) {
        Map<String, String> credentials = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.lines().forEach(line -> {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    String username = parts[0].trim();
                    String password = passwordEncoder.encode(parts[1].trim());
                    credentials.put(username, password);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return credentials;
    }
}
