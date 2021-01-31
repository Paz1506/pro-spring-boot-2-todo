package com.apress.directory.config;

import com.apress.directory.repository.PersonRepository;
import com.apress.directory.security.DirectoryUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Pavel Zaytsev
 */
@Configuration
public class DirectorySecurityConfig extends WebSecurityConfigurerAdapter {

    private final PersonRepository personRepository;

    @Autowired
    public DirectorySecurityConfig(PersonRepository personRepository) {this.personRepository = personRepository;}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/**").hasRole("ADMIN") // запросы для роли ADMIN
            .and()
            .httpBasic(); // и для прошедших базовую аутентификацию
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new DirectoryUserDetailsService(this.personRepository));
    }
}
