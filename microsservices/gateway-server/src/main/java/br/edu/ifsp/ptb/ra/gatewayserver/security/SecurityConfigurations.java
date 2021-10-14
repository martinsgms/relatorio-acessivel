package br.edu.ifsp.ptb.ra.gatewayserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter
{

    @Autowired
    private AuthService authService;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception
    {
        return super.authenticationManager();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http)
    {
        http.authorizeExchange()
                .pathMatchers(HttpMethod.POST, "/login").permitAll()
                .anyExchange().authenticated()
                .and().csrf().disable();

        return http.build();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(authService).passwordEncoder(new BCryptPasswordEncoder());
    }

//    @Bean
//    public WebSessionManager webSessionManager()
//    {
//        // Emulate SessionCreationPolicy.STATELESS
//        return exchange -> Mono.empty();
//    }
}
