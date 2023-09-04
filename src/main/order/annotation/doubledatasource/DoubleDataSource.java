package main.order.annotation.doubledatasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//自定义配置多数据源
@Configuration
public class DoubleDataSource {

    @Bean(value = {"dataSource1"})
    @ConfigurationProperties(prefix = "spring.datasource.defdatasource1")
    public DruidDataSource dataSource1(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(value = {"dataSource2"})
    @ConfigurationProperties(prefix = "spring.datasource.defdatasource2")
    public DruidDataSource dataSource2(){
        return DruidDataSourceBuilder.create().build();
    }


}
