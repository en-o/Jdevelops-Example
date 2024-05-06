//package com.example.jdevelopsconfigdemo;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
//import org.springframework.boot.autoconfigure.domain.EntityScanPackages;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import java.util.List;
//// 不能用
//@Configuration
//public class CustomEntityManagerFactoryConfiguration{
//
//    private final DataSource dataSource;
//    private final JpaProperties jpaProperties;
//    public CustomEntityManagerFactoryConfiguration(DataSource dataSource, JpaProperties jpaProperties) {
//        this.dataSource = dataSource;
//        this.jpaProperties = jpaProperties;
//    }
//
//
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(dataSource)
//                .packages(getPackagesToScan())
//                .properties(jpaProperties.getProperties())
//                .build();
//    }
//
//
//    protected String[] getPackagesToScan() {
////        List<String> packages = EntityScanPackages.get(this.beanFactory).getPackageNames();
////        if (packages.isEmpty() && AutoConfigurationPackages.has(this.beanFactory)) {
////            packages = AutoConfigurationPackages.get(this.beanFactory);
////            packages.add("cn.jdevelops.config.standalone.model");
////        }
////        return StringUtils.toStringArray(packages);
//        return new String[]{"cn.jdevelops.config.standalone.model", "com.example.jdevelopsconfigdemo"};
//    }
//}
