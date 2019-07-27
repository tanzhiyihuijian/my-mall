package com.bobo.demo.mallServiceBoot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Configuration
@EnableJpaRepositories(
        basePackages = {"com.bobo.demo.mallServiceBoot.entity"},
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager"
)
@EnableTransactionManagement
public class SpringDataJpaConfig {


    // 配置 JPA厂商适配器
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.MYSQL);           // 设置数据库类型
        jpaVendorAdapter.setShowSql(true);                      // 打印SQL语句
        jpaVendorAdapter.setGenerateDdl(false);                 // 设置不生成 DDL语句
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");        // 设置Hibernate方言
        return jpaVendorAdapter;
    }


    // 配置实体管理器工厂
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource);                         // 注入数据源
        bean.setJpaVendorAdapter(jpaVendorAdapter);             // 注入 JPA厂商适配器
        bean.setPackagesToScan("com.bobo.mall.api.entity");     // 设置扫描实体类
        return bean;
    }


    // 配置 JPA事务管理器
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);        // 配置实体类管理工厂
        return jpaTransactionManager;
    }



}
