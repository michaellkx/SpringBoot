package main.order.service;

import main.order.entity.LoginVO;
import main.order.mapper.LoginMapper;
import main.order.tool.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private LoginMapper loginMapper;
    //用于判断用户名密码是否正确,还有权限的控制
    public LoginVO judage(LoginVO loginVO) throws MyException{

        try {
            LoginVO loginVOResult  = loginMapper.sreachByUsername(loginVO.getUsername());
            if(loginVOResult == null){
                return null;
            }
            if(loginVO.getPassword().equals(loginVOResult.getPassword())){
                return loginVOResult;
            }
            return null;
        }catch (Exception e){
            throw new MyException("判断用户名密码时出错"+e.getMessage());
        }

    }



}
