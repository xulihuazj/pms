package com.xulihuazj.pms.config.db;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.xulihuazj.pms.config.Config;
import com.xulihuazj.pms.config.annotation.PMSDB;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = {Config.PMSDB_PACKAGE},
        annotationClass = PMSDB.class, sqlSessionFactoryRef = "pmsdbSqlSessionFactory")
public class PMSDBConfig {

    /**
     * application.properties 配置项
     */
    static final String PMSMAPPER_LOCATION = "classpath*:pmsdb/*/*.xml";

    /**
     * 配置数据源
     *
     * @return
     */
    @Bean(name = "pmsdbDataSource")
    @Primary
    @ConfigurationProperties(prefix = "pmsdb.datasource")
    public DataSource pmsdbDataSource() {
        DataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    /**
     * 事务管理器
     */
    @Bean(name = "pmsdbTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(pmsdbDataSource());
    }

    /**
     * 编程式事务管理
     */
    @Bean(name = "pmsDBTransactionTemplate")
    @Primary
    public TransactionTemplate pmsDBTransactionTemplate() {
        return new TransactionTemplate(masterTransactionManager());
    }

    @Bean(name = "pmsdbSqlSessionFactory")
    @Primary
    @Qualifier("pmsdbDataSource")
    public SqlSessionFactory pmsdbSqlSeesionFactory( DataSource dataSource) throws Exception {
        //  Session 工厂
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        // 添加PageHelper插件
        PageHelper pageHelper = new PageHelper();
        pageHelper.setProperties(this.getPageHelperProps());

        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(PMSMAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    /**
     * PageHelper配置
     */
    private Properties getPageHelperProps() {
        Properties props = new Properties();
        props.setProperty("reasonable", Boolean.FALSE.toString());
        props.setProperty("pageSizeZero", Boolean.TRUE.toString());
        props.setProperty("supportMethodsArguments", Boolean.TRUE.toString());
        props.setProperty("params", "count=countSql");
        return props;
    }


}
