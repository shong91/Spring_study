package com.studyspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DAOFactory {
    @Bean
    public UserDAO userDAO(){
        return new UserDAO(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker(){
        return new SimpleConnectionMaker();
    }
}
