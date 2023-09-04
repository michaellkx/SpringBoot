package main.order.mapper;


import main.order.entity.OrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    OrderVO queryOrders(String id);

    int insertOrder(OrderVO orderEntity);

    int insertBatchOrders(List<OrderVO> orderEntityList);

    List<OrderVO> queryByWhere(OrderVO orderEntity);

    List<OrderVO> queryAll();

    void deleteAll();
}
