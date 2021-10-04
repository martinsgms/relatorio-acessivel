package br.edu.ifsp.ptb.ra.exame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients
@SpringBootApplication
public class ExameApplication 
{
    @Bean
//    @LoadBalanced
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }

	public static void main(String[] args) 
	{
		SpringApplication.run(ExameApplication.class, args);
	}

}
