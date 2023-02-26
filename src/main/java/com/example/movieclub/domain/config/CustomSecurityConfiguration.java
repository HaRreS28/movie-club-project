package com.example.movieclub.domain.config;

import com.example.movieclub.domain.user.roles.Roles;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@AllArgsConstructor
public class CustomSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request ->
                request.mvcMatchers("/admin/**")
                        .hasAnyAuthority(Roles.ROLE_ADMIN.name(), Roles.ROLE_MODERATOR.name())
                        .mvcMatchers("/ocen-film/**", "/twoj-profil/**", "/dodaj-komentarz/**",
                                "/feedback/**")
                        .authenticated()
                        .anyRequest().permitAll());
        http.formLogin().loginPage("/login").permitAll();
        http.logout(logout ->
                logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout/**",
                                HttpMethod.GET.name()))
                        .logoutSuccessUrl("/login?logout").permitAll());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
        http.csrf().ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"));
        http.headers().frameOptions().sameOrigin();
        http.exceptionHandling().accessDeniedPage("/error/404.html");
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().mvcMatchers(
                "img/**", "/scripts/**",
                "/styles/**"
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
