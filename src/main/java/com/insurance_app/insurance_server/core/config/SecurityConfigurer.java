package com.insurance_app.insurance_server.core.config;

import com.insurance_app.insurance_server.core.CustomAccessDeniedHandler;
import com.insurance_app.insurance_server.core.filter.JwtFilter;
import com.insurance_app.insurance_server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final String[] excludePath = new String[]{"/user/register", "/user/login", "/user/forgot-password",
            "/swagger-ui/**", "/v2/api-docs", "/swagger-resources/**", "/webjars/**", "/verification/**"};

    private final UserService userService;
    private final JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    public void configure(HttpSecurity security) throws Exception {
        security = security.cors().and().csrf().disable();

        security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

        security.authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll().antMatchers(excludePath)
                .permitAll().anyRequest().authenticated();
        security.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
        security.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
}