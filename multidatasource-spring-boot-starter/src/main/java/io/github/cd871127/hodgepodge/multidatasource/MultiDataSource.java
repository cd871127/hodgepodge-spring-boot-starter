package io.github.cd871127.hodgepodge.multidatasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultiDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return MultiDataSourceManager.getCurrentDataSource();
    }
}
