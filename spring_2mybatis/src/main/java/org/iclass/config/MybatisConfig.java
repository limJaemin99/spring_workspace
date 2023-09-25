package org.iclass.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import oracle.jdbc.driver.OracleDriver;

@Configuration
@ComponentScan(basePackages = {"org.iclass"})
@PropertySource("classpath:META-INF/mybatis/db.properties")
@MapperScan(basePackages = {"org.iclass.dao"})
public class MybatisConfig {

	
	@Value("${db.driver}")
    private String driverClassName;

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    
    //▶ DataSource : 기존 JDBC 객체(Java 내장) - Connection Pool(저장소) 기능을 제공하는 객체
    @Bean	//▶ Bean : 아래 메소드로 리턴받은 객체를 스프링 bean 으로 저장하는 어노테이션
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(OracleDriver.class);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
    
    
    //▶ SqlSessionFactory : mybatis 객체
    @Bean										//setter 주입
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfigLocation(new ClassPathResource("META-INF/mybatis/mybatis-config.xml"));
        sessionFactory.setMapperLocations(new ClassPathResource("META-INF/mybatis/community.xml"),
        									new ClassPathResource("META-INF/mybatis/todoMapper.xml"));
        return sessionFactory.getObject();
    }

    
    //▶ SqlSessionTemplate : mybatis-spring 객체
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
    //	━━━━━━━━━━━━━━━━━━━━ ▼ 의존관계 ▼ ━━━━━━━━━━━━━━━━━━━━
    //	SqlSessionTemplate ▶ SqlSessionFactory ▶ DataSource
    
}
