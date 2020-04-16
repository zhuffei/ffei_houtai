package zhuffei.ffei.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import zhuffei.ffei.entity.Goods;
import zhuffei.ffei.entity.GoodsUserOV;
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

    public List<GoodsUserOV> listRecentGoods(int pageSize, int pageNumber){
        return baseMapper.listRecentGoods(pageSize,pageNumber);
    }

    @Override
    public List<GoodsUserOV> getBanner() {
        return baseMapper.getBanner();
    }

    @Override
    public List<GoodsUserOV> getRollText() {
        return baseMapper.getRollText();
    }
}
