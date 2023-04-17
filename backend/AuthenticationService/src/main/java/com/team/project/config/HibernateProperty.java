package com.team.project.config;//


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
//@PropertySource({"classpath:application.properties"})
public class HibernateProperty {
    @Value("${database.hibernate.url}")
    private String url;
    @Value("${database.hibernate.driver}")
    private String driver;
    @Value("${database.hibernate.username}")
    private String username;
    @Value("${database.hibernate.password}")
    private String password;
    @Value("${database.hibernate.dialect}")
    private String dialect;
    @Value("${database.hibernate.showsql}")
    private String showsql;

    @Value("${database.hibernate.hbm2ddl.auto}")
    private String hbm2ddl;

    public HibernateProperty() {
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return this.driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDialect() {
        return this.dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getShowsql() {
        return this.showsql;
    }

    public void setShowsql(String showsql) {
        this.showsql = showsql;
    }

    public String getHbm2ddl() {
        return hbm2ddl;
    }

    public void setHbm2ddl(String hbm2ddl) {
        this.hbm2ddl = hbm2ddl;
    }
}
