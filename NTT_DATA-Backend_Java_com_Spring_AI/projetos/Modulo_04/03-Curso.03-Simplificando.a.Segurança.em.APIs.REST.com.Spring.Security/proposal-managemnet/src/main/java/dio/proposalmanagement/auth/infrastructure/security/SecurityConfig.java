package dio.proposalmanagement.auth.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import dio.proposalmanagement.auth.domain.UserRole;
import dio.proposalmanagement.auth.infrastructure.persistence.entity.User; // Import explícito da entidade correta
import dio.proposalmanagement.auth.infrastructure.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (repository.count() == 0) {
                User fitnessInfluencer = new User();
                fitnessInfluencer.setUsername("fitness_vibe");
                fitnessInfluencer.setPassword(passwordEncoder.encode("password"));
                fitnessInfluencer.setRole(UserRole.ROLE_INFLUENCER);

                User techInfluencer = new User();
                techInfluencer.setUsername("tech_guru");
                techInfluencer.setPassword(passwordEncoder.encode("password"));
                techInfluencer.setRole(UserRole.ROLE_INFLUENCER);

                User brand = new User();
                brand.setUsername("logistics");
                brand.setPassword(passwordEncoder.encode("password"));
                brand.setRole(UserRole.ROLE_BRAND);

                repository.saveAll(List.<User>of(fitnessInfluencer, techInfluencer, brand));
            }
        };
    }

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationConfiguration authenticationConfiguration,
            ObjectMapper objectMapper) throws Exception {

        RestUsernamePasswordAuthenticationFilter customAuthFilter =
                new RestUsernamePasswordAuthenticationFilter(authenticationConfiguration, objectMapper);

        http
                .csrf(AbstractHttpConfigurer::disable)
                .securityContext(context -> context.requireExplicitSave(false))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login").permitAll()
                        .anyRequest().authenticated())
                .addFilterAt(customAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}