package main.order.service;

import main.order.entity.OrderVO;
import main.order.mapper.OrderMapper;
import main.order.tool.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);


    public OrderService(){

    }



    public String queryOrders(String orderId) {

        OrderVO result = orderMapper.queryOrders(orderId);

        String s = result.toString();;

        return s;
    }

    //查询全部订单
    public List<OrderVO> queryAll(){
        List<OrderVO> orderVOS = orderMapper.queryAll();
        return orderVOS;
    }


    public String insertOrder()throws MyException {  //@RequestBody OrderEntity orderEntity
       try{
           int i = 1;
           do{
               i++;
               OrderVO orderEntity1 = new OrderVO();
               orderEntity1.setOrderId("Id_"+UUID.randomUUID().toString().substring(0,5));
               orderEntity1.setOrderPk("pk_"+UUID.randomUUID().toString().substring(0,6));
               orderEntity1.setOrderNum(i+55);
               orderEntity1.setOrderName("订单名称"+UUID.randomUUID().toString().substring(0,3));
               orderMapper.insertOrder(orderEntity1);
           }while (i < 100);


       }catch (Exception e){
           throw new MyException("新增订单出错"+e.getMessage());
       }
       return "";
    }


    //批量新增订单
    public String insertBatchOrders(List<OrderVO> orderList) throws MyException {
        try{
            int i = orderMapper.insertBatchOrders(orderList);

            return "";
        }catch (Exception e){
            throw new MyException(""+e.getMessage());
        }
    }

    //根据不为空的id或者num查询
    public List<OrderVO> queryByWhere(OrderVO orderEntity){

        List<OrderVO> orderEntities = orderMapper.queryByWhere(orderEntity);

        return orderEntities;
    }

    //删除订单表里的所有数据
    public void deleteAll(){
        orderMapper.deleteAll();
    }

}
