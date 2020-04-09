package zhuffei.ffei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import zhuffei.ffei.entity.Goods;

import java.util.List;

/**
 * @author zhuffei
 * @version 1.0
 * @date 2020/4/9 15:12
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
//    List<Goods> listRecentGoods(@Param("pageSize") int pageSize, @Param("pageNumber") int pageNumber);
}
