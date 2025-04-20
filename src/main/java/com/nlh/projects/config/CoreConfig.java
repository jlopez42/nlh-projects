package com.nlh.projects.config;


import com.nlh.projects.repository.BudgetProjectRepository;
import com.nlh.projects.repository.impl.BudgetProjectRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class CoreConfig {

    @Bean
    public BudgetProjectRepository budgetProjectRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        return new BudgetProjectRepositoryImpl(namedParameterJdbcTemplate);
    }
}
