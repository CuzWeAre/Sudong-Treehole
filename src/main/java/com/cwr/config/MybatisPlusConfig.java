package com.cwr.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

//@Configuration
public class MybatisPlusConfig {

  private final String mapperLocations = "classpath*:com/cwr/mapper/*.xml";

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource)
          throws Exception {
    final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
    return sessionFactory.getObject();
  }
}