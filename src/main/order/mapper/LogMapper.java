package main.order.mapper;

import main.order.aop.log.LogVO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface LogMapper {

    void writeLog (LogVO logVO);

    int updateLog (LogVO logVO);

}
