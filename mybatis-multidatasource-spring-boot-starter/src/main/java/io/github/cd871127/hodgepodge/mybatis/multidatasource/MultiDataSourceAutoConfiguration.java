package io.github.cd871127.hodgepodge.mybatis.multidatasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Anthony
 */
@Configuration
@EnableConfigurationProperties({MultiDataSourceProperties.class})
public class MultiDataSourceAutoConfiguration {

    private Logger logger = LoggerFactory.getLogger(MultiDataSourceAutoConfiguration.class);

    @Bean
    public DataSource dataSource(DataSourceProperties dataSourceProperties, MultiDataSourceProperties multiDataSourceProperties) {
        DataSource defaultDataSource;
        try {
            defaultDataSource = dataSourceProperties.initializeDataSourceBuilder().build();
        } catch (BeanCreationException e) {
            logger.error("Default datasource not found!");
            throw e;
        }
        Map<String, DataSourceProperties> dataSourcePropertiesMap = multiDataSourceProperties.getMultiDatasource();
        if (dataSourcePropertiesMap == null || dataSourcePropertiesMap.isEmpty()) {
            logger.info("Multi datasource not set up, use default.");
            return defaultDataSource;
        }
        Map<Object, Object> multiDataSourceMap = new HashMap<>(dataSourcePropertiesMap.size() + 1);
        multiDataSourceMap.put("default", defaultDataSource);
        dataSourcePropertiesMap.forEach((datasourceName, properties) -> {
            DataSource dataSource = properties.initializeDataSourceBuilder().build();
            multiDataSourceMap.put(datasourceName, dataSource);
        });
        MultiDataSource multiDataSource = new MultiDataSource();
        multiDataSource.setTargetDataSources(multiDataSourceMap);
        multiDataSource.setDefaultTargetDataSource(defaultDataSource);

        return multiDataSource;
    }


    @Bean
    public MultiDataSourceInterceptor multiDataSourceInterceptor() {
        return new MultiDataSourceInterceptor();
    }

}
