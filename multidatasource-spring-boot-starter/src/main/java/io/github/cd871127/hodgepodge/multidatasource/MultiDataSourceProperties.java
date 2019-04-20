package io.github.cd871127.hodgepodge.multidatasource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author Anthony
 */
@ConfigurationProperties(prefix = "hodgepodge")
public class MultiDataSourceProperties {
    private Map<String, DataSourceProperties> multiDatasource;

    public Map<String, DataSourceProperties> getMultiDatasource() {
        return multiDatasource;
    }

    public void setMultiDatasource(Map<String, DataSourceProperties> multiDatasource) {
        this.multiDatasource = multiDatasource;
    }
}
