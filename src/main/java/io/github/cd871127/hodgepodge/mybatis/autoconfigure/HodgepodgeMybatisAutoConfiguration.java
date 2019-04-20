//package io.github.cd871127.hodgepodge.mybatis.autoconfigure;
//
//import org.apache.ibatis.plugin.Interceptor;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//
//import javax.sql.DataSource;
//
//@Configuration
//@ConditionalOnClass({Interceptor.class})
//public class HodgepodgeMybatisAutoConfiguration {
//
//    @Bean
//    @ConditionalOnClass({AbstractRoutingDataSource.class})
//    public DataSource dataSource() {
//        return null;
//    }
//
//    @Bean
//    @ConditionalOnBean({AbstractRoutingDataSource.class})
//    public Interceptor multiDataSourceInterceptor() {
//        return null;
//    }
//
//    @Bean
//    public Interceptor mybatisLogInterceptor() {
//        return null;
//    }
//
//}
