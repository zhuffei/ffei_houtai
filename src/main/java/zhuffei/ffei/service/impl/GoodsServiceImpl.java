package zhuffei.ffei.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zhuffei.ffei.entity.Goods;
import zhuffei.ffei.mapper.GoodsMapper;
import zhuffei.ffei.service.IGoodsService;

import java.util.List;

/**
 * @author zhuffei
 * @version 1.0
 * @date 2020/4/9 15:16
 */
@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper,Goods> implements IGoodsService {

    @Autowired
    GoodsMapper baseMapper;
    public List<Goods> listRecentGoods(int pageSize, int pageNumber){
        return baseMapper.listRecentGoods(pageSize,pageNumber);
    }
}
