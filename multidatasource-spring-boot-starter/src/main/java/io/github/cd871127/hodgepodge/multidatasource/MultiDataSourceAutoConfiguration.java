package io.github.cd871127.hodgepodge.multidatasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class MultiDataSourceAutoConfiguration {


    @Bean
    public DataSource dataSource(DataSourceProperties properties) {
        DataSource dataSource = properties.initializeDataSourceBuilder().build();
        MultiDataSource multiDataSource = new MultiDataSource();

        Map<Object, Object> targetmap = new HashMap<>();
        targetmap.put("root", dataSource);
        properties.setUsername("appopr");
        dataSource = properties.initializeDataSourceBuilder().build();
        targetmap.put("appopr", dataSource);
        multiDataSource.setDefaultTargetDataSource(dataSource);
        multiDataSource.setTargetDataSources(targetmap);
        return multiDataSource;
    }

//    @Bean
//    public MultiDataSourceInterceptor multiDataSourceInterceptor(SqlSessionFactory sqlSessionFactory) {
//        MultiDataSourceInterceptor multiDataSourceInterceptor=new MultiDataSourceInterceptor();
//        sqlSessionFactory.getConfiguration().addInterceptor(multiDataSourceInterceptor);
//        return multiDataSourceInterceptor;
//    }
        @Bean
    public MultiDataSourceInterceptor multiDataSourceInterceptor() {
        MultiDataSourceInterceptor multiDataSourceInterceptor=new MultiDataSourceInterceptor();
        return multiDataSourceInterceptor;
    }

}
