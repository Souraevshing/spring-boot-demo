package spring.todo.app.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// enabling method level security
// we can also use requestMatchers() to authorize users type USER or ADMIN to access routes as commented.

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

    // inject UserDetailsService interface and call its implementation class method loadByUsername() method.
    private UserDetailsService userDetailsService;

    // create PasswordEncoder and return object of BCryptPasswordEncoder class to encrypt password.
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf((csrfConfigurer -> {
                    try {
                        csrfConfigurer.disable()
                                .authorizeHttpRequests(
                                        (authorize) -> {
                                            // WE CAN ALSO USE REQUESTMATCHERS method TO ALLOW ROLE BASED LOGIN RATHER USING METHOD LEVEL SECURITY.
                                            // authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
                                            // authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
                                            // authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
                                            // authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
                                            // authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER");
                                            authorize.anyRequest().authenticated();
                                        }
                                );
                    } catch (Exception e) {
                        System.out.println(e.toString());
                        throw new RuntimeException(e);
                    }
                }))
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }



    // get authenticationManager from AuthenticationConfiguration method getAuthenticationManager()
    // getAuthenticationManager() will allow to enable and use database level authorization.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
