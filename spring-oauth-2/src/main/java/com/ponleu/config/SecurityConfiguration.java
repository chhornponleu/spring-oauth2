package com.ponleu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("ponleu").password("12345").roles("USER", "CLIENT");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "/images/**", "/oauth/uncache_approvals", "/oauth/cache_approvals");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                 http
        .authorizeRequests()
            .antMatchers("/login.jsp").permitAll()
            .anyRequest().hasRole("USER")
            .and()
        .exceptionHandling()
            .accessDeniedPage("/login.jsp?authorization_error=true")
            .and()
        .csrf()
            .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
            .disable()
        .logout()
        	.logoutUrl("/logout")
            .logoutSuccessUrl("/login.jsp")
            .and()
        .formLogin()
        	.loginProcessingUrl("/login")
            .failureUrl("/login.jsp?authentication_error=true")
            .loginPage("/login.jsp");
    }
}
