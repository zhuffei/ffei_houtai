package zhuffei.ffei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import zhuffei.ffei.entity.Goods;

import java.util.List;
import zhuffei.ffei.entity.GoodsUserOV;

/**
 * @author zhuffei
 * @version 1.0
 * @date 2020/4/9 15:16
 */
public interface IGoodsService extends IService<Goods> {
    List<GoodsUserOV> listRecentGoods( int pageSize,int pageNumber);
    List<GoodsUserOV> getBanner();

    List<GoodsUserOV> getRollText();
}
