package com.example.shardingjdbc.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSourceChoose extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return HandlerDataSource.getDataSource();
    }

}
