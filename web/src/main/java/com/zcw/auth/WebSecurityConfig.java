package com.zcw.auth;

/**
 * Created by zhangchengwei on 23/10/2017.
 */

import com.zcw.auth.dao.AccountQuery;
import com.zcw.auth.dao.entity.Account;
import com.zcw.auth.dao.entity.AccountRoleR;
import com.zcw.auth.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountService accountService;

    @Bean
    public UserDetailsService createMyUserDetailsService() {
        UserDetailsService userDetailsService = userName -> {
            AccountQuery query = new AccountQuery();
            query.setUserName(userName);
            Account account = accountService.findUniqueByQuery(query);
            if (account == null) {
                throw new UsernameNotFoundException(userName);
            }

            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            Set<AccountRoleR> accountRoleRs = account.getAccountRoleRs();
            accountRoleRs.forEach(roleR ->
                    grantedAuthorities.add(new SimpleGrantedAuthority(roleR.getRole().getName()))
            );
            User user = new User(account.getUserName(), account.getPassword(), grantedAuthorities);
            return user;
        };

        return userDetailsService;
    }

    //    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password("password").roles("USER").build());
        manager.createUser(User.withUsername("admin").password("password").roles("USER", "ADMIN").build());
        return manager;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/resources/**", "/signup", "/about").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll().and()
                .logout().permitAll();
    }
}
