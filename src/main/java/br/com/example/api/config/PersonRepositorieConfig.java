package br.com.example.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("br.com.example.domain.repositorie")
public class PersonRepositorieConfig {

}
