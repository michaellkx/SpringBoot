package main.order.annotation.doubledatasource;

import main.order.annotation.doubledatasource.DataSourceType;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
@Primary
public class DynamicDS extends AbstractRoutingDataSource {

    public static ThreadLocal<String> flag = new ThreadLocal<>();

    @Resource
    private DataSource dataSource1;

    @Resource
    private DataSource dataSource2;

    public DynamicDS(){
        flag.set(DataSourceType.MYSQL.name());  //默认使用MYSQL数据库
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return flag.get();
    }

    @Override
    public void afterPropertiesSet() {
        Map<Object,Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceType.MYSQL.name(),dataSource1);
        targetDataSource.put(DataSourceType.ORACLE.name(),dataSource2);
        super.setTargetDataSources(targetDataSource);
        super.setDefaultTargetDataSource(dataSource1);
        super.afterPropertiesSet();
    }



}
