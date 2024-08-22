package recipeX.boot.config;

import recipeX.mapper.DbMapper;
import recipeX.mapper.DomainMapper;
import recipeX.mapper.RestMapper;
import recipeX.mapper.UuidMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappersConfig {

  @Bean
  RestMapper restMapper() {
    return Mappers.getMapper(RestMapper.class);
  }

  @Bean
  UuidMapper uuidMapper() {
    return Mappers.getMapper(UuidMapper.class);
  }

  @Bean
  DbMapper dbMapper() {
    return Mappers.getMapper(DbMapper.class);
  }

  @Bean
  DomainMapper domainMapper() {
    return Mappers.getMapper(DomainMapper.class);
  }
}
