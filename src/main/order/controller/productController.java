package main.order.controller;

import main.order.annotation.doubledatasource.DataSourceType;
import main.order.annotation.doubledatasource.WhichDS;
import main.order.mapper.MaterialMapper;
import main.order.tool.ResultTool;
import main.order.tool.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping(value = {"/product"})
@WhichDS(DataSourceType.ORACLE) //指定此控制层的数据源为NC65家家悦的数据库
public class productController {

    @Autowired
    private MaterialMapper airPalneMapper;

    @PostMapping("/selectAll")
    public ResultTool<List<String>> selectAll() throws MyException{
        try {
            List<String> strings = airPalneMapper.selectAll();
            return new ResultTool<List<String>>(strings);
        }catch (Exception e){
            throw new MyException("查询家家悦Orcale数据库失败");
        }
    }




}
