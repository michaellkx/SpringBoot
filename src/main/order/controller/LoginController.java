package main.order.controller;

import main.order.entity.LoginVO;
import main.order.service.LoginService;
import main.order.sys.GloabPoxyInstance;
import main.order.tool.exception.MyException;
import main.order.tool.ResultTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//用于买方登录
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login")
    public ResultTool<String> login(@RequestBody LoginVO loginVO, HttpServletRequest request, HttpServletResponse response)throws MyException{

        if(request.getSession().getAttribute("user") != null){
            throw new MyException("重复的登录");
        }

            if(loginVO == null
                || StringUtils.isBlank(loginVO.getPassword())
                || StringUtils.isBlank(loginVO.getUsername())){
            throw new MyException("非法的账户、密码");
        }
        LoginVO loginVOResult = loginService.judage(loginVO);
        if(loginVOResult != null){
            request.getSession().setAttribute("user",loginVOResult);
        }
        return new ResultTool<String>("登录成功");

    }
    //注销
    @PostMapping("/loginOut")
    public ResultTool<String> loginOut(HttpServletRequest request) throws MyException{

        if(request.getSession().getAttribute("user") == null){
            throw new MyException("注销失败!(本来就未登录)");
        }
        request.getSession().removeAttribute("user");
        return new ResultTool<String>("注销成功");
    }


}
