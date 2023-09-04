package main.order.web.interception;

import main.order.entity.LoginVO;
import main.order.sys.GloabPoxyInstance;
import main.order.sys.ThreadLocalUse;
import main.order.tool.exception.MyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

@Component
public class DefInterceptorImpl extends HandlerInterceptorAdapter {

    @Value("${server.port}")
    private String ymlPort ;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //定义允许访问的路径
        ArrayList<String> allowPaths = new ArrayList<String>();
        allowPaths.add("http://localhost:" + ymlPort + "/login");
        allowPaths.add("http://localhost:" + ymlPort + "/loginOut");

        if(allowPaths.contains(request.getRequestURL().toString())){   //如果是登录请求,则放行
            return true;
        }

        if(request.getSession().getAttribute("user") != null){  //有用户登录,则把用户放在线程Map中
            ThreadLocalUse.getThreadLocalCase().set(request.getSession().getAttribute("user"));
            return true;
        }

        throw new MyException("请登录!");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {


    }




}
