package com.BootcampPragma.Api_Stock.infrastructure.configuration;


import com.BootcampPragma.Api_Stock.application.dto.UserResponse;
import com.BootcampPragma.Api_Stock.infrastructure.Feign.IUserFeign;
import com.BootcampPragma.Api_Stock.infrastructure.Utils.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
public class SecurityConfiguration {
    private final IUserFeign userFeign;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(encoder());
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return idString -> {
            UserResponse userResponse = userFeign.getUserById(idString);
            return new CustomUserDetails(userResponse.getId(), userResponse.getRole());
        };
    }

}

