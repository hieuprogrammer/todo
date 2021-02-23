package dev.tech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("hieu").password("hieu").roles("PROGRAMMER")
                .and()
                .withUser("user").password("hieu").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/", "/todos").permitAll();
//                .antMatchers("/todos/new", "/todos/detail").hasAnyRole("PROGRAMMER", "USER")
//                .antMatchers("/**").hasRole("PROGRAMMER")
//                .and().formLogin().and().exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

//    @Bean
//    public StandardPasswordEncoder getStandardPasswordEncoder() {
//        return new StandardPasswordEncoder();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public Pbkdf2PasswordEncoder getPbkdf2PasswordEncoder() {
//        return new Pbkdf2PasswordEncoder();
//    }
//
//    @Bean
//    public SCryptPasswordEncoder getSCryptPasswordEncoder() {
//        return new SCryptPasswordEncoder();
//    }
}