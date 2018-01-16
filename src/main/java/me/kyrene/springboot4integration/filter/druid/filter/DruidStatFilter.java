package me.kyrene.springboot4integration.filter.druid.filter;

import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.context.annotation.Configuration;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Created by wanglin on 2018/1/16.
 */
@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
        initParams={
                @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源,这些不被拦截
        }
)
public class DruidStatFilter extends WebStatFilter {

}
