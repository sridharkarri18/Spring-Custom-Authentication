package com.example.SpringCSVAuth.config;

import com.example.SpringCSVAuth.service.CredentialLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.Collections;
import java.util.Map;
@Component
public class CsvAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;


    private Map<String, String> userCredentials = CredentialLoader.loadCredentials("C:\\Users\\Developer\\Downloads\\Sridhar\\Details.txt");

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (userCredentials.containsKey(username) && passwordEncoder.matches(password, userCredentials.get(username))) {

            UserDetails user = new User(username, password, Collections.emptyList());
            return new UsernamePasswordAuthenticationToken(user, password, Collections.emptyList());
        } else {
            throw new BadCredentialsException("Authentication failed for " + username);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}