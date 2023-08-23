package com.onurgundogdu.sessioncommerce.config;

import com.onurgundogdu.sessioncommerce.model.User;
import com.onurgundogdu.sessioncommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(username -> {
                User user = userRepository.findByUsername(username);
                if(user != null) {
                    return org.springframework.security.core.userdetails.User
                            .withUsername(username)
                            .password(user.getPassword())
                            .authorities(new ArrayList<>())
                            .accountExpired(false)
                            .accountLocked(false)
                            .credentialsExpired(false)
                            .disabled(false)
                            .build();
                }
                else {
                    throw  new UsernameNotFoundException("User not found");
                }
        });
    }
}
