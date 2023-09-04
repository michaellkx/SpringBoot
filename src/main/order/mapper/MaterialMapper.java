package main.order.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MaterialMapper {
    List<String> selectAll();

}
