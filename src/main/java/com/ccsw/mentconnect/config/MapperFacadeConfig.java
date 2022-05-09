package com.ccsw.mentconnect.config;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperFacadeConfig {

  @Bean
  public MapperFacade getMapperFacade() {

    final DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    return mapperFactory.getMapperFacade();
  }

}