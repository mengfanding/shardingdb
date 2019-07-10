package com.example.shardingjdbc.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShardingDataSourceConfig {

    /*static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource0.druid")
    public DataSource defaultDb() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource1.druid")
    public DataSource secendDb() {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean(name = "dataSource")
    @Primary
    public AbstractRoutingDataSource dataSourceToChoose(@Qualifier("defaultDb") DataSource defaultDb, @Qualifier("secendDb") DataSource secendDataSource) {


        /*Map<Object, Object> targetDataSources = new HashMap<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/base?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from sys_db");
            while (rs.next()) {
                String url = rs.getString("url");
                String userName = rs.getString("user_name");
                String password = rs.getString("password");
                String id = rs.getString("id");
                String driver = rs.getString("driver");
                DruidDataSource dataSource = new DruidDataSource();
                dataSource.setUrl(url);
                dataSource.setDriverClassName(driver);
                dataSource.setUsername(userName);
                dataSource.setPassword(password);
                targetDataSources.put(id, dataSource);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }*/


       /* Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put("firstDataSource", firstDataSource);
//        targetDataSources.put("secendDataSource", secendDataSource);

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1/test?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        targetDataSources.put("firstDataSource", dataSource);

        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setUrl("jdbc:mysql://127.0.0.1/entity?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource1.setUsername("root");
        dataSource1.setPassword("root");
        targetDataSources.put("secendDataSource", dataSource1);*/

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("defaultDb", defaultDb);
        targetDataSources.put("secendDb", secendDataSource);
        AbstractRoutingDataSource routingDataSource = new MultipleDataSourceChoose();
        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(defaultDb);

        return routingDataSource;

    }
}
