package main.order.controller;

import main.order.annotation.authority.HasPressmion;
import main.order.controller.base.BaseController;
import main.order.entity.OrderVO;
import main.order.service.OrderService;
import main.order.tool.Constant;
import main.order.tool.exception.MyException;
import main.order.tool.ResultTool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController()
@RequestMapping("/order")
@Transactional(rollbackFor = Exception.class)
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @GetMapping("query")
    public ResultTool<String> queryOrders(String id) throws MyException {
        try{

            if(StringUtils.isBlank(id)){
                throw new MyException("非法的订单id");
            }
            String s = orderService.queryOrders(id);
            ResultTool<String> result = new ResultTool<String>(s);
            return result;

        }catch (MyException e){
            throw new MyException("查询订单失败"+e.getMessage());
        }catch (Exception e){
            throw new MyException("查询订单失败"+e.getMessage());
        }
    }

    //查询全部订单
    @RequestMapping("queryAll")
    public ResultTool<List<OrderVO>> queryOrders() throws MyException {
        try{

            List<OrderVO> orderVOS = orderService.queryAll();
            return  new ResultTool<List<OrderVO>>(orderVOS);

        }catch (Exception e){
            throw new MyException("查询订单失败"+e.getMessage());
        }
    }


    //新增单个订单
    @RequestMapping("autoInsert")
    public void insertOrder()throws MyException {
        orderService.insertOrder();
        System.out.println("\n\n\n\n\n\n新增完成\n\n\n\n\n\n\n");
    }

    //新增多个订单
    @RequestMapping("batchInsert")
    public String batchInsertOrder(@RequestBody List<OrderVO> orderList)throws MyException {

        System.out.println(orderList.toString());

        if(orderList == null || orderList.size() <1){
            return "传递过来的订单信息为空";
        }

        String s = orderService.insertBatchOrders(orderList);

        return "";
    }


    @PostMapping("queryByWhere")
    public void queryByWhere(@RequestBody OrderVO orderEntity) throws MyException {

      try{
          if(orderEntity == null){
              throw new MyException("传值为空");
          }
          List<OrderVO> orderEntities = orderService.queryByWhere(orderEntity);

          System.out.println(orderEntities);

      }catch (Exception e){
          throw new MyException("传值为空"+e.getMessage());
      }
    }


    @HasPressmion(value = {Constant.SUPER})  //删除全部数据操作是需要SUPER权限的
    @PostMapping(value = "deleteAll")
    public void deleteAll(){
        orderService.deleteAll();
    }


}
