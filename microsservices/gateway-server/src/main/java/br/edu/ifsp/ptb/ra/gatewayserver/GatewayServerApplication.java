package br.edu.ifsp.ptb.ra.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFluxSecurity
@EnableWebFlux
@SpringBootApplication
public class GatewayServerApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(GatewayServerApplication.class, args);
    }
}
