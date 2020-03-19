package ek.zhou.springboot.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 自定义区域信息解析器来切换国际化信息
 * 需要注入到容器中
 */
public class MyLocaleResolver implements LocaleResolver {
    //解析区域信息
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        System.out.println("自定义区域信息解析器...");
        //获取请求头中的参数
        String l = httpServletRequest.getParameter("l");

        //获取浏览器中的区域信息
        Locale locale = httpServletRequest.getLocale();
        //如果参数有区域信息,就用参数里的区域信息
        if(!StringUtils.isEmpty(l)){
            String[] strings = l.split("_");
            //参数1:语音代码,2:国家代码
            locale = new Locale(strings[0],strings[1]);
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
