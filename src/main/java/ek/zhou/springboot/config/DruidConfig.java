package ek.zhou.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid配置类
 */

@Configuration
public class DruidConfig {
    //绑定数据源配置
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){

        return new DruidDataSource();
    }
    /**
     * 配置一个druid的监控
     * 1.配置一个druid的后台 管理servlet
     * 2.配置一个druid的filter
     */
    //1.配置druid 的后台管理servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){

        //请求路径是/druid/*
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //设置参数
        Map<String,String> map = new HashMap();
        //用户名
        map.put(StatViewServlet.PARAM_NAME_USERNAME,"root");
        //密码
        map.put(StatViewServlet.PARAM_NAME_PASSWORD,"admin");
        //允许访问ip 不设置全部ip都可以访问
        map.put(StatViewServlet.PARAM_NAME_ALLOW,"");
        //拒绝访问ip
        map.put(StatViewServlet.PARAM_NAME_DENY,"192.168.1.254");
        //将map设置到bean中
        bean.setInitParameters(map);
        return bean;
    }

    //2.配置druid的filter
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        //设置过滤路径
        bean.setUrlPatterns(Arrays.asList("/*"));
        //设置初始化参数
        Map<String,String> map = new HashMap();
        map.put(WebStatFilter.PARAM_NAME_EXCLUSIONS,"*.js,*.css,/druid/*");
        bean.setInitParameters(map);
        return bean;

    }
}
