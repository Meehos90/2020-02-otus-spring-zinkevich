package ru.otus.spring.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.otus.spring.security.jwt.JwtAuthEntryPoint;
import ru.otus.spring.security.jwt.JwtAuthTokenFilter;
import ru.otus.spring.security.jwt.JwtProvider;
import ru.otus.spring.security.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String USER = "USER";
    private static final String ADMIN = "ADMIN";

    private final JwtProvider jwtProvider;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAuthEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter(jwtProvider, userDetailsService);
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests().antMatchers("/api/auth/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .and()
                .authorizeRequests().antMatchers(HttpMethod.GET, "/api/authors", "/api/books", "/api/genres", "/api/comments/").hasAnyRole(USER, ADMIN)
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST,"/api/comments/**").hasAnyRole(USER, ADMIN)
                .and()
                .authorizeRequests().antMatchers(HttpMethod.POST, "/api/authors", "/api/books", "/api/genres").hasRole(ADMIN)
                .and()
                .authorizeRequests().antMatchers(HttpMethod.PUT, "/api/authors/**", "/api/books/**", "/api/genres/**", "/api/comments/**").hasRole(ADMIN)
                .and()
                .authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/authors/**", "/api/books/**", "/api/genres/**", "/api/comments/**").hasRole(ADMIN)
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler);

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
